package controllers;

import models.Apartment;
import models.ApartmentType;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Hotels extends Controller {

    static Form<Hotel> form = Form.form(Hotel.class);

    public static Result all() {
        return ok(views.html.index.render(Hotel.all(), form));
    }

    public static Result create() {
        return ok(views.html.hotel_new.render(form));
    }

    public static Result open(Long id) {
        Hotel hotel = Hotel.get(id);

        // TODO: hack. Should be fixed in future versions
        for (Apartment apartment: hotel.getApartments()) {
            Long apartmentTypeId = apartment.getApartmentType().getId();
            apartment.setApartmentType(ApartmentType.get(apartmentTypeId));
        }

        return ok(views.html.hotel_update.render(hotel, form));
    }

    public static Result save() {
        Form<Hotel> filledForm = form.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Hotel.save(filledForm.get());
        return redirect(routes.Hotels.all());
    }

    public static Result update(Long id) {
        if(id != null){
            Form<Hotel> filledForm = form.fill(Hotel.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            Hotel.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.all());
    }

    public static Result delete(Long id) {
        Hotel.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form<Hotel> filledForm) {
        return badRequest(
                views.html.index.render(Hotel.all(), filledForm)
        );
    }
}