package controllers;

import models.Apartment;
import models.ApartmentType;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.runtime.Nothing$;

public class Apartments extends Controller {

    static Form<Apartment> apartmentForm = Form.form(Apartment.class);

//    public static Result newApartment() {
//        return ok(views.html.apartment.render(null, apartmentForm));
//    }
//
//    public static Result openApartment(Long id) {
//        Apartment apartment = Apartment.get(id);
//
//        return ok(views.html.apartment.render(apartment, apartmentForm));
//    }

    public static Result saveApartment() {
        Form<Apartment> filledForm = apartmentForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Apartment.save(filledForm.get());
        return redirect(routes.Hotels.allHotel());
    }

    public static Result deleteApartment(Long id) {
        Apartment.delete(id);
        return redirect(routes.Hotels.allHotel());
    }

    private static Status badRequest(Form<Apartment> filledForm) {
        return null;
    }
}