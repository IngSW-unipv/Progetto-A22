package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Colore;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * classe concreta, estende la classe giocatore, 
 * usato solo per quei nodi che non sono ancora conquistati da nessuno.
 */
public class Sistema extends Giocatore {

	public Sistema() {
		super.setNome("sistema");
		super.setPunteggio(0);
		super.setColore(Colore.GRIGIO);
	}

	@Override
	public void run() {
	/* il giocatore sistema non fa nulla serve solo 
	 * per inizializzare i nodi cloud all'inizio della partita
	 */
	}
}
