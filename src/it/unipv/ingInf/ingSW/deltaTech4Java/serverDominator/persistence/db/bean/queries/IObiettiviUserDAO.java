package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;

/**
 * ObiettiviUser query
 * @author ME
 * @version 1.0
 * @see Obiettivi
 * @see UserAccount
 * @see ObiettiviUser
 */
public interface IObiettiviUserDAO {
	/**
	 * Conocsre tutti gi obiettiviUser qualunque sia la persona a cui vengono assegnati
	 * @return ttti gli obirttivi di tutti gi user acccount
	 */
	public ArrayList<ObiettiviUser> selectAll();
	
	/**
	 * Conoscere tutti gli obiettivi di un singolo User Account
	 * @param assInput
	 * @return Gli obiettivi di un singolo giocatore
	 */
	public ArrayList<ObiettiviUser> selectByUserId(UserAccount accInput);
	
	/**
	 * seleziona ObiettiviUser Conoscendo l'id Dell'obiettivo
	 * @param assInput
	 * obiettivo per cui si desidera fare la ricerca
	 * @return tutti gli user che hanno lo stesso obiettivo
	 */
	public ArrayList<ObiettiviUser> selectByObiettiviId(Obiettivi obInput);
	
	/**
	 * Assegnare un obiettivo ad uno user
	 * @param a
	 * @return
	 */
	public boolean insertObiettiviUser(ObiettiviUser o);
	
	/**
	 * aggiornare un obiettivo assegnato a uno stesso user
	 * @param newA
	 * obiettivoUse di interessato al cambiamento 
	 * @return
	 */
	public boolean updateStatoObiettiviUserbyId(ObiettiviUser newOU);
}
