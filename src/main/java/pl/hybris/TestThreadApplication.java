package pl.hybris;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.DriverQuit;
import pl.hybris.core.SetUpChromeDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.threading.TestCallableThread;
import pl.hybris.util.CommonUtil;
import pl.tests.CallableSipleWebDriverTest;
import pl.tests.CallableSipleWebDriverTest10;
import pl.tests.CallableSipleWebDriverTest20;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
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

		int numberOfThreads = 3;

		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		List<WebDriver> browsers = new ArrayList<>();

		for (int i = 0; i < numberOfThreads; i++)
		{
			browsers.add(new SetUpChromeDriver());
		}

		final List<UITest> tests = new ArrayList<>();

		for (int i = 0; i < 4; i++)
		{
			tests.add(new CallableSipleWebDriverTest());
			tests.add(new CallableSipleWebDriverTest10());
			tests.add(new CallableSipleWebDriverTest20());
		}

		solve(executor, tests, browsers);

		executor.shutdown();

		try
		{
			executor.awaitTermination(600, TimeUnit.SECONDS);
		}
		catch (InterruptedException e)
		{
			CommonUtil.printMessage("Ups! Test did not terminate");
		}

		Runtime.getRuntime().addShutdownHook(new DriverQuit(browsers));

	}


	static void solve(Executor e, List<UITest> tests, List<WebDriver> browsers) throws InterruptedException, ExecutionException
	{
		CompletionService<WebDriver> ecs = new ExecutorCompletionService<>(e);
		for (WebDriver browser : browsers)
		{
			ecs.submit(new TestCallableThread(browser, tests.remove(new Random().nextInt(tests.size()))));
		}

		while (!tests.isEmpty())
		{
			CommonUtil.printMessage("WHILE");

			WebDriver finishedBrowser = ecs.take().get();
			if (finishedBrowser != null && !tests.isEmpty())
			{
				UITest currentTest = tests.remove(new Random().nextInt(tests.size()));
				CommonUtil.printMessage("BlaBla " + currentTest.getClass().getName());
				ecs.submit(new TestCallableThread(finishedBrowser, currentTest));
			}

		}
	}



}
