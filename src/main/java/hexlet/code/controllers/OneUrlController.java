package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.javalin.http.Handler;

public class OneUrlController {
    public static Handler oneSite = ctx -> {
        long urlId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = new QUrl()
                .id.equalTo(urlId)
                .findOne();
        if (url == null) {
            ctx.redirect("/urls/");
        } else {
            ctx.attribute("Url", url);
            ctx.render("siteChecking.html");
        }
    };
};
