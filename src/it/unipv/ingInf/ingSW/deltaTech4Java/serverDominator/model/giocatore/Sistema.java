package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

/**
 * @author Luca Casto 
 * v1.0
 * classe concreta, estende la classe giocatore, 
 * usato solo per quei nodi che non sono ancora conquistati da nessuno.
 */

public class Sistema extends Giocatore {

	public Sistema() {
		super.setNome("sistema");
		super.setPunteggio(0);
	}
}
