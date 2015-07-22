package it.siletto.art.batch;

import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.sp.dto.MenuItem;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class GenerateSiteFromMongodb extends BaseBatch {

	static String baseStatic = "D:/dev/workspaces/personale/arthropods.eu/private/arthropods-eu-content/";

	static String inputSite = baseStatic+"site/";
	static String staticPages = baseStatic+"pages/";
	static String[] templateDir = new String[]{
		baseStatic+"templates/includes/",
		baseStatic+"templates/pages/"
		};
	static String staticDir = baseStatic+"static/";
	static String outputDir = "D:/dev/workspaces/personale/arthropods.eu/site";
	static Configuration cfg;
	
	public static void main(String[] args) throws Exception {
		execute(true);
	}
	
	public static void execute(boolean exit) throws Exception {

		init();
		
		createDirectoryStructure();

		cfg = configuration(templateDir);
		
		String siteName="arthropods.eu";
		
		Site site = service.getSite(siteName);
		List<Page> pages = service.getPagesBySite(siteName);

		for (Page page : pages) {
			
			System.out.println("page: "+ page.getVersion() +" - " + page.getOutput());
			
			writePage(site, page);
		}
		
		System.out.println("site: "+ site.getVersion() +" - " + site.getId());
				
		if(exit)
			System.exit(0);

	  }

	public static Configuration configuration(String[] base) throws IOException{
	    Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
	    List<TemplateLoader> loaders = new ArrayList<TemplateLoader>();
	    for (int i = 0; i < base.length; i++) {
	    	loaders.add(new FileTemplateLoader(new File(base[i])));
		}
	    MultiTemplateLoader mtl = new MultiTemplateLoader(loaders.toArray(new TemplateLoader[]{}));
	    cfg.setTemplateLoader(mtl);
	    cfg.setIncompatibleImprovements(new Version(2, 3, 20));
	    cfg.setDefaultEncoding("UTF-8");
	    cfg.setLocale(Locale.US);
	    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	    return cfg;
	}
	
	public static void createDirectoryStructure() throws IOException{
		new File(outputDir).delete();
		new File(outputDir).mkdirs();
		
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"css"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"font-awesome"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"fonts"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"js"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"images"), new File(outputDir));
		

		FileFilter filter = FileFilterUtils.or(
			DirectoryFileFilter.DIRECTORY, 
			FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".jpg")),
			FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".jpeg")),
			FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".png")),
			FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".gif")),
			FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".html"))
		);
		
		FileUtils.copyDirectory(new File(inputSite), new File(outputDir), filter);		
	}
	
	public static void writePage(Site site, Page page) throws IOException, TemplateException{
	    Template template = cfg.getTemplate(page.getInput());

		new File(outputDir+page.getPath()).mkdirs();
	    
	    resetNavBar(site);
	    selectMenus(site, page);
	    	 
	    Map<String,Object> data = new HashMap<String,Object>();
	    data.put("site", site);
	    data.put("page", page);
	    
	    Writer fileWriter = new FileWriter(new File(outputDir+page.getOutput()));
	    try {
	    	template.process(data, fileWriter);
	    } finally {
	    	fileWriter.close();
	    }
	}

	private static void selectMenus(Site site, Page page) {
		for(MenuItem first : site.getNavbar().getMenu()){
			if(page.getOutput().equals(first.getHref()))
				first.setSelected(true);
			for(MenuItem second : first.getChildrens())
				if(page.getOutput().equals(second.getHref())){
					second.setSelected(true);
					first.setSelected(true);
				}
		}
		//for species hightligt menu of the parent group
		if(page instanceof SpeciesDetailPage){
			SpeciesDetailPage sdp = (SpeciesDetailPage)page;
			String speciesGroup = sdp.getParent().getLabel();
			for(MenuItem first : site.getNavbar().getMenu())
				for(MenuItem second : first.getChildrens()){
					if(speciesGroup.equals(second.getLabel())){
						second.setSelected(true);
						first.setSelected(true);
					}
				}

		}
	}

	private static void resetNavBar(Site site) {
		for(MenuItem first : site.getNavbar().getMenu()){
			first.setSelected(false);
			for(MenuItem second : first.getChildrens()){
				second.setSelected(false);
			}
		}
	}
	
}
