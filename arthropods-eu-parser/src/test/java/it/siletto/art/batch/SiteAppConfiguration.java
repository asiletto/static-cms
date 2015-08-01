package it.siletto.art.batch;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.siletto.commons.app.BaseAppConfiguration;

public class SiteAppConfiguration extends BaseAppConfiguration {

	@JsonProperty("mongodb.species.username")
	private String mongodbSpeciesUsername;

	@JsonProperty("mongodb.species.password")
	private String mongodbSpeciesPassword;

	@JsonProperty("mongodb.species.hostname")
	private String mongodbSpeciesHostname;

	@JsonProperty("mongodb.species.database")
	private String mongodbSpeciesDatabase;

	@JsonProperty("mongodb.species.port")
	private String mongodbSpeciesPort;	

	
	@JsonProperty("mongodb.site.username")
	private String mongodbSiteUsername;

	@JsonProperty("mongodb.site.password")
	private String mongodbSitePassword;

	@JsonProperty("mongodb.site.hostname")
	private String mongodbSiteHostname;

	@JsonProperty("mongodb.site.database")
	private String mongodbSiteDatabase;

	@JsonProperty("mongodb.site.port")
	private String mongodbSitePort;

	public String getMongodbSpeciesUsername() {
		return mongodbSpeciesUsername;
	}

	public void setMongodbSpeciesUsername(String mongodbSpeciesUsername) {
		this.mongodbSpeciesUsername = mongodbSpeciesUsername;
	}

	public String getMongodbSpeciesPassword() {
		return mongodbSpeciesPassword;
	}

	public void setMongodbSpeciesPassword(String mongodbSpeciesPassword) {
		this.mongodbSpeciesPassword = mongodbSpeciesPassword;
	}

	public String getMongodbSpeciesHostname() {
		return mongodbSpeciesHostname;
	}

	public void setMongodbSpeciesHostname(String mongodbSpeciesHostname) {
		this.mongodbSpeciesHostname = mongodbSpeciesHostname;
	}

	public String getMongodbSpeciesDatabase() {
		return mongodbSpeciesDatabase;
	}

	public void setMongodbSpeciesDatabase(String mongodbSpeciesDatabase) {
		this.mongodbSpeciesDatabase = mongodbSpeciesDatabase;
	}

	public String getMongodbSpeciesPort() {
		return mongodbSpeciesPort;
	}

	public void setMongodbSpeciesPort(String mongodbSpeciesPort) {
		this.mongodbSpeciesPort = mongodbSpeciesPort;
	}

	public String getMongodbSiteUsername() {
		return mongodbSiteUsername;
	}

	public void setMongodbSiteUsername(String mongodbSiteUsername) {
		this.mongodbSiteUsername = mongodbSiteUsername;
	}

	public String getMongodbSitePassword() {
		return mongodbSitePassword;
	}

	public void setMongodbSitePassword(String mongodbSitePassword) {
		this.mongodbSitePassword = mongodbSitePassword;
	}

	public String getMongodbSiteHostname() {
		return mongodbSiteHostname;
	}

	public void setMongodbSiteHostname(String mongodbSiteHostname) {
		this.mongodbSiteHostname = mongodbSiteHostname;
	}

	public String getMongodbSiteDatabase() {
		return mongodbSiteDatabase;
	}

	public void setMongodbSiteDatabase(String mongodbSiteDatabase) {
		this.mongodbSiteDatabase = mongodbSiteDatabase;
	}

	public String getMongodbSitePort() {
		return mongodbSitePort;
	}

	public void setMongodbSitePort(String mongodbSitePort) {
		this.mongodbSitePort = mongodbSitePort;
	}

}
