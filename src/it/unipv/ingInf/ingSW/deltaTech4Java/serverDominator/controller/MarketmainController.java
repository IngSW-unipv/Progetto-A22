package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class MarketmainController {
	
	private static MarketmainController marketmainController = null;
	private MainDefinitivo mainDefinitivo;
	
	private MarketmainController() {
		super();
	}
	
	public static MarketmainController getInstance() {
		if(marketmainController == null)
			marketmainController = new MarketmainController();
		
		return marketmainController;
	}
	
	
	
	public void initMarket(MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, PartitaStage partitaStage) {
		
		/**
		 * Metodo abbinato all'acquisto di ANTIVIRUS
		 */
		popupFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaAntivirus(), "antivirus");
			
		});
		
		/**
		 * Metodo abbinato all'acquisto di ROOTCRASH
		 */
		popupFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaRootCrash(), "rootcrash");
		});
		
		
		/**
		 * Metodo abbinato all'acquisto di VIRUS
		 */
		popupFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaVirus(), "virus");
			
		});
		
	}//da aggiundere scrollPane?
	
	
}
