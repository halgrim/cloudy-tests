package pl.tests;

import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.interfaces.UITest;
import pl.hybris.util.CommonUtil;

/**
 * Created by i323728 on 25.03.2016.
 */
public class CallableSipleWebDriverTest implements UITest
{

    @Override
    public void runTest()
    {
        CurrentThreadDriver.getCurrentDriver().navigate().to("http://google.pl");
        CommonUtil.printMessage("Test finished!");
    }
}
