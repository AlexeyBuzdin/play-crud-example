package controllers;

import models.Apartment;
import models.ApartmentProposal;
import models.ApartmentType;
import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.runtime.Nothing$;

public class ApartmentProposals extends Controller {

    static Form<ApartmentProposal> form = Form.form(ApartmentProposal.class);

    public static Result create(Long apartmentId) {
        return ok(views.html.apartment_proposal_new.render(apartmentId, form));
    }

    public static Result open(Long id) {
        ApartmentProposal apartmentProposal = ApartmentProposal.get(id);

        return ok(views.html.apartment_proposal_update.render(apartmentProposal, form));
    }

    public static Result update(Long id) {
//        if(id != null){
//            Form<ApartmentProposal> filledForm = form.fill(ApartmentProposal.find.byId(id)).bindFromRequest();
//            if(filledForm.hasErrors()) return badRequest(filledForm);
//            ApartmentProposal apartment = ApartmentProposal.get();
//
//
//            ApartmentProposal.update(apartment, id);
//        }
        return redirect(routes.Hotels.all());
    }

    public static Result save(Long hotelId) {
//        Form<ApartmentProposal> filledForm = form.bindFromRequest();
//        if(filledForm.hasErrors()) return badRequest(filledForm);
//
//        ApartmentProposal apartment = filledForm.get();
//        Hotel hotel = Hotel.get(hotelId);
//        apartment.setHotel(hotel);
//
//        String apartmentTypeId = Form.form().bindFromRequest().get("apartmentTypeId");
//        ApartmentType apartmentType = ApartmentType.get(Long.valueOf(apartmentTypeId));
//
//        apartment.setApartmentType(apartmentType);
//
//        ApartmentProposal.save(apartment);
        return redirect(routes.Hotels.open(hotelId));
    }

    public static Result delete(Long id) {
        ApartmentProposal.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form<Apartment> filledForm) {
        return null;
    }
}
