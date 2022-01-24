package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

/**
 * Partite che si avviano
 * @author ME
 * @version 1.0
 * @see UserAccount
 * @see Partita
 */
public class GiocatoreId {

	/**
	 * userAccount associato al giocatore 
	 */
	private UserAccount userAccount;
	/**
	 * Partita che il giocatore gioca 
	 */
	private Partita partita;

	/**
	 * Crea giocatore id vuoto
	 */
	public GiocatoreId() {
		 this.userAccount=new UserAccount();
		 this.partita=new Partita();
	}

	/**
	 * Crea Giocatore id
	 * @param userAccount
	 * @param partita
	 */
	public GiocatoreId(UserAccount userAccount, Partita partita) {
		this.userAccount = userAccount;
		this.partita = partita;
	}
	/**
	 * @return Partita
	 */
	public Partita getPartita() {
		return this.partita;
	}

	/**
	 * Setta partita
	 * @param partita
	 */
	public void setPartita(Partita partita) {
		this.partita= partita;
	}
	/**
	 * @return UserAccount
	 */
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/**
	 * Setta userAccount
	 * @param userAccount
	 */
	public void setUserAccountUsername(UserAccount userAccount) {
		this.userAccount=userAccount;
	}

	/**
	 *Confronto giocatore id
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GiocatoreId))
			return false;
		GiocatoreId castOther = (GiocatoreId) other;

		return (this.equals(castOther));
	}

}
