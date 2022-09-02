package hexlet.code.controllers;

import io.javalin.http.Handler;


public class RootController {
    public static Handler firstPage = ctx -> {
//        ctx.result("index");
        ctx.render("index.html");
    };
}
