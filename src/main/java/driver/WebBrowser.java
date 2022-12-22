package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static conf.Configuration.DRIVER_TYPE;
import static conf.Configuration.getWebdriversPath;

public class WebBrowser {
    public static WebDriver driver = null;

    public static void initDriver() {
        System.setProperty(
                "webdriver." +
                        (DRIVER_TYPE.equals("FIREFOX") ?
                                "gecko" :
                                DRIVER_TYPE.toLowerCase())
                        + ".driver",
                getWebdriversPath());

        Map<String, Class> driverClasses = Stream.of(new Object[][]{
                {"CHROME", ChromeDriver.class},
                {"FIREFOX", FirefoxDriver.class},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Class) data[1]));

        try {
            driver = (WebDriver) driverClasses.get(DRIVER_TYPE).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
