package api_engine;

public class Route {
    private static final String BOOKING = "/booking/";
    private static final String AUTHENTIFICATION = "/auth/";
    private static final String PING = "/ping/";

    public static String booking(){return BOOKING;}
    public static String authentification(){return AUTHENTIFICATION;}
    public static String ping(){return PING;}
}
