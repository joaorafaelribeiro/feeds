package models;


import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.JsonAdapter;

import play.db.jpa.Model;
import util.FeedJson;
import util.SoundexUtil;

@Entity(name="feeds")
@JsonAdapter(FeedJson.class)
public class Feed extends Model  {

	@Column(nullable=false)
	private String title;
	@Column(columnDefinition="TEXT")
	private String description;
	private String img;
	@Column(nullable=false)
	private String link;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="rsses_id",nullable=false)
	private Rss rss;
	@Column(nullable=false)
	private Boolean active;
	@Column(nullable=false)
	private Boolean favorite;
	@Column(name="title_soundex")
	private String soundex;
	@OneToMany(mappedBy="feed",fetch=FetchType.LAZY)
	private List<Comment> comments;
	
	public Feed() {
		active = true;
		favorite = false;
		comments = new ArrayList<Comment>();
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	public Rss getRss() {
		return rss;
	}
	public void setRss(Rss rss) {
		this.rss = rss;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@PrePersist
	@PreUpdate
	private void prePersiste() {
		soundex = SoundexUtil.soundex(title);
	}
}
