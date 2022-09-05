package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.javalin.http.Handler;
public class URLsController {
    public static Handler site = ctx -> {
        Url url = new QUrl()
                        .id.equalTo(1)
                        .findOne();

        ctx.attribute("id", url.getId());
        ctx.attribute("url", url.getName());
        ctx.attribute("created", url.getCreatedAt());
        ctx.render("site.html");
    };
    public static Handler addUrl= ctx -> {
        String name = ctx.formParam("url");
        Url url = new Url(name);
        url.save();

//        ctx.attribute("id", url.getId());
//        ctx.attribute("url", url.getName());
//        ctx.attribute("created", url.getCreatedAt());
        ctx.render("index.html");
    };
};
