package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Obiettivi di punteggio
 * @author ME
 * @version 1.0
 * @see ObiettiviUserId
 */

@Entity
@Table(name = "OBIETTIVI_USER")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.obiettivi",
        joinColumns = @JoinColumn(name = "idObiettivo")),
    @AssociationOverride(name = "primaryKey.userAccount",
        joinColumns = @JoinColumn(name = "USERNAME")) })
public class ObiettiviUser {

	/**
	 * Identificatore dell'Obiettivo user
	 */
	private ObiettiviUserId primaryKey;
	
	/**
	 * Stato dell'obiettivo (puo essere: 'COMPLETATO' oppure 'NON COMPLETATO')
	 */
	private String stato;

	/**
	 * Crea obiettivoUser vuoto 
	 */
	public ObiettiviUser() {
		this.primaryKey=new ObiettiviUserId();
	}

	/**
	 * Crea obiettivoUser
	 * @param id
	 * @param obiettivi
	 * @param userAccount
	 * @param stato
	 */
	public ObiettiviUser(ObiettiviUserId id, Obiettivi obiettivi, UserAccount userAccount, String stato) {
		this.primaryKey = id;
		this.stato = stato;
	}
	/**
	 * Crea obiettivoUser
	 * @param obiettivi
	 * @param userAccount
	 * @param stato
	 */
	public ObiettiviUser(Obiettivi obiettivi, UserAccount userAccount, String stato) {
		this.primaryKey = new ObiettiviUserId(userAccount,obiettivi);
		this.stato = stato;
	}
	/**
	 * Crea obiettivoUser
	 * @param pk
	 */
	public ObiettiviUser(ObiettiviUserId pk) {
		this.primaryKey=pk;
		this.stato = "NON COMPLETATO";
	}

	/**
	 * @return ObiettiviUserId
	 */
	@EmbeddedId
	public ObiettiviUserId getPrimaryKey() {
		return this.primaryKey;
	}

	/**
	 * setta ObiettiviUserId
	 * @param pk
	 */
	public void setPrimaryKey(ObiettiviUserId pk) {
		this.primaryKey = pk;
	}

	/**
	 * @return Obiettivo
	 */
	@Transient
	public Obiettivi getObiettivi() {
		return this.primaryKey.getObiettivo();
	}

	/**
	 * Setta Obiettivi
	 * @param obiettivi
	 */
	public void setObiettivi(Obiettivi obiettivi) {
		this.primaryKey.setObiettiviIdObiettivo(obiettivi);;
	}

	/**
	 * @return UserAccount
	 */
	@Transient
	public UserAccount getUserAccount() {
		return this.primaryKey.getUserAccount();
	}

	/**
	 * Setta UserAccount
	 * @param userAccount
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.primaryKey.setUserAccount(userAccount); 
	}

	/**
	 * @return stato obiettivo
	 */
	public String getStato() {
		return this.stato;
	}

	/**
	 * Setta stato obiettivo
	 * @param stato
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

}
