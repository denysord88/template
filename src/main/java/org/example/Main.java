package org.example;

import static conf.Configuration.*;
import static driver.WebBrowser.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("selenide.browser", "firefox");
        initDriver();

        driver.get("https://google.com");
        Thread.sleep(4000);

        System.out.println("EMAIL " + EMAIL);
        System.out.println("PASSWORD " + PASSWORD);

        driver.quit();
    }
}
