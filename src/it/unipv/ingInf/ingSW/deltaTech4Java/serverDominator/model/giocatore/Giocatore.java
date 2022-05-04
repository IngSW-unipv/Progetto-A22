package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;


import java.util.Objects;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

public abstract class Giocatore extends Thread implements Comparable<Giocatore>{
	
	/**
	 * Punteggio del giocatore inella singola partita 
	 * 
	 */
	private int punteggio;
	/**
	 * nickName del giocatore
	 */
	private String nome;
	/**
	 * Colore associato al giocatore
	 */
	public String colore;
	/**
	 * Valuta di gioco
	 */
	private int deltaCoin;
	
	private MappaDefinitiva map;

	public Giocatore(String nome) {
		this.nome=nome;
		punteggio=0;
		deltaCoin=0;
	}
	
	public Giocatore() {
		super();
		this.nome=null;
		punteggio=0;
		deltaCoin=0;
	}
	public Giocatore(Giocatore user) {
		this.nome=user.getNome();
		this.punteggio=user.getPunteggio();
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
	 * regcupera la valuta (temporanea) che posside il giocatore
	 * @return
	 * 
	 */
	public int getValuta() {
		return deltaCoin;
	}
	/**
	 * incrementa/decrementa la valuta associata al giocatore
	 * @param valuta
	 * valuta da aggiungere alla valuta che gia possiede il giocatore
	 */
	public void aggiornaValuta(int valuta) {
		this.deltaCoin += valuta;
	}
	
	public void setMap(MappaDefinitiva map) {
		this.map = map;
	}
	
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
	
	public static void main(String[] args) {
		Utente u=new Utente();
		Giocatore g=new Giocatore() {
		};
		UserAccount s=new UserAccount();
		if(g.getClass().isAssignableFrom(u.getClass()))
			System.out.println("ok");
		else
			System.err.println("err");
	}
}
