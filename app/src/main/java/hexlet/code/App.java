package hexlet.code;

import hexlet.code.controllers.OneUrlController;
import hexlet.code.controllers.RootController;
import hexlet.code.controllers.URLsController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

public class App {
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            if (!isProduction()) {
                config.enableDevLogging(); // enable extensive development logging for http and websocket
            }

            config.enableWebjars();
            config.addStaticFiles("/static/css", Location.CLASSPATH);
            JavalinThymeleaf.configure(getTemplateEngine());
        });
        addRoutes(app);
//        app.before(ctx -> {
//            ctx.attribute("ctx", ctx);
//        });
        return app;
    }
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "8090");
        return Integer.parseInt(port);
    }
    private static void addRoutes(Javalin app) {
        app.get("/", RootController.firstPage);
        app.routes(() -> {
            path("urls", () -> {
                get(URLsController.site);
                post(RootController.addUrl);
                path("{id}", () -> {
                    get(OneUrlController.oneSite);
                });
            });
        });
        app.post("/urls/{id}/checks", OneUrlController.checkUrl);
    }
    private static TemplateEngine getTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // HTML is the default mode, but we set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/templates/");
//        templateResolver.setSuffix(".html"); // Не работает
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.addTemplateResolver(templateResolver);
        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new Java8TimeDialect());

        return templateEngine;
    }
    private static String getMode() {
        return System.getenv().getOrDefault("APP_ENV", "development");
    }

    private static boolean isProduction() {
        return getMode().equals("production");
    }
}

