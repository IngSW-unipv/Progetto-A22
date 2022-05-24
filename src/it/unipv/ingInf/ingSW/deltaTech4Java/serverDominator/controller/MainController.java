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
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.FinePartitaObserver;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers.NodoObserver;
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
		
	public static void main(String[] args) {
		MainController m= new MainController();
	}
	
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
							(Base)mainDefinitivo.getTabellone().trovaBase
							(new Utente(userAccount.getUsername(),userAccount.getMny())), lobbyView.getSelectedDifecolta()[0]*60*1000);
					
				partitaCont = new PartitaController(mainDefinitivo, partitaStage,
								(Base)mainDefinitivo.getTabellone().trovaBase(new Utente(userAccount.getUsername(),userAccount.getMny())));
					
				finePartita= new FinePartitaObserver(mainDefinitivo, partitaStage, lobbyView, partitaStage.getClassificaPane());
				Partita p=(Partita)partitaStage;
				p.setFineObserver(finePartita);
				lobbyView.close();
				this.initObservers(mainDefinitivo, finePartita, new BotObserver(mainDefinitivo, partitaStage),new NodoObserver(partitaStage));
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

	private void initObservers(MainDefinitivo main,FinePartitaObserver finePartita,BotObserver bo,NodoObserver o ) {
		main.getGiocatori()[1].getChanges().addPropertyChangeListener(Giocatore.GIOCATORE_PROP,finePartita);
		for(int i=2;i<main.getGiocatori().length;i++) {
			if (Bot.class.isAssignableFrom(main.getGiocatori()[i].getClass()))
				main.getGiocatori()[i].getChanges().addPropertyChangeListener(Bot.BOT_PROP,bo );
		}
		for(int i=0;i<main.getTabellone().getX_max(); i++) {
			for(int j=0; j<main.getTabellone().getY_max();j++) {
				main.getTabellone().getNodo(i, j).getChanges().addPropertyChangeListener(Nodo.NOME_POSS_PROP,o );
			}
		}
		
	}
	/*
	 * Exception in thread "JavaFX Application Thread" java.lang.ClassCastException: class it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Cloud 
	 * cannot be cast to class it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base (it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Cloud 
	 * and it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base are in unnamed module of loader 'app')
	 */
}
