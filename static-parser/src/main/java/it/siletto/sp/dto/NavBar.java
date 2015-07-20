package it.siletto.sp.dto;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "navbar")
public class NavBar extends BaseEntity {

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
