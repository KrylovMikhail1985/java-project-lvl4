package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.javalin.http.Handler;

import java.util.List;

public class URLsController {
    public static Handler site = ctx -> {
        List<Url> urls = new QUrl()
                .orderBy()
                .findList();
        ctx.attribute("ListOfUsers", urls);
        ctx.render("site.html");
    };
    public static Handler addUrl = ctx -> {
        String name = ctx.formParam("url");
        Url url = new Url(name);
        url.save();
        ctx.render("index.html");
    };
};
