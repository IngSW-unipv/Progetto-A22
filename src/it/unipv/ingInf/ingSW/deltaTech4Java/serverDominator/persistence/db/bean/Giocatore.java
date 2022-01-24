package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

import java.time.LocalDateTime;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Relazione che associa i giocatori con le partite
 * @author ME
 * @version 1.0
 * @see GiocatoreId
 */
@Entity
@Table(name = "GIOCATORE")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.partita",
        joinColumns = @JoinColumn(name = "idPartita")),
    @AssociationOverride(name = "primaryKey.userAccount",
        joinColumns = @JoinColumn(name = "USERNAME")) })
public class Giocatore{

	/**
	 * Identificativo del giocatore
	 */
	private GiocatoreId primaryKey;
	/**
	 * Posizione ottenuta in partita
	 */
	private int posizione;
	/**
	 * Tempo in cui il giocatore ha perso / fine partita
	 */
	private LocalDateTime fineGioco;

	/**
	 * Crea giocatote vuoto
	 */
	public Giocatore() {
		this.primaryKey=new GiocatoreId();
	}

	/**
	 * Crea giocatore; solo con id
	 * @param id
	 */
	public Giocatore(GiocatoreId id) {
		this.primaryKey = id;
	}

	/**
	 * Crea giocatore 
	 * @param id
	 * @param posizione
	 * @param fineGioco
	 */
	public Giocatore(GiocatoreId id, int posizione, LocalDateTime fineGioco) {
		this.primaryKey = id;
		this.posizione = posizione;
		this.fineGioco = fineGioco;
	}
	/**
	 * @return identificativo del giocatore 
	 */
	@EmbeddedId
	public GiocatoreId getPrimaryKey() {
		return this.primaryKey;
	}

	/**
	 * Setta identificativo del giocatore
	 * @param id
	 */
	public void setPrimaryKey(GiocatoreId id) {
		this.primaryKey = id;
	}
	/**
	 * @return partita del giocatore
	 */
	@Transient
	public Partita getPartita() {
		return this.primaryKey.getPartita();
	}

	/**
	 * Setta partita del giocatore
	 * @param partita
	 */
	public void setPartita(Partita partita) {
		this.primaryKey.setPartita(partita);
	}
	/**
	 * @return UserAccount che gioca la partita
	 */
	@Transient
	public UserAccount getUserAccount() {
		return this.primaryKey.getUserAccount();
	}

	/**
	 * Setta userAccount
	 * @param userAccount
	 */
	public void setUserAccount(UserAccount userAccount) {
		this.primaryKey.setUserAccountUsername(userAccount);
	}

	/**
	 * @return posizione che ha ottenuto il giocatore
	 */
	public int getPosizione() {
		return this.posizione;
	}

	/**
	 * Setta posizione ottenuta dal giocatore 
	 * @param posizione
	 */
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	/**
	 * @return fine gioco
	 */
	public LocalDateTime getFineGioco() {
		return this.fineGioco;
	}

	/**
	 * Setta fine gioco
	 * @param fineGioco
	 */
	public void setFineGioco(LocalDateTime fineGioco) {
		this.fineGioco = fineGioco;
	}

}
