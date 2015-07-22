package it.siletto.service;

import java.util.List;

import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.art.dto.SpeciesListPage;
import it.siletto.sp.dto.Page;
import it.siletto.sp.service.BasePersistenceService;

public interface ArthropodsPersistenceService extends BasePersistenceService {

	public SpeciesDetailPage getSpeciesDetailPage(String site, String output);

	public SpeciesListPage getSpeciesListPage(String name, String output);

	public List<Page> getPagesBySite(String string);
}
