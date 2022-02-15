package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;

import java.util.ArrayList;


/**
 * Qualunque obiettivo raggiungibele da gioco
 * @author TawaHabib
 * @version 1.0
 * @see Obiettivi
 * @see ArrayList
 */
public class Obiettivi {

	/**
	 * Identificativo dell'obiettivo
	 */
	private Integer idObiettivo;
	/**
	 * Descrizione dell'obiettivo
	 */
	private String descrizione;
	/**
	 * Ricompenza data al compiemento dell'obiettivo
	 */
	private Integer ricompensa;
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
	public Obiettivi(Integer idObiettivo, String descrizione, Integer ricompensa) {
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
	public Obiettivi(Integer idObiettivo, String descrizione, Integer ricompensa, ArrayList<ObiettiviUser> obiettiviUsers) {
		this.idObiettivo = idObiettivo;
		this.descrizione = descrizione;
		this.ricompensa = ricompensa;
		this.obiettiviUsers = obiettiviUsers;
	}

	/**
	 * @return identificativo obiettivo
	 */
	public Integer getIdObiettivo() {
		return this.idObiettivo;
	}

	/**
	 * Setta identificativo obiettivo
	 * @param idObiettivo
	 */
	public void setIdObiettivo(Integer idObiettivo) {
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
	public Integer getRicompensa() {
		return this.ricompensa;
	}

	/**
	 * Setta ricompenza data al compiemento dell'obiettivo
	 * @param ricompensa
	 */
	public void setRicompensa(Integer ricompensa) {
		this.ricompensa = ricompensa;
	}

	/**
	 * @return users a cui � associato l'obiettivo
	 */
	public ArrayList<ObiettiviUser> getObiettiviUsers() {
		return this.obiettiviUsers;
	}

	/**
	 * Setta users a cui � associato l'obiettivo
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

	@Override
	public String toString() {
		return "Obiettivi [idObiettivo=" + idObiettivo + ", descrizione=" + descrizione + ", ricompensa=" + ricompensa
				+ "]";
	}

	public boolean equals(Obiettivi obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Obiettivi other = (Obiettivi) obj;
		if (idObiettivo == other.getIdObiettivo()&&this.descrizione.equals(other.getDescrizione())&&this.ricompensa.equals(other.getRicompensa()))
			return true;
		return false;
	}
	
	
}
