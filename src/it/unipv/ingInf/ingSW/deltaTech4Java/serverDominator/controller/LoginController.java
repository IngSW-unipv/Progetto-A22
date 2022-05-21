package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import javafx.stage.Stage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;

public class LoginController {
	
	private UserAccount userAccount;
	private PersistenceFacade persistenceFacade; 
	private LoginView loginView;
	private PrebattagliaView prebattagliaView;
	private Stage signupStage;
	
	
	public LoginController(UserAccount userAccount, PersistenceFacade persistenceFacade, LoginView loginView,
			PrebattagliaView prebattagliaView, Stage signupStage) {
		super();
		this.userAccount = userAccount;
		this.persistenceFacade = persistenceFacade;
		this.loginView = loginView;
		this.prebattagliaView = prebattagliaView;
		this.signupStage = signupStage;
	}
	public void initListeners() {
		initLogin();
		initChangeContext();

	}
	/**
	 * Metodo action event abbinato al pulsante di Login nell'interfaccia di LoginView, passa al PersistenceFacade lo username e la password 
	 * inserite dall'utente, PersistenceFacade controlla se esistono gia nel database e se corrette passa alla view di scelta di battaglia
	 */
	public void initLogin() {
		loginView.getLoginButton().setOnAction(event ->{
			userAccount=new UserAccount(loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText());
			userAccount=persistenceFacade.getUserAccountById(userAccount);
			if(userAccount == null) {
				loginView.getErrorMessageLabel().setText("Login error, account non existing");
			}else if(userAccount!= null) {//us !=  null --> LoggedIn View
				prebattagliaView.setUserAccount(userAccount);
				loginView.getStage().close();
				prebattagliaView.show();
			}
			
		});
			
	}
	
	/**
	 * Metodo abbinato al pulsante Signup di LoginView, cambia Stage e porta l'utente all'interfaccia di registrazione SignupView
	 */
	public void initChangeContext() { //cambiare scene, non fa il signup, da ControllerFacade
		loginView.getSignupButton().setOnAction(actionEvent->{
			loginView.getStage().close();
			signupStage.show();
		});
			
	
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public PersistenceFacade getPersistenceFacade() {
		return persistenceFacade;
	}
	public void setPersistenceFacade(PersistenceFacade persistenceFacade) {
		this.persistenceFacade = persistenceFacade;
	}
	public LoginView getLoginView() {
		return loginView;
	}
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
	public PrebattagliaView getPrebattagliaView() {
		return prebattagliaView;
	}
	public void setPrebattagliaView(PrebattagliaView prebattagliaView) {
		this.prebattagliaView = prebattagliaView;
	}
	public Stage getSignupStage() {
		return signupStage;
	}
	public void setSignupStage(Stage signupStage) {
		this.signupStage = signupStage;
	}


	

}
