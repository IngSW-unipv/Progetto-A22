/**
 * 
 */
package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;

/**
 * Interrogazioni Asset
 * @author ME
 * @see Asset
 *
 */
public interface IAssetDAO {
	public ArrayList<Asset> selectAll();
	public ArrayList<Asset> selectByPrice(Asset assInput);
	public boolean insertAsset(Asset a);
	public boolean updateAsset(Asset oldA, Asset newA);
	public boolean updateAssetByPrice(Asset oldPrice, Asset newPrice);
}
