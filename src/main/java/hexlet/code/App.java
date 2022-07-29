package hexlet.code;

import io.javalin.Javalin;

public final class App {
    static Javalin app;
    public static void main(String[] args) {
        getApp();
        app.start();
    }
    public static void getApp() {
        app = Javalin.create(config -> {
            config.enableDevLogging(); // enable extensive development logging for http and websocket
        });
        app.get("/", ctx -> ctx.result("I Love You!!! \nAnd now I can do it!!!"));
    }
}

