package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;
import util.CriptUtil;

@With(Secure.class)
public class Profiles extends Controller{

	public static void index() {
		User user = User.find("name = ?", Security.connected()).first();
		String mailHash = CriptUtil.md5Hex(user.getEmail());
		render(user,mailHash);
	}
	
}
