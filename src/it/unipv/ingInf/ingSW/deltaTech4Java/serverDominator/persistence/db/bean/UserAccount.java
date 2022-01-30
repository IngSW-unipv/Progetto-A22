package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.util.ArrayList;

/**
 * UserAccount
 * @author ME
 * @version 1.0
 * @see Obiettivi
 * @see AsetOwn
 * @see Giocatore
 * @see ObiettiviUser
 * @see ArrayList
 */
public class UserAccount {
    /**
     * Username di UserAccount
     */
	private String username;
    
    /**
     * Soldi associati al giocatore 
     */
	private int mny;
    
    /**
     * Punteggio del giocatore 
     */
	private Integer punteggio;

    /**
     * Email dello user Account
     */
    private String Email;
 
    /**
     * Password Dello UserAccount
     */
    private String passw;
    
	/**
	 * Gli asset posseduti dallo  UserAccount
	 */
	private ArrayList<AsetOwn> asetOwns = new ArrayList<AsetOwn>(0);
	/**
	 * Le partite giocate dallo UserAccount
	 */
	private ArrayList<Giocatore> giocatores = new ArrayList<Giocatore>(0);
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
	public UserAccount(String username, int mny) {
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
	public UserAccount(String username, int mny, Integer punteggio, 
			ArrayList<AsetOwn> asetOwns, ArrayList<Giocatore> giocatores, ArrayList<ObiettiviUser> obiettiviUsers) {
		this.username = username;
		this.mny = mny;
		this.punteggio = punteggio;
		this.asetOwns= asetOwns;
		this.giocatores = giocatores;
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
	public int getMny() {
		return this.mny;
	}

	/**
	 * Setta soldi in possesso dello User Account
	 * @param mny
	 */
	public void setMny(int mny) {
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
	 * @return partite giocate da UserAccount
	 */

	public ArrayList<Giocatore> getGiocatores() {
		return this.giocatores;
	}

	/**
	 * Setta partite giocate da UserAccount
	 * @param giocatores
	 */
	public void setGiocatores(ArrayList<Giocatore> giocatores) {
		this.giocatores = giocatores;
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
	 * Aggiungi giocatore
	 * @param obiettiviUsers
	 */
	public void addGiocatores(Giocatore giocatore) {
		this.giocatores.add(giocatore);
	}
	/**
	 * Togli giocatore
	 * @param obiettiviUsers
	 */
	public void removeGiocatores(Giocatore giocatore) {
		this.giocatores.remove(giocatore);
	}

	/**
	 * ristitusce email Dello UserAccount
	 * @return Email Dello UserAccount
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * Setta Email
	 * @param email
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * @return Password 
	 */
	public String getPassw() {
		return passw;
	}

	/**
	 * Setta Password
	 * @param passw
	 */
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	
}
