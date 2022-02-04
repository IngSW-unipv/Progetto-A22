package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;


/**
 * @author ME
 * @version 1.0
 * @see Connection
 * @see DbConnection
 * @see ScriptRunner
 *
 */
public class ExecuteSQLfiel {

	/**
	 * Esecuzione di un file SQL
	 * @param percorsoSQLfile
	 * percorso del file sql da eseguire 
	 * @param percorsoPropertyConnec
	 * percorso del file delle proprità della connessione
	 * @return
	 */
	public static boolean  executeSqlFile(String percorsoSQLfile, String percorsoPropertyConnec) {
		boolean resul=true;
		Connection conn=null;
		conn=DbConnection.startConnection(conn,percorsoPropertyConnec);
		try {
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			runner.runScript(new BufferedReader(new FileReader(percorsoSQLfile)));
			
		} catch (Exception e) {
			resul=false;
			e.printStackTrace();
		}
		
		
		DbConnection.closeConnection(conn);
		return resul;
	}
}
