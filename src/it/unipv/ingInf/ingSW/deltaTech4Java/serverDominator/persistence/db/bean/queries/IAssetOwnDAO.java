package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;

public interface IAssetOwnDAO {
	/**
	 * @return TUTTO IL CONTENUTO DELLA TABELLA ASSEToWN
	 */
	public ArrayList<AsetOwn> selectAll();
	
	/**
	 * @param assInput
	 * @return
	 */
	public ArrayList<Asset> selectByUserOwner(UserAccount assInput);
	
	/**
	 * @param assInput
	 * @return
	 */
	public ArrayList<UserAccount> selectByAssetOwn(AsetOwn assInput);
	
	/**
	 * @param a
	 * @return
	 */
	public boolean insertAssetOwn(AsetOwn a);
	
	/**
	 * @param newA
	 * @return
	 */
	public boolean updateAssetOwnByUserAccount(AsetOwn newA);

	
}
