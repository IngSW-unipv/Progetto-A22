package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Obiettivi;

/**
 * Obiettivi query
 * @author ME
 * @version 1.0
 * @see Obiettivi
 *
 */
public interface IObiettiviDAO {
	
	/**
	 * Seleziona tutti gli obiettivi 
	 * @return Tutti gli obiettivi nel Db
	 */
	public ArrayList<Obiettivi> selectAll();
	
	/**
	 * Seleziona tutti gli obiettivi che hanno come ricompensa la Stessa 
	 * ricompensa dell'obiettivo argomento
	 * @param obRi
	 * @return Obiettivi
	 */
	public ArrayList<Obiettivi> selectByRicompensa(Obiettivi obRi);
	
	/**
	 * Inserisce obiettivo
	 * @param a
	 * @return
	 */
	public boolean insertObiettivo(Obiettivi a);
	
	/**
	 * Aggiorna Obiettivo Dato il Suo id
	 * @param oldO
	 * @param newO
	 * @return
	 */
	public boolean updateObiettiviById(Obiettivi oldO, Obiettivi newO);
	
	/**
	 * Aggiorna Obiettivi per Ricompensa
	 * @param oldR
	 * veccio obiettivo
	 * @param newR
	 * nuovo obettivo
	 * @return
	 */
	public boolean updateAssetByRicompensa(Obiettivi oldR, Obiettivi newR);
	
	/**
	 * seleziona un obiettivo Dato l'identificativo dell'Obiettivo
	 * @param Id
	 * @return
	 */
	public Obiettivi selectObiettiviById(Obiettivi Id);

}
