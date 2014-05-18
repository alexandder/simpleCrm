package controllers;

import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

public class SecuredAdmin extends Security.Authenticator {
	
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("isAdmin");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.login());
	}
	
}