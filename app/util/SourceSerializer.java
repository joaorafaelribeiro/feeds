package util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.Feed;
import models.Rss;

public class SourceSerializer implements JsonSerializer<Rss> {

	@Override
	public JsonElement serialize(Rss rss, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.add("title", new JsonPrimitive(rss.getTitle()));
		json.add("id", new JsonPrimitive(rss.id));
		json.add("date", new JsonPrimitive(rss.getDate().toString()));
		json.add("link", new JsonPrimitive(rss.getLink()));
		if(rss.getCategory() != null) {
			JsonObject cat = new JsonObject();
			cat.add("id", new JsonPrimitive(rss.getCategory().getId()));
			cat.add("name", new JsonPrimitive(rss.getCategory().getName()));
			json.add("category", cat);
		}else {
			json.add("category", null);
		}
		
		return json;
	}

	
}
