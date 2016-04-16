package pl.tests;

import pl.cloudy.core.interfaces.UITest;
import pl.cloudy.core.threading.CurrentThreadDriver;
import pl.hybris.backoffice.actions.LogInActions;
import pl.hybris.backoffice.actions.NavigationPaneActions;
import pl.hybris.backoffice.pageobjects.ImpExImportPopUp;

/**
 * Created by i323728 on 25.03.2016.
 */
@SuppressWarnings("ALL")
public class VerifyUIforToolsImportSource implements UITest
{

    @Override
    public void runTest()
    {
        LogInActions logInActions = new LogInActions();
        NavigationPaneActions navigationPaneActions = new NavigationPaneActions();
        ImpExImportPopUp impExImportPopUp = new ImpExImportPopUp(CurrentThreadDriver.getCurrentDriver());

        CurrentThreadDriver.getCurrentDriver().navigate().to("http://source:9001/backoffice/");
        logInActions.logIn("admin", "nimda");

        navigationPaneActions.navigateToToolsImport();

        impExImportPopUp.synchronize();
    }

}
