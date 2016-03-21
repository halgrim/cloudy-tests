package pl.hybris.backoffice.pageobjects;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.core.interfaces.BasicPageBlock;

import java.util.ArrayList;

/**
 * Created by i323728 on 03.03.2016.
 */
@RequiredArgsConstructor
public class LogInBlock implements BasicPageBlock
{
//    Using  @Setter instead, unknown will it work
//    public void setBredcrubms(final ArrayList<WebElement> parentBreadCrumbs)
//    {
//        this.breadcrumbsElements.addAll(parentBreadCrumbs);
//    }
    @Setter
    ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();

    @NonNull private WebDriver driver;
    private boolean initialized = false;

    By pageByTag = By.cssSelector("[ytestid=\"loginGrid\"]");
    By inputUser = By.cssSelector("[ytestid=\"j_username\"]");
    By inputPass = By.cssSelector("[ytestid=\"j_password\"]");
    By buttonLogin = By.cssSelector("button");


    private void hasToBeInitialized()
    {
        if (!initialized)
        {
            WebElement pageTag = driver.findElement(pageByTag);
            breadcrumbsElements.add(pageTag);
            initialized = true;
        }

    }
    @Override
    public LogInBlock synchronize()
    {
        hasToBeInitialized();
        return this;
        //Additional sync if needed
        //breadcrumbsElements.get(breadcrumbsElements.size()-1).findElement(pageByTag);

    }

    private WebElement getElementOnThisPage(By by)
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(by);
    }

    public LogInBlock setUser(String user)
    {
        getElementOnThisPage(inputUser).sendKeys(user);
        return this;
    }

    public LogInBlock setPass(String pass)
    {
        getElementOnThisPage(inputPass).sendKeys(pass);
        return this;
    }

    public LogInBlock clickLogIn()
    {
        getElementOnThisPage(buttonLogin).click();
        return this;
    }
}
