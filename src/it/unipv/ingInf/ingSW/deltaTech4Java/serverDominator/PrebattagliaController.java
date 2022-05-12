package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.Main;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PrebattagliaView;

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
	public void initEasyGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, String nomeUtente, int valuta) {
		controllerFacade.getPrebattagliaView().getEasyGame().setOnAction(actionEvent ->{
			//mainView.generateEasyMap();
				try {
					mainModello.avvioPartita(15, 10, nomeUtente, valuta);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 //lascio null per username? valuta lo stabilisce controller o db?
		});
			
	}
	
	public void initMediumGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, String nomeUtente) {
		controllerFacade.getPrebattagliaView().getMediumGame().setOnAction(actionEvent ->{
			//mainView.generateMediumMap();
			
				try {
					mainModello.avvioPartita(20, 15, nomeUtente, 50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		
	}
	
	public void initHardGame(Main mainView, MainDefinitivo mainModello, ControllerFacade controllerFacade, String nomeUtente) {
		controllerFacade.getPrebattagliaView().getHardGame().setOnAction(actionEvent ->{
			//mainView.generateHardMap();
				try {
					mainModello.avvioPartita(20, 30, nomeUtente, 25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		});
	}

	
	
}
