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
        if (!urls.isEmpty()) {
            ctx.attribute("ListOfUrls", urls);
        }
        ctx.render("site.html");
    };
};
