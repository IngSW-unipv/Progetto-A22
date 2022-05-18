package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.scriptRunner;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.SQLException;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.exeptions.DataBaseException;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.DbConnection;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;


/**
 * @author TawaHabib
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
	 * @return boolean
	 */
	public static boolean  executeSqlFile(String percorsoSQLfile, String percorsoPropertyConnec) throws NoSuchFileException, IOException, DataBaseException,SQLException {
		boolean resul=true;
		Connection conn=null;
		conn=SessionFactory.getSession().getConnection(percorsoPropertyConnec);
		ScriptRunner runner = new ScriptRunner(conn, false, false);
		runner.runScript(new BufferedReader(new FileReader(percorsoSQLfile)));
		DbConnection.closeConnection(conn);
		return resul;
	}
}
