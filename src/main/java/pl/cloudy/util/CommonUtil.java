package pl.cloudy.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import pl.cloudy.constants.Global;
import pl.cloudy.core.interfaces.CustomDriver;
import pl.cloudy.core.interfaces.WrapsWebElement;
import pl.cloudy.js.JSImageRenderer;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by i323728 on 11/6/15.
 */
public class CommonUtil
{

	public static void printMessage(final String massage)
	{
		System.out.println(massage);
	}


	public static String takeElementScreenshot(final WebElement element)
	{
		final String filePath;

		final WebElement temp = ((WrapsWebElement) element).getWrappedWebElement();

		final JavascriptExecutor bla = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
		final JSImageRenderer js = new JSImageRenderer(bla);
		filePath = js.saveWebElementImageToFile(temp);
		final FileSystemUtil path = new FileSystemUtil();
		for (int i = 0; i < 100; i++)
		{
			if (path.checkIfDownloadImageExist(filePath))
			{
				return filePath;
			}
			try
			{
				Thread.sleep(250);
			} catch (final InterruptedException e)
			{
				break;
			}
		}

		return "Failed to take a screenshot ";

	}

	public static void wait(final int millis)
	{
		try
		{
			Thread.sleep(millis);
		} catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static String doAPrintScreen(WebDriver driver, String suffix)
	{
		String fileName = prepareFileName(suffix);
		String filePath = Global.SAVED_IMAGES_FOLDER;
		File scrFile = takeScreenshot(driver);
		File destination = new File(filePath + fileName);
		try
		{
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return fileName;
	}

	private static String prepareFileName(String suffix)
	{
		return suffix + "_" + UUID.randomUUID().toString() + ".png";
	}

	private static File takeScreenshot(WebDriver driver)
	{
		WebDriver tempDriver ;
		tempDriver	= ((CustomDriver) driver).getDriver();
		File scrFile = ((TakesScreenshot) tempDriver).getScreenshotAs(OutputType.FILE);
		return scrFile;
	}


}
