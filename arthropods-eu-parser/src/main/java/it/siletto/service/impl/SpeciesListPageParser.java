package it.siletto.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import it.siletto.art.dto.PageSection;
import it.siletto.art.dto.SpeciesListPage;
import it.siletto.service.MarkdownParser;
import it.siletto.sp.dto.Site;

public class SpeciesListPageParser extends AbstractParser implements MarkdownParser<SpeciesListPage>{

	public String getPageName(String templateName){
		return "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", ".html");
	}

	@Override
	public SpeciesListPage parse(Site site, String partial, String fullPath) throws Exception {
		String pagePath = getPageName(partial);
		String pageDir = getPageBaseDir(partial);
		SpeciesListPage page = parseSpeciesListPage(site, fullPath, pagePath, pageDir, false);
		page.setPath(pageDir);
		page.setOutput(pagePath);
		page.setId(site.getId() + page.getOutput());
		page.setSite(site);
		page.setIncludeCarousel(false);
		return page;
	}
	
	
	public SpeciesListPage parseSpeciesListPage(Site site, String path, String pageOutput, String pageDir, boolean print) throws Exception {

		SpeciesListPage page = createSpeciesListPage(site, pageOutput);
		
		List<String> lines = FileUtils.readLines(new File(path));

		for (String line : lines) {

			if(print)
				System.out.println(line);
			if(!line.isEmpty()){
				if(line.startsWith("# ")){
					page.setTitle( line.substring(2).trim() );
					page.getSummary().setTitle( line.substring(2).trim() );

				}else if(line.startsWith("DESCRIPTION:")){
					page.setDescription(line.substring("DESCRIPTION:".length()).trim());
				}else if(line.startsWith("INPUT:")){
					page.setInput(line.substring("INPUT:".length()).trim());
				}else if(line.startsWith("IMG:")){
					String image = line.substring("IMG:".length()).trim();
					if(image.startsWith("http") || image.startsWith("/"))
						page.getSummary().setImage(image);
					else
						page.getSummary().setImage(pageDir + "/" + image);
				}else{
						page.getSummary().getParagraphs().add(line.trim());
				}
			}
		}
		
			if(print)
				System.out.println(ToStringBuilder.reflectionToString(page, ToStringStyle.MULTI_LINE_STYLE));
			return page;

	}
	
	
	private SpeciesListPage createSpeciesListPage(Site site, String pageOutput) {
		SpeciesListPage page = service.getSpeciesListPage(site.getId(), pageOutput);
		if(page==null)
			page = new SpeciesListPage();

		PageSection summary = new PageSection();
		summary.setParagraphs(new ArrayList<String>());
		page.setSummary(summary);
		return page;
	}
}
