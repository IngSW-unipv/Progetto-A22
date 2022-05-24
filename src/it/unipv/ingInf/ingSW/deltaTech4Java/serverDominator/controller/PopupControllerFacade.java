package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;

public class PopupControllerFacade {
	private PartitaStage partitaStage;
	private PopUpFacade popUpFacade;
    private MainDefinitivo mainModello;

    
    
	public PopupControllerFacade(PartitaStage partitaStage, PopUpFacade popUpFacade, MainDefinitivo mainModello) {
		super();
		this.partitaStage = partitaStage;
		this.popUpFacade = popUpFacade;
		this.mainModello = mainModello;
		this.initAll();
	}
	public void initAll() {
		this.initOpenPopUpActionController();
		this.initOpenAttaccoController();
		this.initDevelopment();
		this.initPowerUp();
		this.initMarket();
		this.initAttackMain();
		
	}
	public void initOpenPopUpActionController() {
			
			partitaStage.getActionPane().getActionMarketL().setOnAction(event -> {
				popUpFacade.getPopUpMarket().setBaseUtente(partitaStage.getSelectedBase());
				popUpFacade.avviaMarket();
			});
			
			partitaStage.getActionPane().getPowerUpL().setOnAction(event -> {
				popUpFacade.getPopUpPowerup().setNodoUtente(partitaStage.getSelectedNode());
				popUpFacade.avviaPowerUp();
			});
			
			partitaStage.getActionPane().getDevelop().setOnAction(event -> {
				popUpFacade.getPopUpDevelopment().setNodoUtente(partitaStage.getSelectedNode());
				popUpFacade.avviaDevelopment();
			});
		}
	
	public void initOpenAttaccoController() {
		partitaStage.getStatsNodePane().getButtonAttacca().setOnAction(event->{
			popUpFacade.getPopUpSelectmalware().setBaseUtente(partitaStage.getSelectedBase());
			popUpFacade.avviaSelectMalware();
		});
	}
	public void initPowerUp(){
        popUpFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
            if(popUpFacade.getPopUpPowerup().getEnergy()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Energia");
                partitaStage.addPtenziamentoRisorsa("Potenziamento energia in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Energia"));
            }

            if(popUpFacade.getPopUpPowerup().getCpu()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Cpu");
                partitaStage.addPtenziamentoRisorsa("Potenziamento cpu in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Cpu"));
            }

            if(popUpFacade.getPopUpPowerup().getFirewall()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Firewall");
                partitaStage.addPtenziamentoRisorsa("Potenziamento firewall in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Firewall"));
            }

            if(popUpFacade.getPopUpPowerup().getRam()>0) {
                mainModello.powerup(partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Ram");
                partitaStage.addPtenziamentoRisorsa("Potenziamento ram in corso", mainModello.getTempoRisorsa(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(),"Ram"));
            }

            popUpFacade.getPopUpPowerup().getStage().close();
        });
    }

    public void initDevelopment(){
        popUpFacade.getPopUpDevelopment().getButtonDevelop().setOnAction(actionEvent -> {
        	int quantitaVirus=popUpFacade.getPopUpDevelopment().getQuantitaVirus().getNumber().intValue();
        	int quantitaAntiVirus=popUpFacade.getPopUpDevelopment().getQuantitaAntivirus().getNumber().intValue();
        	int quantitaRootcrash=popUpFacade.getPopUpDevelopment().getQuantitaRootCrash().getNumber().intValue();
        	if(quantitaAntiVirus>0) {
                mainModello.creazioneSoftware("antivirus",quantitaAntiVirus,
                        partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
                partitaStage.addProduzioneSoftware("Produzione antivirus: "+quantitaAntiVirus+"Per il nodo ("
                		+partitaStage.getSelectedHexagon().getX()+","+partitaStage.getSelectedHexagon().getY()+")", mainModello.getTempoSoftware(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(), "antivirus"));
        	}
            if(quantitaVirus>0) {

                mainModello.creazioneSoftware("virus",quantitaVirus,
                        partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
                partitaStage.addProduzioneSoftware("Produzione virus: "+quantitaVirus+"Per il nodo ("
                		+partitaStage.getSelectedHexagon().getX()+","+partitaStage.getSelectedHexagon().getY()+")", mainModello.getTempoSoftware(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(), "virus"));
                
            }
            if(quantitaRootcrash>0) {
                mainModello.creazioneSoftware("rootcrash",quantitaRootcrash,
                        partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY());
                partitaStage.addProduzioneSoftware("Produzione rootcrash: "+quantitaRootcrash+"Per il nodo ("
                		+partitaStage.getSelectedHexagon().getX()+","+partitaStage.getSelectedHexagon().getY()+")", mainModello.getTempoSoftware(
                		partitaStage.getSelectedPoint().getIntX(),partitaStage.getSelectedPoint().getIntY(), "virus"));
            }
            popUpFacade.getPopUpDevelopment().getStage().close();
        });


    }
	public void initAttackMain() {
		
		popUpFacade.getPopUpSelectmalware().getFightButton().setOnAction(actionEvent ->{
			/*
			 * mainModello.battlecheck restituisce un tempo in secondi e 
			 * partitaStage.addAttacco accetta tempo in millisecondi; per questo c'è il *1000
			 */
			partitaStage.addAttacco(partitaStage.getSelectedBase().getPossessore().getNome() + "\t vs \t" + partitaStage.getSelectedNode().getPossessore().getNome()  //
			+ "(" + partitaStage.getSelectedPoint().getIntX() + ", " + partitaStage.getSelectedPoint().getIntY() + ") ",  
			mainModello.battlecheck(partitaStage.getSelectedBase().getPossessore(), 
			partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY(), 
			popUpFacade.getPopUpSelectmalware().getQuantitaVirus(), popUpFacade.getPopUpSelectmalware().getQuantitaRootCrash()) *1000 ); //TO-DO pulsante dopo get nome, stampa anche cooridnate

			
			mainModello.avvioBattaglia(partitaStage.getSelectedBase().getPossessore(), partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY());
		});
	
		
		
	}
	public void initMarket() {
		
		/**
		 * Metodo abbinato all'acquisto di risorse software e hardware dal mercato
		 */
		popUpFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
			int quantitaHardware=-1;
			if(popUpFacade.getPopUpMarket().getQuantitaAntivirus()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popUpFacade.getPopUpMarket().getQuantitaAntivirus(), "antivirus");
			if(popUpFacade.getPopUpMarket().getQuantitaVirus()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popUpFacade.getPopUpMarket().getQuantitaVirus(), "virus");
			if(popUpFacade.getPopUpMarket().getQuantitaRootCrash()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), popUpFacade.getPopUpMarket().getQuantitaRootCrash(), "rootcrash");
			if(popUpFacade.getPopUpMarket().getLivelloCPU()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "cpu");
			if(popUpFacade.getPopUpMarket().getLivelloRam()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "ram");
			if(popUpFacade.getPopUpMarket().getLivelloEnergia()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "energia");
			if(popUpFacade.getPopUpMarket().getLivelloFirewall()>0)
				mainModello.acquistoMercato(partitaStage.getSelectedNode().getPossessore(), quantitaHardware, "firewall");
			this.partitaStage.getBaseStatsPane().make();
			});
	}
	public PartitaStage getPartitaStage() {
		return partitaStage;
	}
	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
		this.initAll();
	}
	public PopUpFacade getPopUpFacade() {
		return popUpFacade;
	}
	public void setPopUpFacade(PopUpFacade popUpFacade) {
		this.popUpFacade = popUpFacade;
		this.initAll();
	}
	public MainDefinitivo getMainModello() {
		return mainModello;
	}
	public void setMainModello(MainDefinitivo mainModello) {
		this.mainModello = mainModello;
		this.initAll();
	}
	
}
