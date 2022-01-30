package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;
/**
 * Obiettivi query
 * @author ME
 * @version 1.0
 * @see UserAccount
 * @see ObiettiviUser
 */
public interface IUserAccountDAO {
	/**
	 * Inserire NUOVO user Account
	 * @param us
	 * @return
	 */
	public boolean insetUserAccount(UserAccount us);
	
	/**
	 * controlla e aggiorna tutti i dati di userAccount tranne username e la password
	 * lo username non deve ambiare. se username non esiste o se password non è quella nel db 
	 * tronca l'operazione, Fallisce 
	 * @param us
	 * @return
	 */
	public boolean updateUserAccount(UserAccount us);
	
	/**
	 * Agguirna lo username, se nuova username già usata da un'altro giocatore fallicse
	 * @param us
	 * @param newUsername
	 * @return
	 */
	public boolean updateUserAccountUsername(UserAccount us, String newUsername);
	
	/**
	 * Prende tutti i dati di userAccont salvati el db
	 * @param us
	 * Us deve avere settato almeno username e password
	 * @return UserAccount completo nel db
	 */
	public UserAccount getUserAccountById(UserAccount us);
	
	/**
	 * Cambia la password dello userAccount 
	 * @param us
	 * contiene almeno Username e vecchia password
	 * @param newPassword
	 * nuova password da settare
	 * @return
	 */
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword);
	
	/**
	 * recuperare tutti gli asset posseduti dal giocatore 
	 * @param us
	 * userAccount di cui si vogliono conoscere gli asset posseduti
	 * @return tutti gli asset posseduti dallo UserAccount passato
	 */
	public ArrayList<AsetOwn> getAssetOwndByUserAccount(UserAccount us);
	
	/**
	 * recuperare tutti gli gli obiettivi assegnati al giocatore
	 * @param us
	 * @return
	 */
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us);
	
	/**
	 * aggiorna punteggio dello user account 
	 * @param us
	 * @param newPunteggio
	 * @return
	 */
	public boolean updateUserAccountPunteggio(UserAccount us, String newPunteggio);
	
	/**
	 * aggiorna SOLDI dello user account 
	 * @param us
	 * User account d'interesse
	 * @param newmny
	 * Nuovi Ammonto denaro
	 * @return
	 */
	public boolean updateUserAccountMny(UserAccount us, int newmny);
}
