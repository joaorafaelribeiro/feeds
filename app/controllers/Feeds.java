package controllers;

import java.util.Date;
import java.util.List;

import models.Feed;
import models.Resume;
import models.Rss;
import play.mvc.Controller;
import util.Crawler;
import util.FeedFilter;
import util.exceptions.CrawlerException;

public class Feeds extends Controller {
	
	private static int QTD_PER_PAGE = 200;
	
	public static void index() {
		List<Resume> rsses = Resume.find("order by title").fetch();
		render(rsses);
	}
	
	public static void show(Long id) {
		Feed feed = Feed.findById(id);
		renderJSON(feed);
	}

	public static void list() {
		FeedFilter filter = new FeedFilter(request.params);
		renderJSON(Feed.find(filter.getQuery()).fetch(filter.getPage(), QTD_PER_PAGE));
	}
	
	public static void countToday() {
		renderJSON(Feed.count("date(date) = current_date()"));
	}

	public static void countAll() {
		renderJSON(Feed.count());
	}
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
