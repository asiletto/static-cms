package it.siletto.service.impl;

import it.siletto.service.MarkdownParser;
import it.siletto.sp.dto.Anchor;
import it.siletto.sp.dto.MenuItem;
import it.siletto.sp.dto.NavBar;
import it.siletto.sp.dto.Site;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

public class NavBarParser extends AbstractParser implements MarkdownParser<NavBar>{

	@Override
	public NavBar parse(Site site, String partial, String fullPath) throws Exception {
		NavBar navbar = service.getNavBarBySite(site.getId());
		if(navbar == null)
			navbar = new NavBar();
		
		navbar.setId(site.getId() + "/NAVBAR");
		
		Map<String,MenuItem> mapMenu = new HashMap<String,MenuItem>();
		
		List<String> lines = FileUtils.readLines(new File(fullPath));
		for (String line : lines) {
			if(!line.isEmpty()){
				if(line.startsWith("BRAND:")){
					String tmp = line.substring("BRAND:".length()).trim();
					StringTokenizer tok = new StringTokenizer(tmp, "|");
					String label = tok.nextToken();
					String href = tok.nextToken();
					navbar.setBrand(createHref(label, href));
					navbar.setMenu(new ArrayList<MenuItem>());
				}else if(line.startsWith("MENU:")){
					String tmp = line.substring("MENU:".length()).trim();
					StringTokenizer tok = new StringTokenizer(tmp,"|");
					String label = tok.nextToken();
					String href = null;
					String parent = null;
					if(tok.hasMoreTokens()){
						href = tok.nextToken();
						if(tok.hasMoreTokens()){
							parent = tok.nextToken();
						}
					}
					
					if(parent == null){
						MenuItem item = createMenu(label, href);
						navbar.getMenu().add(item);
						mapMenu.put(label, item);
					}else{
						MenuItem item = mapMenu.get(parent);
						item.getChildrens().add(createMenu(label, href));
					}
				}
			}
		}		
				
		return navbar;
	}
	
	public MenuItem createMenu(String label, String href){
		MenuItem m = new MenuItem();
		m.setLabel(label);
		if(href!=null)
			m.setHref(href);
		m.setChildrens(new ArrayList<MenuItem>());
		return m;
	}
	
	public Anchor createHref(String label, String href){
		Anchor a = new Anchor();
		a.setLabel(label);
		a.setHref(href);
		return a;
	}


}
