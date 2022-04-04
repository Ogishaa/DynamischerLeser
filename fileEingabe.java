package dynamischerLeser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class fileEingabe 
{
	public static void main(String[] args) 
	{
		String[] p = getPropertiesFromConfigFile();
		Connection c = getConnection(p);
		setAutoCommit(c, true);
		dynamischerLeser.createTable(p[3], p[4], c);
	}

	public static Connection getConnection(String[] p) 
	{

		String url = p[0];
		String user = p[1];
		String pass = p[2];

		try 
		{
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Verbindung erfolgreich hergestellt");
			return con;
		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void setAutoCommit(Connection c, boolean ToF) 
	{
		try {
			c.setAutoCommit(ToF);
		} catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Set Auto Commit funktioniert nicht!");
		}

	}

	public static String[] getPropertiesFromConfigFile() 
	{
		String[] props = new String[5];
		Properties prop = new Properties();
		String fileName = "test.cfg";
		try (FileInputStream fis = new FileInputStream(fileName)) 
		{
			prop.load(fis);
		} catch (FileNotFoundException ex) 
		{
			ex.printStackTrace();
		} catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		props[0] = prop.getProperty("url");
		props[1] = prop.getProperty("user");
		props[2] = prop.getProperty("pass");
		props[3] = prop.getProperty("absoluteFilePath");
		props[4] = prop.getProperty("databaseName");

		return props;
	}
}