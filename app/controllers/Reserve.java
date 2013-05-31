package controllers;

import models.Hotel;
import play.mvc.Result;

import java.util.ArrayList;

import static play.mvc.Results.ok;

public class Reserve {

    public static Result reserve() {
        return ok(views.html.reserve.render(Hotel.all()));
    }

    public static Result reserveHotel(Long id) {
        return ok(views.html.reserve_hotel.render(Hotel.get(id), new ArrayList()));
    }

    public static Result reserveApartmentType() {
        return ok(views.html.reserve.render(Hotel.all()));
    }

}
