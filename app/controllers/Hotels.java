package controllers;

import models.Hotel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class Hotels extends Controller {

    static Form<Hotel> hotelForm = Form.form(Hotel.class);

    public static Result hotels() {
        return ok(views.html.index.render(Hotel.all(), hotelForm));
    }

    public static Result newHotel() {
        Form<Hotel> filledForm = hotelForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Hotel.create(filledForm.get());
        return redirect(routes.Hotels.hotels());
    }

    public static Result deleteHotel(Long id) {
        Hotel.delete(id);
        return redirect(routes.Hotels.hotels());
    }

    public static Result modifyHotel(Long id) {
        Form<Hotel> filledForm = hotelForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        Hotel.modify(id, filledForm.get());
        return redirect(routes.Hotels.hotels());
    }

    private static Status badRequest(Form<Hotel> filledForm) {
        return badRequest(
                views.html.index.render(Hotel.all(), filledForm)
        );
    }
}