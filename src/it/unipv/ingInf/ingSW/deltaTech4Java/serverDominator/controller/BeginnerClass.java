package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;

public class BeginnerClass {
	LoginView loginView;
	SignupView signupView;
	PrebattagliaView prebattagliaView;
	UserAccount userAccount;
	
	
	
	public BeginnerClass(LoginView loginView, SignupView signupView) {
		super();
		this.loginView = loginView;
		this.signupView = signupView;
		this.initAll();
	}
	public void initAll() {
		this.initLogin();
		this.initChangeContextlogin();
		this.initSignup();
		this.initChangeContextSignUp();
		
	}
	
	public void initLogin() {
		loginView.getLoginButton().setOnAction(event ->{
			userAccount=new UserAccount(loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText());
			userAccount=PersistenceFacade.getInstance().getUserAccountById(userAccount);
			if(userAccount == null) {
				loginView.getErrorMessageLabel().setText("Login error, account non existing");
			}else if(userAccount!= null) {//us !=  null --> LoggedIn View
				prebattagliaView.setUserAccount(userAccount);
				loginView.getStage().close();
				//TODO: istanziare prebattaglia e visualizzarla
				prebattagliaView.show();
			}
			
		});
			
	}
	
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
			boolean us = PersistenceFacade.getInstance().insertUserAccount(new UserAccount(signupView.getUsernameTextFieldSignup().getText(), 
					signupView.getPasswordTextFieldSignup().getText(), signupView.getEmailTextField().getText()));
			if(us) {
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
