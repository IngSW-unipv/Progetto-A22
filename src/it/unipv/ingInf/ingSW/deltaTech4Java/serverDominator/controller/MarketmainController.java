package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class MarketmainController {
	private MainDefinitivo mainDefinitivo; 
	private PopUpFacade popupFacade;
	private PartitaStage partitaStage;
	
	public MarketmainController(MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, PartitaStage partitaStage) {
		super();
		this.mainDefinitivo = mainDefinitivo;
		this.popupFacade = popupFacade;
		this.partitaStage = partitaStage;
		this.initMarket();
	}

	public void initMarket() {
		
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
			this.partitaStage.getBaseStatsPane().make();
			});
		
		
	}

	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}

	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}

	public PopUpFacade getPopupFacade() {
		return popupFacade;
	}

	public void setPopupFacade(PopUpFacade popupFacade) {
		this.popupFacade = popupFacade;
	}

	public PartitaStage getPartitaStage() {
		return partitaStage;
	}

	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}

	
	
}
