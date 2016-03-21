package pl.hybris.backoffice.actions;

import org.openqa.selenium.WebDriver;
import pl.hybris.backoffice.or.NavigationPaneOR;
import pl.hybris.backoffice.pageobjects.NavigationPaneBlock;

/**
 * Created by i323728 on 15.03.2016.
 */
public class NavigationPaneActions
{

    private final WebDriver driver;
    private NavigationPaneBlock navigationPaneBlock;

    public NavigationPaneActions(final WebDriver driver)
    {
        this.driver = driver;
        navigationPaneBlock = new NavigationPaneBlock(driver);
    }

    public NavigationPaneActions navigateToToolsImport()
    {
        navigationPaneBlock
                .synchronize()
                .expandMenuElement(NavigationPaneOR.AccordionElementSystem)
                .expandMenuElement(NavigationPaneOR.AccordionElementTools)
                .clickAccordionMenuElement(NavigationPaneOR.AccordionElementImport);
        return this;
    }
}
