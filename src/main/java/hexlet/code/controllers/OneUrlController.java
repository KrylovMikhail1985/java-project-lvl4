package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import hexlet.code.domain.query.QUrl;
import hexlet.code.domain.query.QUrlCheck;
import io.javalin.http.Handler;

import java.util.List;

public class OneUrlController {
    public static Handler oneSite = ctx -> {
        long urlId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = getUrlById(urlId);
        if (url == null) {
            ctx.redirect("/urls/");
        } else {
            ctx.attribute("Url", url);
        }
        List<UrlCheck> urlChecks = getUrlChecksListForThisUrl(url);
        if (!urlChecks.isEmpty()) {
            ctx.attribute("ListOfUrlChecks", urlChecks);
        }
        ctx.render("siteChecking.html");
    };
    public static Handler checkUrl = ctx -> {
        long urlId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = getUrlById(urlId);
        creatNewUrlCheckForUrl(url);
        List<UrlCheck> urlChecks = getUrlChecksListForThisUrl(url);

        ctx.attribute("Url", url);
        ctx.attribute("ListOfUrlChecks", urlChecks);
        ctx.render("siteChecking.html");
    };
    private static Url getUrlById(Long id) {
        return new QUrl()
                .id.equalTo(id)
                .findOne();
    }
    private static List<UrlCheck> getUrlChecksListForThisUrl(Url thisUrl) {
        return new QUrlCheck()
                .url.equalTo(thisUrl)
                .orderBy()
                .findList();
    }
    private static void creatNewUrlCheckForUrl(Url url) {
        UrlCheck urlCheck = new UrlCheck();
        urlCheck.setUrl(url);
        urlCheck.setH1("some H1");
        urlCheck.setTitle("some Title");
        urlCheck.setDescription("some description");
        urlCheck.save();
    }
}
