package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Energia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Risorse;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class PotenziamentomainController {
	private static PotenziamentomainController potenziamentomainController = null;
	private MainDefinitivo mainDefinitivo;
	
	private PotenziamentomainController() {
		super();
	}
	
	public static PotenziamentomainController getInstance() {
		if(potenziamentomainController == null)
			potenziamentomainController = new PotenziamentomainController();
		
		return potenziamentomainController;
	}
	
	
//TO-DO aggiungere ScrollPane per ogni actionevent e aggiungere metodo getRisorse() da main modello
	
	public void initPotenziamento (PartitaStage partitaStage, MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, Base base, Risorse risorse, Energia energia) {
		
		/**
		 * Metodo associato al pulsante power up, per potenziare le risorse
		 */
		
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent ->{
			
			if(popupFacade.getPopUpPowerup().getCpuAdd()>0)
				partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO CPU, BASE \t", 
						mainDefinitivo.getTempoRisorsa(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "cpu"));
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "cpu");
			
			if(popupFacade.getPopUpPowerup().getRamAdd()>0)
				partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO RAM, BASE \t", 
						mainDefinitivo.getTempoRisorsa(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "ram"));
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "ram");
			
			if(popupFacade.getPopUpPowerup().getFwAdd()>0)
				partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO FIREWALL, BASE \t", 
						mainDefinitivo.getTempoRisorsa(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "firewall"));
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "firewall");
			
			if(popupFacade.getPopUpPowerup().geteAdd()>0)
				partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO ENERGIA, BASE \t", 
						mainDefinitivo.getTempoRisorsa(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "energia"));
			
			
		});
		
		
		
		
	}
	
}
