package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
/**
 * AssetOwn query
 * @author TawaHabib
 * @version 1.0
 * @see Asset
 * @see UserAccount
 * @see AssetOwn
 */
public interface IAssetOwnDAO {
	/**
	 * @return tutti gli asset possedudi dai singoli giocatori
	 */
	public ArrayList<AssetOwn> selectAll();
	
	/**
	 * seleziona tutti gli asset posseduti dal UserAcocunt
	 * @param user
	 * account del giocatore di cui si vogliono conoscere gli assetOwn
	 * @return tutti gli asset posseduti da assInput
	 */
	public ArrayList<AssetOwn> selectByUserOwner(UserAccount user);
	
	/**
	 * Seleziona Tutti i possessori dell'asset 
	 * @param assInput
	 * @return
	 */
	public ArrayList<UserAccount> selectByAssetOwnd(Asset assInput);
	
	/**
	 * Inserisce asset posseduto
	 * se l'id dell' asset posseduto � gi� esistente alline la quantita con la nuova 
	 * Quantit� nell'asset e se essa � nulla elimina l'asset OWm
	 * @param a
	 * @return
	 */
	public boolean insertAssetOwn(AssetOwn a);
	
	/**
	 * Aggiorna Asset Posseduto Dato il suo id
	 * @param newA
	 * @return
	 */
	public boolean updateQuantityAssetOwnById(AssetOwn newA);
	
}
