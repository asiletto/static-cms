package it.siletto.sp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoDB {

	private static final Logger LOG = Logger.getLogger(MongoDB.class.getName());
	private static final String PROPERTIES_FILE = "mongodb.properties";
	
	private static final MongoDB INSTANCE = new MongoDB();

	private Datastore datastore;

	private MongoDB() {
	}
	
	protected void init(Class[] fromClasses){
		MongoClientOptions mongoOptions = MongoClientOptions.builder()
				.socketTimeout(60000)
				.connectTimeout(15000)
				.maxConnectionIdleTime(600000)
				.readPreference(ReadPreference.primaryPreferred())
				.build();

		Properties props;
		
		try{
			props = load(PROPERTIES_FILE);
		}catch(IOException e){
			throw new RuntimeException(e);
		}

		String username = (String) props.get("username");
		String database = (String) props.get("database");
		String password = (String) props.get("password");
		String hostname = (String) props.get("hostname");
		String port = (String) props.get("port");

		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password))
			credentials.add(
				MongoCredential.createCredential(username, database, password.toCharArray())					
			);
		
		MongoClient mongoClient = new MongoClient(new ServerAddress(hostname, Integer.parseInt(port)), credentials, mongoOptions);
		mongoClient.setWriteConcern(WriteConcern.SAFE);
		
		Morphia morphia = new Morphia();
		for (int i = 0; i < fromClasses.length; i++) {
			morphia.mapPackage(fromClasses[i].getPackage().getName());
		}
		
		datastore = morphia.createDatastore( mongoClient, database);
		datastore.ensureIndexes();
		datastore.ensureCaps();
		LOG.info("Connection to database '" + hostname + ":" + port + "/" + database + "' initialized");
	}

	public static Properties load(String filename) throws IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		in.close();
		return prop;
	}

	public static MongoDB instance() {
		return INSTANCE;
	}

	public Datastore getDatabase(Class[] fromClasses) {
		if(datastore == null)
			init(fromClasses);
		return datastore;
	}
}
