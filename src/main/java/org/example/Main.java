package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static conf.Configuration.*;

public class Main {

    private static WebDriver driver;
    public static void main(String[] args) {
        if (getOSType() == null || getWebdriversPath() == null) return;

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

        System.out.println("EMAIL " + EMAIL);
        System.out.println("PASSWORD " + PASSWORD);

        driver.quit();
    }
}
