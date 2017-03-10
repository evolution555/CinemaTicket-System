package controllers;

import models.users.User;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by evan_ on 10/03/2017.
 */
public class AuthAdmin extends Action.Simple{
    public CompletionStage<Result> call(Http.Context ctx){

        String id = ctx.session().get("email");
        if(id != null){
            User u = User.getUserById(id);
            if("Admin".equals(u.getRole())){
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("Error","Admin Login Required.");
        return CompletableFuture.completedFuture(redirect(routes.HomeController.login()));
    }
}
