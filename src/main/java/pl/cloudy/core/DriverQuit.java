package pl.cloudy.core;

import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Created by i323728 on 18.03.2016.
 */
public class DriverQuit extends Thread {

    private WebDriver driver;
    private List<WebDriver> browsers;

    public DriverQuit(WebDriver driver)
    {
        this.driver = driver;
    }

    public DriverQuit(final List<WebDriver> browsers)
    {
        this.browsers = browsers;
    }

    public void run() {
        if (driver == null && browsers == null)
        {
            System.out.println("All browsers have been already terminated. Bye.");
        }

        if (driver != null)
        {
            driver.quit();
            System.out.println("Bye.");
        }

        if (browsers != null)
        {
            for (int i = 0; i < browsers.size(); i++)
            {
                browsers.get(i).quit();

            }
            System.out.println("Bye All.");

        }

    }
}
