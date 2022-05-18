package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn;

import java.sql.Connection;

public interface ISession {
	public void openSession();
	public void closeSession();
	public Connection getConnection(String connProperty);
}
