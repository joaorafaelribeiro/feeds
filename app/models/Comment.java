package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity(name="comments")
public class Comment extends Model{
	private String comment;
	@Column(name="comment_create")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="feed_id")
	private Feed feed;
	
	public Comment() {
		create = new Date();
		feed = new Feed();
	}
	
	
	public Feed getFeed() {
		return feed;
	}


	public void setFeed(Feed feed) {
		this.feed = feed;
	}


	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
