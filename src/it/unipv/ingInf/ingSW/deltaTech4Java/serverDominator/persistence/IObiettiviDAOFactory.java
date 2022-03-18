package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBObiettiviDOAFactory;

/**
 * Classe responsabile della creazione di {@link IObiettiviDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IObiettiviDAOFactory {
	
	public static IObiettiviDAO getIObiettiviDAO() {
		return DBObiettiviDOAFactory.getIObiettiviDAO();
	}
	public static IObiettiviDAO getIObiettiviDAO(Obiettivi ob) {
		return DBObiettiviDOAFactory.getIObiettiviDAO(ob);
	}
}
