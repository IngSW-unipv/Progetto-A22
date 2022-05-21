package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class AvvioPartitaController {
	
	private static AvvioPartitaController avvioPartitaC;
	private static PartitaStage partitaStage;
	
	public static AvvioPartitaController getInstance() {    // posso solo usare l'oggetto esistente
		
		if (avvioPartitaC == null) 
			avvioPartitaC = new AvvioPartitaController();
		
		return avvioPartitaC;
	}
	
	private AvvioPartitaController() {						// non posso istanziarne di nuovi, ma solo usare l'unico esistente
		
	}
	
	public static PartitaStage creaPartita (PartitaStage ps) {
		if ( partitaStage == null && ps != null)
			
			partitaStage = ps;
		
		return partitaStage;
	}
	
	public static PartitaStage getPartitaInstance() throws NullPointerException {
		
		if( partitaStage == null) 
			throw new NullPointerException();
		return partitaStage;
		
	}
	
	
	public static void disposePartita() {  	// reset a fine partita
		avvioPartitaC = null;
		partitaStage = null; 
	}
	
	// MainDefinitivo è già stato creato in PartitaStage

}

