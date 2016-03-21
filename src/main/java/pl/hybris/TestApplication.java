package pl.hybris;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.SetUpChromeDriver;
import pl.tests.EndToEndTestsApplicationTests;


/**
 * Created by i323728 on 14.03.2016.
 */

public class TestApplication
{

	public static void main(String[] args)
	{

		WebDriver driver = new SetUpChromeDriver();
		EndToEndTestsApplicationTests test1 = new EndToEndTestsApplicationTests(driver);
		test1.VerifyUIforToolsImport();
	}





}
