package hexlet.code.support;

import hexlet.code.domain.Url;
import hexlet.code.domain.UrlCheck;
import hexlet.code.domain.query.QUrl;
import hexlet.code.domain.query.QUrlCheck;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class Support {
//    public String urlStr;
    public static String urlValidator(String str) throws MalformedURLException {
        String result;
        URL url;
        try {
            url = new URL(str);
        } catch (Exception e) {
            return "notValidFormat";
        }
        result = url.getProtocol() + "://" + url.getHost().toLowerCase();
        if (url.getPort() > 0) {
            result = result + ":" + url.getPort();
        }
        Url urlFromTable = new QUrl()
                .name.equalTo(result)
                .findOne();
        if (urlFromTable != null) {
            result = "alreadyExist";
        }
        return result;
    }

    public static LinkedList<String> getAlert(String urlStr, String firstUrl) throws MalformedURLException {
        LinkedList<String> list = new LinkedList<>();
        if (urlStr.equals("notValidFormat")) {
            list.add("alert alert-danger alert-dismissible fade show");
            list.add("Некорректный URL: " + firstUrl);
        } else if (urlStr.equals("alreadyExist")) {
            list.add("alert alert-warning alert-dismissible fade show");
            URL url = new URL(firstUrl);
            String result = url.getProtocol() + "://" + url.getHost().toLowerCase();
            list.add("Страница уже существует: " + result);

        }
        return list;
    }

    public static List<Url> getUrlsList() {
        return new QUrl()
                .orderBy()
                .findList();
    }
    public static List<UrlCheck> getUrlChecksListForThisUrl(Url thisUrl) {
        return new QUrlCheck()
                .url.equalTo(thisUrl)
                .orderBy().id.desc()
                .findList();
    }
    public static Url getUrlById(Long id) {
        return new QUrl()
                .id.equalTo(id)
                .findOne();
    }
}
