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
	 * @return
	 */
	public ArrayList<Asset> selectAll();
	
	/**
	 * @param assInput
	 * @return
	 */
	public ArrayList<Asset> selectByPrice(Asset assInput);
	
	/**
	 * @param a
	 * @return
	 */
	public boolean insertAsset(Asset a);
	
	/**
	 * @param oldA
	 * @param newA
	 * @return
	 */
	public boolean updateAssetById(Asset oldA, Asset newA);
	
	/**
	 * @param oldPrice
	 * @param newPrice
	 * @return
	 */
	public boolean updateAssetByPrice(Asset oldPrice, Asset newPrice);
	
	/**
	 * @param Id
	 * @return
	 */
	public Asset selectAssetById(Asset Id);
}
