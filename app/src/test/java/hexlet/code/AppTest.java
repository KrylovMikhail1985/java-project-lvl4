package hexlet.code;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.ebean.DB;
import io.ebean.Database;
import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    void testInit() {
        assertThat(true).isEqualTo(true);
    }

    private static Javalin app;
    private static String baseUrl;
    private static Url existingArticle;
    private static Database database;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
        database = DB.getDefault();
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Nested
    class RootTest {
        @Test
        void root() {
            HttpResponse<String> response = Unirest
                    .get(baseUrl + "/urls")
                    .asString();
            assertThat(response.getStatus()).isEqualTo(200);
        }

        @Test

        void addUrlSuccess() {
            System.out.println("addUrlSuccess");
            HttpResponse<String> response = Unirest
                    .post(baseUrl + "/urls")
                    .field("url", "https://test.ru:8090/something")
                    .asString();
            String body = response.getBody();

            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(body).contains("Страница успешно добавлена");
            Url url = new QUrl()
                    .name.equalTo("https://test.ru:8090")
                    .findOne();
            assertThat(url).isNotNull();
        }
        @Test
        void addUrlNotValidUrl() {
            System.out.println("addUrlNotValidUrl");
            HttpResponse<String> response = Unirest
                    .post(baseUrl + "/urls")
                    .field("url", "notValid")
                    .asString();
            String body = response.getBody();

            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(body).contains("Некорректный URL");
        }
        @Test
        void addUrlAlreadyExist() {
            System.out.println("addUrlAlreadyExist");
            addUrl();
            HttpResponse<String> response = Unirest
                    .post(baseUrl + "/urls")
                    .field("url", "Https://urlfortest.ru")
                    .asString();
            String body = response.getBody();

            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(body).contains("Страница уже существует");
        }
    }
    @Nested
    class UrlsTest {
        @Test
        void testUrlsGet() {
            System.out.println("testUrlsGet");
            HttpResponse<String> response = Unirest.get(baseUrl + "/urls").asString();
            assertThat(response.getStatus()).isEqualTo(200);
        }
        @Test
        void testUrlWithId() {
            System.out.println("testUrlWithId");
            addUrl();
            HttpResponse<String> response = Unirest.get(baseUrl + "/urls/1").asString();
            assertThat(response.getStatus()).isEqualTo(200);
        }
        @Test
        void testUrlCheck() throws Exception {
            System.out.println("testUrlCheck");
            MockWebServer mockWebServer = startMockServer();

            // Ask the server for its URL. You'll need this to make HTTP requests.
            HttpUrl baseMockUrl = mockWebServer.url("/");
            Unirest.post(baseUrl + "/urls")
                    .field("url", baseMockUrl.toString())
                    .asString();
            System.out.println("testUrlCheck_1");
            HttpResponse<String> response = Unirest.post(baseUrl + "/urls/1/checks").asString();
            assertThat(response.getStatus()).isEqualTo(200);

            System.out.println("testUrlCheck_2");
            HttpResponse<String> response2 = Unirest.post(baseUrl + "/urls/1/checks").asString();
            assertThat(response2.getStatus()).isEqualTo(200);

            System.out.println("testUrlCheck_3");
            HttpResponse<String> response3 = Unirest.post(baseUrl + "/urls/1/checks").asString();
            assertThat(response3.getStatus()).isEqualTo(200);

            // Shut down the server. Instances cannot be reused.
            mockWebServer.shutdown();
        }
//        @Test
//        void urlNotExist() {
//            System.out.println("========1====");
//            addUrl();
//            HttpResponse<String> response = Unirest.post(baseUrl + "/urls/1/checks").asString();
//            assertThat(response.getStatus()).isEqualTo(200);
//            System.out.println("========2====");
//        }

    }
    private static void addUrl() {
        Unirest.post(baseUrl + "/urls")
                .field("url", "https://urlfortest.ru")
                .asString();
    }
    private static MockWebServer startMockServer() throws IOException {
        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        MockWebServer mockWebServer = new MockWebServer();

        // Schedule some responses.
        mockWebServer.enqueue(new MockResponse().setBody(bodyOne()));
        mockWebServer.enqueue(new MockResponse().setBody(bodyTwo()));
        mockWebServer.enqueue(new MockResponse().setBody(""));


        mockWebServer.start();
        return mockWebServer;
    }
    private static String bodyOne() {
        return "<!doctype html>\n"
                + "<html>\n"
                + "\n"
                + "<head>\n"
                + "\t<title>Example Domain</title>\n"
                + "\n"
                + "\t<meta charset=\"utf-8\" />\n"
                + "\t<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n"
                + "\t<meta name=\"description\" content=\"width=device-width, initial-scale=1\" />\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "\t<div>\n"
                + "\t\t<h1>Example Domain</h1>\n"
                + "\t\t<p>This domain is for use in illustrative examples in documents. You may use this\n"
                + "\t\t\tdomain in literature without prior coordination or asking for permission.</p>\n"
                + "\t\t<p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>\n"
                + "\t</div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
    }
    private static String bodyTwo() {
        return "<!doctype html>\n"
                + "<html>\n"
                + "\n"
                + "<head>\n"
                + "\t<title>Example Domain 42342342 234234423 2342342</title>\n"
                + "\n"
                + "\t<meta charset=\"utf-8\" />\n"
                + "\t<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n"
                + "\t<meta name=\"description\" content=\"2342342 23423423 234324234 23423423 "
                + " 32423423 23432423 234324width=device-width, initial-scale=1\" />\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "\t<div>\n"
                + "\t\t<h1>Example Domain rwrw4 r4rw4r34 4r34r34r34 4t34t34t43 34t34t34</h1>\n"
                + "\t\t<p>This domain is for use in illustrative examples in documents. You may use this\n"
                + "\t\t\tdomain in literature without prior coordination or asking for permission.</p>\n"
                + "\t\t<p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>\n"
                + "\t</div>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
    }
}
