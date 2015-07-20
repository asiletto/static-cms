package it.siletto.sp.service.impl;

import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;
import it.siletto.sp.service.BasePersistenceService;

import org.mongodb.morphia.Datastore;

public class BasePersistenceServiceMongodbImpl implements BasePersistenceService {

	protected Datastore datastore;

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	@Override
	public Site getSite(String id) {
		return datastore.find(Site.class, "_id", id).get();
	}

	@Override
	public Page getPage(String site, String output) {
		return getPage(site + (output.startsWith("/")?"":"/") + output);
	}

	@Override
	public Page getPage(String id) {
		return datastore.find(Page.class, "_id", id).get();
	}

	@Override
	public NavBar getNavBarBySite(String siteName) {
		return getNavBar(siteName+"/NAVBAR");
	}

	@Override
	public NavBar getNavBar(String id) {
		return datastore.find(NavBar.class, "_id", id).get();
	}
	
	
}
