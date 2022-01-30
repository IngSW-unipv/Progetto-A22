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
	 * @return ttti gli obirttivi di tutti gi user acccount
	 */
	public ArrayList<ObiettiviUser> selectAll();
	
	/**
	 * 
	 * @param assInput
	 * @return Gli obiettivi di un singolo giocatore
	 */
	public ArrayList<ObiettiviUser> selectByUserId(UserAccount assInput);
	
	/**
	 * @param assInput
	 * obiettivo per cui si desidera fare la ricerca
	 * @return tutti gli user che hanno lo stesso obiettivo
	 */
	public ArrayList<ObiettiviUser> selectByObiettiviId(Obiettivi assInput);
	
	/**
	 * Assegnare un obiettivo ad uno user
	 * @param a
	 * @return
	 */
	public boolean insertObiettiviUser(ObiettiviUser a);
	
	/**
	 * aggiornare un obiettivo assegnato a uno stesso user
	 * @param newA
	 * obiettivoUse di interessato al cambiamento 
	 * @return
	 */
	public boolean updateObiettiviUserbyId(ObiettiviUser newA);
}
