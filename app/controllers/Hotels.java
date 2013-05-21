package controllers;

import models.Apartment;
import models.ApartmentType;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Hotels extends Controller {

    static Form<Hotel> hotelForm = Form.form(Hotel.class);

    public static Result allHotel() {
        return ok(views.html.index.render(Hotel.all(), hotelForm));
    }

    public static Result newHotel() {
        return ok(views.html.hotel_new.render(hotelForm));
    }

    public static Result openHotel(Long id) {
        Hotel hotel = Hotel.get(id);

        // TODO: hack. Should be fixed in future versions
        for (Apartment apartment: hotel.getApartments()) {
            Long apartmentTypeId = apartment.getApartmentType().getId();
            apartment.setApartmentType(ApartmentType.get(apartmentTypeId));
        }

        return ok(views.html.hotel_update.render(hotel, hotelForm));
    }

    public static Result saveHotel() {
        Form<Hotel> filledForm = hotelForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Hotel.save(filledForm.get());
        return redirect(routes.Hotels.allHotel());
    }

    public static Result updateHotel(Long id) {
        if(id != null){
            Form<Hotel> filledForm = hotelForm.fill(Hotel.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            Hotel.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.allHotel());
    }

    public static Result deleteHotel(Long id) {
        Hotel.delete(id);
        return redirect(routes.Hotels.allHotel());
    }

    private static Status badRequest(Form<Hotel> filledForm) {
        return badRequest(
                views.html.index.render(Hotel.all(), filledForm)
        );
    }
}