package it.siletto.service.impl;

import it.siletto.service.MarkdownParser;
import it.siletto.sp.dto.Page;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BasePageParser extends AbstractParser implements MarkdownParser<Page>{

	public String getPageNameBase(String templateName){
		return "/"+templateName.replaceAll("\\\\", "/").replaceAll(".md", ".html");
	}
	
	public String getPageBaseDir(String templateName){
		String tmp = "/"+templateName.replaceAll("\\\\", "/");
		if(tmp.endsWith("/"))
		tmp = tmp.substring(0, tmp.length()-1);
		tmp = tmp.substring(0, tmp.lastIndexOf("/"));
		return tmp;
	}

	@Override
	public Page parse(Site site, String partial, String fullPath) throws Exception {
		String pagePath = getPageNameBase(partial);
		String pageDir = getPageBaseDir(partial);
		Page page = parsePage(site, fullPath, pagePath, pageDir, false);
		page.setPath(pageDir);
		page.setOutput(pagePath);
		page.setId(site.getId() + page.getOutput());
		page.setSite(site);
		return page;
	}

	public Page parsePage(Site site, String path, String pageOutput, String pageDir, boolean print) throws Exception {

		Page page = createPage(site, pageOutput);
		
		List<String> lines = FileUtils.readLines(new File(path));

		for (String line : lines) {

			if(print)
				System.out.println(line);
			if(!line.isEmpty()){
				if(line.startsWith("# ")){
					page.setTitle( line.substring(2).trim() );
				}else if(line.startsWith("DESCRIPTION:")){
					page.setDescription(line.substring("DESCRIPTION:".length()).trim());
				}else if(line.startsWith("INPUT:")){
					page.setInput(line.substring("INPUT:".length()).trim());
				}else if(line.startsWith("CAROUSEL:")){
					page.setIncludeCarousel(Boolean.parseBoolean(line.substring("CAROUSEL:".length()).trim()));
				}
			}
		}
		
			if(print)
				System.out.println(ToStringBuilder.reflectionToString(page, ToStringStyle.MULTI_LINE_STYLE));
			return page;

	}
	
	public Page createPage(Site site, String pageOutput){
		Page page = service.getPage(site.getId(), pageOutput);
		if(page==null)
			page = new Page();

		page.setIncludeCarousel(false);
		return page;
	}

	
}
