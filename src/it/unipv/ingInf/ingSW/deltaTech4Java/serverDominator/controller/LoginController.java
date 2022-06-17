package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;

public class LoginController {
	private LoginView loginView;
	private SignupView signupView;
	
	
	
	public LoginController(LoginView loginView, SignupView signupView) {
		super();
		this.loginView = loginView;
		this.signupView = signupView;
		this.initAll();
	}
	public void initAll() {
		//this.initLogin();
		this.initChangeContextlogin();
		this.initSignup();
		this.initChangeContextSignUp();
		
	}
	/*
	public void initLogin() {
		loginView.getLoginButton().setOnAction(event ->{
			userAccount=new UserAccount(loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText());
			userAccount=PersistenceFacade.getInstance().getUserAccountById(userAccount);
			if(userAccount == null) {
				loginView.getErrorMessageLabel().setText("Login error, account non existing");
			}else if(userAccount!= null) {//us !=  null --> LoggedIn View
				prebattagliaView.setUserAccount(userAccount);
				loginView.getStage().close();
				//TODO: istanziare prebattaglia e visualizzarla. PrebattagliaView lobby = new Prebattaglia(base)
				LobbyView lobby = new LobbyView(userAccount);
				lobby.show();
			}
			
		});
			
	}*/
	
	/**
	 * Metodo abbinato al pulsante Signup di LoginView, cambia Stage e porta l'utente all'interfaccia di registrazione SignupView
	 */
	public void initChangeContextSignUp() { //cambiare scene, non fa il signup, da ControllerFacade
		loginView.getSignupButton().setOnAction(actionEvent->{
			loginView.getStage().close();
			signupView.getStage().show();
		});
			
	
	}
	public void initSignup() {
		signupView.getSignupButtonLegit().setOnAction(ActionEvent ->{
			UserAccount user=new UserAccount(signupView.getUsernameTextFieldSignup().getText(), 
					 signupView.getEmailTextField().getText(),signupView.getPasswordTextFieldSignup().getText());
			
			boolean us = PersistenceFacade.getInstance().insertUserAccount(user);
			ArrayList<Obiettivo> obDiPunteggio= PersistenceFacade.getInstance().getObiettivi(new ObPunteggio()).selectAll();
			
			
			if(us) {

				for (Obiettivo o:obDiPunteggio) {
					PersistenceFacade.getInstance().insertObiettiviUser(new ObiettiviUser(o,user,"NON COMPLETATO"));
				}
				
				signupView.getStage().close();
				loginView.getStage().show();
				
			}else {
				signupView.getErrorMessageLabel().setText("Signup error!");
			}
		});
	
	}
	
	public void initChangeContextlogin() {//cambiare scene, non fa il login, da ControllerFacade
		signupView.getLoginButtonChange().setOnAction(actionEvent -> {
			signupView.getStage().close();
			loginView.getStage().show();
		});
	
	}
	
}