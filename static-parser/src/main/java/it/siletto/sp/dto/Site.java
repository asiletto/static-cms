package it.siletto.sp.dto;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "site")
public class Site extends BaseEntity {

	@Id
	private String id;
	private String jqueryJs;
	private String bootstrapJs;
	private String bootstrapCss;
	private String copyright;
	private String author;

	@Reference
	private NavBar navbar;
  
	public String getJqueryJs() {
		return jqueryJs;
	}
	public void setJqueryJs(String jqueryJs) {
		this.jqueryJs = jqueryJs;
	}
	public String getBootstrapJs() {
		return bootstrapJs;
	}
	public void setBootstrapJs(String bootstrapJs) {
		this.bootstrapJs = bootstrapJs;
	}
	public String getBootstrapCss() {
		return bootstrapCss;
	}
	public void setBootstrapCss(String bootstrapCss) {
		this.bootstrapCss = bootstrapCss;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public NavBar getNavbar() {
		return navbar;
	}
	public void setNavbar(NavBar navbar) {
		this.navbar = navbar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
