package controllers;

import models.ApartmentType;
import models.Hotel;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;

public class Reserve {

    public static Result reserve() {
        return ok(views.html.reserve.render(Hotel.all()));
    }

    public static Result reserveHotel(Long id) {
        // TODO: load required apartment types
        List<ApartmentType> apartmentTypes = ApartmentType.all();
        return ok(views.html.reserve_hotel.render(Hotel.get(id), apartmentTypes));
    }

    public static Result reserveApartmentType() {
        return ok(views.html.reserve.render(Hotel.all()));
    }

}
