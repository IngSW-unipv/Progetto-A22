package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.Main;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PrebattagliaView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class PrebattagliaController {
	
	private static PrebattagliaController prebattagliaController = null;
	
	public PrebattagliaController() {
		super();
	}
	
	public static PrebattagliaController getIstance(){
	      if(prebattagliaController==null)
	    	  prebattagliaController=new PrebattagliaController();
	      return prebattagliaController;
	    }
	
	//.getPrebattagliaView da correggere/completare
	//try/catch giusto nell'avvio partita? valuta passa da persistencefacade
	
	/**
	 * @author Gianl Castillo
	 * @param mainView
	 * @param mainModello
	 * @param controllerFacade
	 * @param userAccount
	 * Metodo per generare la partita e la mappa di gioco, con modalità facile e dimensioni ridotte
	 */
	public void initEasyGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, UserAccount userAccount, PartitaStage partitaStage) {
		controllerFacade.getPrebattagliaView().getEasyGame().setOnAction(actionEvent ->{
			//mainView.generateEasyMap();
			
			//controllerFacade.getPartitaStage().getScene().show();
			partitaStage.show(); //non esattamente
				try {
					mainModello.avvioPartita(15, 10, userAccount.getUsername(), userAccount.getMny());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		});
			
	}
	
	/**
	 * @author Gianl Castillo
	 * @param mainView
	 * @param mainModello
	 * @param controllerFacade
	 * @param userAccount
	 * Metodo per generare la partita e la mappa di gioco, con modalità media (standard) e dimensioni normali
	 */
	public void initMediumGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, UserAccount userAccount) {
		controllerFacade.getPrebattagliaView().getMediumGame().setOnAction(actionEvent ->{
			//mainView.generateMediumMap();
			//mainView.getDimensioniMappa(null);
				try {
					mainModello.avvioPartita(20, 15, userAccount.getUsername(), userAccount.getMny());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		
	}
	
	/**
	 * @author Gianl Castillo
	 * @param mainView
	 * @param mainModello
	 * @param controllerFacade
	 * @param userAccount
	 * Metodo per generare la partita e la mappa di gioco, con modalità difficile e dimensioni massime
	 */
	public void initHardGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, UserAccount userAccount) {
		controllerFacade.getPrebattagliaView().getHardGame().setOnAction(actionEvent ->{
			//mainView.generateHardMap();
			//mainView.getDimensioniMappa(null);
			
				try {
					mainModello.avvioPartita(20, 30, userAccount.getUsername(), userAccount.getMny());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		});
	}

	
	
}
