package controllers;


import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;
import models.users.*;


/**
 * Created by evan_ on 10/03/2017.
 */
public class Secured extends Security.Authenticator{

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }
    @Override
    public Result onUnauthorized(Context ctx){
        ctx.flash().put("Error","Admin Login Required.");
        return redirect(controllers.routes.HomeController.login());

    }
}
