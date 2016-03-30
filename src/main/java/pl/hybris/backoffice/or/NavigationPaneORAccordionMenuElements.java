package pl.hybris.backoffice.or;

import org.openqa.selenium.By;

/**
 * Created by i323728 on 24.03.2016.
 */
public enum NavigationPaneORAccordionMenuElements
{

    System(By.cssSelector("[ytestid=\"root_hmc_treenode_system_2\"]")),
    Tools(By.cssSelector("[ytestid=\"root_hmc_treenode_system_hmc_treenode_systemtools_1\"]")),
    Import(By.cssSelector("[ytestid=\"root_hmc_treenode_system_hmc_treenode_systemtools_hmc_treenode_impex_import_wizard_1\"]"));

    private By locator;

    NavigationPaneORAccordionMenuElements(By locator) {
        this.locator = locator;
    }

    public By getValue()
    {
        return this.locator;
    }


}
