package hexlet.code;

import io.javalin.Javalin;

public final class App {
//    static Javalin app;
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging(); // enable extensive development logging for http and websocket
        });
        app.get("/", ctx -> ctx.result("I Love You!!! \nAnd now I can do it!!!"));
        return app;
    }
    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 8091;
    }
}

