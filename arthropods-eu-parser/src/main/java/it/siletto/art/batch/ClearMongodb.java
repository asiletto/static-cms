package it.siletto.art.batch;

import it.siletto.sp.MongoDB;
import it.siletto.sp.dto.BaseEntity;
import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import org.mongodb.morphia.Datastore;

public class ClearMongodb {

	public static void main(String[] args) {
		execute(true);
	}
	
	public static void execute(boolean exit) {
		
		Datastore mongoDatastore = MongoDB.instance().getDatabase(new Class[]{BaseEntity.class});

		mongoDatastore.delete(mongoDatastore.createQuery(Site.class));
		mongoDatastore.delete(mongoDatastore.createQuery(Page.class));
		mongoDatastore.delete(mongoDatastore.createQuery(NavBar.class));
		
		if(exit)
			System.exit(1);
	}
}
