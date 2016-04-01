package controllers;

import play.*;
import play.mvc.*;
import util.CriptUtil;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void register() {
		render();
	}
    
    public static void save(User user) throws Throwable {
    	checkAuthenticity();
    	user.setPassword(CriptUtil.criptografarSenha(user.getPassword()));
    	user.save();
		flash.success("usuário cadastrado");
    	Secure.login();
	}
}