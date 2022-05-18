package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Cpu;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Energia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Firewall;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Ram;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Risorse;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;

public class PotenziamentomainController {
	private static PotenziamentomainController potenziamentomainController = null;
	private MainDefinitivo mainDefinitivo;
	
	private PotenziamentomainController() {
		super();
	}
	
	public static PotenziamentomainController getInstance() {
		if(potenziamentomainController == null)
			potenziamentomainController = new PotenziamentomainController();
		
		return potenziamentomainController;
	}
	
	
//TO-DO aggiungere ScrollPane per ogni actionevent e aggiungere metodo getRisorse() da main modello
	
	public void initPotenziamento (PartitaStage partitaStage, MainDefinitivo mainDefinitivo, PopUpFacade popupFacade, Base base, Risorse risorse, Energia energia) {
		
		/**
		 * Metodo associato al pulsante power up riguardante la RAM
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO RAM, BASE \t", 0); //al posto di 0, meotdo da modello getRisorseDisponibile getnodo.getselectednodo.getRisorse()esporreRisorse per il tempo
			//addScrollPane
			
		
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
					 "ram");
			
		});
		
		/**
		 * Metodo potenziamento relativo alla CPU
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO CPU, BASE \t", 0); //sameissue mainDefinitivo
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "cpu");
			
		});
		
		
		/**
		 * Metodo potenziamento relativo al FIREWALL
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO FIREWALL, BASE \t", 0);
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "firewall");
		});
		
		
		/**
		 * Metodo potenziamento all'ENERGIA
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent ->{
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO ENERGIA, BASE\t", 0);
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "energia");
			
		});
		
		/**
		 * Metodo potenziamento relativo all'antivirus
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO ANTIVIRUS, BASE \t", 0);
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "antivirus");
		});
		
		
		/**
		 * Metodo potenziamento relativo al rootcrash
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO ROOTCRASH, BASE \t", 0);
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "rootcrash");
		});
		
		/**
		 * Metodo potenziamento relativo al virus
		 */
		popupFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
			partitaStage.addPtenziamentoRisorsa("POTENZIAMENTO VIRUS, BASE \t",0 );
			mainDefinitivo.powerup(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), "virus");
		});
		
		
		
		
	}
	
}
