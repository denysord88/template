package conf;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {
    private static final Dotenv dotenv = Dotenv
            .configure()
            .ignoreIfMissing()
            .load();

    public static final String EMAIL = get("TA_USER_EMAIL");
    public static final String PASSWORD = get("TA_USER_PASSWORD");

    private static String get(String parameterName) {
        String property = System.getProperty(parameterName);
        if (property == null) {
            property = dotenv.get(parameterName);
        }
        return property;
    }
}
