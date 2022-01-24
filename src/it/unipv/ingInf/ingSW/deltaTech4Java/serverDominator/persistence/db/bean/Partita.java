package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Partite che si avviano
 * @author ME
 * @version 1.0
 * @see Giocatore
 * @see ArrayList
 */
@Entity
@Table(name = "PARTITA")
public class Partita{

	/**
	 * Id della partita
	 */
    @Id
    @GeneratedValue
    @Column(name = "idPartita")
	private int idPartita;
	/**
	 * Inizio della partita
	 */
    @Column(name = "INIZIO")
	private LocalDateTime inizio;
	/**
	 * Fine della partita
	 */
    @Column(name = "FINE")
	private LocalDateTime fine;
	/**
	 * Lista giocatori della partita
	 */
	   @OneToMany(mappedBy = "primaryKey.partita",
	            cascade = CascadeType.ALL)
	private ArrayList<Giocatore> giocatores = new ArrayList<Giocatore>(0);

	/**
	 * Crea partita vuota
	 */
	public Partita() {
	}

	/**
	 * Crea partita
	 * @param idPartita
	 */
	public Partita(int idPartita) {
		this.idPartita = idPartita;
		this.inizio=LocalDateTime.now(ZoneId.systemDefault());
	}

	/**
	 * Crea partita 
	 * @param idPartita
	 * @param inizio
	 * @param fine
	 * @param giocatores
	 */
	public Partita(int idPartita, LocalDateTime inizio, LocalDateTime fine, ArrayList<Giocatore> giocatores) {
		this.idPartita = idPartita;
		this.inizio = inizio;
		this.fine = fine;
		this.giocatores = giocatores;
	}

	/**
	 * @return Id partita
	 */
	public int getIdPartita() {
		return this.idPartita;
	}

	/**
	 * Setta id partita
	 * @param idPartita
	 */
	public void setIdPartita(int idPartita) {
		this.idPartita = idPartita;
	}

	/**
	 * @return Inizio partita
	 */
	public LocalDateTime getInizio() {
		return this.inizio;
	}

	/**
	 * Setta inizio partita
	 * @param inizio
	 */
	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}

	/**
	 * @return Fine alla partita
	 */
	public LocalDateTime getFine() {
		return this.fine;
	}

	/**
	 * Setta Fine partita
	 * @param fine
	 */
	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}

	/**
	 * @return Lista giocatori nella partita
	 */
	public ArrayList<Giocatore> getGiocatores() {
		return this.giocatores;
	}

	/**
	 * Aggiunge lista ai giocatori
	 * @param giocatores
	 */
	public void setGiocatores(ArrayList<Giocatore> giocatores) {
		this.giocatores = giocatores;
	}
	/**
	 * Agguinge giocatore alla partita
	 * @param gioc
	 */
	public void addGiocatore(Giocatore gioc) {
		this.giocatores.add(gioc);
	}
	/**
	 * Rimuove giocatore alla partita
	 * @param gioc
	 */
	public void removeGiocatore(Giocatore gioc) {
		this.giocatores.remove(idPartita);
	}

}