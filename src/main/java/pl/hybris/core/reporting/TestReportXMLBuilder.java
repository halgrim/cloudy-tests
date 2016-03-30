package pl.hybris.core.reporting;

import com.jamesmurty.utils.XMLBuilder2;


/**
 * Created by i323728 on 22.03.2016.
 */
public class TestReportXMLBuilder
{
    private static int level;

    public static XMLBuilder2 getBuilder()
    {
        return builder;
    }

    public static void setBuilder(final XMLBuilder2 builder)
    {
        TestReportXMLBuilder.builder = builder;
    }

    private static XMLBuilder2 builder;


    public TestReportXMLBuilder()
    {
    }

    public TestReportXMLBuilder initialize()
    {
        builder = XMLBuilder2.create("Test").a("Version", "6.1");
        level = 0;
        return this;
    }


    public static int getLevel()
    {
        return level;
    }

    public static int increaseLevel()
    {
        TestReportXMLBuilder.level = level + 1;
        return level;
    }

    public static int decreaseLevel()
    {
        TestReportXMLBuilder.level = level - 1;
        return level;
    }

}