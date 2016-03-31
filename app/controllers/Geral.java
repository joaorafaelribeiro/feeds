package controllers;

import models.Config;
import models.Profile;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
@With(Secure.class)
public class Geral extends Controller{
	
	@Before
	static void checkAuthentification(){
		if(!Security.check(Profile.ADMINISTRADOR.toString())) {
			unauthorized();
		}
	}

    public static void config() {
    	Config config = (Config) Config.findAll().get(0);
        render(config);
    }
    
    public static void save(Config config) {
    	checkAuthenticity();
    	config.merge();
    	config.save();
    	flash.success("Configuração atualizada com sucesso!!!");
    	config();
    }
}
