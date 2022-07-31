package hexlet.code;


import io.javalin.Javalin;

public class App {
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
        String port = System.getenv().getOrDefault("PORT", "8091");
        return Integer.valueOf(port);
    }
}

