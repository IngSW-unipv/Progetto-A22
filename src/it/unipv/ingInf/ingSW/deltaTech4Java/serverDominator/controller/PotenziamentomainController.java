package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Cpu;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Firewall;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Ram;
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
	
	

	
	public void initPotenziamento (PartitaStage partitaStage, MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, UserAccount userAccount, PersistenceFacade persistenceFacade, Ram ram, Cpu cpu, Firewall fireWall) {
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> { //RAM
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO RAM,BASE \t",
					mainDefinitivo.powerupCheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
					persistenceFacade.getAssetOwndByUserAccount(userAccount.addAssetOwn(ram.getLivello_risorsa())) //ERROR RICHIAMO addAssetOwn, che stat bisogna richiamare? getRichiesta
					,ram.getTempo_richiesto()); //getTempoRichiesto() dal modello
			
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), ram.); //risorsa
		});
		
		
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> { //CPU
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO CPU, BASE \t",
					mainDefinitivo.powerupCheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
					persistenceFacade.getAssetOwndByUserAccount(userAccount.addAssetOwn(cpu.getLivello_risorsa()))
					,cpu.getTempo_richiesto());
			
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), cpu.)
		});
	
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> { //FIREWALL
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO FIREWALL,BASE \t",
					mainDefinitivo.powerupCheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
					persistenceFacade.getAssetOwndByUserAccount(userAccount.addAssetOwn(fireWall.getLivello_risorsa()))) //ERROR RICHIAMO addAssetOwn, che stat bisogna richiamare? getRichiesta
					,fireWall.getTempo_richiesto())); //getTempoRichiesto() dal modello
			
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), fireWall.); //risorsa
		});
		
		
		
	}
	
}
