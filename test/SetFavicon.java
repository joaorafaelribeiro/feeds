import java.util.List;

import org.junit.Test;

import models.Rss;
import play.test.UnitTest;
import util.Crawler;
import util.exceptions.CrawlerException;

public class SetFavicon extends UnitTest {

	 @Test
    public void aVeryImportantThingToTest() throws CrawlerException {
		 List<Rss> rsses = Rss.findAll();
		 for (Rss rss : rsses) {
			Crawler aux = new Crawler(rss.getSite());
			rss.setIcon(aux.getIcon());
			rss.save();
		}
	 }
	
}
