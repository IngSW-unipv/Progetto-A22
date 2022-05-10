package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

/**
 * @author Matteo c
 * @version 1.0
 * @since 1.0
 */
public class Utente extends Giocatore{

	/**Permette di creare un oggetto di tipo Utente il cui nome sara quello passato come parametro
	 * @param nome
	 * nome che avra l'utente
	 * @param valuta
	 * moneta di gioco che l'utente possiede ad inizio partita
	 */
	public Utente(String nome, int valuta) {
		super(nome);
		super.setPunteggio(0);
		super.setValuta(valuta);;
	}
	
	public Utente() {
		super();
		super.setPunteggio(0);
		super.setValuta(0);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
