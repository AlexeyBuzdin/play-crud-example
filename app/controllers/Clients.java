package controllers;

import models.Client;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Clients extends Controller {

    static Form<Client> form = Form.form(Client.class);

    public static Result create() {
        return ok(views.html.client_new.render(form));
    }

    public static Result open(Long id) {
        Client hotel = Client.get(id);

        return ok(views.html.client_update.render(hotel, form));
    }

    public static Result save() {
        Form<Client> filledForm = form.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Client.save(filledForm.get());
        return redirect(routes.Hotels.all());
    }

    public static Result update(Long id) {
        if(id != null){
            Form<Client> filledForm = form.fill(Client.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            Client.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.all());
    }

    public static Result delete(Long id) {
        Client.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form<Client> filledForm) {
        return null;
    }
}