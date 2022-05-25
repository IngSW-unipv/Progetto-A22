package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class PartitaController {

	//popup controller, cambio base controller
	private PopupControllerFacade popupcontroller;
	private BaseCambioController cambioBaseController;
	private PopUpFacade popUp;
	
	public PartitaController(MainDefinitivo main, PartitaStage partita,Nodo b){
		popUp=new PopUpFacade(main.getMercato(), b);
		cambioBaseController=new BaseCambioController(main, partita);
		popupcontroller=new PopupControllerFacade(partita, popUp, main);
		
	}

	public PopupControllerFacade getPopupcontroller() {
		return popupcontroller;
	}

	public void setPopupcontroller(PopupControllerFacade popupcontroller) {
		this.popupcontroller = popupcontroller;
	}

	public BaseCambioController getCambioBaseController() {
		return cambioBaseController;
	}

	public void setCambioBaseController(BaseCambioController cambioBaseController) {
		this.cambioBaseController = cambioBaseController;
	}

	public PopUpFacade getPopUp() {
		return popUp;
	}

	public void setPopUp(PopUpFacade popUp) {
		this.popUp = popUp;
	}
	
	
}
