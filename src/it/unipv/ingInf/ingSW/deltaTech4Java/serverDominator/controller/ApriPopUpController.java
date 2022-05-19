package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class ApriPopUpController {
	private PartitaStage partitaStage;
	private PopUpFacade popUpFacade;
	
	public ApriPopUpController(PartitaStage partitaStage, PopUpFacade popUpFacade) {
		super();
		this.partitaStage = partitaStage;
		this.popUpFacade = popUpFacade;
		this.initopenPopUpController();
	}
	
	public void initopenPopUpController() {
		this.initOpenPopUpActionController();
		this.initOpenAttaccoController();
	}
	
	public void initOpenPopUpActionController() {
		
		partitaStage.getActionPane().getActionMarketL().setOnAction(event -> {
			popUpFacade.getPopUpMarket().setBaseUtente(partitaStage.getSelectedBase());
			popUpFacade.avviaMarket();
		});
		
		partitaStage.getActionPane().getPowerUpL().setOnAction(event -> {
			popUpFacade.getPopUpPowerup().setNodoUtente(partitaStage.getSelectedNode());
			popUpFacade.avviaPowerUp();
		});
		
		partitaStage.getActionPane().getDevelop().setOnAction(event -> {
			popUpFacade.getPopUpDevelopment().setNodoUtente(partitaStage.getSelectedNode());
			popUpFacade.avviaDevelopment();
		});
	}
	
	public void initOpenAttaccoController() {
		partitaStage.getStatsNodePane().getButtonAttacca().setOnAction(event->{
			popUpFacade.getPopUpSelectmalware().setBaseUtente(partitaStage.getSelectedBase());
			popUpFacade.avviaSelectMalware();
		});
	}
}
