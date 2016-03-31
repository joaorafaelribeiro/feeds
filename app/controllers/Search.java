package controllers;

import java.util.List;

import models.Category;
import models.Feed;
import play.mvc.Controller;
import play.mvc.With;
import util.FeedSerializer;
import util.SoundexUtil;
@With(Secure.class)
public class Search extends Controller{

	public static void index(String search) {
		render(search);
	}
	
	public static void search() {
		int page = 1;
		if(request.params.get("page") != null) 
			page = Integer.parseInt(request.params.get("page"));
		if("true".equals(request.params.get("count"))) {
			renderJSON(Feed.count(query()));
		} else {
			StringBuilder q = new StringBuilder(query());
			q.append(" order by date desc");
			List<Feed> feeds = Feed.find(q.toString()).fetch(page, 12);
			renderJSON(feeds,new FeedSerializer());
		}
	}
	
	
	
	private static String query() {
		String search = null;
		if(request.params.get("search") != null)
			search = request.params.get("search");
		
		Object[] words = SoundexUtil.soundexWords(search);
		StringBuilder q = new StringBuilder();
		q.append("1 = 1 ");
		for (Object string : words) {
			q.append(" and soundex like '%"+string.toString()+"%' ");
		}
		return q.toString();
	}
}
