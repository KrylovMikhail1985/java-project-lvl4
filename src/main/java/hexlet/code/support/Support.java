package hexlet.code.support;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

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

    public static LinkedList<String> getAlert(String urlStr, String firstUrl) {
        LinkedList<String> list = new LinkedList<>();
        if (urlStr.equals("notValidFormat")) {
            list.add("alert alert-danger alert-dismissible fade show");
            list.add("Некорректный URL: " + firstUrl);
        } else if (urlStr.equals("alreadyExist")) {
            list.add("alert alert-warning alert-dismissible fade show");
            list.add("Страница уже существует: " + firstUrl);

        } else {
            list.add("alert alert-success alert-dismissible fade show");
            list.add("Страница успешно добавлена");
        }
        return list;
    }
}
