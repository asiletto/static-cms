package it.siletto.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import it.siletto.art.dto.Image;
import it.siletto.art.dto.PageSection;
import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.service.MarkdownParser;
import it.siletto.sp.dto.Site;

public class SpeciesDetailPageParser extends AbstractParser implements MarkdownParser<SpeciesDetailPage>{

	public String getPageName(String templateName){
		return "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", ".html");
	}

	
	@Override
	public SpeciesDetailPage parse(Site site, String partial, String fullPath) throws Exception {

		String pagePath = getPageName(partial);
		String pageDir = getPageBaseDir(partial);
		SpeciesDetailPage page = parseSpeciesDetailPage(site, fullPath, pagePath, pageDir, false);
		page.setPath(pageDir);
		page.setOutput(pagePath);
		page.setId(site.getId() + page.getOutput());
		page.setSite(site);
		page.setIncludeCarousel(false);
		return page;
	}
	
	public SpeciesDetailPage parseSpeciesDetailPage(Site site, String path, String pageOutput, String pageDir, boolean print) throws Exception {

		SpeciesDetailPage page = createBlankSpeciesDetailPage(site, pageOutput);

		String sectionType="";
		
		PageSection current = null;
		
		List<String> lines = FileUtils.readLines(new File(path));

		for (String line : lines) {

			if(print)
				System.out.println(line);
			if(!line.isEmpty()){
				if(line.startsWith("# ")){
					page.setTitle( line.substring(2).trim() );
					sectionType="title";
				}else if(line.startsWith("## ")){
					sectionType="paragraph";
					if(current!=null)
						page.getSections().add(current);
					
					current = new PageSection();
					current.setTitle(line.substring(3).trim());
					current.setParagraphs(new ArrayList<String>());


				}else if(line.startsWith("DESCRIPTION:")){
					page.setDescription(line.substring("DESCRIPTION:".length()).trim());
				}else if(line.startsWith("INPUT:")){
					page.setInput(line.substring("INPUT:".length()).trim());
				}else if(line.startsWith("IMG:")){
					String image = line.substring("IMG:".length()).trim();
					if(image.startsWith("http") || image.startsWith("/"))
						current.setImage(image);
					else
						current.setImage(pageDir + "/" + image);
				}else if(line.startsWith("FRONTIMG:")){
					String image = line.substring("FRONTIMG:".length()).trim();
					if(image.startsWith("http") || image.startsWith("/"))
						page.setFrontImage(image);
					else
						page.setFrontImage(pageDir + "/" + image);
				}else if(line.startsWith("THUMBNAIL:")){
					String image = line.substring("THUMBNAIL:".length()).trim();
					if(image.startsWith("http") || image.startsWith("/"))
						page.setThumbnail( image );
					else
						page.setThumbnail( pageDir + "/" + image );
				}else if(line.startsWith("GALLERY:")){
					page.getGallery().add( parseGallery(pageDir, line.substring("GALLERY:".length()).trim() ) );
				}else if(line.startsWith("<!--")){
					//comment
				}else{
					if("title".equals(sectionType))
						page.getSummary().getParagraphs().add(line.trim());
					if("paragraph".equals(sectionType))
						current.getParagraphs().add(line.trim());
					
				}
			}
		}
		
			if(print)
				System.out.println(ToStringBuilder.reflectionToString(page, ToStringStyle.MULTI_LINE_STYLE));
			return page;

	}
	
	public SpeciesDetailPage createBlankSpeciesDetailPage(Site site, String pageOutput){
		SpeciesDetailPage page = service.getSpeciesDetailPage(site.getId(), pageOutput);
		if(page==null)
			page = new SpeciesDetailPage();

		page.setSections(new ArrayList<PageSection>());
		page.setGallery(new ArrayList<Image>());
		PageSection summary = new PageSection();
		summary.setParagraphs(new ArrayList<String>());
		page.setSummary(summary);
		return page;
	}

	private Image parseGallery(String pageDir, String line) {
		Image img = new Image();
		StringTokenizer tok = new StringTokenizer(line, "|");

		String image = tok.nextToken();
		if(image.startsWith("http") || image.startsWith("/"))
			img.setThumbnail(image);
		else
			img.setThumbnail(pageDir + "/" + image);

		image = tok.nextToken();
		if(image.startsWith("http") || image.startsWith("/"))
			img.setFull(image);
		else
			img.setFull(pageDir + "/" + image);
		
		img.setLabel(tok.nextToken());

		return img;
	}
}
