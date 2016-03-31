package util;



import java.io.IOException;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import util.exceptions.CrawlerException;

import org.jsoup.nodes.Attribute;


public class Crawler {

	private Document document ;
	
	public Crawler(String url) throws CrawlerException {
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			Logger.getGlobal().warning("Falha crawler url :"+url+" "+e.getMessage());
			throw new CrawlerException(e);
		}
	}
	
	public String getImage() throws CrawlerException {
		//1 - try extract image to meta tag
		for(Element meta : document.getElementsByTag("meta")) {
			for(Attribute attr : meta.attributes()) {
				if(attr.getValue().indexOf("og:image") >= 0 || attr.getValue().indexOf("image") == 0)
					return meta.attr("content");
			}
		}
		//2 - try extract img into tag p
		for(Element p : document.getElementsByTag("p")) {
			for(Element img : p.getElementsByTag("img")) {
				return img.attributes().get("src");
			}
		}
		//3 - try extract any img tag
		for(Element img : document.getElementsByTag("img")) {
			return img.attributes().get("src");
		}
		throw new CrawlerException("Não foi possível gerar uma imagem");
	}
	
}
