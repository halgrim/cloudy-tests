package pl.hybris;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.DriverQuit;
import pl.hybris.core.SetUpChromeDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.reporting.InitializeTestData;
import pl.hybris.core.threading.CurrentThreadDatabaseConnection;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.threading.CurrentThreadTestData;
import pl.tests.VerifyUIforToolsImportSource;


/**
 * Created by i323728 on 14.03.2016.
 */

public class TestApplication
{

	public static void main(String[] args)
	{
		try
		{
			UITest test = new VerifyUIforToolsImportSource();
			WebDriver driver = new SetUpChromeDriver();
			CurrentThreadDriver.setCurrentDriver(driver);
			Runtime.getRuntime().addShutdownHook(new DriverQuit(driver));
			CurrentThreadDatabaseConnection.setConnection();

			InitializeTestData initTest = new InitializeTestData();
			String testName = test.getClass().getSimpleName().toString();
			int testID = initTest.initializeTestDataReport(testName);
			CurrentThreadTestData.setCurrentTestData(testID);

			test.runTest();

		} finally
        {
            //XMLBuilder2 xml = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
            //CommonUtil.printMessage(xml.asString());

            //SaveXMLToDB.saveReportToDB();

        }
	}





}
