package controllers;

import models.Apartment;
import models.ApartmentType;
import models.Client;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Clients extends Controller {

    static Form<Client> clientForm = Form.form(Client.class);

    public static Result newClient() {
        return ok(views.html.client_new.render(clientForm));
    }

    public static Result openClient(Long id) {
        Client hotel = Client.get(id);

        return ok(views.html.client_update.render(hotel, clientForm));
    }

    public static Result saveClient() {
        Form<Client> filledForm = clientForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Client.save(filledForm.get());
        return redirect(routes.Hotels.allHotel());
    }

    public static Result updateClient(Long id) {
        if(id != null){
            Form<Client> filledForm = clientForm.fill(Client.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            Client.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.allHotel());
    }

    public static Result deleteClient(Long id) {
        Client.delete(id);
        return redirect(routes.Hotels.allHotel());
    }

    private static Status badRequest(Form<Client> filledForm) {
        return null;
    }
}