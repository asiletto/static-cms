package it.siletto.service.impl;

import it.siletto.service.ArthropodsPersistenceService;

public abstract class AbstractParser {

	protected ArthropodsPersistenceService service;

	public void setService(ArthropodsPersistenceService service) {
		this.service = service;
	}
	
	public String getPageBaseDir(String templateName){
		String tmp = "/"+templateName.replaceAll("\\\\", "/").replaceAll("/page.md", "/");
		if(tmp.endsWith("/"))
			return tmp.substring(0, tmp.length()-1);
		return tmp;
	}
}
