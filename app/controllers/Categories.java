package controllers;

import java.util.List;

import models.Category;
import models.Profile;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Categories extends Controller{

	@Before
	static void checkAuthentification(){
		if(!Security.check(Profile.ADMINISTRADOR.toString())) {
			unauthorized();
		}
	}

	public static void index() {
		render();
	}
	
	public static void list() {
		if("true".equals(request.params.get("count"))) {
			renderJSON(Category.count());
		} else {
			int page = 1;
			if(request.params.get("page")!=null)
				page = Integer.parseInt(request.params.get("page"));
			List<Category> categories = Category.find("order by name").fetch(page,10);
			renderJSON(categories);
		}
	}
	
	public static void edit(Long id) {
		Category category = Category.findById(id);
		render(category);
	}
	public static void save(Category category) {
		checkAuthenticity();
		category.save();
		flash.success("Opercação realizada com sucesso");
		index();
	}
	public static void delete(Long id) {
		Category category = Category.findById(id);
		category.delete();
		flash.success("Opercação realizada com sucesso");
		index();
	}
}
