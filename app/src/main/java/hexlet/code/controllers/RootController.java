package hexlet.code.controllers;

import hexlet.code.domain.Url;
import hexlet.code.support.Support;
import io.javalin.http.Handler;

import java.util.LinkedList;


public class RootController {
    public static Handler firstPage = ctx -> {
//        ctx.result("index");
        ctx.render("index.html");
    };
    public static Handler addUrl = ctx -> {
        String name = ctx.formParam("url");
        String urlStr = Support.urlValidator(name);
        LinkedList<String> thInfo = Support.getAlert(urlStr, name);
        if (urlStr.equals("notValidFormat") | urlStr.equals("alreadyExist")) {
            ctx.attribute("class", thInfo.getFirst());
            ctx.attribute("text", thInfo.getLast());
            ctx.attribute("showAlert", true);
            ctx.render("index.html");
        } else {
            Url url = new Url(urlStr);
            url.save();
            ctx.cookie("checking", "urlIsCorrect");
            ctx.redirect("/urls");
        }
    };
}
