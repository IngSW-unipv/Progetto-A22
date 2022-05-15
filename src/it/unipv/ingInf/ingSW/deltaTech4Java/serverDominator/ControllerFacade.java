package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.SignupView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Market;
import javafx.stage.Stage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.Main;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PopUp;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PrebattagliaView;

/**
 * 
 * @author Gianl Castillo
 * @version 1.0
 * @see LoginView
 * @see SignupView
 * @see PrebattagliaView
 * @see PersistenceFacade
 * @see MainDefinitivo
 * @see Main 
 * 
 * Main plain è riferito al Main della View
 *
 */

public class ControllerFacade {
	
	private LoginView loginView;
	private LoginView loginStage;
	private SignupView signupView;
	private SignupView signupStage;
	private Main mainView;
	private Main easyGame;
	private Main mediumGame;
	private Main hardGame;
	private MainDefinitivo mainModello;
	private LoginController loginController;
	private SignupController signupController;
	private PrebattagliaController prebattagliaController;
	private PrebattagliaView prebattagliaView;
	private PrebattagliaView prebattagliaStage;
	private PopUp popupView;
	private PopUpController popupController;
	private Market market;
	private Main mView = new Main();
	

	public ControllerFacade(Main mainView, MainDefinitivo mainModello,LoginController loginController, SignupController signupController) {
		this.mView=mainView;
		this.mainModello=mainModello;
		this.loginController = loginController;
		this.signupController = signupController;
	}
	
	public ControllerFacade() {
		super();
		this.mView = new Main();
	}
	
	//DA RIPULIRE BENE poi in assemblaggio
	public void setSignup(SignupView signupView) {
		this.signupView = signupView;
	}
	
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}


	public void setSignupController(SignupController signupController) {
		this.signupController = signupController;
	}
	
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public void setPrebattagliaView(PrebattagliaView prebattagliaView) {
		this.prebattagliaView = prebattagliaView;
	}
	
	public void setPrebattagliaController(PrebattagliaController prebattagliaController) {
		this.prebattagliaController = prebattagliaController;
	}
	
	public void setPopUpView(PopUp popupView) {
		this.popupView = popupView;
	}
	
	public void setPopUpController(PopUpController popupController) {
		this.popupController = popupController;
	}
	
	
	
	
	
	public PrebattagliaView getPrebattagliaView() {
		return prebattagliaView;
	}
	
	public LoginView getLoginView() {
		return loginView;
	}
	
	public SignupView getSignupView() {
		return signupView;
	}
	
	public Main getmainView() {
		return mainView;
	}
	
	public Market getMarket() {
		return market;
	}
	
	public LoginView getLoginStage() {
		// TODO Auto-generated method stub
		return loginStage;
	}
	
	public SignupView getSignupStage() {
		// TODO Auto-generated method stub
		return signupStage;
	}
	
	public PrebattagliaView getPrebattagliaStage() {
		return prebattagliaStage;
	}
	//da cambiare, da richiamare da prebattaglia view o mainview? dalla view, prebattaglia non genera la mappa
//metodi easyGame, mediumGame,hardGame da mettere in mainView
	//13052022 rimovibili
	//public Main getPrebattagliaeasyView() {
	//	return easyGame;
	//}
	
	//public Main getPrebattagliamediumView() {
	//	return mediumGame;
	//}
	
	//public Main getPrebattagliahardView() {
	//	return hardGame;
	//}

	

	
}
