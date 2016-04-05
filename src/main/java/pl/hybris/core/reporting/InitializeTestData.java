package pl.hybris.core.reporting;

import org.joda.time.DateTime;
import pl.hybris.constants.Global;
import pl.hybris.core.database.ActionObject;
import pl.hybris.core.database.PageObject;
import pl.hybris.core.threading.CurrentThreadDatabaseConnection;
import pl.hybris.core.threading.CurrentThreadTestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Created by i323728 on 29.03.2016.
 */
@SuppressWarnings("Duplicates")
public class InitializeTestData
{


    //CREATE TABLE tests(        testId MEDIUMINT NOT NULL AUTO_INCREMENT,     testName VARCHAR(512) NOT NULL,         startTimestamp TIMESTAMP NOT NULL,   report BLOB,      PRIMARY KEY ( testId )         );
    //CREATE TABLE images(        imageId MEDIUMINT NOT NULL AUTO_INCREMENT,     testId MEDIUMINT NOT NULL, image MEDIUMBLOB,      PRIMARY KEY ( imageId )         );
    //CREATE TABLE actions(        testId MEDIUMINT NOT NULL, className VARCHAR(128) NOT NULL, methodName VARCHAR(128) NOT NULL, arguments VARCHAR(256) NOT NULL, startTimestamp TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),  finishTimestamp TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),     returnValue VARCHAR(256) NOT NULL, screenshotBefore VARCHAR(512) NOT NULL  );
    //CREATE TABLE pageObjectActions(        testId MEDIUMINT NOT NULL, className VARCHAR(128) NOT NULL, methodName VARCHAR(128) NOT NULL, arguments VARCHAR(256) NOT NULL, startTimestamp TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),  finishTimestamp TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),     returnValue VARCHAR(256) NOT NULL, screenshotBefore VARCHAR(512) NOT NULL  );

    public int initializeTestDataReport(String testClassName)
    {
        DateTime dateTime = new DateTime();
        Timestamp startDate = new Timestamp(dateTime.getMillis());

        String query = " insert into tests (testName, startTimestamp)" + " values (?, ?)";

        int autoIncrementedTestID = 0;

        try
        {
            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, testClassName);
            preparedStmt.setTimestamp(2, startDate);

            preparedStmt.executeUpdate();

            if(true) {
                ResultSet rs = preparedStmt.getGeneratedKeys();
                rs.next();
                autoIncrementedTestID = rs.getInt(1);
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return autoIncrementedTestID;
    }

    public static void saveActionObjectToDB(final ActionObject action)
    {
        String query = " insert into actions (testId, className, methodName, arguments, startTimestamp, finishTimestamp, returnValue, screenshotBefore )"


                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query);
            preparedStmt.setInt(1, action.getTestID());
            preparedStmt.setString(2, action.getClassName());
            preparedStmt.setString(3, action.getMethodName());
            preparedStmt.setString(4, action.getArguments());
            preparedStmt.setTimestamp(5, action.getStartTime());
            preparedStmt.setTimestamp(6, action.getFinishTime());
            preparedStmt.setString(7, action.getReturnValue());
            preparedStmt.setString(8 ,action.getScreenshotBefore());


            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static void savePageObjectToDB(final PageObject pageObjectAction)
    {
        String query = " insert into pageObjectActions (testId, className, methodName, arguments, startTimestamp, finishTimestamp, returnValue, screenshotBefore )"


                + " values (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query);
            preparedStmt.setInt(1, pageObjectAction.getTestID());
            preparedStmt.setString(2, pageObjectAction.getClassName());
            preparedStmt.setString(3, pageObjectAction.getMethodName());
            preparedStmt.setString(4, pageObjectAction.getArguments());
            preparedStmt.setTimestamp(5, pageObjectAction.getStartTime());
            preparedStmt.setTimestamp(6, pageObjectAction.getFinishTime());
            preparedStmt.setString(7, pageObjectAction.getReturnValue());
            preparedStmt.setString(8 ,pageObjectAction.getScreenshotBefore());

            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveScreenshotToDB(final String fileName)
    {
        String query = " insert into images (testId, image)" + " values (?, ?)";
        FileInputStream fis;
        File file = new File(Global.SAVED_IMAGES_FOLDER + fileName);
        try
        {

            fis = new FileInputStream(file);

            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query);
            preparedStmt.setInt(1, CurrentThreadTestData.getCurrentTestData());
            preparedStmt.setBlob(2, fis);

            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
