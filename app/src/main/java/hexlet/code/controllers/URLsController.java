package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.support.Support;
import io.javalin.http.Handler;

import java.util.List;

public class URLsController {
    public static Handler site = ctx -> {
        String checking = ctx.cookie("checking");
        ctx.removeCookie("checking");
        List<Url> urls = Support.getUrlsList();
        if (!urls.isEmpty()) {
            ctx.attribute("ListOfUrls", urls);
        }

        Boolean hidden = true;
        if (checking != null) {
            hidden = false;
        }
        ctx.attribute("hidden", hidden);
        ctx.render("site.html");
    };
};
