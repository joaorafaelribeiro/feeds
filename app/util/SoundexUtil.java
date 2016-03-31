package util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.language.RefinedSoundex;

public class SoundexUtil {

	public static String soundex(String title) {
		RefinedSoundex r = new RefinedSoundex(RefinedSoundex.US_ENGLISH_MAPPING_STRING);
		String aux = normalize(title);
		StringBuilder soundex = new StringBuilder();
		for(String word : aux.split(" ")) {
			soundex.append(r.soundex(word));
		}
 		return soundex.toString();
	}
	
	public static Object[] soundexWords(String title) {
		RefinedSoundex r = new RefinedSoundex(RefinedSoundex.US_ENGLISH_MAPPING_STRING);
		String aux = normalize(title);
		List<String> words = new ArrayList<String>();
		for(String word : aux.split(" ")) {
			words.add(r.soundex(word));
		}
		return (Object[]) words.toArray();
	}
	
	private static String normalize(String title) {
		String aux = Normalizer.normalize(title, Normalizer.Form.NFD);
		return aux.replaceAll("[^\\p{ASCII}]", "");
	}

}
