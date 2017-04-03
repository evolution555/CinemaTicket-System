package controllers;

import models.Booking;
import models.Payments;
import models.users.Login;
import models.users.User;
import play.api.Environment;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.booking;
import views.html.payment;
import views.html.signUp;

import javax.inject.Inject;

public class PaymentController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public PaymentController(Environment e, FormFactory f) {
        this.env = e;
        this.formFactory = f;
    }

    public Result payment() {
        Form<Payments> newPaymentForm = formFactory.form(Payments.class);
        User u = HomeController.getUserFromSession();
        String error = null;
        return ok(payment.render(newPaymentForm, u, env, error));
    }

    public Result paymentSubmit() {
        Form<Payments> newPaymentForm = formFactory.form(Payments.class).bindFromRequest();
        Form<Payments> errorForm = formFactory.form(Payments.class).bindFromRequest();
        if (newPaymentForm.hasErrors()) {
            return badRequest(payment.render(errorForm, HomeController.getUserFromSession(), env, "Error in form."));
        }
         Payments p = newPaymentForm.get();
         p.save();
        flash("success");
        return redirect(routes.HomeController.index());
    }
}
