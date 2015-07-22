package it.siletto.sp;

import it.siletto.sp.dto.BaseEntity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class DbUtils {

	public static Properties load(String filename) throws IOException{
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		in.close();
		return prop;
	}

	public static DB getDb(String string) throws IOException {
		Properties props = load("mongodb.properties");
		
		String username = (String)props.get("username");
		String database = (String)props.get("database");
		String password = (String)props.get("password");
		String hostname = (String)props.get("hostname");
		String port = (String)props.get("port");
		
		
		MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(hostname, Integer.parseInt(port)), Arrays.asList(credential));
		
		DB db = mongoClient.getDB(database);
		return db;
	}

}
