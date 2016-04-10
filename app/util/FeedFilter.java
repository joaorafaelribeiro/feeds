package util;

import java.util.ArrayList;
import java.util.List;

import play.mvc.Scope.Params;
import util.SoundexUtil;


public class FeedFilter {
	
	private int page;
	private Object[] soundex;
	private Long rssId;

	public FeedFilter(Params params) {
		page = 1;
		if(params.get("page") != null) 
			page = Integer.parseInt(params.get("page"));
		if(params.get("search") != null) 
			soundex = SoundexUtil.soundexWords(params.get("search"));
		if(params.get("rssId") != null)
			this.rssId = (Long.parseLong(params.get("rssId")));
	}

	public int getPage() {
		return page;
	}

	public String getQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append(" 1=1 ");
		if(rssId != null) {
			if(rssId > 0)
				sql.append(" and rss.id ="+rssId+" ");
			if(rssId == -2)
				sql.append(" and date(date) = current_date() ");
			if(rssId == -3)
				sql.append(" and favorite = true ");
		}
			
		if(soundex != null)
			for(Object sound : soundex)
				sql.append(" and soundex like '%"+sound+"%' ");
		sql.append(" order by date desc ");
		return sql.toString();
	}
}
