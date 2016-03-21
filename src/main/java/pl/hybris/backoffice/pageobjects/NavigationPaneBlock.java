package pl.hybris.backoffice.pageobjects;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.hybris.core.interfaces.BasicPageBlock;
import pl.hybris.core.interfaces.Locator;

import java.util.ArrayList;


/**
 * Created by i323728 on 03.03.2016.
 */
@RequiredArgsConstructor
public class NavigationPaneBlock implements BasicPageBlock
{

	ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();

	@NonNull private WebDriver driver;
    private boolean initialized = false;
	By pageByTag = By.cssSelector("[ytestid=\"explorerTree\"]");

	By elementIndicatingIfAccordionElementIsExpanded = By.cssSelector("td > div > span > i");


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
	public NavigationPaneBlock synchronize()
	{
        hasToBeInitialized();
		return this;

	}

    private WebElement getElementOnThisPage(By by)
    {
        return breadcrumbsElements.get(breadcrumbsElements.size() - 1).findElement(by);
    }

	public NavigationPaneBlock expandMenuElement(Locator by)
	{
		WebElement menuWebElement = getElementOnThisPage(by.getLocator());

		if (checkIfMenuElementIsExpanded(menuWebElement))
		{
			return this;
		} else
		{
			menuWebElement.click();
			return this;
		}
	}

	public NavigationPaneBlock clickAccordionMenuElement(Locator by)
	{
        getElementOnThisPage(by.getLocator()).click();
		return this;
	}


	private boolean checkIfMenuElementIsExpanded(WebElement menuWebElement)
	{
		WebElement webElementIndicatingIfAccordionElementIsExpanded = menuWebElement.findElement(elementIndicatingIfAccordionElementIsExpanded);
		String classAttributeValue =  webElementIndicatingIfAccordionElementIsExpanded.getAttribute("class");

		return classAttributeValue.contains("z-vfiletree-open");
	}


}
