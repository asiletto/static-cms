package it.siletto.sp.dto;

import java.util.List;

public class NavBar {

	private Anchor brand;
	private List<MenuItem> menu;

	public Anchor getBrand() {
		return brand;
	}

	public void setBrand(Anchor brand) {
		this.brand = brand;
	}

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}
	
	
}
