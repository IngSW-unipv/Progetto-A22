package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
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
	
	
	public void initPotenziamento(MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, PartitaStage partitaStage, UserAccount userAccount,Base base) {
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent-> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO RAM, BASE:\t,
					mainDefinitivo.powerupCheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
					mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
							userAccount.addAssetOwn(popupFacade.avviaDevelopment(null))) //DA CORREGGERE
					
					, 0); //TO-DO da inserire i parametri, String titolo battaglia e temppo
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), null);
		});
	}
	
}
