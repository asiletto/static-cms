package it.siletto.sp;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import it.siletto.sp.dto.Anchor;
import it.siletto.sp.dto.MenuItem;
import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestBuilder {

	public static Page createPage(String input, String output, String title, String subtitle, String description, boolean includeCarousel, Site site, NavBar navbar){
		Page p = new Page();
		p.setDescription(description);
		p.setTitle(title);
		p.setSubtitle(subtitle);
		p.setIncludeCarousel(includeCarousel);
		p.setSite(site);
		p.setNavbar(navbar);
		p.setInput(input);
		p.setOutput(output);
		return p;
	}

	
	public static Site localSite(){
		Site s = new Site();
		s.setBootstrapCss("css/bootstrap.min.css");
		s.setBootstrapJs("js/bootstrap.min.js");
		s.setJqueryJs("js/jquery.js");
		s.setCopyright("Copyright &copy; Your Website 2014");
		s.setAuthor("arthropods.eu");
		return s;
	}

	public static Site cdnSite(){
		Site s = new Site();
		s.setBootstrapCss("//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css");
		s.setBootstrapJs("//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js");
		s.setJqueryJs("//code.jquery.com/jquery-1.11.3.min.js");
		s.setCopyright("Copyright &copy; Your Website 2014");
		s.setAuthor("arthropods.eu");
		return s;
	}

	public static NavBar navbar(){
		NavBar navbar = new NavBar();
		
		navbar.setBrand(createHref("Start Bootstrap", "index.html"));
		navbar.setMenu(new ArrayList<MenuItem>());
		
		navbar.getMenu().add(createMenu("About", "about.html", false));
		navbar.getMenu().add(createMenu("Services", "services.html", false));
		navbar.getMenu().add(createMenu("Contact", "contact.html", true));
		
		MenuItem portfolio = createMenu("Portfolio", null, false);
		navbar.getMenu().add(portfolio);
		portfolio.getChildrens().add(createMenu("1 Column Portfolio", "portfolio-1-col.html", false));
		portfolio.getChildrens().add(createMenu("2 Column Portfolio", "portfolio-2-col.html", false));
		portfolio.getChildrens().add(createMenu("3 Column Portfolio", "portfolio-3-col.html", false));
		portfolio.getChildrens().add(createMenu("4 Column Portfolio", "portfolio-4-col.html", false));
		portfolio.getChildrens().add(createMenu("Single Portfolio Item", "portfolio-item.html", false));
		
		MenuItem blog = createMenu("Blog", null, false);
		navbar.getMenu().add(blog);
		blog.getChildrens().add(createMenu("Blog Home 1", "blog-home-1.html", false));
		blog.getChildrens().add(createMenu("Blog Home 2", "blog-home-2.html", false));
		blog.getChildrens().add(createMenu("Blog Post", "blog-post.html", false));
		
		MenuItem other = createMenu("Other pages", null, false);
		navbar.getMenu().add(other);
		other.getChildrens().add(createMenu("Full Width Page", "full-width.html", false));
		other.getChildrens().add(createMenu("Sidebar Page", "sidebar.html", false));
		other.getChildrens().add(createMenu("FAQ", "faq.html", false));
		other.getChildrens().add(createMenu("404", "404.html", false));
		other.getChildrens().add(createMenu("Pricing table", "pricing.html", false));
		
		return navbar;
	}
	
	public static MenuItem createMenu(String label, String href, boolean selected){
		MenuItem m = new MenuItem();
		m.setLabel(label);
		if(href!=null)
			m.setHref(href);
		m.setSelected(selected);
		m.setChildrens(new ArrayList<MenuItem>());
		return m;
	}
	
	public static Anchor createHref(String label, String href){
		Anchor a = new Anchor();
		a.setLabel(label);
		a.setHref(href);
		return a;
	}
	
	public static Configuration configuration(String[] base) throws IOException{
	    Configuration cfg = new Configuration();
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
}
