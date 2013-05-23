package controllers;

import models.HotelService;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class HotelServices extends Controller {

    static Form<HotelService> form = Form.form(HotelService.class);

    public static Result create() {
        return ok(views.html.hotel_service_new.render(form));
    }

    public static Result open(Long id) {
        HotelService hotel = HotelService.get(id);

        return ok(views.html.hotel_service_update.render(hotel, form));
    }

    public static Result save() {
        Form<HotelService> filledForm = form.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        HotelService.save(filledForm.get());
        return redirect(routes.Hotels.all());
    }

    public static Result update(Long id) {
        if(id != null){
            Form<HotelService> filledForm = form.fill(HotelService.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            HotelService.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.all());
    }

    public static Result delete(Long id) {
        HotelService.delete(id);
        return redirect(routes.Hotels.all());
    }

    private static Status badRequest(Form<HotelService> filledForm) {
        return null;
    }
}