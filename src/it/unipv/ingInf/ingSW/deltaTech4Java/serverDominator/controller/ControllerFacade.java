package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.BotObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.ClassificaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.FinePartitaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.NodoObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LobbyView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;
import sreverDominator.view.Main;

/**
 * 
 * @author Gianl Castillo
 * @version 1.0
 * @see LoginView
 * @see SignupView
 * @see LobbyView
 * @see PersistenceFacade
 * @see MainDefinitivo
 * @see Main 
 * 
 * Main plain è riferito al Main della View
 *
 */

public class ControllerFacade {
	
	private LoginView loginStage;
	private SignupView signupView;
	private PartitaStage partitaStage;
	private MainDefinitivo mainModello;
	private LoginController loginController;
	private SignupController signupController;
	private PrebattagliaController prebattagliaController;
	private LobbyView prebattagliaView;
	private PopUpFacade popupView;
	private PopUpController popupController;
	
	private AttackmainController attackmainController;
	private CheckButtonController checkbuttonController;
	private PotenziamentomainController potenziamentomainController;
	private MarketmainController marketmainController;
	private ProduzionemainController produzionemainController;
	
	private BotObserver botObserver;
	private FinePartitaObserver finepartitaObserver;
	private NodoObserver nodoObserver;
	
	
 /**
  * @author Gianl Castillo
  * @param mainView
  * @param mainModello
  * @param loginController
  * @param signupController
  */ 
	
	public ControllerFacade(Main mainView, MainDefinitivo mainModello, LoginController loginController,
			SignupController signupController, Base b) {

		this.mainModello = mainModello;

		this.partitaStageMaker(b);
		this.loginController = loginController;
		this.signupController = signupController;
		this.botObserver = new BotObserver(mainModello, partitaStage);
		this.finepartitaObserver = new FinePartitaObserver(mainModello, partitaStage, prebattagliaView.getStage(),
				partitaStage.getClassificaPane()); // fine partita
		this.nodoObserver = new NodoObserver(partitaStage);
	}
	
	public ControllerFacade() {
		super();
		
	}
	
	//DA RIPULIRE BENE poi in assemblaggio
	public void setSignup(SignupView signupView) {
		this.signupView = signupView;
	}
	
	/**
	 * @author Gianl Castillo
	 * @param b
	 * 
	 * metodo che permette di richiamare le logiche dal main del modello, per permettere ai pulsanti di essere cliccabili o meno
	 * a seconda del nodo cliccato
	 */
	
	public void partitaStageMaker(Base b) {
		PartitaStage ps = new PartitaStage(mainModello, b, 0) { //durataPartitaSeconds*60
			@Override 
			
			public void doOnClic() { //logica dei pulsanti
				// Importante leggere i commenti
				// TODO Auto-generated method stub
				// non fa niene di aggiuntico
				// RIVOLTO A GIAN: QUI PUOI METTERE LE ISTRUZIONI (AGGIUNTIVE) CHE
				// VENGONO ESEGUITE QUANDO IL GIOCATORECLICCA SU UN NODO 
				//(ESEMPIO LE POLITICHE E I CONTROLLI CHE SI FANNO PER ATTIVAZIONE DEI PULSANTI)
				//SOLO A TITOLO DIMOSTRATIVO STAMPO LE COORDINATE DEL NODO CLICCATO
				
				//actionevents o semplici if? NO ACTIONEVENT
				//dopo click nodo , metodi partono tutti insieme - powerupcheck, softcheck, marketcheck, nodecheck(abilita attacca), non interessanti per la view
				//setButtonAvailabilityInPane
				//setButtonVisibilityInStatsPane
				boolean samePlayer = this.getSelectedBase().getPossessore().getNome().equals(this.getSelectedNode().getPossessore().getNome());
				this.setButtonsVisibilityInActionPane(true, samePlayer, samePlayer); // market etc
				
				boolean precedenteBase=mainModello.getTabellone().getScelta()>0? true:false;
				mainModello.getTabellone().checkbasi(this.getSelectedBase().getPossessore());
				boolean prossimaBase=mainModello.getTabellone().getContabasi()-mainModello.getTabellone().getScelta()>0? true:false;
				this.setButtonsVisibilityInActionPaneStatsPane(precedenteBase, prossimaBase); // cambiare base
				
				boolean attaccabile=mainModello.nodecheck(this.getSelectedBase().getPossessore(),this.getSelectedPoint().getIntX(),
						this.getSelectedPoint().getIntY());//attiva pulsante attacca
				this.getStatsNodePane().getButtonAttacca().setDisable(!attaccabile);
				
			}

			@Override
			public void fineGioco() {
				// TODO Auto-generated method stub
				
			}
		};
		
		partitaStage = ps;
	}


	public void setSignupController(SignupController signupController) {
		this.signupController = signupController;
	}
	
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	public void setPrebattagliaView(LobbyView prebattagliaView) {
		this.prebattagliaView = prebattagliaView;
	}
	
	public void setPrebattagliaController(PrebattagliaController prebattagliaController) {
		this.prebattagliaController = prebattagliaController;
	}
	
	public void setPopUpView(PopUpFacade popupView) {
		this.popupView = popupView;
	}
	
	public void setPopUpController(PopUpController popupController) {
		this.popupController = popupController;
	}
	
	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}
	
	public void setAttackmainController(AttackmainController attackmainController) {
		this.attackmainController = attackmainController;
	}
	
	public void setCheckButtonController(CheckButtonController checkbuttonController) {
		this.checkbuttonController = checkbuttonController;
	}

	public void setMarketmainController(MarketmainController marketmainController) {
		this.marketmainController = marketmainController;
	}
	
	public void setPotenziamentomainController(PotenziamentomainController potenziamentomainController) {
		this.potenziamentomainController = potenziamentomainController;
	}
	
	public void setProduzionemainController(ProduzionemainController produzionemainController) {
		this.produzionemainController = produzionemainController;
	}
	
	public void setBotObserver(BotObserver botObserver) {
		this.botObserver = botObserver;
	}
	
	//public void setClassificaObserver(ClassificaObserver classificaObserver) {
		//this.classificaObserver = classificaObserver;
	//}
	
	public void setFinePartitaObserver(FinePartitaObserver finepartitaObserver) {
		this.finepartitaObserver = finepartitaObserver;
	}
	
	public  void setNodoObserver(NodoObserver nodoObserver) {
		this.nodoObserver = nodoObserver;
	}
	
	public LobbyView getPrebattagliaView() {
		return prebattagliaView;
	}

	
	public SignupView getSignupView() {
		return signupView;
	}

	public LoginView getLoginStage() {
		// TODO Auto-generated method stub
		return loginStage;
	}
	
	public PartitaStage getPartitaStage() {
		return partitaStage;
	}
	
	public AttackmainController getAttackmainController() {
		return attackmainController;
	}
	
	public CheckButtonController getCheckButtonController() {
		return checkbuttonController;
	}
	
	public MarketmainController getMarketmainController() {
		return marketmainController;
	}
	
	public PotenziamentomainController getPotenziamentomainController() {
		return potenziamentomainController;
	}
	
	public ProduzionemainController getProduzionemainController() {
		return produzionemainController;
	}
	
	public BotObserver getBotObserver() {
		return botObserver;
	}
	
	//public ClassificaObserver getClassificaObserver() {
		//return classificaObserver;
	//}
	
	public FinePartitaObserver getFinePartitaObserver() {
		return finepartitaObserver;
	}

	public NodoObserver getNodoObserver() {
		return nodoObserver;
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
