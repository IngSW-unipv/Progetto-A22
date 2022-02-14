package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBObiettiviDOAFactory;

/**
 * @author TawaHabib
 *
 */
public class IObiettiviDAOFactory {
	
	public static IObiettiviDAO getIObiettiviDAO() {
		return DBObiettiviDOAFactory.getIObiettiviDAO();
	}
	public static IObiettiviDAO getIObiettiviDAO(Obiettivi ob) {
		return DBObiettiviDOAFactory.getIObiettiviDAO(ob);
	}
}
