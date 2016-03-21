package pl.hybris.backoffice.or;

import org.openqa.selenium.By;
import pl.hybris.core.interfaces.Locator;

/**
 * Created by i323728 on 21.03.2016.
 */
public enum NavigationPaneOR implements Locator
{

    AccordionElementSystem(By.cssSelector("[ytestid=\"root_hmc_treenode_system_2\"]")),
    AccordionElementTools(By.cssSelector("[ytestid=\"root_hmc_treenode_system_hmc_treenode_systemtools_1\"]")),
    AccordionElementImport(By.cssSelector("[ytestid=\"root_hmc_treenode_system_hmc_treenode_systemtools_hmc_treenode_impex_import_wizard_1\"]"));


    private By by;

    NavigationPaneOR(By by) {
        this.by = by;
    }

    @Override
    public By getLocator()
    {
        return by;
    }

}
