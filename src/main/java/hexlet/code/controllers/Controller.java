package hexlet.code.controllers;

import io.javalin.http.Handler;


public final class Controller {


    public static Handler test = ctx -> {
        ctx.result("Some words\n" + "Какие-то слова");
    };

}
