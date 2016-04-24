package controllers;

import java.util.List;

import models.Feed;
import play.mvc.Controller;
import util.FeedFilter;

public class Favorites extends Controller{

	public static void index() {
		render();
	}
	public static void setFavorite(Long id) {
		Feed feed = Feed.findById(id);
		feed.setFavorite(!feed.getFavorite());
		feed.save();
	}
}
