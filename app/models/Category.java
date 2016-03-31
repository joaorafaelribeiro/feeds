package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Category extends Model {
	private String name;
	private String color;
	private Boolean active;
	
	public Category() {
		active = true;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
