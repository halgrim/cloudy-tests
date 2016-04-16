package pl.hybris.backoffice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.cloudy.core.interfaces.BasicPageBlock;
import pl.cloudy.js.JavaScriptPlayground;

import java.util.ArrayList;

/**
 * Created by i323728 on 21.03.2016.
 */
public class ImpExImportPopUp implements BasicPageBlock
{

    ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();

    private WebDriver driver;
    private boolean initialized = false;
    By pageByTag = By.xpath("//body/div[contains(@class,'z-window')]");

    public ImpExImportPopUp(final WebDriver driver)
    {
        this.driver = driver;
    }

    @Override
    public ImpExImportPopUp synchronize()
    {
        if (!initialized)
        {
            WebElement pageTag = driver.findElement(pageByTag);
            JavaScriptPlayground.elementHighlight(pageTag);
            breadcrumbsElements.add(pageTag);
            initialized = true;
        }

        return this;
    }

    private WebElement getElementOnThisPage(By by)
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(by);
    }

}
