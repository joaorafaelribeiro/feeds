package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="resume")
public class Resume extends Model{

	private String title;
	private Integer qtd;
	private String icon;
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String site) {
		this.icon = site;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	
	
}
