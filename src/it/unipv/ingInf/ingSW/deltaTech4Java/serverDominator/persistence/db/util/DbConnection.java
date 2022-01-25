package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	public static Connection startConnection(Connection conn, String popFile)
	{
		String DbDriver=null;
		String DbURL=null;
		String username=null;
		String password=null;
		
		try {
			
			DbDriver=loadPropertiesFile(popFile).getProperty("driver");
			DbURL=loadPropertiesFile(popFile).getProperty("url");
			username=loadPropertiesFile(popFile).getProperty("username");
			password=loadPropertiesFile(popFile).getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		if ( isOpen(conn) )
			closeConnection(conn);
		try
		{

			Class.forName(DbDriver);

			conn = DriverManager.getConnection(DbURL, username, password);// Apertura connessione

		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	
	
	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}

	
	
	public static Connection closeConnection(Connection conn)
	{
		if ( !isOpen(conn) )
			return null;
		try
		{
			conn.close();
			conn = null;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	public static Properties loadPropertiesFile(String fls){
		try {
			Properties prop= new Properties();
			FileInputStream in = new FileInputStream(fls);
			prop.load(in);
			in.close();
			return prop;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		try {
			Properties prop= new Properties();
			FileInputStream in = new FileInputStream("resorces/config.txt");
			prop.load(in);
			if(prop!=null) {
				System.out.println("file apposto");
				System.out.println(
						"driver " + prop.getProperty("driver")+ "\n "
						+ "url "+ prop.getProperty("url")+ "\n "
						+ "username "+ prop.getProperty("username")+ "\n "
						+ "password "+ prop.getProperty("password")+ "\n ");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
