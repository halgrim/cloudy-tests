package pl.hybris.core.threading;

/**
 * Created by i323728 on 29.03.2016.
 */
public class CurrentThreadTestData
{
    private static final ThreadLocal <Integer> threadLocal =
            new ThreadLocal < Integer > () {};

    public static void setCurrentTestRunID(int testData)
    {
        threadLocal.set(testData);
    }

    public static Integer getCurrentTestRunID()
    {
        return threadLocal.get();
    }
}
