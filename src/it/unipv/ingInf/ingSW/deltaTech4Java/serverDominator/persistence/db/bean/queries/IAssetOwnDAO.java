package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;
/**
 * AssetOwn query
 * @author ME
 * @version 1.0
 * @see Asset
 * @see UserAccount
 * @see AsetOwn
 */
public interface IAssetOwnDAO {
	/**
	 * @return tutti gli asset possedudi dai singoli giocatori
	 */
	public ArrayList<AsetOwn> selectAll();
	
	/**
	 * @param assInput
	 * account del giocatore di cui si vogliono conoscere gli assetOwn
	 * @return tutti gli asset posseduti da assInput
	 */
	public ArrayList<Asset> selectByUserOwner(UserAccount assInput);
	
	/**
	 * @param assInput
	 * @return
	 */
	public ArrayList<UserAccount> selectByAssetOwnd(AsetOwn assInput);
	
	/**
	 * @param a
	 * @return
	 */
	public boolean insertAssetOwn(AsetOwn a);
	
	/**
	 * @param newA
	 * @return
	 */
	public boolean updateAssetOwnId(AsetOwn newA);
	
}
