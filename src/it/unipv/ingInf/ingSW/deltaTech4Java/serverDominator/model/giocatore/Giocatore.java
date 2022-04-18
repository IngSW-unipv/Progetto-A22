package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Colore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public abstract class Giocatore {
	public int punteggio;
	public String nome;
	public String colore;

	public Giocatore(String nome) {
		this.nome=nome;
		punteggio=0;
	}
	public Giocatore() {
		this.nome=null;
		punteggio=0;
	}
	public Giocatore(Giocatore user) {
		this.nome=user.getNome();
		this.punteggio=user.getPunteggio();
	}
	/**Restituisce il valore della valuta temporanea*/
	public int getPunteggio() {
		return punteggio;
	}
	/**Aggiorna la valuta temporanea con il valore passato come parrametro*/
	public void setPunteggio(int punteggio) {
		this.punteggio =punteggio;
	}
	/**Restituisce il nome del Giocatore*/
	public String getNome() {
		return nome;
	}
	/**Aggiorna il nome con la stringa passata come parrametro*/
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void aggiornaPunteggio(int punteggio) {
		this.punteggio+=punteggio;
	}
	
	public void setColore(String colore) {
		this.colore=colore;
	}
	/*public Colore getColore() {
		return colore;
	}*/
	
	public String getColore() {
		return colore;
	}
}
