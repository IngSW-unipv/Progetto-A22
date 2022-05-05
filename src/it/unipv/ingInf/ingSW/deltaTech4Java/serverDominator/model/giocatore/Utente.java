package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class Utente extends Giocatore{

	/**Permette di creare un oggetto di tipo Utente il cui nome sar� quello passato come parametro*/
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
