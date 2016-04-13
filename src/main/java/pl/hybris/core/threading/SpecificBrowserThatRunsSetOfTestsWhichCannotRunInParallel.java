package pl.hybris.core.threading;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.SetUpChromeDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.rest.PostHelper;
import pl.hybris.util.CommonUtil;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by i323728 on 08.04.2016.
 */
public class SpecificBrowserThatRunsSetOfTestsWhichCannotRunInParallel implements Callable
{
    private List<UITest> tests;

    public SpecificBrowserThatRunsSetOfTestsWhichCannotRunInParallel(List<UITest> tests)
    {
        this.tests = tests;
    }

    @Override
    public Object call() throws Exception
    {

        WebDriver driver = new SetUpChromeDriver();
        CurrentThreadDriver.setCurrentDriver(driver);

        while (!tests.isEmpty())
        {

            UITest testToRun = tests.remove(new Random().nextInt(tests.size()));
            String testName = testToRun.getClass().getSimpleName().toString();

            CommonUtil.printMessage("Running test " + testName);

            PostHelper postResult = new PostHelper();
            int testRunID = postResult.postInitializeTestRun(testName);
            CurrentThreadTestData.setCurrentTestRunID(testRunID);

            testToRun.runTest();

            if (CurrentThreadDriver.getCurrentDriver() == null)
            {
                CommonUtil.printMessage("Browser died or something");
            }

        }

        driver.quit();


        return null;
    }
}
