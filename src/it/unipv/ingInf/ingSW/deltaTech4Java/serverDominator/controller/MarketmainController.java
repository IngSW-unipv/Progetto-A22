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
		 * Metodo abbinato all'acquisto di risorse software e hardware dal mercato
		 */
		popupFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			int quantitaHardware=-1;
			if(popupFacade.getPopUpMarket().getQuantitaAntivirus()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaAntivirus(), "antivirus");
			if(popupFacade.getPopUpMarket().getQuantitaVirus()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaVirus(), "virus");
			if(popupFacade.getPopUpMarket().getQuantitaRootCrash()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popupFacade.getPopUpMarket().getQuantitaRootCrash(), "rootcrash");
			if(popupFacade.getPopUpMarket().getLivelloCPU()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "cpu");
			if(popupFacade.getPopUpMarket().getLivelloRam()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "ram");
			if(popupFacade.getPopUpMarket().getLivelloEnergia()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "energia");
			if(popupFacade.getPopUpMarket().getLivelloFirewall()>0)
				mainDefinitivo.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "firewall");
		});
		
		
	}//da aggiundere scrollPane?
	
	
}
