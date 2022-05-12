package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBObiettiviDOAFactory;

/**
 * Classe responsabile della creazione di {@link IObiettiviDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IObiettiviDAOFactory {
	
	/**
	 * Metodo che crea IObiettiviDAO che serve per accedere un obiettivo di default
	 * @return IObiettiviDAO
	 */
	public static IObiettiviDAO getIObiettiviDAO() {
		return DBObiettiviDOAFactory.getIObiettiviDAO();
	}
	
	
	/**
	 * Metodo che crea IObiettiviDAO che serve per accedere  
	 * alll'oggetto mappato (passato come argomento)
	 * @param ob
	 * oggetto mappato
	 * @return
	 * IObiettiviDAO che accede alla tabella che viene mappata dall'oggetto 
	 */
	public static IObiettiviDAO getIObiettiviDAO(Obiettivo ob) {
		return DBObiettiviDOAFactory.getIObiettiviDAO(ob);
	}
}
