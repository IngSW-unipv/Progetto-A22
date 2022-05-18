package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class AttackmainController {

	private static AttackmainController attackmainController = null;
	private MainDefinitivo mainDefinitivo;
	private AttackmainController() {
		super();
	}
	
	public static AttackmainController getInstance() {
		if(attackmainController == null) 
			attackmainController = new AttackmainController();
			
			return attackmainController;
	}
	
	/**
	 * 
	 * @param mainDefinitivo
	 * @param partitaStage
	 * @param popupFacade
	 * 
	 * metodo abbinato al button di attacco del popup, avviobattaglia implementa quello preparato da battlecheck
	 * avanzamento della battaglia, grafica in ms, in modello secondi, causa *1000
	 */
	
	public void initAttackMain(MainDefinitivo mainDefinitivo, PartitaStage partitaStage, PopUpFacade popupFacade) { //dov'è il getButton d'attacco del modello?
		
		popupFacade.getPopUpSelectmalware().getFightButton().setOnAction(actionEvent ->{
			//nodo selezionato, da modello t d'attacco
			partitaStage.addAttacco(partitaStage.getSelectedBase().getPossessore().getNome() + "\t vs \t" + partitaStage.getSelectedNode().getPossessore().getNome()  //
			+ "(" + partitaStage.getSelectedPoint().getIntX() + ", " + partitaStage.getSelectedPoint().getIntY() + ") ",  
			mainDefinitivo.battlecheck(partitaStage.getSelectedBase().getPossessore(), 
			partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
			popupFacade.getPopUpSelectmalware().getQuantitaVirus(), popupFacade.getPopUpSelectmalware().getQuantitaRootCrash() *1000 )); //TO-DO pulsante dopo get nome, stampa anche cooridnate

			
			mainDefinitivo.avvioBattaglia(partitaStage.getSelectedBase().getPossessore(), partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY());
		});
	
		
		
	}

	
	
	
	
	
	
	
	
	
	
}
