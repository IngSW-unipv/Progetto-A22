package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Obiettivi di punteggio
 * @author ME
 * @version 1.0
 * @see Obiettivi
 * @see AsetOwn
 * @see Giocatore
 * @see ObiettiviUser
 * @see ArrayList
 */
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {
    /**
     * Username di UserAccount
     */
    @Id
    @Column(name = "USERNAME")
	private String username;
    
    /**
     * Soldi associati al giocatore 
     */
    @Column(name = "MNY")
	private int mny;
    
    /**
     * Punteggio del giocatore 
     */
    @Column(name = "PUNTEGGIO")
	private Integer punteggio;
    
    
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
	 * Soldi dello UserAccount
	 * @param mny
	 * @param punteggio
	 * Asset posseduti dallo UserAccount
	 * @param asetOwns
	 * partite Giocate Dallo UserAccount
	 * @param giocatores
	 * Obiettivi Dello UserAccount
	 * @param obiettiviUsers
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
	@Id
    @Column(name = "USERNAME")
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
	@OneToMany(mappedBy = "primaryKey.userAccount",
	            cascade = CascadeType.ALL)
	public ArrayList<AsetOwn> getAsetOwns() {
		return this.asetOwns;
	}
	   
	/**
	 * Setta gli asset in possesso di UserAccount
	 * @param asetOwns
	 */
	@OneToMany(mappedBy = "primaryKey.userAccount",
	            cascade = CascadeType.ALL)
	public void setAsetOwns(ArrayList<AsetOwn> asetOwns) {
		this.asetOwns = asetOwns;
	}
	   
	/**
	 * @return partite giocate da UserAccount
	 */
	@OneToMany(mappedBy = "primaryKey.userAccount",
	            cascade = CascadeType.ALL)
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
	
}
