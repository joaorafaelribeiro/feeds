package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import models.Profile;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import util.CriptUtil;
@With(Secure.class)
public class Users extends Controller{
	
	@Before
	static void checkAuthentification(){
		if(!Security.check(Profile.ADMINISTRADOR.toString())) {
			unauthorized();
		}
	}

	public static void index() {
		List<User> users = User.find("order by name").fetch();
		render(users);
	}
	
	public static void form() {
		render();
	}
	
	public static void save(User user) throws NoSuchAlgorithmException {
		checkAuthenticity();
		user.setPassword(new String(CriptUtil.criptografarSenha(user.getPassword())));
		user.save();
		flash.success("Usuário cadastrado com sucesso");
		index();
	}
	
	public static void update(User user) throws NoSuchAlgorithmException {
		checkAuthenticity();
		user.save();
		flash.success("Usuário atualizado com sucesso");
		index();
	}
	
	public static void edit(Long id) {
		User user = User.findById(id);
		Profile[] profiles = Profile.values();
		render(user,profiles);
	}
	
	public static void delete(Long id) {
		User user = User.findById(id);
		user.delete();
		flash.success("Usuário deletado com sucesso");
		index();
	}
}
