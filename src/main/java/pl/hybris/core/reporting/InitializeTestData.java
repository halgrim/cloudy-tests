package pl.hybris.core.reporting;

import com.jamesmurty.utils.XMLBuilder2;
import org.joda.time.DateTime;
import pl.hybris.core.threading.CurrentThreadDatabaseConnection;
import pl.hybris.core.threading.CurrentThreadTestData;
import pl.hybris.util.CommonUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by i323728 on 29.03.2016.
 */
public class InitializeTestData
{


    //CREATE TABLE tests(        test_id VARCHAR(50) NOT NULL,     test_name VARCHAR(512) NOT NULL,         start_timestamp TIMESTAMP,   report BLOB,      PRIMARY KEY ( test_id )         );
    //CREATE TABLE images(        image_id VARCHAR(50) NOT NULL,     image BLOB,      PRIMARY KEY ( image_id )         );

    public String initializeTestDataReport(String testClassName)
    {
        UUID idOne = UUID.randomUUID();
        String test_id = idOne.toString();
        DateTime dateTime = new DateTime();
        java.sql.Timestamp startDate = new Timestamp(dateTime.getMillis());

        String query = " insert into tests (test_id, test_name, start_timestamp)" + " values (?, ?, ?)";

        try
        {
            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query);
            preparedStmt.setString(1, test_id);

            preparedStmt.setString(2, testClassName);
            preparedStmt.setTimestamp(3, startDate);

            preparedStmt.execute();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return test_id;
    }

    public static void saveReportToDB()
    {
        CommonUtil.printMessage("Saving report after action");
        String query = " UPDATE tests SET report = ? WHERE test_id = ?";

        XMLBuilder2 localCopy = UniqueXMLReportGenerator.getCurrentReporter().getBuilder();
        //XMLBuilder2 appendedReport = localCopy.document();

        InputStream stream = new ByteArrayInputStream(localCopy.asString().getBytes(StandardCharsets.UTF_8));
        try
        {
            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query);

            preparedStmt.setBlob(1, stream);
            preparedStmt.setString(2, CurrentThreadTestData.getCurrentTestData());

            preparedStmt.execute();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
}
