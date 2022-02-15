package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.util.concurrent.TimeUnit;

public class Timer {
	public boolean potenziamentoRisorsa(int tempo){
		try {
		    TimeUnit.SECONDS.sleep(tempo);
		    return true;
		} catch (InterruptedException ie) {
			System.out.println("errore timer");
			return false;
		}
	}
	/*public boolean creazioneSoftware(int tempo){
		try {
		    TimeUnit.SECONDS.sleep(tempo);
		    return true;
		} catch (InterruptedException ie) {
			System.out.println("errore timer");
			return false;
		}
	}
	
	public boolean spostamentoSoftware(int tempo) {
		try {
		    TimeUnit.SECONDS.sleep(tempo);
		    return true;
		} catch (InterruptedException ie) {
			System.out.println("errore timer");
			return false;
		}
	}
	public boolean attacco(int tempo) {
		try {
		    TimeUnit.SECONDS.sleep(tempo);
		    return true;
		} catch (InterruptedException ie) {
			System.out.println("errore timer");
			return false;
		}
	}*/
}
