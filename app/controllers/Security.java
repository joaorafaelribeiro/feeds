package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.User;
import play.Logger;
import play.cache.Cache;
import util.CriptUtil;

public class Security extends Secure.Security{

	static boolean authenticate(String username, String password) {
		try {
			String aux = CriptUtil.criptografarSenha(password);
	        User user = User.find("name", username).first();
	        if( user != null && user.getPassword().equals(aux)){
	        	Cache.set("profile", user.getProfile().toString());
	        	return true;
	        } else
	        	return false;
	        	
		} catch (Exception e) {
			Logger.error("Fatal erro segurança "+e.getMessage());
		}
		return false;
		
    }
	
	static boolean check(String profile) {
		if(connected() == null || Cache.get("profile") == null) return false;
        return Cache.get("profile").equals(profile);
	}
	
}
