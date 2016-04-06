package util;

import java.util.ArrayList;
import java.util.List;

import play.mvc.Scope.Params;
import util.SoundexUtil;


public class FeedFilter {
	
	private int page;
	private Object[] soundex;
	private Long rssId;
	private Boolean favorite = null;

	public FeedFilter(Params params) {
		page = 1;
		if(params.get("page") != null) 
			page = Integer.parseInt(params.get("page"));
		if(params.get("search") != null) 
			soundex = SoundexUtil.soundexWords(params.get("search"));
		if(params.get("rssId") != null)
			this.rssId = (Long.parseLong(params.get("rssId")));
		if(params.get("favorite") != null)
			this.favorite = (Boolean.parseBoolean(params.get("favorite")));
	}

	public int getPage() {
		return page;
	}

	public String getQuery() {
		StringBuilder sql = new StringBuilder();
		sql.append(" 1=1 ");
		if(rssId != null)
			sql.append(" and rss.id ="+rssId+" ");
		if(soundex != null)
			for(Object sound : soundex)
				sql.append(" and soundex like '%"+sound+"%' ");
		if(favorite != null)
			sql.append(" and favorite = "+favorite);
		return sql.toString();
	}
}
