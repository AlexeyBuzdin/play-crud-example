package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ApartmentHistories extends Controller {

    public static Result all() {
        List<ApartmentHistory> all = ApartmentHistory.all();
        // TODO: Dirty hack. Ebean doesn't select oneToMany dependencies by default
        for(ApartmentHistory apartmentHistory: all){
            apartmentHistory.setClient(Client.get(apartmentHistory.getClient().getId()));
            ApartmentProposal proposal = ApartmentProposal.get(apartmentHistory.getProposal().getId());
            proposal.setApartment(Apartment.get(proposal.getApartment().getId()));
            apartmentHistory.setProposal(proposal);
        }
        return ok(views.html.apartment_history.render(all));
    }

}
