package util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import models.Feed;
import models.Rss;
import play.Logger;
import play.Play;
import util.exceptions.CrawlerException;
import util.exceptions.ReaderRSSException;

public class ReaderRSS {
	private SyndFeed feed;
	
	public ReaderRSS(String url) throws ReaderRSSException  {
		 SyndFeedInput input = new SyndFeedInput();
		 System.setProperty("http.agent", Play.configuration.getProperty("application.user.agent"));
		try {
			feed = input.build(new XmlReader(new URL(url)));
		} catch (Exception e) {
			throw new ReaderRSSException(e);
		} 
	}
	
	public Rss getRss() {
		Rss rss = new Rss();
		rss.setTitle(feed.getTitle());
		rss.setSite(feed.getLink());
		for(SyndEntry enty : (List<SyndEntry>) feed.getEntries()) {
			Feed f = new Feed();
			f.setTitle(enty.getTitle());
 			f.setDate(getDate(enty));
			if(enty.getDescription() != null)
			f.setDescription(enty.getDescription().getValue().trim());
			f.setLink(enty.getLink());
			f.setRss(rss);
			try {
				Crawler crawler = new Crawler(f.getLink());
				f.setImg(crawler.getImage());
			} catch (CrawlerException e) {
				f.setImg("/public/images/no_image.png");
			}
			rss.getFeeds().add(f);
		}
		
		return rss;
	}
	
	private Date getDate(SyndEntry enty) {
		if(enty.getPublishedDate() != null) {
			return enty.getPublishedDate();
		}
		if(enty.getUpdatedDate() != null) {
			return enty.getUpdatedDate();
		}
		return new Date();
	}
}
