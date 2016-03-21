package pl.tests;

import org.openqa.selenium.WebDriver;
import pl.hybris.backoffice.actions.LogInActions;
import pl.hybris.backoffice.actions.NavigationPaneActions;
import pl.hybris.backoffice.pageobjects.ImpExImportPopUp;
import pl.hybris.core.interfaces.UITest;

public class EndToEndTestsApplicationTests implements UITest
{

    WebDriver driver;
    LogInActions logInActions;
    NavigationPaneActions navigationPaneActions;
    ImpExImportPopUp impExImportPopUp;

    public EndToEndTestsApplicationTests(WebDriver driver)
    {
        this.driver = driver;
        logInActions = new LogInActions(driver);
        navigationPaneActions = new NavigationPaneActions(driver);
        impExImportPopUp = new ImpExImportPopUp(driver);
    }


    public void VerifyUIforToolsImport() {
        driver.navigate().to("http://target:9001/backoffice/");

        logInActions.logIn("admin", "nimda");

        navigationPaneActions.navigateToToolsImport();

        impExImportPopUp.synchronize();


    }

}
