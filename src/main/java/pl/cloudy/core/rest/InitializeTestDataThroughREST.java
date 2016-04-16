package pl.cloudy.core.rest;

public class InitializeTestDataThroughREST
{
//    public int initializeTestDataReport(String testClassName)
//    {
//        int testRunID = 0;
//        try
//        {
//
//            final String saveTestIfDoNotExistQuery = Queries.saveTestIfDoNotExist;
//
//            Connection conn = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection();
//
//            PreparedStatement stmt = conn.prepareStatement(saveTestIfDoNotExistQuery, Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, testClassName);
//            //stmt.setString(2, testClassName);
//            stmt.executeUpdate();
//
//
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.getFetchSize() != 1 && rs.getFetchSize() != 0)
//            {
//                throw new SQLException("This query should return 1 or 0");
//            }
//
//            String getTestId = "SELECT testId FROM tests WHERE testName = '" + testClassName + "'";
//
//            stmt = conn.prepareStatement(getTestId);
//            stmt.executeQuery();
//
//            rs = stmt.getResultSet();
//            int testID = 0;
//            if(rs.next())
//            {
//                testID = rs.getInt(1);
//            }
//
//            if (testID == 0 )
//
//            {
//                throw new SQLException("There should be some test with name " +testClassName);
//            }
//
//            DateTime dateTime = new DateTime();
//            Timestamp startDate = new Timestamp(dateTime.getMillis());
//
//            String query = "insert into testRun (testID, startTimestamp)" + " values (?, ?)";
//
//            PreparedStatement preparedStmt = CurrentThreadDatabaseConnection.getCurrentDatabaseConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            preparedStmt.setInt(1, testID);
//            preparedStmt.setTimestamp(2, startDate);
//
//            preparedStmt.executeUpdate();
//
//            rs = preparedStmt.getGeneratedKeys();
//            if(rs.next())
//            {
//                testRunID = rs.getInt(1);
//            }
//
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return  testRunID;
//
//    }
}
