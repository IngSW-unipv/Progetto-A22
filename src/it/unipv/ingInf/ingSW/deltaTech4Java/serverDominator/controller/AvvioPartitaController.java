package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AvvioPartitaController {
	
	private AvvioPartitaController avvioPartitaC;
	private PartitaStage partitaStage;
	private PrebattagliaView prebattagliaView;
	private ControllerFacade controllerFacade;
	
	public AvvioPartitaController getInstance() {    // posso solo usare l'oggetto esistente
		
		if (avvioPartitaC == null) 
			avvioPartitaC = new AvvioPartitaController();
		
		return avvioPartitaC;
	}
	
	
	
	// MainDefinitivo è già stato creato in PartitaStage
	
	private AvvioPartitaController() {	
		super();
	}

	/**
	 * Metodo che associa alla scelta del radioButton la difficoltà stabilita e genera la partita di server dominator
	 */
	public void initAvvioPartita() {
		
		prebattagliaView.getAvvioPartita().setOnAction(actionEvent -> {
			
			if(prebattagliaView.getGroup().getSelectedToggle().getToggleGroup()) //facile choice? getSelectedDifficolta.getSelectedToggle, come richiamo il giusto radiobutton?
				
				partitaStage.getDimensioni().getValue();
				
		});
		
	}
	
}

