package pl.hybris.backoffice.pageobjects;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.core.interfaces.BasicPageBlock;
import pl.hybris.js.JavaScriptPlayground;

import java.util.ArrayList;

/**
 * Created by i323728 on 21.03.2016.
 */
@RequiredArgsConstructor
public class ImpExImportPopUp implements BasicPageBlock
{

    ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();

    @NonNull
    private WebDriver driver;
    private boolean initialized = false;
    By pageByTag = By.xpath("//body/div[contains(@class,'z-window')]");

    private void hasToBeInitialized()
    {
        if (!initialized)
        {
            WebElement pageTag = driver.findElement(pageByTag);
            JavaScriptPlayground.elementHighlight(pageTag);
            breadcrumbsElements.add(pageTag);
            initialized = true;
        }

    }

    @Override
    public ImpExImportPopUp synchronize()
    {
        hasToBeInitialized();
        return this;

    }

    private WebElement getElementOnThisPage(By by)
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(by);
    }

}
