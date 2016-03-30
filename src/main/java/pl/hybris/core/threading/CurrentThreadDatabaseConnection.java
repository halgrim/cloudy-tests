package pl.hybris.core.threading;

import pl.hybris.core.reporting.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by i323728 on 29.03.2016.
 */
public class CurrentThreadDatabaseConnection
{
    private static final ThreadLocal <Connection> threadLocal =
            new ThreadLocal < Connection > () {};

    public static void setConnection()
    {

        threadLocal.set(new DatabaseConnection().getConnect());

    }


    public static Connection getCurrentDatabaseConnection()
    {
        return threadLocal.get();
    }

    public static void closeConnection()
    {
        try
        {
            getCurrentDatabaseConnection().close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
