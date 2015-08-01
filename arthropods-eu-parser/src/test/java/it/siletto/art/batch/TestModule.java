package it.siletto.art.batch;

import it.siletto.commons.annotations.Mongodb;
import it.siletto.commons.service.CypherService;
import it.siletto.commons.service.impl.CypherServiceRSAImpl;
import it.siletto.sp.dto.Page;
import it.siletto.species.dto.Species;
import it.siletto.species.service.SpeciesPersistence;
import it.siletto.species.service.impl.SpeciesPersistenceMongodbImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class TestModule implements Module {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	protected SiteAppConfiguration cfg;
	
	public TestModule(SiteAppConfiguration appConfiguration) {
		super();
		this.cfg = appConfiguration;
	}

	@Override
	public void configure(Binder binder) {
		
		binder
			.bind(CypherService.class)
			.to(CypherServiceRSAImpl.class);

		binder
			.bind(SpeciesPersistence.class)
			.annotatedWith(Mongodb.class)
			.to(SpeciesPersistenceMongodbImpl.class);


	}

	@Provides
	@Singleton
	@SuppressWarnings("rawtypes")
	@Named("mongodb.site")
	public Datastore getMongodbShops(){
		
		Class[] fromClasses = new Class[]{Page.class};
				
		MongoClientOptions mongoOptions = MongoClientOptions.builder()
				.socketTimeout(60000)
				.connectTimeout(15000)
				.maxConnectionIdleTime(600000)
				.readPreference(ReadPreference.primaryPreferred())
				.build();

		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		if(StringUtils.isNotEmpty(cfg.getMongodbSiteUsername()) && StringUtils.isNotEmpty(cfg.getMongodbSitePassword()))
			credentials.add(
				MongoCredential.createCredential(cfg.getMongodbSiteUsername(), cfg.getMongodbSiteDatabase(), cfg.getMongodbSitePassword().toCharArray())					
			);
		
		MongoClient mongoClient = new MongoClient(new ServerAddress(cfg.getMongodbSiteHostname(), Integer.parseInt(cfg.getMongodbSitePort())), credentials, mongoOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		
		Morphia morphia = new Morphia();
		for (int i = 0; i < fromClasses.length; i++) {
			morphia.mapPackage(fromClasses[i].getPackage().getName());
		}
		
		Datastore datastore = morphia.createDatastore( mongoClient, cfg.getMongodbSiteDatabase());
		datastore.ensureIndexes();
		datastore.ensureCaps();
		log.info("Connection to database '" + cfg.getMongodbSiteHostname() + ":" + cfg.getMongodbSitePort() + "/" + cfg.getMongodbSiteDatabase() + "' initialized");

		return datastore;
	}

	@Provides
	@Singleton
	@SuppressWarnings("rawtypes")
	@Named("mongodb.species")
	public Datastore getMongodbSpecies(){
		
		Class[] fromClasses = new Class[]{Species.class};
				
		MongoClientOptions mongoOptions = MongoClientOptions.builder()
				.socketTimeout(60000)
				.connectTimeout(15000)
				.maxConnectionIdleTime(600000)
				.readPreference(ReadPreference.primaryPreferred())
				.build();

		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		if(StringUtils.isNotEmpty(cfg.getMongodbSpeciesUsername()) && StringUtils.isNotEmpty(cfg.getMongodbSpeciesPassword()))
			credentials.add(
				MongoCredential.createCredential(cfg.getMongodbSpeciesUsername(), cfg.getMongodbSpeciesDatabase(), cfg.getMongodbSpeciesPassword().toCharArray())					
			);
		
		MongoClient mongoClient = new MongoClient(new ServerAddress(cfg.getMongodbSpeciesHostname(), Integer.parseInt(cfg.getMongodbSpeciesPort())), credentials, mongoOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		
		Morphia morphia = new Morphia();
		for (int i = 0; i < fromClasses.length; i++) {
			morphia.mapPackage(fromClasses[i].getPackage().getName());
		}
		
		Datastore datastore = morphia.createDatastore( mongoClient, cfg.getMongodbSpeciesDatabase());
		datastore.ensureIndexes();
		datastore.ensureCaps();
		log.info("Connection to database '" + cfg.getMongodbSpeciesHostname() + ":" + cfg.getMongodbSpeciesPort() + "/" + cfg.getMongodbSpeciesDatabase() + "' initialized");

		return datastore;
	}

	@Provides
	@Named("publicKeyFile")
	public String getPublicKeyFile(){
		return cfg.getPublicKeyFile();
	}

	@Provides
	@Named("privateKeyFile")
	public String getPrivateKeyFile(){
		return cfg.getPrivateKeyFile();
	}

}
