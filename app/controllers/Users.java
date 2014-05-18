package controllers;

import java.util.List;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.list;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Users extends Controller{

	@Security.Authenticated(SecuredAdmin.class)
	public static Result showAll() {
		List<User> users = User.findAll();
    	return ok(list.render(users));
    }
	
	public static Result details() {
		return TODO;
	}
}
