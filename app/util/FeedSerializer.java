package util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Feed;

public class FeedSerializer implements JsonSerializer<Feed>{

	@Override
	public JsonElement serialize(Feed feed, Type arg1, JsonSerializationContext arg2) {
		
		JsonObject json = new JsonObject();
		json.add("id", new JsonPrimitive(feed.id));
		json.add("title", new JsonPrimitive(feed.getTitle()));
		json.add("link", new JsonPrimitive(feed.getLink()));
		json.add("favorite", new JsonPrimitive(feed.getFavorite()));
		if(feed.getImg() != null)
			json.add("img", new JsonPrimitive(feed.getImg()));
		else
			json.add("img", null);
		if(feed.getDescription() != null)
			json.add("description", new JsonPrimitive(feed.getDescription()));
		else
			json.add("description", null);
		json.add("date", new JsonPrimitive(new SimpleDateFormat("dd/MM/yyyy").format(feed.getDate())));
		JsonObject json2 = new JsonObject();
		json2.add("id", new JsonPrimitive(feed.getRss().id));
		json2.add("title", new JsonPrimitive(feed.getRss().getTitle()));
		json2.add("link", new JsonPrimitive(feed.getRss().getLink()));
		json.add("rss", json2);
		return json;
	}

}
