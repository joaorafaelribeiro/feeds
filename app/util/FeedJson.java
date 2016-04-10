package util;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import models.Feed;
import models.Rss;

public class FeedJson extends TypeAdapter<Feed>{

	@Override
	public Feed read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(JsonWriter json, Feed feed) throws IOException {
		json.beginObject();
		json.name("id").value(feed.id);
		json.name("title").value(feed.getTitle());
		json.name("date").value(feed.getDate().getTime());
		json.name("img").value(feed.getImg());
		json.name("favorite").value(feed.getFavorite());
		json.name("active").value(feed.getActive());
		json.name("description").value(feed.getDescription());
		json.name("link").value(feed.getLink());
		JsonWriter rss = json.name("rss").beginObject();
		rss.name("id").value(feed.getRss().id);
		rss.name("title").value(feed.getRss().getTitle());
		rss.name("icon").value(feed.getRss().getIcon());
		rss.endObject();
		json.endObject();
	}


	
}
