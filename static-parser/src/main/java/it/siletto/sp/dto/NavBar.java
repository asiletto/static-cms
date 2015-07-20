package it.siletto.sp.dto;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "navbar")
public class NavBar extends BaseEntity {

	@Id
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
