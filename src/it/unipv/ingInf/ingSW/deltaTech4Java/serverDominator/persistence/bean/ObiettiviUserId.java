package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;

import java.util.Objects;

/**
 * Identificativo composto ObiettiviUser
 * @author TawaHabib
 * @version 1.0
 * @see UserAccount
 * @see Obiettivo
 */

public class ObiettiviUserId {

	/**
	 * User Account
	 */
	private UserAccount userAccount;
	/**
	 * Obiattivo
	 */
	private Obiettivo obiettivo;

	/**
	 * Crea identificativo vuoto
	 */
	public ObiettiviUserId() {
		this.userAccount = new UserAccount();
		this.obiettivo = new Obiettivo();
	}

	/**
	 * crea identificativo
	 * @param userAccount
	 * @param obiettivo
	 */
	public ObiettiviUserId(UserAccount userAccount, Obiettivo obiettivo) {
		this.userAccount = userAccount;
		this.obiettivo = obiettivo;
	}
	/**
	 * @return UserAccount (parte dell'id)
	 */
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
	public Obiettivo getObiettivo() {
		return this.obiettivo;
	}

	/**
	 * Setta Obiettivi (parte dell'id)
	 * @param obiettivo
	 */
	public void setObiettiviIdObiettivo(Obiettivo obiettivo) {
		this.obiettivo = obiettivo;
	}

	@Override
	public String toString() {
		return "ObiettiviUserId [userAccount[ " + userAccount.getUsername() + "] ," + obiettivo.toString() + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObiettiviUserId other = (ObiettiviUserId) obj;
		return Objects.equals(obiettivo, other.obiettivo) && Objects.equals(userAccount, other.userAccount);
	}



}
