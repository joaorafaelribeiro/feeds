package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.language.RefinedSoundex;

import sun.misc.BASE64Encoder;

public class CriptUtil {
	public static String criptografarSenha(String senha) throws NoSuchAlgorithmException {
	    try {
	               MessageDigest digest = MessageDigest.getInstance("MD5");
	               digest.update(senha.getBytes());
	               BASE64Encoder encoder = new BASE64Encoder ();
	               return encoder.encode (digest.digest ());
	          } catch (NoSuchAlgorithmException ns) {
	               ns.printStackTrace ();
	               return senha;
	          }
	}
	
	public static String md5Hex (String message) {
	      try {
	      MessageDigest md = 
	          MessageDigest.getInstance("MD5");
	      return Hex.encodeHexString(md.digest(message.getBytes("CP1252")));
	      } catch (NoSuchAlgorithmException e) {
	      } catch (UnsupportedEncodingException e) {
	      }
	      return null;
	}
	
}
