package pl.hybris.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import pl.hybris.core.models.ActionObject;
import pl.hybris.core.rest.PostHelper;
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
        CommonUtil.printMessage("------------------------------------------------------");
        return saveActionData(joinPoint, "action", true);
    }

    @Around("execution(* pl.hybris.backoffice..pageobjects..*(..))")
    public Object aroundPageObject(ProceedingJoinPoint joinPoint) throws Throwable
    {
        CommonUtil.printMessage("------------------------------------------------------");
        return saveActionData(joinPoint, "pageAction", false);
    }

    private Object saveActionData(ProceedingJoinPoint joinPoint, String type, boolean doScreenShot) throws Throwable
    {
        ActionObject action = new ActionObject();
        Integer testRunID = CurrentThreadTestData.getCurrentTestRunID();
        String arguments = "" ;

        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++)
        {
            arguments = arguments + args[i].toString() + ",";
        }

        if (args.length > 0 )
        {
            arguments.substring(0, arguments.length() - 1);
        }

        String fileName = "empty";

        if (doScreenShot)
        {
            fileName = CommonUtil.doAPrintScreen(CurrentThreadDriver.getCurrentDriver(), "BeforeActionScreenshot");
        }

        action
                .setTestRunID(testRunID)
                .setActionType(type)
                .setClassName(joinPoint.getTarget().getClass().getName())
                .setMethodName(joinPoint.getSignature().getName())
                .setArguments(arguments)
                .setStartTime(new DateTime())
                .setScreenshotBefore(fileName);


        Object result = joinPoint.proceed();

        action
                .setFinishTime(new DateTime())
                .setReturnValue(String.valueOf(result));

        PostHelper postResult = new PostHelper();
        postResult.postAction(action);

        //InitializeTestData.saveActionObjectToDB(action);


        if (!fileName.contains("empty"))
        {
            postResult.postImage(fileName);
            //InitializeTestData.saveScreenshotToDB(fileName);
        }

        return result;

    }

}






