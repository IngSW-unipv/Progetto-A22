package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.Partita;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.BotObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.ClassificaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.FinePartitaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.NodoObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.util.ProgressStyle;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LobbyView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MainController {

	private MainDefinitivo mainDefinitivo;

	private LoginController login;
	private PartitaController partitaCont;
	private PartitaStage partitaStage;
	private FinePartitaObserver finePartita;
	private LobbyView lobbyView;
	private LoginView loginView;
	private SignupView signupView;
	private UserAccount userAccount;
	
	public MainController() {
		this.loginView= new LoginView();
		this.signupView = new SignupView();
		this.login= new LoginController(loginView, signupView);
		this.loginView.getStage().show();
		this.initLogin();
		}

	public void initLogin() {
		//quando utente clicca su login
		loginView.getLoginButton().setOnAction(event ->{
			userAccount=new UserAccount(loginView.getUsernameTextField().getText(), loginView.getPasswordTextField().getText());
			userAccount=PersistenceFacade.getInstance().getUserAccountById(userAccount);
			if(userAccount == null) {
				loginView.getErrorMessageLabel().setText("Login error, account non existing");
			}else if(userAccount!= null) {//us !=  null --> LoggedIn View
				
				loginView.getStage().close();
				lobbyView= new LobbyView(userAccount);
				initLobbyController();
				lobbyView.show();
			}
			
		});
		
	}
	
	public void initLobbyController() {
		this.initAvvioPartita();
		this.initLogoutController();
	}
	
	
	public void initAvvioPartita() {
		//quando utente dopo aver scelto difficolta clicca su avvio partita
		
		lobbyView.getAvvioPartita().setOnAction(actionEvent -> {
			mainDefinitivo=new MainDefinitivo();
			try {
				mainDefinitivo.avvioPartita(lobbyView.getSelectedDifecolta()[0], lobbyView.getSelectedDifecolta()[1], 
						lobbyView.getUserAccount().getUsername(), lobbyView.getUserAccount().getMny());
					
				partitaStage=new Partita(mainDefinitivo, 
							mainDefinitivo.getTabellone().trovaBase(new Utente(userAccount.getUsername(),userAccount.getMny())));
					
				partitaCont = new PartitaController(mainDefinitivo, partitaStage,
								(Base)mainDefinitivo.getTabellone().trovaBase(new Utente(userAccount.getUsername(),userAccount.getMny())));
					
				finePartita= new FinePartitaObserver(mainDefinitivo, partitaStage, lobbyView, userAccount);
				Partita p=(Partita)partitaStage;
				p.setFineObserver(finePartita);
				partitaStage.getFineProgress().addElement("tempo partita", lobbyView.getSelectedDifecolta()[0]*60*1000, ProgressStyle.BLACK_STYLE);
				partitaStage.addAvviso("inizio partita...");
				p.setFineObserver(finePartita);
				lobbyView.close();
				this.initObservers(mainDefinitivo, finePartita, partitaStage);
				partitaStage.show();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}
	
	public void initLogoutController() {
		Menu menu=new Menu("Menu");
		MenuItem menuItem=new MenuItem("Logout");
		menuItem.setOnAction(event->{
			PersistenceFacade.getInstance().persistenceOff();
			lobbyView.close();
			loginView.getStage().show();
			
		});
		menu.getItems().addAll(menuItem);
		lobbyView.getMenu().getMenus().addAll(menu);
	}

	private void initObservers(MainDefinitivo main,FinePartitaObserver finePartita,PartitaStage  partitaStage) {
		//mainDefinitivo, finePartita, new BotObserver(mainDefinitivo, partitaStage),new NodoObserver(partitaStage)
		main.getGiocatori()[1].getChanges().addPropertyChangeListener(Giocatore.GIOCATORE_PROP,finePartita);
		for(int i=2;i<main.getGiocatori().length;i++) {
			if (Bot.class.isAssignableFrom(main.getGiocatori()[i].getClass()))
				main.getGiocatori()[i].getChanges().addPropertyChangeListener(Bot.BOT_PROP,new BotObserver(mainDefinitivo, partitaStage) );
				main.getGiocatori()[i].getChanges().addPropertyChangeListener(Bot.PUNTEGGIO_BOT,new ClassificaObserver(this.partitaStage.getClassificaPane()));
		}
		for(int i=0;i<main.getTabellone().getX_max(); i++) {
			for(int j=0; j<main.getTabellone().getY_max();j++) {
				main.getTabellone().getNodo(i, j).getChanges().addPropertyChangeListener(Nodo.NOME_POSS_PROP,new NodoObserver(partitaStage) );
			}
		}
		
	}

	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}

	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}

	public LoginController getLogin() {
		return login;
	}

	public void setLogin(LoginController login) {
		this.login = login;
	}

	public PartitaController getPartitaCont() {
		return partitaCont;
	}

	public void setPartitaCont(PartitaController partitaCont) {
		this.partitaCont = partitaCont;
	}

	public PartitaStage getPartitaStage() {
		return partitaStage;
	}

	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}

	public FinePartitaObserver getFinePartita() {
		return finePartita;
	}

	public void setFinePartita(FinePartitaObserver finePartita) {
		this.finePartita = finePartita;
	}

	public LobbyView getLobbyView() {
		return lobbyView;
	}

	public void setLobbyView(LobbyView lobbyView) {
		this.lobbyView = lobbyView;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public SignupView getSignupView() {
		return signupView;
	}

	public void setSignupView(SignupView signupView) {
		this.signupView = signupView;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
