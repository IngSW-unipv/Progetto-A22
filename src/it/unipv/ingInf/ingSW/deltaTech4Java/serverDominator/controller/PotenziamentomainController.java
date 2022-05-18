package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Cpu;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Energia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Firewall;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Ram;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Risorse;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;

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
	
	

	
	public void initPotenziamento (PartitaStage partitaStage, MainDefinitivo mainDefinitivo, PopUpFacade popupFacade,  Ram ram, Cpu cpu, Firewall fireWall, Base base, Risorse risorse, Energia energia) {
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO RAM, BASE \t", 
					mainDefinitivo.powerupCheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
					
				base.getLvl_ram(), base.getE_disponibile(), risorse.getTempo_richiesto();
			
			
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
					base.potenzia_risorsa(ram.toString()));
			
		});
		
		
		
	}
	
}
