package controllers.relatorios;

import java.util.List;

import models.Feed;
import play.mvc.Controller;

public class RelatorioFeeds extends Controller{

	public static void index() {
		render();
	}
	
	public static void listar() {
		List<Feed> feeds = Feed.findAll();
		renderJSON(feeds);
	}
}
