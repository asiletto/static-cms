package it.siletto.sp.dto;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "site_sites")
public class Site extends BaseEntity {

	@Id
	private String id;
	private String copyright;
	private String author;
	
	private String googleKey;
	private String facebookKey;
	private String oauthRedirect;
	private String backendUrl;

	@Reference
	private NavBar navbar;
  
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
	public String getGoogleKey() {
		return googleKey;
	}
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	public String getFacebookKey() {
		return facebookKey;
	}
	public void setFacebookKey(String facebookKey) {
		this.facebookKey = facebookKey;
	}
	public String getOauthRedirect() {
		return oauthRedirect;
	}
	public void setOauthRedirect(String oauthRedirect) {
		this.oauthRedirect = oauthRedirect;
	}
	public String getBackendUrl() {
		return backendUrl;
	}
	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

}
