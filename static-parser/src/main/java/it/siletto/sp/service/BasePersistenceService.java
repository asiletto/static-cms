package it.siletto.sp.service;

import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import org.mongodb.morphia.Datastore;

public interface BasePersistenceService {

	public void setDatastore(Datastore datastore);
	
	public Site getSite(String id);
	
	public Page getPage(String site, String output);

	public Page getPage(String id);

	public NavBar getNavBarBySite(String siteName);

	public NavBar getNavBar(String id);

}
