package sreverDominator.persistence.prove.util;

import java.sql.Connection;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.DbConnection;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.ISession;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;

public class PrestazioneConnessione {
	public static void main(String[] args) {
		ISession s=SessionFactory.getSession();
		Connection c=null;
		c=DbConnection.startConnection(c, "resources/config/persistence/dataBase/connWith_sd_sys");
		DbConnection.closeConnection(c);
		c=null;
		System.out.println("Connessione normale");
		for(int i=0;i<100;i++) {
			LocalTime t1=LocalTime.now();
			c=DbConnection.startConnection(c, "resources/config/persistence/dataBase/connWith_sd_sys");
			System.out.println("apertura connessione "+(i+1)+" volta.tempo(nanos): "+t1.until(LocalTime.now(), ChronoUnit.NANOS));
			//*
			try {
				
				Thread.sleep(500);
				DbConnection.closeConnection(c);
				c=null;
			} catch (InterruptedException e) {
				continue;
			}
			//*/
		}
		
		System.out.println("\n\nConnessione con ISesion");
		for(int i=0;i<100;i++) {
			LocalTime t1=LocalTime.now();
			c=s.getConnection("resources/config/persistence/dataBase/connWith_sd_sys");
			System.out.println("apertura connessione "+(i+1)+" volta. tempo(nanos): "+t1.until(LocalTime.now(), ChronoUnit.NANOS));
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				continue;
			}
		}
		s.closeSession();
		
	}
}
