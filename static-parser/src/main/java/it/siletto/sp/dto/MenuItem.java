package it.siletto.sp.dto;

import java.util.List;

public class MenuItem extends Anchor {

	private List<MenuItem> childrens;
	private Boolean selected;
	
	public Boolean hasChildrens(){
		return childrens!=null && childrens.size()>0;
	}
	
	public List<MenuItem> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<MenuItem> childrens) {
		this.childrens = childrens;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}
