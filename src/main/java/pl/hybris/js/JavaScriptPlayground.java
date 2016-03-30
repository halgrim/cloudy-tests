package pl.hybris.js;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import pl.hybris.core.CustomWebElement;
import pl.hybris.util.CommonUtil;


/**
 * Created by i323728 on 10/30/15.
 */
public class JavaScriptPlayground
{

	public static void elementHighlight(final WebElement tempElement)
	{
		final WebElement element;
		if (tempElement instanceof CustomWebElement)
		{
			element = ((WrapsElement) tempElement).getWrappedElement();
		} else
		{
			element = tempElement;
		}

		for (int i = 0; i < 2; i++)
		{
			final JavascriptExecutor js = (JavascriptExecutor) ((WrapsDriver) element).getWrappedDriver();
			String currentStyle = (String) js.executeScript("return arguments[0].getAttribute('style');", element);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, currentStyle + " ;color: red; border: 3px solid red;");
			CommonUtil.wait(500);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, currentStyle);
		}
	}

}
