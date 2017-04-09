package controllers;

import controllers.*;
import controllers.routes;
import play.api.Environment;
import play.mvc.*;

import sun.rmi.runtime.Log;
import views.html.*;
import play.data.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;

import models.users.*;
import models.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public HomeController(FormFactory f, Environment e) {
        this.formFactory = f;
        this.env = e;
    }

    public Result index() {
        User u = getUserFromSession();
        List<Film> allFilms = Film.findAll();
        List<carousel> allCarousel = carousel.findAll();
        return ok(index.render(u, allFilms, env, allCarousel));
    }


    public Result film() {
        User u = getUserFromSession();
        Film f = null;
        List<Showing> s = null;
        return ok(film.render(u, f, env, s));
    }

    public Result viewMovie(String title) {
        Film f = Film.find.byId(title);
        List<Showing> showingsList = Showing.findMovieShowings(title);
        return ok(film.render(getUserFromSession(), f, env, showingsList));
    }

    public Result booking(String title, String sId, String time) {
        Film f = Film.find.byId(title);
        Showing s = Showing.find.byId(sId);
        Booking b = new Booking();
        DynamicForm newBookingForm = formFactory.form().bindFromRequest();
        return ok(booking.render(b, newBookingForm, getUserFromSession(), f, env, s, time, null));
    }

    public Result bookingSubmit() {
        DynamicForm newBookingForm = formFactory.form().bindFromRequest();
        Film f = null;
        Showing s = null;
        String time = null;
        Booking bk = null;
        String error = null;
        /*
        if (newBookingForm.hasErrors()) {
            return badRequest(booking.render(bk, newBookingForm, getUserFromSession(), f, env, s, time, "Error in form."));
        }*/
        //Adding Booking to database
        int qty = Integer.parseInt(newBookingForm.get("qty"));
        if (qty == 0) {
            flash("error", " Invalid amount selected.");
            List<Film> allFilms = Film.findAll();
            List<carousel> allCarousel = carousel.findAll();
            return ok(index.render(getUserFromSession(), allFilms, env, allCarousel));
        }
        String title = newBookingForm.get("title");
        String timeIn = newBookingForm.get("time");
        String date = newBookingForm.get("date");

        Booking b = new Booking(qty, timeIn, date, title);
        DynamicForm newPaymentForm = formFactory.form();
        b.save();
        flash("success");
        return ok(payment.render(b, newPaymentForm, getUserFromSession(), env, error)); // change to payments
    }

    public Result signUp() {
        Form<User> adduserForm = formFactory.form(User.class);
        return ok(signUp.render(adduserForm, null));
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm));
    }

    public Result addUserSubmit() {
        DynamicForm newUserForm = formFactory.form().bindFromRequest();
        Form errorForm = formFactory.form().bindFromRequest();
        if (newUserForm.hasErrors()) {
            return badRequest(signUp.render(errorForm, "Error with form."));
    }
        if (newUserForm.get("email").equals("") || newUserForm.get("name").equals("")) {
            return badRequest(signUp.render(errorForm, "Please enter an email and a name."));
        }
        if (newUserForm.get("role").equals("select")) {
            return badRequest(signUp.render(errorForm, "Please enter a role."));
        }
        if (!newUserForm.get("password").equals(newUserForm.get("passwordConfirm"))) {
            return badRequest(signUp.render(errorForm, "Passwords do not match."));
        }
        if (newUserForm.get("password").length() < 6) {
            return badRequest(signUp.render(errorForm, "Password must be at least six characters."));
        }
        List<User> allusers = User.findAll();
        for (User a : allusers) {
            if (a.getEmail().equals(newUserForm.get("email"))) {
                return badRequest(signUp.render(errorForm, "Email already exists in system."));
            }
        }
        User.create(newUserForm.get("email"), newUserForm.get("name"), newUserForm.get("role"), newUserForm.get("password"));
        flash("success", "Your account has been successfully created. Please sign in with your details.");
        return redirect(controllers.routes.HomeController.index());
    }

    public static User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }

}
