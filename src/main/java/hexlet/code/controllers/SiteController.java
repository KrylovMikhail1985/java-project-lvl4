package hexlet.code.controllers;

import io.javalin.http.Handler;

public class SiteController {
    public static Handler site = ctx -> {

        ctx.render("site.html");
    };
}
