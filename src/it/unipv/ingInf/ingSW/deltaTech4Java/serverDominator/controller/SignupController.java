package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.SignupView;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.LoginView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class SignupController {
	
	private static SignupController signupController = null;
	
	private SignupController() {
		super();
	}
	
	public static SignupController getInstanSignupController() {
		if(signupController == null) 
			signupController = new SignupController();
		
		return signupController;
	}
	
			
	public void initSignup(SignupView signupView, PersistenceFacade persistenceFacade, Stage loginStage, UserAccount userAccount) {
		//String username = null;
		//String passw = null;
		//String email = null;
		signupView.getSignupButtonLegit().setOnAction(ActionEvent ->{
			
			//userAccount=new UserAccount(signupView.getUsernameTextFieldSignup().getText(), signupView.getPasswordTextFieldSignup().getText());
			boolean user = PersistenceFacade.getInstance().insertUserAccount(new UserAccount(signupView.getUsernameTextFieldSignup().getText(), 
					signupView.getUsernameTextFieldSignup().getText(), signupView.getEmailTextField().getText()));
			if(user) {
				//boolean true passa logged in
				signupView.getStage().close();
				loginStage.show();
				
			}else {
				//boolean false error message
				signupView.getErrorMessageLabel().setText("Signup error!");
			}
		});
	
	}
	
	public void initChangeContext(Stage loginStage, ControllerFacade controllerFacade, SignupView signupView) {//cambiare scene, non fa il login, da ControllerFacade
		controllerFacade.getSignupView().getLoginButtonChange().setOnAction(actionEvent ->{
			signupView.getStage().close();
			loginStage.show();
		});
	
}
		
}
