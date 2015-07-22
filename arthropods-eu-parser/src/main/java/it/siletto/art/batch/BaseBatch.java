package it.siletto.art.batch;

import it.siletto.service.ArthropodsPersistenceService;
import it.siletto.service.PageBuilder;
import it.siletto.service.impl.ArthropodsPersistenceServiceMongodbImpl;
import it.siletto.service.impl.PageBuilderImpl;
import it.siletto.sp.MongoDB;
import it.siletto.sp.dto.BaseEntity;

import org.mongodb.morphia.Datastore;

public class BaseBatch {

	public static Datastore ds;
	public static ArthropodsPersistenceService service;
	public static PageBuilder pageBuilder;
	
	public static void init(){
		ds = MongoDB.instance().getDatabase(new Class[]{BaseEntity.class});
		
		service = new ArthropodsPersistenceServiceMongodbImpl();
		service.setDatastore(ds);
		
		pageBuilder = new PageBuilderImpl();
		pageBuilder.setService(service);
		pageBuilder.init();
	}

}
