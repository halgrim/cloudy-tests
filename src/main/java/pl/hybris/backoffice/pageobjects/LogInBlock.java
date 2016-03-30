package pl.hybris.backoffice.pageobjects;

import org.openqa.selenium.WebElement;
import pl.hybris.backoffice.or.LogInOR;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.CustomSelect;
import pl.hybris.core.interfaces.BasicPageBlock;

import java.util.ArrayList;

/**
 * Created by i323728 on 03.03.2016.
 */

public class LogInBlock implements BasicPageBlock
{

    ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();
    private LogInOR or;

    public LogInBlock(){}

    private boolean initialized = false;


    @Override
    public LogInBlock synchronize()
    {
        if (!initialized)
        {
            or = new LogInOR(breadcrumbsElements, CurrentThreadDriver.getCurrentDriver());
            initialized = true;
        }
        return this;
    }

    public LogInBlock setUser(String user)
    {
        or.inputUser().sendKeys(user);
        return this;
    }

    public LogInBlock setPass(String pass)
    {
        or.inputPass().sendKeys(pass);
        return this;
    }

    public LogInBlock clickLogIn()
    {
        or.buttonLogin().click();
        return this;
    }

    public LogInBlock setLanguage(String language)
    {
        CustomSelect selector = or.selectLanguage();
        selector.selectByVisibleText(language);
        selector.waitUntilSelectionHappens(language);
        return this;
    }
}
