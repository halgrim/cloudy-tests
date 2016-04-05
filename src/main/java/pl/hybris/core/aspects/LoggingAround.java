package pl.hybris.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import pl.hybris.core.database.ActionObject;
import pl.hybris.core.reporting.InitializeTestData;
import pl.hybris.core.database.PageObject;
import pl.hybris.core.threading.CurrentThreadDriver;
import pl.hybris.core.threading.CurrentThreadTestData;
import pl.hybris.util.CommonUtil;

/**
 * Created by i323728 on 11.03.2016.
 */
@Aspect
public class LoggingAround
{

    @Around("execution(* pl.hybris.backoffice..actions..*(..))")
    public Object aroundActions(ProceedingJoinPoint joinPoint) throws Throwable
    {
        ActionObject action = new ActionObject();
        Integer testID = CurrentThreadTestData.getCurrentTestData();
        String arguments = "" ;

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++)
        {
            arguments = arguments + args[i].toString() + ",";
        }

        String fileName ;

        if (true)
        {
            fileName = CommonUtil.doAPrintScreen(CurrentThreadDriver.getCurrentDriver(), "BeforeActionScreenshot");
        }

        action
                .setTestID(testID)
                .setClassName(joinPoint.getTarget().getClass().getName())
                .setMethodName(joinPoint.getSignature().getName())
                .setArguments(arguments)
                .setStartTime(new DateTime())
                .setScreenshotBefore(fileName);

        Object result = joinPoint.proceed();

        action
                .setFinishTime(new DateTime())
                .setReturnValue(String.valueOf(result));

        InitializeTestData.saveActionObjectToDB(action);
        if (!fileName.contains("empty"))
        {
            InitializeTestData.saveScreenshotToDB(fileName);
        }
        return result;
    }

    @Around("execution(* pl.hybris.backoffice..pageobjects..*(..))")
    public Object aroundPageObject(ProceedingJoinPoint joinPoint) throws Throwable
    {
        PageObject pageObject = new PageObject();
        Integer testID = CurrentThreadTestData.getCurrentTestData();
        String arguments = "" ;

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++)
        {
            arguments = arguments + args[i].toString() + ",";
        }

        String fileName = "empty";

        if (false)
        {
            fileName = CommonUtil.doAPrintScreen(CurrentThreadDriver.getCurrentDriver(), "BeforePageObjectScreenshot");
        }

        pageObject
                .setTestID(testID)
                .setClassName(joinPoint.getTarget().getClass().getName())
                .setMethodName(joinPoint.getSignature().getName())
                .setArguments(arguments)
                .setStartTime(new DateTime())
                .setScreenshotBefore(fileName);

        Object result = joinPoint.proceed();

        pageObject
                .setFinishTime(new DateTime())
                .setReturnValue(String.valueOf(result));

        InitializeTestData.savePageObjectToDB(pageObject);
        if (!fileName.contains("empty"))
        {
            InitializeTestData.saveScreenshotToDB(fileName);
        }
        return result;
    }

}






