package it.siletto.service;

import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.util.List;

public interface PageBuilder {

	public void init();
	
	public abstract Site createSite(String path) throws Exception;

	public abstract NavBar createNavbar(Site site, String path) throws Exception;

	public abstract List<Page> createPages(Site site, String outputDir, String speciesDir) throws Exception;

	public abstract void setService(ArthropodsPersistenceService service);

}