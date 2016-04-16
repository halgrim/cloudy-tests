package pl.hybris;

import org.openqa.selenium.WebDriver;

import pl.cloudy.core.DriverQuit;
import pl.cloudy.core.SetUpChromeDriver;
import pl.cloudy.core.interfaces.UITest;
import pl.cloudy.core.rest.PostHelper;
import pl.cloudy.core.threading.CurrentThreadDriver;
import pl.cloudy.core.threading.CurrentThreadTestData;
import pl.tests.VerifyUIforToolsImportSource;


/**
 * Created by i323728 on 14.03.2016.
 */

public class TestApplication
{

	public static void main(String[] args)
	{

		UITest test = new VerifyUIforToolsImportSource();
		WebDriver driver = new SetUpChromeDriver();
		CurrentThreadDriver.setCurrentDriver(driver);
		Runtime.getRuntime().addShutdownHook(new DriverQuit(driver));

		String testName = test.getClass().getSimpleName().toString();
		PostHelper postResult = new PostHelper();
		int testRunID = postResult.initializeTestRun(testName);

		CurrentThreadTestData.setCurrentTestRunID(testRunID);

		test.runTest();


	}





}
