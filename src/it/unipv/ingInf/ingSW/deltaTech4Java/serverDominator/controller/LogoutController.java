package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PrebattagliaView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class LogoutController {

	private static LogoutController logoutController = null;
	private MainDefinitivo mainDefinitivo;
	private PrebattagliaView prebattagliaView;
	private PersistenceFacade persistenceFacade;
	private LoginView loginStage;
	
	//DA CAMBIARE PrebattagliaView, restyling da parte di Habib
	
	
	
	public LogoutController() {
		super();
		this.initLogoutController();
	}
	
	
	public LogoutController getInstancer() {
		if(logoutController == null) 
			logoutController = new LogoutController();
	
		return logoutController;
	}
	
	/**
	 * @param persistenceFacade
	 * @param loginStage
	 * @param prebattagliaView
	 * Metodo associato al pulsante Log Out nella lobby, per chiudere la sessione e tornare alla view di Login
	 */
	
	private void initLogoutController() {
		// TODO Auto-generated method stub
		prebattagliaView.getLogoutButton().setOnAction(actionEvent -> {
			//disactivate persistencefacade
			//loginView show
			persistenceFacade.persistenceOff();
			
			prebattagliaView.getStage().close();
			loginStage.show();
		});
		
	}
	
}
