package it.siletto.sp.dto;

public class Page {

	private Site site;
	private NavBar navbar;
	private String title;
	private String subtitle;
	private String description;
	private Boolean includeCarousel;
	private String input;
	private String output;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIncludeCarousel() {
		return includeCarousel;
	}
	public void setIncludeCarousel(Boolean includeCarousel) {
		this.includeCarousel = includeCarousel;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public NavBar getNavbar() {
		return navbar;
	}
	public void setNavbar(NavBar navbar) {
		this.navbar = navbar;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	
}
