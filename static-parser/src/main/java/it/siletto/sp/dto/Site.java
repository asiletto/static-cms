package it.siletto.sp.dto;

public class Site {

	private String jqueryJs;
	private String bootstrapJs;
	private String bootstrapCss;
	private String copyright;
	private String author;
	
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
}
