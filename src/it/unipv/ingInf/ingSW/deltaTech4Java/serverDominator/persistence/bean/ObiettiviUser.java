package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;


/**
 * Obiettivi di punteggio
 * @author TawaHabib
 * @version 1.0
 * @see ObiettiviUserId
 */

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
	public ObiettiviUser(ObiettiviUserId id, Obiettivo obiettivi, UserAccount userAccount, String stato) {
		this.primaryKey = id;
		this.stato = stato;
	}
	/**
	 * Crea obiettivoUser
	 * @param obiettivi
	 * @param userAccount
	 * @param stato
	 */
	public ObiettiviUser(Obiettivo obiettivi, UserAccount userAccount, String stato) {
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
	public Obiettivo getObiettivi() {
		return this.primaryKey.getObiettivo();
	}

	/**
	 * Setta Obiettivi
	 * @param obiettivi
	 */
	public void setObiettivi(Obiettivo obiettivi) {
		this.primaryKey.setObiettiviIdObiettivo(obiettivi);;
	}

	/**
	 * @return UserAccount
	 */
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

	@Override
	public String toString() {
		return "ObiettiviUser [" +primaryKey.toString() + ", stato=" + stato + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObiettiviUser other = (ObiettiviUser) obj;
		if( primaryKey.getObiettivo().equals(other.getPrimaryKey().getObiettivo())&& stato.equals(other.getStato())&&primaryKey.getUserAccount().equals(other.getPrimaryKey().getUserAccount()))
			return true;
		else
			return false;
	}

}
