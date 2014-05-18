package controllers;

import static play.data.Form.form;

import com.avaje.ebean.Ebean;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;
import views.html.contacts.details;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.login());
    }
    
    public static Result login() {
    	return ok(login.render(form(LoginData.class)));
    }
    
    public static Result register() {
    	return ok(register.render(form(LoginData.class)));
    }
    
    public static Result addUser() {
    	Form<LoginData> registerForm = form(LoginData.class).bindFromRequest();
    	String email = registerForm.get().email;
    	String password = registerForm.get().password;
    	String password2 = registerForm.get().password2;
    	if (!password.equals(password2)) {
    		flash("error", "Hasła nie zgadzają się. Proszę poprawić formularz.");
    		return badRequest(register.render(form(LoginData.class)));
    	}
    	if (email == null || email.length() == 0) {
    		flash("error", "Proszę podać email.");
    		return badRequest(register.render(form(LoginData.class)));
    	}
    	if (password == null || password.length() == 0) {
    		flash("error", "Proszę podać hasło.");
    		return badRequest(register.render(form(LoginData.class)));
    	}
    	User newUser = new User(email, password);
    	Ebean.save(newUser);
    	flash("success", "Rejestracja powiodła się. Można się zalogować.");
    	return redirect(routes.Application.login());
    }
    
    public static Result authenticate() {
    	Form<LoginData> loginForm = form(LoginData.class).bindFromRequest();
    	String email = loginForm.get().email;
    	String password = loginForm.get().password;
    	User user = User.authenticate(email, password);
    	if (user == null) {
    		return forbidden("Niepoprawne dane");
    	}
    	session().clear();
    	session("email", email);
    	if (user.isAdmin) {
    		session("isAdmin", "true");
    	}
    	
    	return redirect(routes.Contacts.showAll());
    }
    
    public static Result logout() {
    	session().clear();
    	return redirect(routes.Application.login());
    }
    
    public static class LoginData {
    	
    	public String email;
    	public String password;
    	public String password2;
    	
    }

}
