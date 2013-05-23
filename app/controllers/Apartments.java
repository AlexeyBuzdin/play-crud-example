package controllers;

import models.Apartment;
import models.ApartmentType;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Apartments extends Controller {

    static Form<Apartment> form = Form.form(Apartment.class);

    public static Result create(Long hotelId) {
        return ok(views.html.apartment_new.render(hotelId, form));
    }

    public static Result open(Long id) {
        Apartment apartment = Apartment.get(id);

        return ok(views.html.apartment_update.render(apartment, form));
    }

    public static Result update(Long id) {
        if(id != null){
            Form<Apartment> filledForm = form.fill(Apartment.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);
            Apartment apartment = filledForm.get();

            String apartmentTypeId = Form.form().bindFromRequest().get("apartmentTypeId");
            ApartmentType apartmentType = ApartmentType.get(Long.valueOf(apartmentTypeId));

            apartment.setApartmentType(apartmentType);

            Apartment.update(apartment, id);
        }
        return redirect(routes.Hotels.all());
    }

    public static Result save(Long hotelId) {
        Form<Apartment> filledForm = form.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Apartment apartment = filledForm.get();
        Hotel hotel = Hotel.get(hotelId);
        apartment.setHotel(hotel);

        String apartmentTypeId = Form.form().bindFromRequest().get("apartmentTypeId");
        ApartmentType apartmentType = ApartmentType.get(Long.valueOf(apartmentTypeId));

        apartment.setApartmentType(apartmentType);

        Apartment.save(apartment);
        return redirect(routes.Hotels.open(hotelId));
    }

    public static Result delete(Long id) {
        Apartment.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form<Apartment> filledForm) {
        return null;
    }
}