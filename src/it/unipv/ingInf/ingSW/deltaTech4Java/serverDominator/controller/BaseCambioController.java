package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class BaseCambioController {

	private MainDefinitivo mainDefinitivo;
	private PartitaStage partitaStage;

	public BaseCambioController(MainDefinitivo mainDefinitivo, PartitaStage partitaStage) {
		super();
		this.mainDefinitivo = mainDefinitivo;
		this.partitaStage = partitaStage;
		this.initAll();
	}
	public void initAll() {
		initAvantiBase();
		iniIndietroBase();
	}
	/**
	 * 
	 * @param partitaStage
	 * @param b
	 * @param giocatore
	 * Metodo associato al pulsante per visualizzare la prossima base posseduta dal giocatore
	 */
	public void initAvantiBase() {
	/*	partitaStage.getBaseStatsPane().getButtonNext().setOnAction(actionEvent -> {
			mainDefinitivo.getTabellone().checkbasi(partitaStage.getSelectedBase().getPossessore());
			{
				if(mainDefinitivo.getTabellone().getContabasi()>mainDefinitivo.getTabellone().getScelta()) {
					mainDefinitivo.getTabellone().setScelta(mainDefinitivo.getTabellone().getScelta()+1);
					partitaStage.changeSelectedBase((Base)mainDefinitivo.getTabellone().trovaBase(partitaStage.getSelectedBase().getPossessore()));
				}else {
					partitaStage.getBaseStatsPane().getButtonNext().setDisable(true);
				}
				
			}
			
		});*/
	}
	
	/**
	 * 
	 * @param partitaStage
	 * @param b
	 * @param giocatore
	 * Metodo associato al pulsante per visualizzare la precedente base posseduta dal giocatore
	 */
	public void iniIndietroBase() {
	/*	partitaStage.getBaseStatsPane().getButtonBack().setOnAction(actionEvent -> {
			mainDefinitivo.getTabellone().checkbasi(partitaStage.getSelectedBase().getPossessore());
			int i=mainDefinitivo.getTabellone().getScelta()-1;
			if(i>=0) {
				mainDefinitivo.getTabellone().setScelta(mainDefinitivo.getTabellone().getScelta()+1);
				partitaStage.changeSelectedBase((Base)mainDefinitivo.getTabellone().trovaBase(partitaStage.getSelectedBase().getPossessore()));
			}else {
				partitaStage.getBaseStatsPane().getButtonBack().setDisable(true);
			}
			
		}); */
	}
	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}
	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}
	public PartitaStage getPartitaStage() {
		return partitaStage;
	}
	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}
	
}
