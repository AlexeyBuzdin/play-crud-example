package controllers;

import models.Apartment;
import models.ApartmentProposal;
import models.ApartmentType;
import models.Hotel;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.runtime.Nothing$;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static play.data.Form.*;

public class ApartmentProposals extends Controller {

    public static Result create(Long apartmentId) {
        return ok(views.html.apartment_proposal_new.render(apartmentId));
    }

    public static Result open(Long id) {
        ApartmentProposal apartmentProposal = ApartmentProposal.get(id);

        return ok(views.html.apartment_proposal_update.render(apartmentProposal));
    }

    public static Result update(Long id) {
//        TODO: add ApartmentProposal update
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

    public static Result save(Long apartmentId) {
        DynamicForm dynamicForm = form().bindFromRequest();

        String dateFrom = dynamicForm.get("dateFrom");
        String dateTo = dynamicForm.get("dateTo");
        String price = dynamicForm.get("price");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            ApartmentProposal proposal = new ApartmentProposal();
            proposal.setDateFrom(dateFormat.parse(dateFrom));
            proposal.setDateTo(dateFormat.parse(dateTo));
            proposal.setPrice(new Long(price.replace(".", "")));
            proposal.setApartment(Apartment.get(apartmentId));
            ApartmentProposal.save(proposal);
        } catch (ParseException e) {


        }

        return redirect(routes.Apartments.open(apartmentId));
    }

    public static Result delete(Long id) {
        ApartmentProposal.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form filledForm) {
        return null;
    }
}
