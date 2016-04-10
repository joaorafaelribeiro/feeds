package util;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import models.Rss;

public class RssJson extends TypeAdapter<Rss>{

	@Override
	public Rss read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter json, Rss rss) throws IOException {
		json.beginObject();
		json.name("id").value(rss.id);
		json.name("title").value(rss.getTitle());
		json.name("link").value(rss.getLink());
		json.name("date").value(rss.getDate().getTime());
		json.name("site").value(rss.getSite());
		json.name("icon").value(rss.getIcon());
		json.endObject();
	}

}
