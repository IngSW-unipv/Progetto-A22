package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Identificativo composto ObiettiviUser
 * @author ME
 * @version 1.0
 * @see UserAccount
 * @see Obiettivi
 */
@Embeddable
/* 
 * @Embeddable
 * sta per che questa classe può essere incorporata in altre entity
 * 
 */
public class ObiettiviUserId implements Serializable {

	/**
	 * User Account
	 */
	private UserAccount userAccount;
	/**
	 * Obiattivo
	 */
	private Obiettivi obiettivo;

	/**
	 * Crea identificativo vuoto
	 */
	public ObiettiviUserId() {
		this.userAccount = new UserAccount();
		this.obiettivo = new Obiettivi();
	}

	/**
	 * crea identificativo
	 * @param userAccount
	 * @param obiettivo
	 */
	public ObiettiviUserId(UserAccount userAccount, Obiettivi obiettivo) {
		this.userAccount = userAccount;
		this.obiettivo = obiettivo;
	}
	/**
	 * @return UserAccount (parte dell'id)
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/**
	 * Setta UserAccount (parte dell'id)
	 * @param userAccount
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	/**
	 * @return Obiettivi (parte dell'id)
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	public Obiettivi getObiettivo() {
		return this.obiettivo;
	}

	/**
	 * Setta Obiettivi (parte dell'id)
	 * @param obiettivo
	 */
	public void setObiettiviIdObiettivo(Obiettivi obiettivo) {
		this.obiettivo = obiettivo;
	}

	/**
	 * Controllo l'uguaglianza 
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ObiettiviUserId))
			return false;
		ObiettiviUserId castOther = (ObiettiviUserId) other;
		return (this.equals(castOther));
	}



}
