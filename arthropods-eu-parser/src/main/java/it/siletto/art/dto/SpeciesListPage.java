package it.siletto.art.dto;

import it.siletto.sp.dto.Page;

import java.util.List;

public class SpeciesListPage extends Page {

	private PageSection summary;

	private List<SpeciesDetailPage> species;

	public List<SpeciesDetailPage> getSpecies() {
		return species;
	}

	public void setSpecies(List<SpeciesDetailPage> species) {
		this.species = species;
	}

	public PageSection getSummary() {
		return summary;
	}

	public void setSummary(PageSection summary) {
		this.summary = summary;
	}

}
