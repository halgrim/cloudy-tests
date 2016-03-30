package pl.hybris.backoffice.pageobjects;


import org.openqa.selenium.WebElement;
import pl.hybris.backoffice.or.NavigationPaneOR;
import pl.hybris.backoffice.or.NavigationPaneORAccordionMenuElements;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.interfaces.BasicPageBlock;

import java.util.ArrayList;


/**
 * Created by i323728 on 03.03.2016.
 */
public class NavigationPaneBlock implements BasicPageBlock
{

	ArrayList<WebElement> breadcrumbsElements = new ArrayList<>();
	private NavigationPaneOR or;

	public NavigationPaneBlock(){}

	private boolean initialized = false;


	@Override
	public NavigationPaneBlock synchronize()
	{
		if (!initialized)
		{
			or = new NavigationPaneOR(breadcrumbsElements, CurrentThreadDriver.getCurrentDriver());
			initialized = true;
		}
		return this;

	}

	public NavigationPaneBlock expandMenuElement(NavigationPaneORAccordionMenuElements menuElement)
	{

		if (checkIfMenuElementIsExpanded(menuElement))
		{
			return this;
		} else
		{
			clickAccordionMenuElement(menuElement);
			return this;
		}
	}

	public NavigationPaneBlock clickAccordionMenuElement(NavigationPaneORAccordionMenuElements menuElement)
	{
		or.accordionMenu(menuElement).click();
		return this;
	}


	private boolean checkIfMenuElementIsExpanded(NavigationPaneORAccordionMenuElements menuElement)
	{
		String classAttributeValue =  or.elementIndicatingIfAccordionElementIsExpanded(menuElement).getAttribute("class");
		return classAttributeValue.contains("z-vfiletree-open");
	}


}
