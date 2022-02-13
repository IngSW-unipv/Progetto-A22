package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;

import java.util.ArrayList;
import java.util.Objects;

/**
 * UserAccount
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivi
 * @see AsetOwn
 * @see Giocatore
 * @see ObiettiviUser
 * @see ArrayList
 */
/**
 * @author TAWADROS
 *
 */
public class UserAccount {
    /**
     * Username di UserAccount
     */
	private String username;
    
    /**
     * Soldi associati al giocatore 
     */
	private Integer mny;
    
    /**
     * Punteggio del giocatore 
     */
	private Integer punteggio;

    /**
     * Email dello user Account
     */
    private String email;
 
    /**
     * Password Dello UserAccount
     */
    private String passw;
    
	/**
	 * Gli asset posseduti dallo  UserAccount
	 */
	private ArrayList<AsetOwn> asetOwns = new ArrayList<AsetOwn>(0);
	
	/**
	 * Gli obiettivi associati Allo UserAccount
	 */
	private ArrayList<ObiettiviUser> obiettiviUsers = new ArrayList<ObiettiviUser>(0);

	/**
	 * Crea UserAccount vuoto
	 */
	public UserAccount() {
	}
	
	/**
	 * crea user Acount solo con username e password
	 * @param username
	 * @param passw
	 */
	public UserAccount(String username, String passw) {
		this.punteggio=0;
		this.username = username;
		this.passw = passw;
		this.mny=0;
	} 

	/**
	 * crea useraccount con username, email , passw
	 * @param username
	 * @param email
	 * @param passw
	 */
	public UserAccount(String username, String email, String passw) {
		this.punteggio=0;
		this.mny=0;
		this.username = username;
		this.email = email;
		this.passw = passw;
	}

	/**
	 * Crea User account con mny=punteggio=0
	 * username dello userAccount
	 * @param username
	 */
	public UserAccount(String username) {
		this.username = username;
		this.mny=0;
		this.punteggio=0;
	}

	/**
	 * Crea UserAccount
	 * @param username
	 * @param mny
	 */
	public UserAccount(String username, Integer mny) {
		this.username = username;
		this.mny = mny;
	}

	/**
	 * Crea UserAccount
	 * @param username
	 * Identificativo dello user Account
	 * @param mny
	 * Soldi dello UserAccount
	 * @param punteggio
	 * Punteggio dello userAccount
	 * @param asetOwns
	 * Asset posseduti dallo UserAccount
	 * @param giocatores
	 * partite Giocate Dallo UserAccount
	 * @param obiettiviUsers
	 * Obiettivi Dello UserAccounts
	 */
	public UserAccount(String username, Integer mny, Integer punteggio, 
			ArrayList<AsetOwn> asetOwns, ArrayList<ObiettiviUser> obiettiviUsers) {
		this.username = username;
		this.mny = mny;
		this.punteggio = punteggio;
		this.asetOwns= asetOwns;
		this.obiettiviUsers = obiettiviUsers;
	}
	
	/**
	 * @return username associato allo UserAccount
	 */

	public String getUsername() {
		return this.username;
	}

	/**
	 * Setta username associato allo UserAccount
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Soldi in possesso dello User Account
	 */
	public Integer getMny() {
		return this.mny;
	}

	/**
	 * Setta soldi in possesso dello User Account
	 * @param mny
	 */
	public void setMny(Integer mny) {
		this.mny = mny;
	}

	/**
	 * @return Punteggio dello UserAccount
	 */
	public Integer getPunteggio() {
		return this.punteggio;
	}

	/**
	 * Setta punteggio dello UserAccount
	 * @param punteggio
	 */
	public void setPunteggio(Integer punteggio) {
		this.punteggio = punteggio;
	}
	

	/**
	 * @return Asset in possesso di account
	 */

	public ArrayList<AsetOwn> getAsetOwns() {
		return this.asetOwns;
	}
	   
	/**
	 * Setta gli asset in possesso di UserAccount
	 * @param asetOwns
	 */

	public void setAsetOwns(ArrayList<AsetOwn> asetOwns) {
		this.asetOwns = asetOwns;
	}
	
	/**
	 * @return obiettivi di UserAccount
	 */
	public ArrayList<ObiettiviUser> getObiettiviUsers() {
		return this.obiettiviUsers;
	}

	/**
	 * Setta Gli obiettivi di UserAccount
	 * @param obiettiviUsers
	 */
	public void setObiettiviUsers(ArrayList<ObiettiviUser> obiettiviUsers) {
		this.obiettiviUsers = obiettiviUsers;
	}
	/**
	 * Aggiungi Asset posseduto dallo UserAccount
	 * @param asetOwns
	 */
	public void addAssetOwn(AsetOwn asetOwns) {
		this.asetOwns.add(asetOwns);
	}
	/**
	 * Togli Asset posseduto dallo UserAccount
	 * @param asetOwns
	 */
	public void removeAssetOwn(AsetOwn asetOwns) {
		this.asetOwns.remove(asetOwns);
	}
	
	/**
	 * Aggiungi obiettivo
	 * @param obiettiviUsers
	 */
	public void addObiettiviUser(ObiettiviUser obiettiviUsers) {
		this.obiettiviUsers.add(obiettiviUsers);
	}
	/**
	 * Togli obiettivo
	 * @param obiettiviUsers
	 */
	public void removeObiettiviUsers(ObiettiviUser obiettiviUsers) {
		this.obiettiviUsers.remove(obiettiviUsers);
	}

	/**
	 * Togli giocatore
	 * @param obiettiviUsers
	 */

	/**
	 * ristitusce email Dello UserAccount
	 * @return Email Dello UserAccount
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Setta Email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Password 
	 */
	public String getPassw() {
		return this.passw;
	}

	/**
	 * Setta Password
	 * @param passw
	 */
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	
	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", mny=" + mny + ", punteggio=" + punteggio + ", email=" + email
				+ ", passw=" + passw + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(email, other.email) && mny == other.mny && Objects.equals(passw, other.passw)
				&& Objects.equals(punteggio, other.punteggio) && Objects.equals(username, other.username);
	}
	
	
}
