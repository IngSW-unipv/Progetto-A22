package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;


import java.beans.PropertyChangeSupport;
import java.util.Objects;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

/**
 * @author Matteo c
 * @version 1.0
 * @since 1.0
 */
public abstract class Giocatore extends Thread implements Comparable<Giocatore>{
	
	private int punteggio;//Punteggio del giocatore nella singola partita
	private String nome;//nickName del giocatore
	public String colore;//Colore associato al giocatore
	private int deltaCoin;//Valuta di gioco
	private MappaDefinitiva map;
	private int basi_prese;
	private PropertyChangeSupport changes;
	public static final String GIOCATORE_PROP="numero_basi";
	/**
	 * Costruttore di un nuovo giocatore
	 * @param nome
	 * assegna al giocatore il nome passato come parametro
	 */
	public Giocatore(String nome) {
		this.nome=nome;
		punteggio=0;
		deltaCoin=0;
		basi_prese=1;
		this.changes = new PropertyChangeSupport(this);
	}
	/**
	 * costruttore di un nuovo giocatore
	 */
	public Giocatore() {
		super();
		this.nome=null;
		punteggio=0;
		deltaCoin=0;
		basi_prese=1;
		this.changes = new PropertyChangeSupport(this);
	}
	/**
	 * crea un nuovo giocatore con gli stessi valori del giocatore passato come parametro
	 * @param user
	 */
	public Giocatore(Giocatore user) {
		this.nome=user.getNome();
		this.punteggio=user.getPunteggio();
		basi_prese=1;
		this.changes = new PropertyChangeSupport(this);
	}
	/**
	 * Recupera il Punteggio del giocatore
	 * @return punteggio del giocatore
	 */
	public int getPunteggio() {
		return punteggio;
	}
	/**
	 * Aggiorna li punteggio del giocatoree
	 * @param punteggio
	 * nuovo punteggio
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio =punteggio;
	}
	
	/**
	 * Restituisce il nome del Giocatore
	 * @return
	 * Nome giocatore
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Aggiorna il nome con la stringa passata come parametro
	 * @param nome
	 * nuovo nome da assegnare all'utenete
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Incementa/decrementa il punteggio
	 * @param punteggio
	 * valore da sommare al vecchio punteggio
	 */
	public void aggiornaPunteggio(int punteggio) {
		this.punteggio+=punteggio;
	}
	
	/**
	 * Aggiorna colore associato al giocatore
	 * @param colore
	 * Nuovo colore in (hex)
	 */
	public void setColore(String colore) {
		this.colore=colore;
	}
	
	/**
	 * recupera il colore associato al giuocatore
	 * @return
	 * colore del giocatore in hex
	 */
	public String getColore() {
		return colore;
	}
	
	/**
	 * regcupera la valuta che posside il giocatore
	 * @return
	 * 
	 */
	public int getValuta() {
		return deltaCoin;
	}
	/**
	 * setta il valore della valuta di gioco assegnata al giocatore uguale al valore passato come parametro
	 * @param deltaCoin
	 */
	public void setValuta(int deltaCoin) {
		this.deltaCoin = deltaCoin;
	}

	/**restituisce il numero di nodi base che possiede il giocatore
	 * nel caso sia 0 il giocatore esce dalla partita
	 * @return
	 */
	public int getBasi_prese() {
		return basi_prese;
	}
	/**
	 * setta il numero di basi conquistate dal giocatore uguale a quello passato come parametro
	 * @param basi_prese
	 */
	public void setBasi_prese(int basi_prese) {
		Giocatore old= new Giocatore(this) {	
		};
		this.basi_prese = basi_prese;
		changes.firePropertyChange(GIOCATORE_PROP, old.getBasi_prese(), this.getBasi_prese());
				
	}
	
	/**
	 * incrementa/decrementa la valuta associata al giocatore
	 * @param valuta
	 * valuta da aggiungere alla valuta che gia possiede il giocatore
	 */
	public void aggiornaValuta(int valuta) {
		this.deltaCoin += valuta;
	}
	/**
	 * setta il valore di mappa uguale a quella passata come parametro
	 * @param mappa
	 */
	public void setMap(MappaDefinitiva map) {
		this.map = map;
	}
	/**
	 * returna la mappa
	 * @return
	 * mappa
	 */
	public MappaDefinitiva getMap() {
		return map;
	}

	@Override
	public int compareTo(Giocatore o) {
		return -this.getPunteggio()+o.getPunteggio();
	}

	@Override
	public int hashCode() {
		return Objects.hash(colore, deltaCoin, nome, punteggio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Giocatore other = (Giocatore) obj;
		return Objects.equals(nome, other.nome)&& punteggio == other.punteggio;
	}

	@Override
	public String toString() {
		return "Giocatore [punteggio=" + punteggio + ", nome=" + nome + ", colore=" + colore + ", deltaCoin="
				+ deltaCoin + "]";
	}
	public PropertyChangeSupport getChanges() {
		return changes;
	}
	
	
}
