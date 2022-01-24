package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Qualunque obiettivo raggiungibele da gioco
 * @author ME
 * @version 1.0
 * @see Obiettivi
 * @see ArrayList
 */
@Entity
@Table(name = "OBIETTIVI")
public class Obiettivi{

	/**
	 * Identificativo dell'obiettivo
	 */
	private int idObiettivo;
	/**
	 * Descrizione dell'obiettivo
	 */
	private String descrizione;
	/**
	 * Ricompenza data al compiemento dell'obiettivo
	 */
	private int ricompensa;
	/**
	 * 
	 */
	private ArrayList<ObiettiviUser> obiettiviUsers = new ArrayList<ObiettiviUser>(0);

	/**
	 * Crea obiettivo vuoto
	 */
	public Obiettivi() {
	}

	/**
	 * Crea obiettivo
	 * @param idObiettivo
	 * @param descrizione
	 * @param ricompensa
	 */
	public Obiettivi(int idObiettivo, String descrizione, int ricompensa) {
		this.idObiettivo = idObiettivo;
		this.descrizione = descrizione;
		this.ricompensa = ricompensa;
	}

	/**
	 * Crea Obiettivo
	 * @param idObiettivo
	 * @param descrizione
	 * @param ricompensa
	 * @param obiettiviUsers
	 */
	public Obiettivi(int idObiettivo, String descrizione, int ricompensa, ArrayList<ObiettiviUser> obiettiviUsers) {
		this.idObiettivo = idObiettivo;
		this.descrizione = descrizione;
		this.ricompensa = ricompensa;
		this.obiettiviUsers = obiettiviUsers;
	}

	/**
	 * @return identificativo obiettivo
	 */
	@Id
	@Column(name="idObiettivo")
	public int getIdObiettivo() {
		return this.idObiettivo;
	}

	/**
	 * Setta identificativo obiettivo
	 * @param idObiettivo
	 */
	public void setIdObiettivo(int idObiettivo) {
		this.idObiettivo = idObiettivo;
	}

	/**
	 * @return Descrizione Obiettivo
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * Setta Descrizione Obiettivo
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return Ricompenza data al compiemento dell'obiettivo
	 */
	public int getRicompensa() {
		return this.ricompensa;
	}

	/**
	 * Setta ricompenza data al compiemento dell'obiettivo
	 * @param ricompensa
	 */
	public void setRicompensa(int ricompensa) {
		this.ricompensa = ricompensa;
	}

	/**
	 * @return users a cui è associato l'obiettivo
	 */
	   @OneToMany(mappedBy = "primaryKey.obiettivi",
	            cascade = CascadeType.ALL)
	public ArrayList<ObiettiviUser> getObiettiviUsers() {
		return this.obiettiviUsers;
	}

	/**
	 * Setta users a cui è associato l'obiettivo
	 * @param obiettiviUsers
	 */
	public void setObiettiviUsers(ArrayList<ObiettiviUser> obiettiviUsers) {
		this.obiettiviUsers = obiettiviUsers;
	}


	/**
	 * @return Questo obiettivo
	 */
	public Obiettivi getObiettivi() {
		return this;
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
	public void remuveObiettiviUsers(ObiettiviUser obiettiviUsers) {
		this.obiettiviUsers.remove(obiettiviUsers);
	}
}
