package controllers;

import models.*;
import play.data.DynamicForm;
import play.mvc.Result;
import views.html.reserve;
import views.html.reserve_apartment_proposal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static play.data.Form.form;
import static play.mvc.Results.ok;

public class Reserve {

    public static Result reserve() {
        return ok(views.html.reserve.render(Hotel.all()));
    }

    public static Result reserveHotel(Long hotelId) {
        List<ApartmentType> apartmentTypes = ApartmentType.availableFor(hotelId);
        return ok(views.html.reserve_hotel.render(Hotel.get(hotelId), apartmentTypes));
    }

    public static Result reserveApartmentType(Long hotelId, Long apartmentTypeId) {
        List<ApartmentProposal> apartmentProposals = ApartmentProposal.availableFor(hotelId, apartmentTypeId);
        return ok(views.html.reserve_apartment_type.render(ApartmentType.get(apartmentTypeId), apartmentProposals));
    }

    public static Result reserveProposal(long proposalId) {
        ApartmentProposal apartmentProposal = ApartmentProposal.get(proposalId);

        // TODO: Dirty hack because of ebean doesn't load dependencies by default
        Apartment apartment = Apartment.get(apartmentProposal.apartment.id);
        apartmentProposal.setApartment(apartment);

        ApartmentType apartmentType = ApartmentType.get(apartment.apartmentType.id);
        apartment.setApartmentType(apartmentType);

        return ok(reserve_apartment_proposal.render(apartmentProposal, false));
    }

    public static Result reserveProposalByClient(long proposalId) {
        DynamicForm dynamicForm = form().bindFromRequest();

        String name = dynamicForm.get("name");
        String surname = dynamicForm.get("surname");
        String email = dynamicForm.get("email");

        Client client = new Client(name, surname, email);
        client = Client.saveIfNotPresent(client);

        ApartmentProposal apartmentProposal = ApartmentProposal.get(proposalId);

        // TODO: Dirty hack because of ebean doesn't load dependencies by default
        Apartment apartment = Apartment.get(apartmentProposal.apartment.id);
        apartmentProposal.setApartment(apartment);

        ApartmentType apartmentType = ApartmentType.get(apartment.apartmentType.id);
        apartment.setApartmentType(apartmentType);


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            Date bookedToDate = dateFormat.parse(dynamicForm.get("bookedTo"));
            Date bookedFromDate = dateFormat.parse(dynamicForm.get("bookedFrom"));

            if(roomAlreadyReserved(apartmentProposal, bookedFromDate, bookedToDate)){
                return ok(reserve_apartment_proposal.render(apartmentProposal, true));
            }

            ApartmentHistory apartmentHistory = new ApartmentHistory(bookedFromDate,
                    bookedToDate, apartmentProposal, client);
            ApartmentHistory.save(apartmentHistory);
        } catch (ParseException e) {

        }

        return ok(reserve.render(Hotel.all()));
    }

    private static boolean roomAlreadyReserved(ApartmentProposal apartmentProposal, Date bookedFromDate, Date bookedToDate) {
        // TODO: Should look
//        select * from APARTMENT_HISTORY
//        where APARTMENT_PROPOSAL_ID in (?, ?, ?)
//        and BOOKED_FROM between ? and ?
//        and BOOKED_TO between '? and '?'
//        and BOOKED_FROM > '?'
//        and BOOKED_TO > '?'

        Long apartmentId = apartmentProposal.getApartment().getId();
        List<ApartmentProposal> apartmentProposals = ApartmentProposal.find.where().eq("APARTMENT_ID", apartmentId).findList();
        List<Long> extractedIds = extractId(apartmentProposals);

        List<ApartmentHistory> conflicts = ApartmentHistory.find.where().
                in("APARTMENT_PROPOSAL_ID", extractedIds).findList();

//        between("BOOKED_FROM", bookedFromDate, bookedToDate).
//                between("BOOKED_TO", bookedFromDate, bookedToDate).
//                gt("BOOKED_FROM", bookedFromDate).
//                gt("BOOKED_TO", bookedToDate)

        Iterator<ApartmentHistory> iterator = conflicts.iterator();
        while (iterator.hasNext()){
            ApartmentHistory next = iterator.next();
            if(next.bookedFrom.after(bookedFromDate) && next.bookedTo.before(bookedFromDate) ||
               next.bookedFrom.after(bookedToDate) && next.bookedTo.before(bookedToDate) ||
               bookedFromDate.after(next.bookedFrom) && bookedFromDate.before(next.bookedFrom) ||
               bookedToDate.after(next.bookedTo) && bookedToDate.before(next.bookedTo) ){

               iterator.remove();

            }
        }

        return conflicts.size() != 0;
    }

    private static List<Long> extractId(List<ApartmentProposal> apartmentProposals) {
        List<Long> ids = new ArrayList<Long>();
        for(ApartmentProposal proposal: apartmentProposals){
            ids.add(proposal.getId());
        }
        return ids;
    }
}
