package pl.hybris.backoffice.actions;


import pl.hybris.backoffice.or.NavigationPaneORAccordionMenuElements;
import pl.hybris.backoffice.pageobjects.NavigationPaneBlock;

/**
 * Created by i323728 on 15.03.2016.
 */
public class NavigationPaneActions
{
    private NavigationPaneBlock navigationPaneBlock;

    public NavigationPaneActions()
    {
        navigationPaneBlock = new NavigationPaneBlock();
    }

    public NavigationPaneActions navigateToToolsImport()
    {
        navigationPaneBlock
                .synchronize()
                .expandMenuElement(NavigationPaneORAccordionMenuElements.System)
                .expandMenuElement(NavigationPaneORAccordionMenuElements.Tools)
                .clickAccordionMenuElement(NavigationPaneORAccordionMenuElements.Import);
        return this;
    }
}
