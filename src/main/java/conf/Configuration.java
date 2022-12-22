package conf;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {
    private static final Dotenv dotenv = Dotenv
            .configure()
            .ignoreIfMissing()
            .load();

    public static final String EMAIL = get("TA_USER_EMAIL");
    public static final String PASSWORD = get("TA_USER_PASSWORD");
    public static final String DRIVER_TYPE = get("TA_DRIVER_TYPE");

    private static String get(String parameterName) {
        String property = System.getProperty(parameterName);
        if (property == null) {
            property = dotenv.get(parameterName);
        }
        return property;
    }

    public static String getOSType() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "WINDOWS";
        } else if (os.contains("mac")) {
            return "MACOS";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return "LINUX";
        }
        return null;
    }

    public static String getWebdriversPath() {
        if (getOSType() == null) return null;
        String path = "webdrivers/" + getOSType() + "_" + DRIVER_TYPE;
        if (getOSType().equals("WINDOWS")) {
            path += ".exe";
        }
        return path;
    }
}
