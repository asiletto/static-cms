package it.siletto.service;

import it.siletto.sp.dto.Site;

public interface MarkdownParser<T> {

	public void setService(ArthropodsPersistenceService service);
	
	public T parse(Site site, String partial, String fullPath) throws Exception;

}
