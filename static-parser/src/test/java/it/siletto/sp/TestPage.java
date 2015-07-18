package it.siletto.sp;

import it.siletto.sp.dto.MenuItem;
import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestPage {

	static String[] templateDir = new String[]{
		"src/test/resources/template/includes/",
		"src/test/resources/template/pages/",
		"src/main/resources/template/includes/",
		"src/main/resources/template/pages/"
		};
	static String staticDir = "src/main/resources/template/staticFiles/";
	static String outputDir = "target/site/";
	static Configuration cfg;
	
	public static void main(String[] args) throws Exception {

		//copy static files
		new File(outputDir).delete();
		new File(outputDir).mkdirs();
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"css"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"font-awesome"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"fonts"), new File(outputDir));
		FileUtils.copyDirectoryToDirectory(new File(staticDir+"js"), new File(outputDir));
		
		cfg = TestBuilder.configuration(templateDir);

		Site site = TestBuilder.localSite();
		NavBar navbar = TestBuilder.navbar();
		
		List<Page> pages = new ArrayList<Page>();
		pages.add(TestBuilder.createPage("index.ftl", "index.html", "Site name", null, "test freemarker", true, site, navbar));
		pages.add(TestBuilder.createPage("404.ftl", "404.html", "404", "Page Not Found", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("about.ftl", "about.html", "About", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("blog-home-1.ftl", "blog-home-1.html", "Blog Home 1", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("blog-home-2.ftl", "blog-home-2.html", "Blog Home 2", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("blog-post.ftl", "blog-post.html", "Blog Post", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("contact.ftl", "contact.html", "Contact", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("faq.ftl", "faq.html", "FAQ", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("full-width.ftl", "full-width.html", "Full width", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("portfolio-1-col.ftl", "portfolio-1-col.html", "One Column Portfolio", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("portfolio-2-col.ftl", "portfolio-2-col.html", "Two Column Portfolio", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("portfolio-3-col.ftl", "portfolio-3-col.html", "Three Column Portfolio", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("portfolio-4-col.ftl", "portfolio-4-col.html", "Four Column Portfolio", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("portfolio-item.ftl", "portfolio-item.html", "Portfolio Item", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("pricing.ftl", "pricing.html", "Pricing", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("services.ftl", "services.html", "Services", "Subheading", "test freemarker", false, site, navbar));
		pages.add(TestBuilder.createPage("sidebar.ftl", "sidebar.html", "Sidebar", "Subheading", "test freemarker", false, site, navbar));
		
		for (Page page : pages) {
			writePage(page);
		}
		

	  }

	public static void writePage(Page page) throws IOException, TemplateException{
	    Template template = cfg.getTemplate(page.getInput());

	    resetNavBar(page);
	    selectMenus(page);
	    
	    Writer fileWriter = new FileWriter(new File(outputDir+page.getOutput()));
	    try {
	      template.process(page, fileWriter);
	    } finally {
	      fileWriter.close();
	    }
	}

	private static void selectMenus(Page page) {
		for(MenuItem first : page.getNavbar().getMenu()){
			if(page.getOutput().equals(first.getHref()))
				first.setSelected(true);
			for(MenuItem second : first.getChildrens())
				if(page.getOutput().equals(second.getHref())){
					second.setSelected(true);
					first.setSelected(true);
				}
		}
	}

	private static void resetNavBar(Page page) {
		for(MenuItem first : page.getNavbar().getMenu()){
			first.setSelected(false);
			for(MenuItem second : first.getChildrens()){
				second.setSelected(false);
			}
		}
	}
	
}
