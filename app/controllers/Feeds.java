package controllers;

import java.util.Date;
import java.util.List;

import models.Feed;
import play.mvc.Controller;
import util.Crawler;
import util.FeedFilter;
import util.FeedSerializer;
import util.exceptions.CrawlerException;

public class Feeds extends Controller {
	
	private static int QTD_PER_PAGE = 16;
	
	public static void index() {
		render();
	}
	
	public static void show(Long id) {
		Feed feed = Feed.findById(id);
		renderJSON(feed,new FeedSerializer());
	}

	public static void list() {
		FeedFilter filter = new FeedFilter(request.params);
		renderJSON(Feed.find(filter.getQuery()).fetch(filter.getPage(), QTD_PER_PAGE));
	}
	
//	public static void today() {
//		FeedFilter filter = new FeedFilter(request.params);
//		renderJSON(Feed.find(filter.getQuery()).fetch(filter.getPage(), QTD_PER_PAGE));
//	}
//
//	public static void all() {
//		FeedFilter filter = new FeedFilter(request.params);
//		renderJSON(Feed.find(filter.getQuery()).fetch(filter.getPage(), QTD_PER_PAGE));
//	}
//
//	public static void favorites() {
//		FeedFilter filter = new FeedFilter(request.params);
//		renderJSON(Feed.find(filter.getQuery()).fetch(filter.getPage(), QTD_PER_PAGE));
//	}
	
	public static void count() {
		FeedFilter filter = new FeedFilter(request.params);
		renderJSON(Feed.count(filter.getQuery()));
	}

}
