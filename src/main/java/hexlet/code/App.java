package hexlet.code;


import hexlet.code.controllers.Controller;
import hexlet.code.controllers.RootController;
import hexlet.code.controllers.SiteController;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class App {
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging(); // enable extensive development logging for http and websocket
//            config.enableWebjars();
        });
        addRoutes(app);
        app.before(ctx -> {
            ctx.attribute("ctx", ctx);
        });
        JavalinThymeleaf.configure(getTemplateEngine());
        return app;
    }
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "8091");
        return Integer.valueOf(port);
    }
    private static void addRoutes(Javalin app) {
        app.get("/", RootController.firstPage);
        app.get("/site/", SiteController.site);
        app.get("/se/", Controller.test);
        app.get("/123/", ctx -> ctx.result("I can do it!!!"));
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
}

