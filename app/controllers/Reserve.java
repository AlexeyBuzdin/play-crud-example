package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class Reserve {

    public static Result inquiryForm() {
        return ok(views.html.reserve.render());
    }

}
