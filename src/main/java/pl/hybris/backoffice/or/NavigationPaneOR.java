package pl.hybris.backoffice.or;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
/**
 * Created by i323728 on 21.03.2016.
 */
public class NavigationPaneOR
{

    ArrayList<WebElement> breadcrumbsElements;
    private final WebDriver driver;

    By pageTag = By.cssSelector("[ytestid=\"explorerTree\"]");

    By elementIndicatingIfAccordionElementIsExpandedBy = By.cssSelector("td > div > span > i");

    public NavigationPaneOR(final ArrayList<WebElement> breadcrumbs,final WebDriver driver)
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

    public WebElement elementIndicatingIfAccordionElementIsExpanded(final NavigationPaneORAccordionMenuElements menuElement)
    {
        return accordionMenu(menuElement).findElement(elementIndicatingIfAccordionElementIsExpandedBy);
    }


    public WebElement accordionMenu(final NavigationPaneORAccordionMenuElements menuElement)
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(menuElement.getValue());
    }
}
