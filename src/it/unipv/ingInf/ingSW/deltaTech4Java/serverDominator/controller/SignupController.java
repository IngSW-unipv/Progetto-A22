package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class SignupController {
	
	
	private UserAccount userAccount;
	private PersistenceFacade persistenceFacade; 
	//private LoginView loginView;
	private PrebattagliaView prebattagliaView;
	private SignupView signupView;
	private Stage loginStage;
	
	private static SignupController signupController = null;
	
	private SignupController() {
		super();
	}
	
	public static SignupController getInstanSignupController() {
		if(signupController == null) 
			signupController = new SignupController();
		
		return signupController;
	}
	
	/**
	 * 
	 * @param signupView
	 * @param persistenceFacade
	 * @param loginStage
	 * @param userAccount
	 * Metodo associato al pulsante Signup per registrare un nuovo utende a Server Dominator
	 */
			
	public void initSignup() {
		//String username = null;
		//String passw = null;
		//String email = null;
		signupView.getSignupButtonLegit().setOnAction(ActionEvent ->{
			
			//userAccount=new UserAccount(signupView.getUsernameTextFieldSignup().getText(), signupView.getPasswordTextFieldSignup().getText());
			boolean us = persistenceFacade.getInstance().insertUserAccount(new UserAccount(signupView.getUsernameTextFieldSignup().getText(),  //togliere static a getInstance? 
					signupView.getPasswordTextFieldSignup().getText(), signupView.getEmailTextField().getText()));
			if(us) {
				//boolean true passa logged in
				signupView.getStage().close();
				loginStage.show();
				
			}else {
				//boolean false error message
				signupView.getErrorMessageLabel().setText("Signup error!");
			}
		});
	
	}
	
	/**
	 * @param signupView
	 * @param loginStage
	 * Metodo abbitnato al pulsante Log in nella view di Signup, per passare alla view di Login
	 * 
	 */
	
	public void initChangeContext() {//cambiare scene, non fa il login, da ControllerFacade
		signupView.getLoginButtonChange().setOnAction(actionEvent -> {
			signupView.getStage().close();
			loginStage.show();
		});
	
}
		
}
