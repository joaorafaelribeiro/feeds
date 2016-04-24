package util;

import java.util.Date;
import java.util.List;

import models.Feed;
import models.Rss;
import play.Logger;
import play.jobs.Every;
import play.jobs.Job;
import util.exceptions.ReaderRSSException;

@Every("10mn")
public class UpdateJobRSS extends Job<Rss>{

	public void doJob() {
		Logger.info("Atualizando RSS");
		List<Rss> rsses = Rss.find("TIMESTAMPDIFF (MINUTE,update,?) > 15",new Date()).fetch(5);
		for(Rss rss : rsses) {
			try {
				Rss aux = new ReaderRSS(rss.getLink()).getRss();
				for(Feed feed : aux.getFeeds()) {
					if(Feed.count("link = ? and rss.id = ?",feed.getLink(),rss.id) == 0) {
						feed.setRss(rss);
						feed.save();
					}
				}
				
			} catch (ReaderRSSException e) {
				Logger.error(e.getMessage());
			}
			rss.setUpdate(new Date());
			rss.save();
		}
		int qtd = Feed.em().createQuery("delete models.Feed where TIMESTAMPDIFF(DAY,date,:date) > 10 and favorite = false").setParameter("date", new Date()).executeUpdate();
		Logger.info("? Feeds deleted",qtd);
	}
}
