package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * stabilire connessione con il db
 * @author ME
 * @version 1.0
 * @see FetchPropertyFromFile
 * @see Properties
 * @see Connection
 */
public class DbConnection {

	/**
	 * Avvio onnessione
	 * @param conn
	 * Connection
	 * @param popFile
	 * Percorso file delle prop
	 * @return Connessione
	 */
	public static Connection startConnection(Connection conn, String popFile)
	{
		String DbDriver=null;
		String DbURL=null;
		String username=null;
		String password=null;
		Properties prop=FetchPropertyFromFile.loadPropertiesFile(popFile);
		
		try {
			DbDriver	=	prop.getProperty("driver");
			DbURL		=	prop.getProperty("url");
			username	=	prop.getProperty("username");
			password	=	prop.getProperty("password");
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
	/**
	 * Start Connection without Property File
	 * @param conn
	 * Connessione
	 * @param DbDriver
	 * J-drive
	 * @param DbURL
	 * schema location
	 * @param username
	 * Username to use
	 * @param password
	 * Password to use
	 * @return connection opened 
	 */
	public static Connection startConnection(Connection conn, String DbDriver,String DbURL,String username,String password )
	{
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
	
	/**
	 * stabilire se la connessione passata � aperta o no
	 * @param conn
	 * connesione
	 * @return boolean
	 * verro: conn is open; false:conn is not open
	 */
	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}
	
	/**
	 * Method to close a connection
	 * @param conn
	 * Connection to close
	 * @return Closed Connection
	 */
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

}
