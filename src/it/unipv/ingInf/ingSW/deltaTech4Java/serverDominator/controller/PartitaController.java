package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class PartitaController {

	//popup controller, cambio base controller
	private PopupControllerFacade popupcontroller;
	private PopUpFacade popUp;
	
	public PartitaController(MainDefinitivo main, PartitaStage partita,Nodo b){
		popUp=new PopUpFacade(main.getMercato(), b);
		popupcontroller=new PopupControllerFacade(partita, popUp, main);
		
	}

	public PopupControllerFacade getPopupcontroller() {
		return popupcontroller;
	}

	public void setPopupcontroller(PopupControllerFacade popupcontroller) {
		this.popupcontroller = popupcontroller;
	}

	public PopUpFacade getPopUp() {
		return popUp;
	}

	public void setPopUp(PopUpFacade popUp) {
		this.popUp = popUp;
	}
	
	
}
