package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class AttackmainController {

	private MainDefinitivo mainDefinitivo;
	private PartitaStage partitaStage; 
	private PopUpFacade popupFacade;
	
	
	public AttackmainController(MainDefinitivo mainDefinitivo, PartitaStage partitaStage, PopUpFacade popupFacade) {
		super();
		this.mainDefinitivo = mainDefinitivo;
		this.partitaStage = partitaStage;
		this.popupFacade = popupFacade;
		this.initAttackMain();
	}


	/**
	 * Controller attacco inizializzazione;
	 */
	
	public void initAttackMain() {
		
		popupFacade.getPopUpSelectmalware().getFightButton().setOnAction(actionEvent ->{
			/*
			 * mainDefinitivo.battlecheck restituisce un tempo in secondi e 
			 * partitaStage.addAttacco accetta tempo in millisecondi; per questo c'è il *1000
			 */
			partitaStage.addAttacco(partitaStage.getSelectedBase().getPossessore().getNome() + "\t vs \t" + partitaStage.getSelectedNode().getPossessore().getNome()  //
			+ "(" + partitaStage.getSelectedPoint().getIntX() + ", " + partitaStage.getSelectedPoint().getIntY() + ") ",  
			mainDefinitivo.battlecheck(partitaStage.getSelectedBase().getPossessore(), 
			partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
			popupFacade.getPopUpSelectmalware().getQuantitaVirus(), popupFacade.getPopUpSelectmalware().getQuantitaRootCrash()) *1000 ); //TO-DO pulsante dopo get nome, stampa anche cooridnate

			
			mainDefinitivo.avvioBattaglia(partitaStage.getSelectedBase().getPossessore(), partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY());
		});
	
		
		
	}


	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}


	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}


	public PartitaStage getPartitaStage() {
		return partitaStage;
	}


	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}


	public PopUpFacade getPopupFacade() {
		return popupFacade;
	}


	public void setPopupFacade(PopUpFacade popupFacade) {
		this.popupFacade = popupFacade;
	}

	
}
