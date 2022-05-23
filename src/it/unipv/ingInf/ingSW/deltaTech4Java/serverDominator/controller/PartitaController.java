package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class PartitaController {

	//popup controller, cambio base controller
	PopupControllerFacade popupcontroller;
	BaseCambioController cambioBaseController;
	PopUpFacade popUp;
	
	public PartitaController(MainDefinitivo main, PartitaStage partita,Base b){
		cambioBaseController=new BaseCambioController(main, partita);
		popUp=new PopUpFacade(main.getMercato(), b);
		popupcontroller=new PopupControllerFacade(partita, popUp, main);
		
	}
	
	
}
