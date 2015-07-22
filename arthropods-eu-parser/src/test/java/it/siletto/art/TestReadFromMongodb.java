package it.siletto.art;

import it.siletto.art.batch.BaseBatch;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestReadFromMongodb extends BaseBatch {
	
	static String outputDir = "target/site/";

	public static void main(String[] args) throws Exception {

		init();
		
		String siteName="arthropods.eu";
		
		Site site = service.getSite(siteName);

		List<Page> pages = service.getPagesBySite(siteName);

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		FileUtils.write(new File(outputDir+"site.json"),  mapper.writeValueAsString(site));
		FileUtils.write(new File(outputDir+"pages.json"),  mapper.writeValueAsString(pages));

		System.exit(0);
	  }

	
}
