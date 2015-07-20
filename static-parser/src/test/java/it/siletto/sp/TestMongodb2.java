package it.siletto.sp;

import it.siletto.sp.utils.DbUtils;

import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCursor;


public class TestMongodb2 {

	public static void main(String[] args) throws IOException {
		

		DB db = DbUtils.getDb("mongodb.properties");
		DBCursor cursor = db.getCollection("test").find();

		while(cursor.hasNext()){
			System.out.println(cursor.next().toString());
		}
		
		cursor.close();
	}
}
