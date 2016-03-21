package pl.hybris.backoffice.actions;

import org.openqa.selenium.WebDriver;
import pl.hybris.backoffice.pageobjects.LogInBlock;

/**
 * Created by i323728 on 15.03.2016.
 */
public class LogInActions
{

    private LogInBlock logInBlock;
    private WebDriver driver;

    public LogInActions(final WebDriver driver)
    {
        this.driver = driver;
        logInBlock = new LogInBlock(driver);

    }

    public LogInActions logIn(String user, String pass)
    {
        logInBlock.synchronize()
                .setUser(user)
                .setPass(pass)
                .clickLogIn();
        return this;
    }




}
