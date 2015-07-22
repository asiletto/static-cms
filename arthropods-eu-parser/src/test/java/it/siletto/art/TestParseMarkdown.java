package it.siletto.art;

import it.siletto.art.dto.Image;
import it.siletto.art.dto.PageSection;
import it.siletto.art.dto.SpeciesDetailPage;
import it.siletto.art.dto.SpeciesListPage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TestParseMarkdown {

	public static SpeciesDetailPage createBlankSpeciesDetailPage(){
		SpeciesDetailPage page = new SpeciesDetailPage();
		page.setSections(new ArrayList<PageSection>());
		page.setGallery(new ArrayList<Image>());
		PageSection summary = new PageSection();
		summary.setParagraphs(new ArrayList<String>());
		page.setSummary(summary);
		return page;
	}
	
	
	private static SpeciesListPage createSpeciesListPage() {
		SpeciesListPage page = new SpeciesListPage();
		PageSection summary = new PageSection();
		summary.setParagraphs(new ArrayList<String>());
		page.setSummary(summary);
		return page;
	}
	
	public static void main(String[] args) throws Exception {
	//	parseSpeciesDetailPage("template.md", true);
	//	parseSpeciesListPage("list.md", true);
	}

	public static SpeciesListPage parseSpeciesListPage(String path, boolean print) throws Exception {

		SpeciesListPage page = createSpeciesListPage();
		
		List<String> lines = FileUtils.readLines(new File(path));

		for (String line : lines) {

			if(print)
				System.out.println(line);
			if(!line.isEmpty()){
				if(line.startsWith("# ")){
					page.setTitle( line.substring(2).trim() );

				}else if(line.startsWith("IMG:")){
						page.getSummary().setImage(line.substring("IMG:".length()).trim());
				}else{
						page.getSummary().getParagraphs().add(line.trim());
				}
			}
		}
		
			if(print)
				System.out.println(ToStringBuilder.reflectionToString(page, ToStringStyle.MULTI_LINE_STYLE));
			return page;

	}


	public static SpeciesDetailPage parseSpeciesDetailPage(String path, boolean print) throws Exception {

		SpeciesDetailPage page = createBlankSpeciesDetailPage();

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


				}else if(line.startsWith("IMG:")){
					current.setImage(line.substring("IMG:".length()).trim());
				}else if(line.startsWith("PATH:")){
					page.setOutput( line.substring("PATH:".length()).trim() );
				}else if(line.startsWith("FRONTIMG:")){
					page.setFrontImage(line.substring("FRONTIMG:".length()).trim());
				}else if(line.startsWith("THUMBNAIL:")){
					page.setThumbnail( line.substring("THUMBNAIL:".length()).trim() );
				}else if(line.startsWith("GALLERY:")){
					page.getGallery().add( parseGallery( line.substring("GALLERY:".length()).trim() ) );
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

	private static Image parseGallery(String line) {
		Image img = new Image();
		StringTokenizer tok = new StringTokenizer(line, "|");
		img.setThumbnail(tok.nextToken());
		img.setFull(tok.nextToken());
		img.setLabel(tok.nextToken());
		return img;
	}
}
