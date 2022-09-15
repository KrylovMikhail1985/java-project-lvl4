package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import hexlet.code.support.Support;
import io.javalin.http.Handler;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class URLsController {
    public static Handler site = ctx -> {
        List<Url> urls = new QUrl()
                .orderBy()
                .findList();
        if (urls.isEmpty()) {
            Url url = new Url("EMPTY");
            url.setId(99);
            url.setCreatedAt(new Date());
            urls.add(url);
        }
        ctx.attribute("ListOfUrls", urls);
        ctx.render("site.html");
    };
    public static Handler addUrl = ctx -> {
        String name = ctx.formParam("url");
        String urlStr = Support.urlValidator(name);
        LinkedList<String> thInfo = Support.getAlert(urlStr);
        if (!urlStr.equals("notValidFormat") && !urlStr.equals("alreadyExist")) {
            Url url = new Url(urlStr);
            url.save();
        }
        ctx.attribute("class", thInfo.getFirst());
        ctx.attribute("text", thInfo.getLast());
        ctx.render("indexWithAlarm.html");
    };
};
