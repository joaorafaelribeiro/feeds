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
		
		FeedFilter filter = new FeedFilter(request.params);
		if("true".equals(request.params.get("count"))) {
			renderJSON(Feed.count(filter.getQuery()));
		} else {
			List<Feed> feeds = Feed.find(filter.getQuery()+" order by date desc, active desc").fetch(filter.getPage(), 12);
			for(Feed feed : feeds) {
				if(feed.getActive())
					feed.setActive(!feed.getActive());
					feed.save();
			}
			renderJSON(feeds, new FeedSerializer());
		}
	}
	
		
	public static void show(Long id) {
		Feed feed = Feed.findById(id);
		//render(feed);
		renderJSON(feed,new FeedSerializer());
	}
	


}
