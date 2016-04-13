package pl.hybris.core.threading;

import org.openqa.selenium.WebDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.core.rest.PostHelper;

import java.util.concurrent.Callable;

/**
 * Created by i323728 on 25.03.2016.
 */
public class TestCallableThread implements Callable
{

    private WebDriver driver;
    private UITest testToRun;

    public TestCallableThread(final WebDriver driver, final UITest testToRun)
    {
        this.driver = driver;
        this.testToRun = testToRun;
    }

    @Override
    public WebDriver call()
    {

        CurrentThreadDriver.setCurrentDriver(driver);

        String testName = testToRun.getClass().getSimpleName().toString();
        PostHelper postResult = new PostHelper();
        int testRunID = postResult.postInitializeTestRun(testName);
        CurrentThreadTestData.setCurrentTestRunID(testRunID);

        testToRun.runTest();

        return driver;

    }


}
