package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn;

import java.sql.Connection;
import java.util.Hashtable;

/**
 * Classe che ha l'obiettivo di migliorare le prestazioni 
 * quando si vogliono fare operazioni sulla persistenza 
 * (semplicemente evita lo stabilire effettivamente ogni volta la connessione al db
 * ma tiene traccia delle connessioni che passa ai suoi utilizzatori)
 * @author TawaHabib
 *
 */
public class ConnectionFactory implements ISession{
	
	private Hashtable<String, Connection> conns;
	
	public ConnectionFactory() {
		this.conns=new Hashtable<String, Connection>();
	}

	 @Override
	/**
	 * Metodo che prende la connessione che utilizza come file di proprità
	 * il file passato per argomento
	 * @param pathToOpenConnection
	 * file delle proprità della connsione
	 * @return
	 * Connection
	 */
	public Connection getConnection(String pathToOpenConnection) {
		if(this.conns.containsKey(pathToOpenConnection))
			if(DbConnection.isOpen(this.conns.get(pathToOpenConnection)))
				return this.conns.get(pathToOpenConnection);
			else 
				conns.put(pathToOpenConnection,
						DbConnection.startConnection(this.conns.get(pathToOpenConnection), pathToOpenConnection));
		else {
			Connection conn=null;conn=DbConnection.startConnection(conn, pathToOpenConnection);
			if(conn!=null)
				conns.put(pathToOpenConnection,conn);
		}
		return this.conns.get(pathToOpenConnection);
	}
	
	/**
	 * Metodo che chiude la connessione che utilizza 
	 * le proprità del file passato come argomento
	 * @param ConnectionToClose
	 * File argomento di cui si vogliono eleminare le connessioni a lui associate
	 */
	public void closeConnection(String ConnectionToClose) {
		DbConnection.closeConnection(this.conns.get(ConnectionToClose));
		this.conns.remove(ConnectionToClose);
	}
	
	/**
	 * Metodo che chiude tutte le connessioni aperte
	 */
	public void closeAllConnections() {
		for(String c:conns.keySet()) {
			this.closeConnection(c);
			
		}
	}

	@Override
	public void openSession() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeSession() {
		this.closeAllConnections();		
	}
}
