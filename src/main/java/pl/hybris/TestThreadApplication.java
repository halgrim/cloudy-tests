package pl.hybris;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.SetUpChromeDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.threading.SpecificBrowserThatRunsSetOfTestsWhichCannotRunInParallel;
import pl.hybris.core.threading.TestCallableThread;
import pl.hybris.util.CommonUtil;
import pl.tests.VerifyUIforToolsImportTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by i323728 on 26.03.2016.
 */
public class TestThreadApplication
{

	public static void main(String[] arg) throws ExecutionException, InterruptedException
	{

		int numberOfConcurrentThreads = 2;



		List<List<UITest>> listOListsOfTests = new ArrayList<List<UITest>>();

		for (int i = 0; i < numberOfConcurrentThreads; i++)
		{

			ArrayList<UITest> tests = new ArrayList<>();

			tests.add(new VerifyUIforToolsImportTarget());
			//tests.add(new VerifyUIforToolsImportSource());

			listOListsOfTests.add(tests);

		}

//		for (int i = 0; i < 4; i++)
//		{
//			tests.add(new CallableSipleWebDriverTest());
//			tests.add(new CallableSipleWebDriverTest10());
//			tests.add(new CallableSipleWebDriverTest20());
//		}

		ExecutorService executor = Executors.newFixedThreadPool(numberOfConcurrentThreads);
		CompletionService ecs = new ExecutorCompletionService(executor);

		for (List<UITest> tests : listOListsOfTests)
		{
			ecs.submit(new SpecificBrowserThatRunsSetOfTestsWhichCannotRunInParallel(tests));
		}

	}


	static void solve(List<UITest> tests) throws InterruptedException, ExecutionException
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();

		CompletionService<WebDriver> ecs = new ExecutorCompletionService<>(executor);

		WebDriver driver = new SetUpChromeDriver();

		ecs.submit(new TestCallableThread(driver, tests.remove(new Random().nextInt(tests.size()))));

		while (!tests.isEmpty())
		{
			CommonUtil.printMessage("WHILE");

			WebDriver finishedBrowser = ecs.take().get();

			if (finishedBrowser == null)
			{
				CommonUtil.printMessage("Browser died or something");
			}
			if(!tests.isEmpty())
			{
				UITest currentTest = tests.remove(new Random().nextInt(tests.size()));
				CommonUtil.printMessage("BlaBla " + currentTest.getClass().getName());
				ecs.submit(new TestCallableThread(finishedBrowser, currentTest));
			}

		}

		executor.shutdown();

		try
		{
			executor.awaitTermination(600, TimeUnit.SECONDS);
			driver.quit();
			//Runtime.getRuntime().addShutdownHook(new DriverQuit(browsers));
		}
		catch (InterruptedException e)
		{
			CommonUtil.printMessage("Ups! Tests did not terminate");
		}
	}


}
