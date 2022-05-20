package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class BaseCambioController {

	private static BaseCambioController basecambioController = null;
	private MainDefinitivo mainDefinitivo;
	private BaseCambioController() {
		super();
	}
	
	public static BaseCambioController getIstance() {
		if(basecambioController == null)
			basecambioController = new BaseCambioController();
		
		return basecambioController;
	}
	
	/**
	 * 
	 * @param partitaStage
	 * @param b
	 * @param giocatore
	 * Metodo associato al pulsante per visualizzare la prossima base posseduta dal giocatore
	 */
	public void initAvantiBase(PartitaStage partitaStage, Base b, Giocatore giocatore) {
		partitaStage.getBaseStatsPane().getButtonNext().setOnAction(actionEvent -> {
			mainDefinitivo.getTabellone().checkbasi(giocatore);
			mainDefinitivo.getTabellone().trovaBase(giocatore);
			//manca un comando?
			partitaStage.changeSelectedBase(b);
		});
	}
	
	/**
	 * 
	 * @param partitaStage
	 * @param b
	 * @param giocatore
	 * Metodo associato al pulsante per visualizzare la precedente base posseduta dal giocatore
	 */
	public void iniIndietroBase(PartitaStage partitaStage, Base b, Giocatore giocatore) {
		partitaStage.getBaseStatsPane().getButtonBack().setOnAction(actionEvent -> {
			mainDefinitivo.getTabellone().checkbasi(giocatore);
			mainDefinitivo.getTabellone().trovaBase(giocatore);
			partitaStage.changeSelectedBase(b);
		});
	}
	
}
