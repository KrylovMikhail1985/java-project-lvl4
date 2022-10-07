package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import hexlet.code.support.Support;
import io.javalin.http.Handler;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class OneUrlController {
    public static Handler oneSite = ctx -> {
        long urlId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = Support.getUrlById(urlId);
        ctx.attribute("Url", url);
        List<UrlCheck> urlChecks = Support.getUrlChecksListForThisUrl(url);
        if (!urlChecks.isEmpty()) {
            ctx.attribute("ListOfUrlChecks", urlChecks);
        }
        ctx.render("siteChecking.html");
    };
    public static Handler checkUrl = ctx -> {
        long urlId = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        Url url = Support.getUrlById(urlId);
        createNewUrlCheckForUrl(url);
        List<UrlCheck> urlChecks = Support.getUrlChecksListForThisUrl(url);

        ctx.attribute("urlIsChecked", true);

        ctx.attribute("Url", url);
        ctx.attribute("ListOfUrlChecks", urlChecks);
        ctx.render("siteChecking.html");
    };
    private static void createNewUrlCheckForUrl(Url url) {
        UrlCheck urlCheck = new UrlCheck();
        urlCheck.setUrl(url);
        Document doc;
        try {
            HttpResponse<String> response = Unirest
                    .get(url.getName())
                    .asString();
            doc = Jsoup.parse(response.getBody());
            urlCheck.setStatusCode(response.getStatus());
        } catch (Exception e) {
            System.out.println("We cached Exception: " + e);
            return;
        }

        String h1 = "";
        try {
            h1 = doc.selectFirst("h1").ownText();
        } catch (Exception e) {
            System.out.println("There is not H1 teg");
        }
        if (!h1.isEmpty() && h1.length() < 200) {
            urlCheck.setH1(h1);
        }

        String title = "";
        try {
            title = doc.title();
        } catch (Exception e) {
            System.out.println("There is not Title");
        }
        urlCheck.setTitle(title);

        String description = "";
        try {
            description = doc.selectFirst("meta[name=description]").attr("content");
        } catch (Exception e) {
            System.out.println("There is not description");
        }
        urlCheck.setDescription(description);

        urlCheck.save();
    }
}
