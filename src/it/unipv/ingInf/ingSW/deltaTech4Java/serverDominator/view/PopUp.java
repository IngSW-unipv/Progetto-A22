package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;



import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Development;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Market;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Powerup;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Selectmalware;
import javafx.geometry.Insets;

public class PopUp {

	// NOTA: le variabili di ritorno sono definite allì'interno di ogni PopUp
	int useRootcrash, useVirus;
	public static int sX = 400;
	public static int sY = 400;	
	NumberSpinner useRcNs = new NumberSpinner();
	NumberSpinner useVrNs = new NumberSpinner();
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);

	int cpuAdd, cpuFinal, fwAdd, fwFinal, ramAdd, ramFinal, eAdd, eFinal, xS, yS;
	Mercato mkt = new Mercato();
	int prAv = mkt.prezzoAntivirus;
	
	
	public void selectMalware(Base baseUtente) {
		Selectmalware sm= new Selectmalware(baseUtente);
		
		sm.selectMalware();
		
	}

	public void development(Base baseUtente) {
		
		Development dvl= new Development(baseUtente);
		dvl.development();
		// -> inserire le variabili di ritorno
		// TODO
		
	}
	
	public void powerUp(Base baseUtente) {
		
		Powerup pu=new Powerup(baseUtente);
		
		pu.powerUp();
				
	}
	
	
	public void market(Base baseUtente) {
		
		// -> inserire le variabili di ritorno
		// TODO
		
		Market m=new Market(baseUtente);
		m.market();
	}
}
