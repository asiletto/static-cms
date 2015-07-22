package it.siletto.art.batch;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class CleanAndRecreate {

	public static void main(String[] args) throws Exception {
		
		System.out.println("clear");
		ClearMongodb.execute(false);
		
		System.out.println("write");
		WriteToMongodb.execute(false);
		
		FileUtils.deleteDirectory(new File("D:/dev/workspaces/personale/arthropods.eu/site"));
		
		System.out.println("generate");
		GenerateSiteFromMongodb.execute(false);
		
		System.exit(0);
	}
}
