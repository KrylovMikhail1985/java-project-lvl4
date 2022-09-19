package hexlet.code;

import hexlet.code.domain.Url;
import hexlet.code.domain.query.QUrl;
import io.ebean.DB;
import io.ebean.Database;
import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        void addUrlSuccess() {
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
            Unirest.post(baseUrl + "/urls")
                    .field("url", "https://yandex.ru")
                    .asString();
            HttpResponse<String> response = Unirest
                    .post(baseUrl + "/urls")
                    .field("url", "Https://yandex.ru")
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
            HttpResponse<String> response = Unirest.get(baseUrl + "/urls").asString();
            assertThat(response.getStatus()).isEqualTo(200);
        }

//
//        @Test
//        void testShow() {
//            HttpResponse<String> response = Unirest
//                    .get(baseUrl + "/articles/1")
//                    .asString();
//            String body = response.getBody();
//
//            assertThat(response.getStatus()).isEqualTo(200);
//            assertThat(body).contains("The Man Within");
//            assertThat(body).contains("Every flight begins with a fall");
//        }
//
//        @Test
//        void testNew() {
//            HttpResponse<String> response = Unirest
//                    .get(baseUrl + "/articles/new")
//                    .asString();
//            String body = response.getBody();
//
//            assertThat(response.getStatus()).isEqualTo(200);
//        }
//
//        @Test
//        void testCreate() {
//            String inputName = "new name";
//            String inputDescription = "new description";
//            HttpResponse<String> responsePost = Unirest
//                    .post(baseUrl + "/articles")
//                    .field("name", inputName)
//                    .field("description", inputDescription)
//                    .asEmpty();
//
//            assertThat(responsePost.getStatus()).isEqualTo(302);
//            assertThat(responsePost.getHeaders().getFirst("Location")).isEqualTo("/articles");
//
//            HttpResponse<String> response = Unirest
//                    .get(baseUrl + "/articles")
//                    .asString();
//            String body = response.getBody();
//
//            assertThat(response.getStatus()).isEqualTo(200);
//            assertThat(body).contains(inputName);
//            assertThat(body).contains("Статья успешно создана");
//
//            Article actualArticle = new QArticle()
//                    .name.equalTo(inputName)
//                    .findOne();
//
//            assertThat(actualArticle).isNotNull();
//            assertThat(actualArticle.getName()).isEqualTo(inputName);
//            assertThat(actualArticle.getDescription()).isEqualTo(inputDescription);
//        }
//
//        @Test
//        void testSearch() {
//            var queryString = "?term=man";
//            HttpResponse<String> response = Unirest
//                    .get(baseUrl + "/articles" + queryString)
//                    .asString();
//            String body = response.getBody();
//            System.out.println(body);
//            assertThat(response.getStatus()).isEqualTo(200);
//            assertThat(body).contains("The Man Within");
//            assertThat(body).doesNotContain("Consider the Lilies");
//        }
    }
}
