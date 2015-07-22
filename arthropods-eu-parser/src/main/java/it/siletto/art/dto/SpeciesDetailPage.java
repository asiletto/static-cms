package it.siletto.art.dto;

import it.siletto.sp.dto.Anchor;
import it.siletto.sp.dto.Page;

import java.util.List;

public class SpeciesDetailPage extends Page {

	private Anchor parent;

	private PageSection summary;
	
	private String frontImage;
	private String thumbnail;
		
	private List<Image> gallery;
	
	private List<PageSection> sections;

	public List<Image> getGallery() {
		return gallery;
	}

	public void setGallery(List<Image> gallery) {
		this.gallery = gallery;
	}

	public String getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}

	public List<PageSection> getSections() {
		return sections;
	}

	public void setSections(List<PageSection> sections) {
		this.sections = sections;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Anchor getParent() {
		return parent;
	}

	public void setParent(Anchor parent) {
		this.parent = parent;
	}

	public PageSection getSummary() {
		return summary;
	}

	public void setSummary(PageSection summary) {
		this.summary = summary;
	}
	
}
