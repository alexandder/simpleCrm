package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.list;
import views.html.users.details;
import play.mvc.Security;
import static play.mvc.Http.MultipartFormData;


@Security.Authenticated(Secured.class)
public class Users extends Controller{

	
	@Security.Authenticated(SecuredAdmin.class)
	public static Result showAll() {
		List<User> users = User.findAll();
    	return ok(list.render(users));
    }
	
	public static Result details() {
		User currentUser = User.findByEmail(session().get("email"));
		return ok(details.render(currentUser));
	}
	
	public static Result update() {
		MultipartFormData body = request().body().asMultipartFormData();
		User currentUser = User.findByEmail(session().get("email"));
		User user = currentUser;
		
		MultipartFormData.FilePart image = body.getFile("image");
		if (image != null) {
			String imageName = image.getFilename();
			File file = image.getFile();
			try {
			Files.move(Paths.get(file.getPath()), Paths.get("public/images/"+imageName), StandardCopyOption.ATOMIC_MOVE);
			user.image = "images/"+imageName;
			}
			catch (IOException e) {
				System.out.println(e);
				flash("error", "Błąd przy wczytywaniu zdjęcia");
				return ok(details.render(currentUser));
			}
		}
		Ebean.update(user);
		flash("success", "Pomyślnie dodano zdjęcie");
		return redirect(routes.Contacts.showAll());
	}
	
	public static Result getImage(String path) {
		return ok(new File(path));
	}
}
