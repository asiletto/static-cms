package it.siletto.art;

import java.io.File;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class TestWriteMarkdownFromTemplate {

	public static void main(String[] args) throws Exception {
		
		String basePath = "D:/dev/workspaces/personale/arthropods.eu/arthropods-eu-content/site/";
		
		String template = FileUtils.readFileToString(new File("template.md"));
		String list = FileUtils.readFileToString(new File("list.md"));
		
		File path = new File(basePath);
		Collection<File> templates = FileUtils.listFiles(path, new String[]{"md"}, true);

		for (File file : templates) {
			String fullPath = file.getPath();
			String partial = fullPath.substring(basePath.length());
			int size = getSlashCount(partial);

			if(size == 2){
				String species = getSpeciesName(partial);
				String data = template.replaceAll("SPECIESNAME", species);
				FileUtils.write(file, data);
			}
			
			if(size == 1){
				String listName = getListName(partial);
				String data = list.replaceAll("SPECIESLIST", listName);
				FileUtils.write(file, data);
			}

		}
	}
	
	private static String getListName(String path) {
		StringTokenizer tok = new StringTokenizer(path, "\\");
		String tmp = tok.nextToken();
		return StringUtils.capitalize(tmp.replaceAll("-", " "));
	}

	public static String getSpeciesName(String path){
		StringTokenizer tok = new StringTokenizer(path, "\\");
		tok.nextToken();
		String tmp = tok.nextToken();
		return StringUtils.capitalize(tmp.replaceAll("-", " "));
	}
	
	public static int getSlashCount(String path){
		path = path.replaceAll("\\\\", "/");
		Pattern p = Pattern.compile("\\/");
	    Matcher m = p.matcher(path);
	    int count = 0;
	    while (m.find()){
	    	count +=1;
	    }
	    return count;
	}
}
