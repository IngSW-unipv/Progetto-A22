package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class Utente extends Giocatore implements Comparable<Utente>{

	int punteggio, valuta;
	/**Permette di creare un oggetto di tipo Utente il cui nome sarà quello passato come parametro*/
	public Utente(String nome) {
		super(nome);
		this.punteggio=0;
		this.valuta=0;
	}
	
	public Utente() {
		super();
		this.punteggio=0;
		this.valuta=0;
	}
	
	public Utente(Utente user) {
		super(user.getNome());
		this.punteggio=user.getPunteggio();
		this.valuta=user.getValuta();
	}
	
	/**Restituisce il punteggio dell'Utente*/
	public int getPunteggio() {
		return punteggio;
	}
	/**Aggiorna il punteggio dell'Utente con il valore passato come parametro*/
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	public int getValuta() {
		return valuta;
	}
	
	public void setValuta(int valuta) {
		this.valuta=valuta;
	}
	
	public void aggiornaValuta(int valuta) {
		this.valuta+=valuta;
	}
	/**Aggiunge al punteggio dell'Utente il valore passato come parametro*/
	public void aggiornaPunteggio(int punteggio) {
		this.punteggio+=punteggio;
	}
	/**Restituisce la differenza di punteggio tra l'Utente e l'Utente passato come parametro*/
	public int compareTo(Utente obj) {
		return this.punteggio-obj.getPunteggio();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
