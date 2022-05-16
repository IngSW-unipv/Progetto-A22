package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.LoginView;
import javafx.stage.Stage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

public class LoginController {
	
	private static LoginController loginController = null;
	private UserAccount userAccount;
	private LoginController() {
		super();
	}
	
	
	public static LoginController getInstance() {
		if(loginController == null)
			loginController = new LoginController();
		
		return loginController;
	}
	
	//public void setView(UserAccount userAccount) {
		//LoginView.getUsernameTextField().userAccount(getUsername());
	//}
	
	
	
	public void initLogin(PersistenceFacade persistenceFacade, ControllerFacade controllerFacade, LoginView loginView) {
		//UserAccount userAccount;
		//link View TextFIelds a Persistence Facade
		//LoginView.getUsernameTextField().textProperty(userAccount.getUsername());
		//String username = null;
		//String passw = null;
		loginView.getLoginButton().setOnAction(actionEvent ->{
			userAccount=new UserAccount(loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText());
			userAccount=persistenceFacade.getUserAccountById(userAccount);
			if(userAccount == null) {
				loginView.getErrorMessageLabel().setText("Login error, account non existing");
			}else if(userAccount!= null) {//us !=  null --> LoggedIn View
				controllerFacade.getPrebattagliaView().getStage().show(); //14052022 da correggere
				loginView.getStage().close();
			}
			
		});
			
	}
	
	
	public void initChangeContext(Stage signupStage, PersistenceFacade persistenceFacade, LoginView loginView) { //cambiare scene, non fa il signup, da ControllerFacade
		loginView.getSignupButton().setOnAction(actionEvent->{
			loginView.getStage().close();
			signupStage.show();
		});
			
	
	}


	

}
