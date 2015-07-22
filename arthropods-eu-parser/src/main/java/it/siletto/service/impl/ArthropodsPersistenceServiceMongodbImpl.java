package it.siletto.service.impl;

import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.art.dto.SpeciesListPage;
import it.siletto.service.ArthropodsPersistenceService;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;
import it.siletto.sp.service.impl.BasePersistenceServiceMongodbImpl;

import java.util.List;

import org.mongodb.morphia.query.Query;

public class ArthropodsPersistenceServiceMongodbImpl extends BasePersistenceServiceMongodbImpl implements ArthropodsPersistenceService {

	@Override
	public SpeciesDetailPage getSpeciesDetailPage(String site, String output) {
		return getSpeciesDetailPage(site + (output.startsWith("/")?"":"/") + output);
	}

	@Override
	public SpeciesListPage getSpeciesListPage(String site, String output) {
		return getSpeciesListPage(site + (output.startsWith("/")?"":"/") + output);
	}

	public SpeciesDetailPage getSpeciesDetailPage(String id) {
		return datastore.find(SpeciesDetailPage.class, "_id", id).get();
	}

	public SpeciesListPage getSpeciesListPage(String id) {
		return datastore.find(SpeciesListPage.class, "_id", id).get();
	}

	@Override
	public List<Page> getPagesBySite(String siteName) {
		Query<Page> query = datastore.createQuery(Page.class);
		query.retrievedFields(false, "site");
		Site site = new Site();
		site.setId(siteName);
		query.field("site").equal(site);
		return query.asList();
	}

}

