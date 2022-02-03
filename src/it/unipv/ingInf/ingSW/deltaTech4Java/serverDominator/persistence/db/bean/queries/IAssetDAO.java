package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;

/**
 * Asset query
 * @author ME
 * @version 1.0
 * @see Asset
 */
public interface IAssetDAO {
	
	/**
	 * Seleziona tutti gli asset
	 * @return
	 */
	public ArrayList<Asset> selectAll();
	
	/**
	 * Seleziona Gli asset Che hanno un prezzo uguale al prezzo dell'asset argomeno
	 * @param assInput
	 * @return
	 */
	public ArrayList<Asset> selectByPrice(Asset assInput);
	
	/**
	 * Inserisce asset
	 * @param a
	 * @return
	 */
	public boolean insertAsset(Asset a);
	
	/**
	 * Aggiorna Asset
	 * @param oldA
	 * Vecchio asset
	 * @param newA
	 * Nuovo asset
	 * @return
	 */
	public boolean updateAssetById(Asset oldA, Asset newA);
	
	/**
	 * Aggiorna prezzo asset
	 * @param oldPrice
	 * Veccio prezzo
	 * @param newPrice
	 * Nuovo prezo
	 * @return
	 */
	public boolean updateAssetByPrice(Asset oldPrice, Asset newPrice);
	
	/**
	 * Reupera I dati dell'asset id Passato per argomento
	 * @param Id
	 * @return
	 */
	public Asset selectAssetById(Asset Id);
}
