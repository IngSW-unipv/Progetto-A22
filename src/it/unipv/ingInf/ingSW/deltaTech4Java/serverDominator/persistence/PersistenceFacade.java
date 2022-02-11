package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

/**
 * <h1>La facciata a cui si deve riferire se si vogliono fare operazione sulla persistenza </h1></br>
 * @author ME 
 * @version 1.0
 * @see IAssetDAO
 * @see IAssetOwnDAO
 * @see IObiettiviDAO 
 * @see IObiettiviUserDAO 
 * @see IUserAccountDAO 
 *
 */
public class PersistenceFacade{
	private IAssetDAO asset;
	private IAssetOwnDAO assetOwn;
	private IObiettiviDAO obiettivi;
	private IObiettiviUserDAO obiettiviUser;
	private IUserAccountDAO userAccount;
	public static PersistenceFacade instance;
	
	private PersistenceFacade() {
		this.asset=IAssetDAOFactory.getIAssetDAO();
		this.assetOwn=IAssetOwnDAOFactory.getIAssetDAO();
		this.obiettivi=IObiettiviDAOFactory.getIObiettiviDAO();
		this.obiettiviUser=IObiettiviUserDAOFactory.getIObiettiviUserDAO();
		this.userAccount=IUserAccountDAOFactory.getUserAccountDAO();
		instance=this;
	}
	
	public static PersistenceFacade getInstance() {
		if(instance==null)
			instance=new PersistenceFacade();
		return instance;
	}
	
	
	/**
	 * Seleziona gli asset in base al prezzo 
	 * @param assInput
	 * E' sufficente che contenga almeno il prezzo
	 * @return
	 * Lista asset che hanno lo stesso prezzo dell'asset argomento
	 */
	public ArrayList<Asset> selectAssetByPrice(Asset assInput){
		return asset.selectByPrice(assInput);
	}
	 
	/**
	 * Inserisce asset
	 * @param a
	 * Asset da inserire
	 * @return
	 * vero se asset � stato inserito con sucesso 
	 * </br>falso se l'operazione non � andata a buon fine
	 */
	public boolean insertAsset(Asset a) {
		return asset.insertAsset(a);
	}
	
	/**
	 * Aggoirna l'asset memorizzato che ha lo stetto id dell'asset argomento.
	 * </br>Falisce se l'id dell'asset passato non esiste nel database
	 * @param newA
	 * Asset da aggiornare
	 * @return
	 * vero se asset � stato inserito con sucesso 
	 * </br>falso se l'operazione non � andata a buon fine
	 */
	public boolean updateAssetById(Asset newA) {
		return asset.updateAssetById(newA);
	}
	
	/**
	 * Aggiorna il prezzo degli asset che habbo un determinato prezzo
	 * @param oldPrice
	 * Prezzo da aggiornare 
	 * @param newPrice
	 * prezzo aggiornato
	 * @return
	 * vero se asset � stato inserito con sucesso 
	 * </br>falso se l'operazione non � andata a buon fine
	 */
	public boolean updatePriceAssetByPrice(Asset oldPrice, Asset newPrice) {
		return asset.updatePriceAssetByPrice(oldPrice, newPrice);
	}
	
	/**
	 * Seleziona l'asset in base all'id 
	 * @param id
	 * Id dell'asset da selezionare </br> E' sufficiente che questo contega solamente l'identificativo
	 * @return
	 * Asset completo
	 */
	public Asset selectAssetByAssetId(Asset id) {
		return asset.selectAssetById(id);
	}
	
	/**
	 * Seleziona tutti gli user account username che possiedono l'asset argomento
	 * @param assInput
	 * Asset di cui si vogliono conoscer i possessori
	 * @return
	 * Lista user Accounrt che possiedono l'asset</br>
	 * Gli userAccount contengono solo lo username
	 */
	public ArrayList<UserAccount> selectUsersOwnerByAssetOwnd(Asset assInput){
		return assetOwn.selectByAssetOwnd(assInput);
	}
	
	/**
	 * Inserisce Asset in possesso dal giocatpre 
	 * @see AsetOwn
	 * @param a
	 * AssetOwn che si vole inserire
	 * @return
	 *  Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 * 
	 */
	public boolean insertAssetOwn(AsetOwn a){
		return assetOwn.insertAssetOwn(a);
	}
	
	/**
	 * Aggiorna quantita di un'asset posseduto dal giocatore 
	 * @see AsetOwn
	 * @param newA
	 * 
	 * @return
	 *  Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean updateQuantityAssetOwnByAssetOwnId(AsetOwn newA) {
		return assetOwn.updateQuantityAssetOwnById(newA);
	}
	
	/**
	 * Seleziona gli obiettivi che hanno la stessa ricompensa dell'obiettivo argomento
	 * @param obRi
	 * </br>Obiettivo argomento; � sifficiente che abbia solo la ricompensa settata
	 * @return
	 * Lista Obiettivi che hanno la stessa ricompenza dell'obiettivo argomento
	 */
	public ArrayList<Obiettivi> selectObiettiviByRicompensa(Obiettivi obRi){
		this.obiettivi=IObiettiviDAOFactory.getIObiettiviDAO(obRi);
		return obiettivi.selectByRicompensa(obRi);
	}
	
	/**
	 * Insericsi obiettivo
	 * @param a
	 * </br>Obiettivo da inserire 
	 * @return
	 * Vero se l'operazione � andata a buon fine </br>
	 * Falso se loperazione � fallita
	 */
	public boolean insertObiettivo(Obiettivi a){
		this.obiettivi=IObiettiviDAOFactory.getIObiettiviDAO(a);
		return this.obiettivi.insertObiettivo(a);
	}
	
	/**
	 * Aggiorna Dati dell'obiettivo argomento; 
	 * </br>Id dell'obiettivo deve esistere nel database
	 * @param newO
	 * </br> Obiettivo da aggiornare
	 * @return
	 * Vero se l'operazion � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean updateObiettiviByObiettivoId( Obiettivi newO){
		this.obiettivi=IObiettiviDAOFactory.getIObiettiviDAO(newO);
		return this.obiettivi.updateObiettiviById(newO);
	}
	
	/**
	 * Aggiorna ricompensa obiettivi che hanno una determinata ricompensa
	 * @param oldR 
	 * </br>Ricompensa vecchia 
	 * @param newR
	 * </br>Ricompensa nuova
	 * @return
	 * Vero se l'aggiornamento � andato a buon fine 
	 * </br>Falso se l'aggiornamento � fallito
	 */
	public boolean updateRicompensaObiettivoByRicompensa(Obiettivi oldR, Obiettivi newR){
		
		return this.obiettivi.updateRicompensaObiettivoByRicompensa(oldR, newR);
		
	}
	
	/**
	 * Seleziona biettivo dal id dell'obiettivo
	 * @param Id
	 * </br>Obiettivo da selezionare;
	 * </br>� sufficiente che abbia almeno l'id Settato
	 * @return
	 * Obiettivo Completo di tutte le informazioni
	 */
	public Obiettivi selectObiettiviByObiettiviId(Obiettivi Id) {
		this.obiettivi=IObiettiviDAOFactory.getIObiettiviDAO(Id);
		return this.obiettivi.selectObiettiviById(Id);
	}
	
	/**
	 * Seleziona gli obiettivi assegnati allo userAccount Argomento
	 * @param accInput
	 * </br>UserAccount di cui si cogliono conoscere gli obiettivi
	 * @return
	 * Lista Obiettivi del giocatore
	 * </br>null se qualcosa � andato storto
	 */
	public ArrayList<ObiettiviUser> selectObiettiviUserByUserId(UserAccount accInput){
		return this.userAccount.getObiettiviUserByUserAccount(accInput);
	}
	
	/**
	 * Uelezione tutti gli user account che hanno ssegnato un determinato obiettivo
	 * @param obInput
	 * </br>Obeittivo di cui si vogliono conoscere gli user account a lui assegnati
	 * @return
	 * Lista UserAccount che hanno l'obiettivoargomento; 
	 * </br> Si conosce solo lo username degli userAccount
	 * </br> lista nulla se l'operazione non � andata abuon fine
	 */
	public ArrayList<ObiettiviUser> selectObiettiviUserByObiettiviId(Obiettivi obInput){
		return this.obiettiviUser.selectByObiettiviId(obInput);
	}
	
	/**
	 * Assegna Un'obiettivo a un user account
	 * @param o
	 * @see ObiettiviUser
	 * @return
	 * Vero se l'assegnazione � andata a buon fine 
	 * Fasso se l'assegnazione � fallita
	 */
	public boolean insertObiettiviUser(ObiettiviUser o){
		return this.obiettiviUser.insertObiettiviUser(o);
	}
	
	/**
	 * Aggiorna lo stato dell'obiettivo user
	 * @param newOU
	 * </br>Obiettivo user di cui si vuole aggiornare lo stato 
	 * </br>Esso deve contenere lo ObiettiviUserId corretto e il nuovo stato
	 * @see ObiettiviUserId
	 * @see ObiettiviUser
	 * @return
	 * Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean updateStatoObiettiviUserbyObiettiviUserId(ObiettiviUser newOU) {
		return this.obiettiviUser.updateStatoObiettiviUserbyId(newOU);
	}
	
	/**
	 * Inserisci nuovo user acount;
	 * @param us
	 * vuser Account che si vuole inserire
	 * @return
	 * Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean insetUserAccount(UserAccount us){
		return this.userAccount.insetUserAccount(us);
	}
	
	/**
	 * Agiorna tutti i dati dello user Account </br> 
	 * Non puo aggiornare ne username ne password;
	 * </br> se l'utente non esiste nel data base lo inserisce
	 * @param us
	 * @return
	 * Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean updateUserAccount(UserAccount us){
		return this.userAccount.updateUserAccount(us);
	}
	
	/**
	 * Aggiorna Username dello user Account passato per argomento
	 * @param us
	 * </br> UserAccount con vecchio username e password aggiornata di cuoi si vuole aggiornare username
	 * @param newUsername
	 * </br> Nuovo username da sostituire a quello vecchio
	 * @return
	 * Vero se l'operazione � andata a buon fine 
	 * </br>Falso se l'operazione � fallita
	 */
	public boolean updateUserAccountUsername(UserAccount us, String newUsername){
		return this.userAccount.updateUserAccountUsername(us, newUsername);
	}
	
	/**
	 * Recupera i dati dallo user account </br> E' neccessario che username e password siano corretti
	 * @param us
	 * </br>User id di cui si vogliono recuperare i dati i dati
	 * @return
	 * User account completo se tutto � andato bene</br>
	 * null se qualcosa � andato storto
	 */
	public UserAccount getUserAccountById(UserAccount us){
		return this.userAccount.getUserAccountById(us);
		
	}
	
	/**
	 * Cambia Password dello user account
	 * @param us
	 * </br>User account con vecchia password, che si vuole cambiare, e username attuale
	 * @param newPassword 
	 * </br>Nuova password da assegnare allo user account
	 * @return
	 */
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword){
		return this.userAccount.chengeUserAccountPassword(us, newPassword);
	}
	
	/**
	 * Seleziona tutti gli asset posseduti dallo user account argomenti
	 * @param us 
	 * <br>User account di cui si vogliono conoscere gli asset da lio posseduti </br> 
	 * E' neccessario che questo userAccount abbia username e password corretti
	 * @return
	 * Tutti gli asset posseduti dallo user account
	 */
	public ArrayList<AsetOwn> getAssetOwndByUserAccount(UserAccount us){
		return this.userAccount.getAssetOwndByUserAccount(us);
	}
	
	/**
	 * Recupera gli obiettivi assegnati allo userAcocunt
	 * @param us
	 * userAccount </br> � sufficiente che abbia username e password correte
	 * @return 
	 * Lista obiettivi assegnati allo userAccount </br> 
	 * nullo se � impossibile recuperare le informazioni richeiste 
	 */
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us){
		return this.userAccount.getObiettiviUserByUserAccount(us);
	}
	
	/**
	 *Aggiorna punteggio di userAccount
	 * @param us
	 * userAccount </br> 
	 * � sufficiente che abbia username e password correte
	 * @param newPunteggio
	 * nuovo punteggio da assegnare a useraccount 
	 * @return 
	 * </br> vero se aggiornamento fatto
	 * </br> falso se aggiornamento � fallito
	 */
	public boolean updateUserAccountPunteggio(UserAccount us, int newPunteggio){
		return this.userAccount.updateUserAccountPunteggio(us, newPunteggio);
	}
	
	/**
	 * Aggiorna soldi giocatore 
	 * @param us
	 * userAccount </br>
	 * � sufficiente che abbia username e password correte
	 * @param newmny
	 * nuovo ammoto di denaro
	 * @return
	 * </br> vero se aggiornamento fatto
	 * </br> falso se aggiornamento � fallit
	 */
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		return this.userAccount.updateUserAccountMny(us, newmny);
	}
	
}
