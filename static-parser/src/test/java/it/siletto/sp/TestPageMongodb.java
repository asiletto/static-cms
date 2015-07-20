package it.siletto.sp;

import it.siletto.sp.dto.BaseEntity;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;

public class TestPageMongodb {
	
	public static void main(String[] args) throws Exception {

		Datastore ds = MongoDB.instance().getDatabase(new Class[]{BaseEntity.class});

		Site old = ds.find(Site.class, "_id", "my.site.it").get();
		
		Site site = TestBuilder.localSite(old);
		site.setNavbar(TestBuilder.navbar(site));

		List<Page> pages = new ArrayList<Page>();
		pages.add(TestBuilder.createPage("index.ftl", "index.html", "Site name", null, "test freemarker", true, site));
		pages.add(TestBuilder.createPage("404.ftl", "404.html", "404", "Page Not Found", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("about.ftl", "about.html", "About", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("blog-home-1.ftl", "blog-home-1.html", "Blog Home 1", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("blog-home-2.ftl", "blog-home-2.html", "Blog Home 2", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("blog-post.ftl", "blog-post.html", "Blog Post", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("contact.ftl", "contact.html", "Contact", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("faq.ftl", "faq.html", "FAQ", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("full-width.ftl", "full-width.html", "Full width", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("portfolio-1-col.ftl", "portfolio-1-col.html", "One Column Portfolio", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("portfolio-2-col.ftl", "portfolio-2-col.html", "Two Column Portfolio", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("portfolio-3-col.ftl", "portfolio-3-col.html", "Three Column Portfolio", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("portfolio-4-col.ftl", "portfolio-4-col.html", "Four Column Portfolio", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("portfolio-item.ftl", "portfolio-item.html", "Portfolio Item", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("pricing.ftl", "pricing.html", "Pricing", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("services.ftl", "services.html", "Services", "Subheading", "test freemarker", false, site));
		pages.add(TestBuilder.createPage("sidebar.ftl", "sidebar.html", "Sidebar", "Subheading", "test freemarker", false, site));
		
		ds.save(site.getNavbar());
			
		ds.save(site);
		
		for (Page page : pages) {
			ds.save(page);
		}
		
		System.exit(0);
	  }

	
}
