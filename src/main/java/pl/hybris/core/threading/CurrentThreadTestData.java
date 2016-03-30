package pl.hybris.core.threading;

/**
 * Created by i323728 on 29.03.2016.
 */
public class CurrentThreadTestData
{
    private static final ThreadLocal <String> threadLocal =
            new ThreadLocal < String > () {};

    public static void setCurrentTestData(String testData)
    {
        threadLocal.set(testData);
    }

    public static String getCurrentTestData()
    {
        return threadLocal.get();
    }
}
