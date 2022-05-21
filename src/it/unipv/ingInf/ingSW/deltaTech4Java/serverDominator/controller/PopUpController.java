package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;


public class PopUpController {

    private MainDefinitivo mainModello;
    private PopUpFacade popUpFacade;
    private PartitaStage partitaStage;
    
    


    public PopUpController(MainDefinitivo mainModello, PopUpFacade popUpFacade, PartitaStage partitaStage) {
		super();
		this.mainModello = mainModello;
		this.popUpFacade = popUpFacade;
		this.partitaStage = partitaStage;
		this.initPowerUp();
		this.initDevelopment();
	}

	public void initPowerUp(){
        popUpFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
            if(popUpFacade.getPopUpPowerup().getEnergy()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"energia");
                partitaStage.addPtenziamentoRisorsa("Potenziamento energia in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"energia"));
            }

            if(popUpFacade.getPopUpPowerup().getCpu()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"cpu");
                partitaStage.addPtenziamentoRisorsa("Potenziamento cpu in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"cpu"));
            }

            if(popUpFacade.getPopUpPowerup().getFirewall()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"firewall");
                partitaStage.addPtenziamentoRisorsa("Potenziamento firewall in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"firewall"));
            }

            if(popUpFacade.getPopUpPowerup().getRam()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"ram");
                partitaStage.addPtenziamentoRisorsa("Potenziamento ram in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"ram"));
            }

            popUpFacade.getPopUpPowerup().getStage().close();
        });
    }

    public void initDevelopment(){
        popUpFacade.getPopUpDevelopment().getButtonDevelop().setOnAction(actionEvent -> {
            mainModello.creazioneSoftware("antivirus",
                    popUpFacade.getPopUpDevelopment().getQuantitaAntivirus().getNumber().intValue(),
                    partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
            mainModello.creazioneSoftware("virus",
                    popUpFacade.getPopUpDevelopment().getQuantitaVirus().getNumber().intValue(),
                    partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
            mainModello.creazioneSoftware("rootcrash",
                    popUpFacade.getPopUpDevelopment().getQuantitaRootCrash().getNumber().intValue(),
                    partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
            popUpFacade.getPopUpDevelopment().getStage().close();
        });


    }

	public MainDefinitivo getMainModello() {
		return mainModello;
	}

	public void setMainModello(MainDefinitivo mainModello) {
		this.mainModello = mainModello;
	}

	public PopUpFacade getPopUpFacade() {
		return popUpFacade;
	}

	public void setPopUpFacade(PopUpFacade popUpFacade) {
		this.popUpFacade = popUpFacade;
	}

	public PartitaStage getPartitaStage() {
		return partitaStage;
	}

	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}


}
