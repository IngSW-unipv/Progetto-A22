package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;

public class ProduzionemainController {

	public static ProduzionemainController produzionemainController = null;
	private MainDefinitivo mainDefinitivo;
	private ProduzionemainController() {
		super();	
	}
	
	public static ProduzionemainController getInstance() {
		if(produzionemainController == null)
			produzionemainController = new ProduzionemainController();
	
		return produzionemainController;
	}
	
	
	public void initProduzioneMain(PersistenceFacade persistenceFacade, PopUpFacade popupFacade,PartitaStage partitaStage) {
		popupFacade.getPopUpDevelopment().getButtonDevelop().setOnAction(actionEvent -> {
			partitaStage.addProduzioneSoftware(null, 0); //String titolo e durata come parametri
			
			
		});
	}
	
}
