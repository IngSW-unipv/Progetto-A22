package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LobbyView;

public class AvvioPartitaController {
	
	private PartitaStage partitaStage;
	
	private LobbyView prebattagliaView;
	private MainDefinitivo mainDefinitivo;
	private UserAccount userAccount;
	
	
	
	
	public AvvioPartitaController(LobbyView prebattagliaView, UserAccount userAccount) {

		this.userAccount = userAccount;
	}

	/**
	 * Metodo che associa alla scelta del radioButton la difficoltà stabilita e genera la partita di server dominator
	 */
	
	

	public PartitaStage getPartitaStage() {
		return partitaStage;
	}

	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}

	public LobbyView getPrebattagliaView() {
		return prebattagliaView;
	}

	public void setPrebattagliaView(LobbyView prebattagliaView) {
		this.prebattagliaView = prebattagliaView;
	}

	public MainDefinitivo getMainDefinitivo() {
		return mainDefinitivo;
	}

	public void setMainDefinitivo(MainDefinitivo mainDefinitivo) {
		this.mainDefinitivo = mainDefinitivo;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


}

