package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;

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
		popupFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			//partitaStage.
		});
	}
	
	
}
