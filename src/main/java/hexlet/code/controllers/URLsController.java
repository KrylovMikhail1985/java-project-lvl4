package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.support.Support;
import io.javalin.http.Handler;

import java.util.List;

public class URLsController {
    public static Handler site = ctx -> {
//        List<UrlAndChecks> urlsAndCheckList = Support.getUrlsAndCheckList();



        List<Url> urls = Support.getUrlsList();
        if (!urls.isEmpty()) {
            ctx.attribute("ListOfUrls", urls);
        }
//        List<UrlCheck> urlChecks = Support.getUrlChecksListForThisUrl(url);
//        ctx.attribute("ListOfUrlChecks", urlChecks);
        ctx.render("site.html");
    };
};
