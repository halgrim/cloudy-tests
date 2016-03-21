package pl.hybris.core;

import org.openqa.selenium.WebDriver;

/**
 * Created by i323728 on 18.03.2016.
 */
public class DriverQuit extends Thread {

    private WebDriver driver;

    public DriverQuit(WebDriver driver)
    {
        this.driver = driver;
    }

    public void run() {
        driver.quit();
        System.out.println("Bye.");
    }
}
