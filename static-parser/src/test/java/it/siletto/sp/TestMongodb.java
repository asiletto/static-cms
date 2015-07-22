package it.siletto.sp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


public class TestMongodb {

	public static void main(String[] args) throws IOException {
		
		Properties props = DbUtils.load("mongodb.properties");
		
		String username = (String)props.get("username");
		String database = (String)props.get("database");
		String password = (String)props.get("password");
		String hostname = (String)props.get("hostname");
		String port = (String)props.get("port");
		
		
		MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress(hostname, Integer.parseInt(port)), Arrays.asList(credential));
		
		DB db = mongoClient.getDB(database);
		DBCursor cursor = db.getCollection("test").find();
		while(cursor.hasNext()){
			System.out.println(cursor.next().toString());
		}
		
	}
}
