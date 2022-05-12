package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;

/**
 * Obiettivi query
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivo
 *
 */
public interface IObiettiviDAO {
	
	/**
	 * Seleziona tutti gli obiettivi 
	 * @return Tutti gli obiettivi nel Db
	 */
	public ArrayList<Obiettivo> selectAll();
	
	/**
	 * Seleziona tutti gli obiettivi che hanno come ricompensa la Stessa 
	 * ricompensa dell'obiettivo argomento
	 * @param obRi
	 * @return Obiettivi
	 */
	public ArrayList<Obiettivo> selectByRicompensa(Obiettivo obRi);
	
	/**
	 * Inserisce obiettivo
	 * @param a
	 * @return
	 */
	public boolean insertObiettivo(Obiettivo a);
	
	/**
	 * Aggiorna Obiettivo Dato il Suo id
	 * @param newO
	 * @return
	 */
	public boolean updateObiettiviById( Obiettivo newO);
	
	/**
	 * Aggiorna Obiettivi per Ricompensa
	 * @param oldR
	 * veccio obiettivo
	 * @param newR
	 * nuovo obettivo
	 * @return
	 */
	public boolean updateRicompensaObiettivoByRicompensa(Obiettivo oldR, Obiettivo newR);
	
	/**
	 * seleziona un obiettivo Dato l'identificativo dell'Obiettivo
	 * @param Id
	 * @return
	 */
	public Obiettivo selectObiettiviById(Obiettivo Id);

}
