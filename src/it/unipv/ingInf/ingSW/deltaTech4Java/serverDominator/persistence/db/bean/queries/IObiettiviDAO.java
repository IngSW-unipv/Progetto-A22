package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Obiettivi;

public interface IObiettiviDAO {
	
	/**
	 * @return
	 */
	public ArrayList<Obiettivi> selectAll();
	
	/**
	 * @param obRi
	 * @return
	 */
	public ArrayList<Obiettivi> selectByRicompensa(Obiettivi obRi);
	
	/**
	 * @param a
	 * @return
	 */
	public boolean insertObiettivo(Obiettivi a);
	
	/**
	 * @param oldO
	 * @param newO
	 * @return
	 */
	public boolean updateObiettiviById(Obiettivi oldO, Obiettivi newO);
	
	/**
	 * @param oldR
	 * @param newR
	 * @return
	 */
	public boolean updateAssetByRicompensa(Obiettivi oldR, Obiettivi newR);
	
	/**
	 * @param Id
	 * @return
	 */
	public Obiettivi selectObiettiviById(Obiettivi Id);

}
