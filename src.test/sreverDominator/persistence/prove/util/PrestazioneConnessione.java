package sreverDominator.persistence.prove.util;

import java.sql.Connection;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.DbConnection;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.ISession;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;

public class PrestazioneConnessione {
	public static void main(String[] args) throws InterruptedException {
		int n=100;
		int attesa=100
				;
		ISession s=SessionFactory.getSession();
		Connection c=null;
		c=DbConnection.startConnection(c, "resources/config/persistence/dataBase/connWith_sd_sys");
		DbConnection.closeConnection(c);
		c=null;
		System.out.println("Connessione normale");
		LocalTime t1=LocalTime.now();
		for(int i=0;i<n;i++) {
			c=DbConnection.startConnection(c, "resources/config/persistence/dataBase/connWith_sd_sys");
			DbConnection.closeConnection(c);
			//System.out.println("\tApertura connessione "+(i)+". Tempo: "+((t1.until(LocalTime.now(), ChronoUnit.NANOS)/1000000)-i*attesa));
			Thread.sleep(attesa);
		
		}
		Long p1=t1.until(LocalTime.now(), ChronoUnit.NANOS)/1000000;
		System.out.println("\tApertura connessione "+(n)+" volte. Tempo per aprire le connessioni(milli): "+(p1-(attesa*n)));

		System.out.println("\nConnessione con ISesion");
		LocalTime t2=LocalTime.now();

		for(int i=0;i<n;i++) {
			c=s.getConnection("resources/config/persistence/dataBase/connWith_sd_sys");
			Thread.sleep(attesa);
			//System.out.println("\tApertura connessione "+(i)+". Tempo: "+((t2.until(LocalTime.now(), ChronoUnit.NANOS)/1000000)-i*attesa));


		}
		Long p2=t2.until(LocalTime.now(), ChronoUnit.NANOS)/1000000;
		System.out.println("\tApertura connessione "+(n)+" volte. Tempo per aprire le connessioni(milli): "+(p2-(attesa*n)));
		Double d=(((( p1.doubleValue()-(attesa*n))-(p2.doubleValue()-(attesa*n)))/(p2.doubleValue()-(attesa*n)))*100);
		System.out.println("\nISession migliore di "+(p1-p2)+" millis= "+d.intValue()+"%");
		
		s.closeSession();
		
	}
}
