package it.siletto.service.impl;

import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.art.dto.SpeciesListPage;
import it.siletto.service.ArthropodsPersistenceService;
import it.siletto.service.MarkdownParser;
import it.siletto.service.PageBuilder;
import it.siletto.sp.dto.Anchor;
import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class PageBuilderImpl implements PageBuilder {

	protected ArthropodsPersistenceService service;
	protected MarkdownParser<SpeciesDetailPage> speciesDetailPageParser;
	protected MarkdownParser<SpeciesListPage> speciesListPageParser;
	protected MarkdownParser<Page> basePageParser;
	protected MarkdownParser<Site> siteParser;
	protected MarkdownParser<NavBar> navbarParser;
	
	public void init(){
		speciesDetailPageParser = new SpeciesDetailPageParser();
		speciesDetailPageParser.setService(service);
		
		speciesListPageParser = new SpeciesListPageParser();
		speciesListPageParser.setService(service);
		
		basePageParser = new BasePageParser();
		basePageParser.setService(service);
		
		siteParser = new SiteParser();
		siteParser.setService(service);
		
		navbarParser = new NavBarParser();
		navbarParser.setService(service);
	}
	
	/* (non-Javadoc)
	 * @see it.siletto.service.impl.PageBuilder#createSite(java.lang.String)
	 */
	@Override
	public Site createSite(String path) throws Exception {
		return siteParser.parse(null, null, path);
	}
	
	/* (non-Javadoc)
	 * @see it.siletto.service.impl.PageBuilder#createNavbar(it.siletto.sp.dto.Site, java.lang.String)
	 */
	@Override
	public NavBar createNavbar(Site site, String path) throws Exception {
		return navbarParser.parse(site, null, path);
	}
	
	public static int getSlashCount(String path){
		path = path.replaceAll("\\\\", "/");
		Pattern p = Pattern.compile("\\/");
	    Matcher m = p.matcher(path);
	    int count = 0;
	    while (m.find()){
	    	count +=1;
	    }
	    return count;
	}
	
	private String getParentPath(String templateName) {
		String tmp = "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", "/");
		return tmp.substring(0, tmp.indexOf('/', 1));
	}
	
	/* (non-Javadoc)
	 * @see it.siletto.service.impl.PageBuilder#createPages(it.siletto.sp.dto.Site, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Page> createPages(Site site, String outputDir, String speciesDir) throws Exception {
				
		List<Page> pages = new ArrayList<Page>();

		Map<String,List<SpeciesDetailPage>> mapSpecies = new HashMap<String,List<SpeciesDetailPage>>();
		List<SpeciesListPage> listSpecies = new ArrayList<SpeciesListPage>();
		
		File path = new File(speciesDir);
		Collection<File> templates = FileUtils.listFiles(path, new String[]{"md"}, true);
		for (File file : templates) {
			String fullPath = file.getPath();
			String partial = fullPath.substring(speciesDir.length());
			int size = getSlashCount(partial);
			if(size == 2){

				SpeciesDetailPage page = speciesDetailPageParser.parse(site, partial, fullPath);
				pages.add(page);
				
				//handle parent relationship
				String parent = getParentPath(partial);
				List<SpeciesDetailPage> list = mapSpecies.get(parent);
				if(list==null)
					list = new ArrayList<SpeciesDetailPage>();
				list.add(page);
				mapSpecies.put(parent, list);
				
			}else if(size == 1){
				
				SpeciesListPage page = speciesListPageParser.parse(site, partial, fullPath);
				pages.add(page);
				
				listSpecies.add(page);
				
			}else if(size == 0){
				
				Page page = basePageParser.parse(site, partial, fullPath);
				pages.add(page);
				
			}
		}
		
		for (SpeciesListPage speciesList : listSpecies) {
			List<SpeciesDetailPage> speciesDetail = mapSpecies.get(speciesList.getPath());
			for (SpeciesDetailPage speciesDetailPage : speciesDetail) {
				Anchor parent = new Anchor();
				parent.setHref(speciesList.getOutput());
				parent.setLabel(speciesList.getTitle());
				speciesDetailPage.setParent(parent);
			}
			speciesList.setSpecies(speciesDetail);
		}
		
		return pages;
	}
	
	public ArthropodsPersistenceService getService() {
		return service;
	}

	/* (non-Javadoc)
	 * @see it.siletto.service.impl.PageBuilder#setService(it.siletto.service.ArthropodsPersistenceService)
	 */
	@Override
	public void setService(ArthropodsPersistenceService service) {
		this.service = service;
	}

}
