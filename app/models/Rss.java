package models;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;

import play.db.jpa.Model;
@Entity(name="rsses")
public class Rss extends Model {

	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_create")
	private Date date;
	private String link;
	@OneToMany(mappedBy="rss",fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	private List<Feed> feeds;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	private String site;
	private String icon;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_update")
	private Date update;
	
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String rss) {
		this.site = rss;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Rss() {
		feeds = new ArrayList<Feed>();
		date = new Date();
		update = new Date();
	}
	public List<Feed> getFeeds() {
		return feeds;
	}
	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date data) {
		this.date = data;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}	
	
}
