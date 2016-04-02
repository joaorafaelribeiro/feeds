package util;

import java.util.ArrayList;
import java.util.List;
import util.SoundexUtil;


public class FeedFilter {

	private Object[] soundex;
	private Long rssId;

	public void setSearch(String search) {
		soundex = SoundexUtil.soundexWords(search);
	}

	public void setRssId(Long rssId) {
		this.rssId = rssId;
	}

	public String getQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append(" 1=1 ");
		if(rssId != null)
			sql.append(" and rss.id ="+rssId+" ");
		if(soundex != null)
			for(Object sound : soundex)
				sql.append(" and soundex like '%"+sound+"%' ");
		return sql.toString();
	}
}
