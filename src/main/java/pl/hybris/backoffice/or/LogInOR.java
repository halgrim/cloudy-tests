package pl.hybris.backoffice.or;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.cloudy.core.CustomSelect;

import java.util.ArrayList;

/**
 * Created by i323728 on 24.03.2016.
 */
public class LogInOR
{
    ArrayList<WebElement> breadcrumbsElements;
    private final WebDriver driver;

    By pageTag = By.cssSelector("[ytestid=\"loginGrid\"]");

    By inputUser = By.cssSelector("[ytestid=\"j_username\"]");
    By inputPass = By.cssSelector("[ytestid=\"j_password\"]");
    By selectLanguage = By.cssSelector("[ytestid=\"language\"]");
    By buttonLogin = By.cssSelector("button");

    public LogInOR(final ArrayList<WebElement> breadcrumbs,final WebDriver driver)
    {
        this.breadcrumbsElements = breadcrumbs;
        this.driver = driver;

        if (breadcrumbsElements.isEmpty())
        {
            breadcrumbsElements.add(driver.findElement(pageTag));
        } else
        {
            breadcrumbsElements.add(pageTag());
        }

    }

    public WebElement pageTag()
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(pageTag);
    }

    public WebElement inputUser()
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(inputUser);
    }

    public WebElement inputPass()
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(inputPass);
    }

    public CustomSelect selectLanguage()
    {
        return new CustomSelect(breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(selectLanguage));
    }

    public WebElement buttonLogin()
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(buttonLogin);
    }


}
