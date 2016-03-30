package pl.hybris.core.reporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by i323728 on 29.03.2016.
 */
public class DatabaseConnection
{
	public Connection getConnect()
	{
		return connect;
	}

	private Connection connect = null;
	public DatabaseConnection()
	{
		String url = "jdbc:mysql://localhost:3306/testresults?autoReconnect=true&useSSL=false";
		String user = "root";
		String password = "";

		try
		{
			connect = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}



}
