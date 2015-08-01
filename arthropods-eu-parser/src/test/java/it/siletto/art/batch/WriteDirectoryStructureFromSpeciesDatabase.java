package it.siletto.art.batch;

import it.siletto.commons.annotations.Mongodb;
import it.siletto.species.dto.Rank;
import it.siletto.species.dto.Species;
import it.siletto.species.service.SpeciesPersistence;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import com.google.inject.Key;

public class WriteDirectoryStructureFromSpeciesDatabase extends BaseDropwizardBatch {

	public static void main(String[] args) throws Exception {

		new WriteDirectoryStructureFromSpeciesDatabase().run();
	}
	
	public void run() throws Exception {
		setup(new String[]{});

		String base = "D:/dev/workspaces/personale/arthropods.eu/private/arthropods-eu-content/";
		
		SpeciesPersistence speciesService = injector.getInstance(Key.get(SpeciesPersistence.class, Mongodb.class));

		List<Species> species = speciesService.getAllSpecies();

		Set<String> parents = new HashSet<String>();
		
		for (Species item : species) {
			if(item.getLevel() == Rank.Species){
				String cat = item.getCategory();
				if(cat!=null){
					parents.add(cat);
					String path = "/" + cat + "/" + item.getName();
					path = path.toLowerCase();
					path = path.replaceAll(" ", "-");
					System.out.println(path);
					
					String dir = base + "site" + path;
					new File(dir).mkdirs();
					
					FileFilter filter = FileFilterUtils.and(FileFileFilter.FILE, FileFilterUtils.suffixFileFilter(".jpg"));
					FileUtils.copyDirectory(new File(base+"species-template"), new File(dir), filter);
					
					String template = FileUtils.readFileToString(new File(base+"species-template/template.md"));
					String data = template
								.replaceAll("%SPECIESNAME%", item.getName())
								.replaceAll("%SPECIESID%", ""+item.getId());
				
					FileUtils.write(new File(dir+"/page.md"), data);					
				
				}
				
			}
		}
		
		for (String parent : parents) {
			String path = "/" + parent + "/";
			path = path.toLowerCase();
			path = path.replaceAll(" ", "-");
			System.out.println(path);
			
			String dir = base + "site" + path;
			String template = FileUtils.readFileToString(new File(base+"species-template/list.md"));
			String data = template.replaceAll("%SPECIESLIST%", parent);
	
			FileUtils.write(new File(dir+"/page.md"), data);
		
			FileUtils.copyFile(new File(base+"species-template/thumbnail.jpg"), new File(dir+"/species.jpg"));

		}

		
		System.exit(0);
	}
}
