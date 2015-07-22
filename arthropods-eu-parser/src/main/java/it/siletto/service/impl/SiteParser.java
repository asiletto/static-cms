package it.siletto.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import it.siletto.service.MarkdownParser;
import it.siletto.sp.dto.Site;

public class SiteParser extends AbstractParser implements MarkdownParser<Site>{

	@Override
	public Site parse(Site site, String partial, String fullPath) throws Exception {
		List<String> lines = FileUtils.readLines(new File(fullPath));
		for (String line : lines) {

			if(!line.isEmpty()){
				if(line.startsWith("# ")){
					String siteId =  line.substring(2).trim();
					site = service.getSite(siteId);
					if(site == null){
						site = new Site();
						site.setId(siteId);
					}
					site.setAuthor(siteId);
				}
				
				if(line.startsWith("COPYRIGHT:")){
					site.setCopyright(line.substring("COPYRIGHT:".length()).trim());
				}
			}
		}
		return site;
	}

}
