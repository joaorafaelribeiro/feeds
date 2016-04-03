package util;



import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import play.Play;
import util.exceptions.CrawlerException;

import org.jsoup.nodes.Attribute;


public class Crawler {

	private Document document ;
	
	public Crawler(String url) throws CrawlerException {
		try {
			System.setProperty("http.agent", Play.configuration.getProperty("application.user.agent"));
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			Logger.getGlobal().warning("Falha crawler url :"+url+" "+e.getMessage());
			throw new CrawlerException(e);
		}
	}
	
	public String getImage() throws CrawlerException {
		//1 - try extract image to meta tag
		//org.jsoup.select.Elements metas = document.getElementsByTag("meta");
		org.jsoup.select.Elements metas = null; 
		metas = document.select("meta[property=og:image]");
		for(Element meta : metas) {
			return meta.attr("content");
		}
		metas = document.select("meta[property=image]");
		for(Element meta : metas) {
			return meta.attr("content");
		}
		//2 - try extract img into tag p
		metas = document.select("p > img");
		for(Element p : metas) {
				return p.attributes().get("src");
		}
		//3 - try extract any img tag
		metas = document.select("img");
		for(Element p : metas) {
				return p.attributes().get("src");
		}
		throw new CrawlerException("Não foi possível gerar uma imagem");
	}
	
	public String getIcon() throws CrawlerException {
		
		org.jsoup.select.Elements metas = null;
		metas = document.select("link[rel^=shortcut]");
		for(Element p : metas) {
			return p.attributes().get("href");
		}
		metas = document.select("meta[property=og:image]");
		for(Element meta : metas) {
			return meta.attr("content");
		}
		throw new CrawlerException("Não foi possível gerar uma imagem");
	}
	
}
