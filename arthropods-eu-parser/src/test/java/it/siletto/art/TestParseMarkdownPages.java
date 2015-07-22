package it.siletto.art;

import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.art.dto.SpeciesListPage;

import java.io.File;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class TestParseMarkdownPages {

	public static void main(String[] args) throws Exception {
		
		String basePath = "D:/dev/workspaces/personale/arthropods.eu/arthropods-eu-content/site/";
		
		File path = new File(basePath);
		Collection<File> templates = FileUtils.listFiles(path, new String[]{"md"}, true);

		for (File file : templates) {
			String fullPath = file.getPath();
			String partial = fullPath.substring(basePath.length());
			int size = getSlashCount(partial);
//			System.out.println(size + " " + partial);
			if(size == 2){
				String pagePath = getPageName(partial);
				String pageDir = getPageBaseDir(partial);
				String parent = getParentPath(partial);
				SpeciesDetailPage page = TestParseMarkdown.parseSpeciesDetailPage(fullPath, false);
				System.out.println("title:" + page.getTitle());
				System.out.println("path :" + pagePath);
				System.out.println("paren:" + parent);
				System.out.println("dir  :" + pageDir);
				System.out.println("==");
			}else if(size == 1){
				String pagePath = getPageName(partial);
				String pageDir = getPageBaseDir(partial);
				SpeciesListPage page = TestParseMarkdown.parseSpeciesListPage(fullPath, false);
				System.out.println("title:" + page.getTitle());
				System.out.println("path :" + pagePath);
				System.out.println("dir  :" + pageDir);
				System.out.println("==");
			}else if(size == 0){
				System.out.println("page:" + fullPath);
			}
		}
	}

	private static String getParentPath(String templateName) {
		String tmp = "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", "/");
		return tmp.substring(0, tmp.indexOf('/', 1));
	}

	public static String getPageBaseDir(String templateName){
		return "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", "/");
	}

	public static String getPageName(String templateName){
		return "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", ".html");
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
