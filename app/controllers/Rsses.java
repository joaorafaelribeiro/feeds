package controllers;

import java.util.List;

import models.Category;
import models.Feed;
import models.Rss;
import play.mvc.Controller;
import play.mvc.Scope.Flash;
import play.mvc.With;
import util.ReaderRSS;
import util.FeedJson;
import util.exceptions.ReaderRSSException;


public class Rsses extends Controller{
	
	public static void index() {
		render();
	}
	
	public static void sources() {
		String page = request.params.get("page");
		String count = request.params.get("count");
		if(page == null) page = "1";
		if("true".equals(count)) {
			renderJSON(Rss.count());
		}else {
			List<Rss> rsses = Rss.find("order by title").fetch(Integer.parseInt(page),10);
			renderJSON(rsses);
		}
		
	}
	
	public static void list() {
		List<Rss> rsses = Rss.findAll();
		renderJSON(rsses);
	}
	
	
	public static void save(String rss) {
		checkAuthenticity();
		try {
			ReaderRSS aux = new ReaderRSS(rss);
			Rss newRss= aux.getRss();
			newRss.setLink(rss);
			newRss.save();
			flash.success("Novo RSS cadastrado com sucesso!!!");
			index();
		} catch (ReaderRSSException e) {
			flash.error("Não foi possível carregar o RSS");
			index();
		}
	}
	
	public static void edit(Long id) {
		Rss rss = Rss.findById(id);
		List<Category> categories = Category.find("order by name").fetch();
		render(rss,categories);
	}
	
	public static void update(Rss rss) {
		checkAuthenticity();
		rss.merge();
		rss.save();
		flash.success("RSS atualizado com sucesso!!!");
		index();
	}
	
	public static void delete(Long id) {
		Rss rss = Rss.findById(id);
		rss.delete();
		flash.success("RSS removido com sucesso!!!");
		index();
	}
}
