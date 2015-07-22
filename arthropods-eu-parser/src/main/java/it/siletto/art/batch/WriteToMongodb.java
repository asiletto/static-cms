package it.siletto.art.batch;

import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.util.List;

public class WriteToMongodb extends BaseBatch {
	
	static String outputDir = "target/site/";

	static String baseDir = "D:/dev/workspaces/personale/arthropods.eu/arthropods-eu-content/";
	
	public static void main(String[] args) throws Exception {
		execute(true);
	}
	
	public static void execute(boolean exit) throws Exception {

		init();
		
		Site site = pageBuilder.createSite(baseDir+"site.md");
		site.setNavbar(pageBuilder.createNavbar(site, baseDir+"navbar.md"));
		
		List<Page> pages = pageBuilder.createPages(site, outputDir, baseDir+"site/");

		System.out.println("number of pages:" + pages.size());
		
		ds.save(site.getNavbar());

		ds.save(site);
		
		for (Page page : pages) {
			ds.save(page);
		}
		
		if(exit)
			System.exit(0);
	  }

	
}
