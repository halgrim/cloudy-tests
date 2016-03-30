package pl.hybris.core.threading;

import org.openqa.selenium.WebDriver;

/**
 * Created by i323728 on 22.03.2016.
 */
public class CurrentThreadDriver
{

    private static final ThreadLocal < WebDriver > threadLocal =
            new ThreadLocal < WebDriver > () {};

    public static void setCurrentDriver(WebDriver driver)
    {
        threadLocal.set(driver);
    }

    public static WebDriver getCurrentDriver()
    {
        return threadLocal.get();
    }

}
