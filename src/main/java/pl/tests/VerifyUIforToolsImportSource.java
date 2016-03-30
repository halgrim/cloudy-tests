package pl.tests;

import com.jamesmurty.utils.XMLBuilder2;
import pl.hybris.backoffice.actions.LogInActions;
import pl.hybris.backoffice.actions.NavigationPaneActions;
import pl.hybris.backoffice.pageobjects.ImpExImportPopUp;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.reporting.UniqueXMLReportGenerator;
import pl.hybris.util.CommonUtil;

/**
 * Created by i323728 on 25.03.2016.
 */
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

        XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
        CommonUtil.printMessage(xml.asString());

    }

}
