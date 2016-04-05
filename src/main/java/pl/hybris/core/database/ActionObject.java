package pl.hybris.core.database;

import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by i323728 on 05.04.2016.
 */
public class ActionObject
{

    private Integer testID;
    private String className;
    private String methodName;
    private String arguments;
    private Timestamp startTime;
    private Timestamp finishTime;
    private String returnValue;
    private String screenshotBefore;

    public Integer getTestID()
    {
        return testID;
    }

    public String getClassName()
    {
        return className;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public String getArguments()
    {
        return arguments;
    }

    public Timestamp getStartTime()
    {
        return startTime;
    }

    public Timestamp getFinishTime()
    {
        return finishTime;
    }

    public String getReturnValue()
    {
        return returnValue;
    }

    public String getScreenshotBefore()
    {
        return screenshotBefore;
    }

    public ActionObject setTestID(final Integer testID)
    {
        this.testID = testID;
        return this;
    }

    public ActionObject setClassName(final String className)
    {
        this.className = className; return this;
    }

    public ActionObject setMethodName(final String methodName)
    {
        this.methodName = methodName; return this;
    }

    public ActionObject setArguments(final String arguments)
    {
        this.arguments = arguments;
        return this;
    }

    public ActionObject setStartTime(final DateTime startTime)
    {
        Timestamp startDate = new Timestamp(startTime.getMillis());
        this.startTime = startDate;
        return this;
    }

    public ActionObject setFinishTime(final DateTime finishTime)
    {
        Timestamp finishDate = new Timestamp(finishTime.getMillis());
        this.finishTime = finishDate;
        return this;
    }

    public ActionObject setReturnValue(final String returnValue)
    {
        this.returnValue = returnValue;
        return this;
    }


    public ActionObject setScreenshotBefore(final String screenshotBefore)
    {
        this.screenshotBefore = screenshotBefore;
        return this;
    }
}
