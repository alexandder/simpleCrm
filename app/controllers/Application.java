package controllers;

import static play.data.Form.form;
import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.login());
    }
    
    public static Result login() {
    	return ok(login.render(form(LoginData.class)));
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
    
    public static class LoginData {
    	
    	public String email;
    	public String password;
    	
    }

}
