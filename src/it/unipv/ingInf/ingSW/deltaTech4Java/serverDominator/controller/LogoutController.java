package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class LogoutController {

	private PrebattagliaView prebattagliaView;
	private PersistenceFacade persistenceFacade;
	private LoginView loginStage;
	
	
	
	public LogoutController(PrebattagliaView prebattagliaView, PersistenceFacade persistenceFacade,
			LoginView loginStage) {
		super();
		this.prebattagliaView = prebattagliaView;
		this.persistenceFacade = persistenceFacade;
		this.loginStage = loginStage;
		this.initLogoutController();
	}



	/**
	 * Metodo che aggiunge la azioni che è neccessario svolgere per 
	 * fare il logOut
	 */
	
	public void initLogoutController() {
		Menu menu=new Menu("SdMenu");
		MenuItem menuItem=new MenuItem("Logout");
		menuItem.setOnAction(event->{
			persistenceFacade.persistenceOff();
			prebattagliaView.close();
			loginStage.getStage().show();
			
		});
		menu.getItems().addAll(menuItem);
		prebattagliaView.getMenu().getMenus().addAll(menu);
	}


	/**
	 * Restituisce la view della lobby
	 * @return
	 */
	public PrebattagliaView getPrebattagliaView() {
		return prebattagliaView;
	}



	public void setPrebattagliaView(PrebattagliaView prebattagliaView) {
		this.prebattagliaView = prebattagliaView;
	}



	public PersistenceFacade getPersistenceFacade() {
		return persistenceFacade;
	}



	public void setPersistenceFacade(PersistenceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}

	/**
	 * Restituisce la view della login
	 * @return
	 */

	public LoginView getLoginStage() {
		return loginStage;
	}



	public void setLoginStage(LoginView loginStage) {
		this.loginStage = loginStage;
	}
	
}
