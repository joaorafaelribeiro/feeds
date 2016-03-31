package controllers;

import models.Comment;
import models.Feed;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Comments extends Controller{
	
	public static void save(Comment comment) {
		comment.setFeed((Feed) Feed.findById(comment.getFeed().id));
		comment.setUser((User) User.find("name", Security.connected()).first());
		comment.save();
		Feeds.show(comment.getFeed().id);
	}
}
