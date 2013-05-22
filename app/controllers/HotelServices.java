package controllers;

import models.Client;
import models.HotelService;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class HotelServices extends Controller {

    static Form<HotelService> hotelServiceForm = Form.form(HotelService.class);

    public static Result newHotelService() {
        return ok(views.html.hotel_service_new.render(hotelServiceForm));
    }

    public static Result openHotelService(Long id) {
        HotelService hotel = HotelService.get(id);

        return ok(views.html.hotel_service_update.render(hotel, hotelServiceForm));
    }

    public static Result saveHotelService() {
        Form<HotelService> filledForm = hotelServiceForm.bindFromRequest();
        if(filledForm.hasErrors()) return badRequest(filledForm);

        HotelService.save(filledForm.get());
        return redirect(routes.Hotels.allHotel());
    }

    public static Result updateHotelService(Long id) {
        if(id != null){
            Form<HotelService> filledForm = hotelServiceForm.fill(HotelService.find.byId(id)).bindFromRequest();
            if(filledForm.hasErrors()) return badRequest(filledForm);

            HotelService.update(filledForm.get(), id);
        }
        return redirect(routes.Hotels.allHotel());
    }

    public static Result deleteHotelService(Long id) {
        HotelService.delete(id);
        return redirect(routes.Hotels.allHotel());
    }

    private static Status badRequest(Form<HotelService> filledForm) {
        return null;
    }
}