package pl.tests;

import pl.cloudy.core.threading.CurrentThreadDriver;
import pl.cloudy.core.interfaces.UITest;
import pl.cloudy.util.CommonUtil;

/**
 * Created by i323728 on 25.03.2016.
 */
public class CallableSipleWebDriverTest20 implements UITest
{

    @Override
    public void runTest()
    {
        CommonUtil.wait(2500);
        CurrentThreadDriver.getCurrentDriver().navigate().to("http://google.pl");

        CommonUtil.printMessage("Test finished!");

    }
}
