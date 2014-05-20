package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {
	
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("email");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		play.mvc.Controller.flash("message", "Dostęp zabroniony. Proszę się zalogować.");
		return redirect(routes.Application.login());
	}
	
}
