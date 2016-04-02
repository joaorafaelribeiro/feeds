package controllers;

import java.util.List;

import models.Feed;
import play.mvc.Controller;
import util.Crawler;
import util.FeedFilter;
import util.FeedSerializer;
import util.exceptions.CrawlerException;

public class Feeds extends Controller {
	
	public static void index() {
		render();
	}
	
	public static void list() {
		int page = 1;
		FeedFilter filter = new FeedFilter();
		if(request.params.get("page") != null) 
			page = Integer.parseInt(request.params.get("page"));
		if(request.params.get("search") != null) 
			filter.setSearch(request.params.get("search"));
		if(request.params.get("rssId") != null)
			filter.setRssId(Long.parseLong(request.params.get("rssId")));
		if("true".equals(request.params.get("count"))) {
			renderJSON(Feed.count(filter.getQuery()));
		} else {
			List<Feed> feeds = Feed.find(filter.getQuery()+" order by date desc, active desc").fetch(page, 9);
			for(Feed feed : feeds) {
				if(feed.getActive())
					feed.setActive(!feed.getActive());
					feed.save();
			}
			renderJSON(feeds, new FeedSerializer());
		}
	}
	
	
	public static void favorites() {
		String page = request.params.get("page");
		if(page == null) page = "1";
		List<Feed> feeds = Feed.find("favorite = true order by date").fetch(Integer.parseInt(page), 9);
		Long total = Feed.count("favorite = true");
		render(feeds,total);
		render();
	}
	
	public static void show(Long id) {
		Feed feed = Feed.findById(id);
		//render(feed);
		renderJSON(feed,new FeedSerializer());
	}
	
	public static void setFavorite(Long id) {
		Feed feed = Feed.findById(id);
		feed.setFavorite(!feed.getFavorite());
		feed.save();
	}

}
