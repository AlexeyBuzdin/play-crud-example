package controllers;

import models.Apartment;
import models.ApartmentProposal;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static play.data.Form.*;

public class ApartmentProposals extends Controller {

    public static Result create(Long apartmentId) {
        return ok(views.html.apartment_proposal_new.render(apartmentId));
    }

    public static Result open(Long id, Long aId) {
        ApartmentProposal apartmentProposal = ApartmentProposal.get(id);

        return ok(views.html.apartment_proposal_update.render(apartmentProposal, aId));
    }

    public static Result update(Long id, Long aId) {
//        TODO: add ApartmentProposal update
        if(id != null){
            ApartmentProposal apartmentProposal = ApartmentProposal.get(id);
            if(apartmentProposal != null){
                DynamicForm dynamicForm = form().bindFromRequest();
                ApartmentProposal apartmentProposalNew = getApartment(aId, dynamicForm);
                apartmentProposalNew.update(id);
            }
        }
        return redirect(routes.Apartments.open(aId));
    }

    public static Result save(Long apartmentId) {
        DynamicForm dynamicForm = form().bindFromRequest();

        ApartmentProposal apartmentProposal = getApartment(apartmentId, dynamicForm);
        ApartmentProposal.save(apartmentProposal);

        return redirect(routes.Apartments.open(apartmentId));
    }

    private static ApartmentProposal getApartment(Long apartmentId, DynamicForm dynamicForm) {
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
            return proposal;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Result delete(Long id, Long aId) {
        ApartmentProposal.delete(id);
        return redirect(routes.Apartments.open(aId));
    }

    private static Status badRequest(Form filledForm) {
        return null;
    }
}
