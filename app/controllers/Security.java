package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.User;
import play.Logger;
import util.CriptUtil;

public class Security extends Secure.Security{

	static boolean authenticate(String username, String password) {
		try {
			String aux = CriptUtil.criptografarSenha(password);
	        User user = User.find("name", username).first();
	        return user != null && user.getPassword().equals(aux);
		} catch (Exception e) {
			Logger.error("Fatal erro segurança "+e.getMessage());
		}
		return false;
		
    }
	
	static boolean check(String profile) {
		if(connected() == null) return false;
        User user = User.find("name", connected()).first();
        return user.getProfile().toString().equals(profile);
	}
	
}
