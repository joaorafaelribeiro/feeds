package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity(name="config")
public class Config extends Model {
	@Column(nullable=false)
	private Integer updateTime;
	@Column(nullable=false)
	private Integer itemsPerPage;
	@Column(nullable=false)
	private Integer	maxSource;
	public Integer getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Integer update) {
		this.updateTime = update;
	}
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Integer getMaxSource() {
		return maxSource;
	}
	public void setMaxSource(Integer maxSource) {
		this.maxSource = maxSource;
	}
	
	
	
}
