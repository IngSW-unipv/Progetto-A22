package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;

public class AvvioPartitaController {
	
	private PartitaStage partitaStage;
	private PrebattagliaView prebattagliaView;
	private MainDefinitivo mainDefinitivo;
	private UserAccount userAccount;
	
	
	
	
	
	
	
	
	
	
	
	public AvvioPartitaController(PrebattagliaView prebattagliaView, UserAccount userAccount) {

		this.userAccount = userAccount;
	}

	/**
	 * Metodo che associa alla scelta del radioButton la difficoltà stabilita e genera la partita di server dominator
	 */
	public void initAvvioPartita() {
		
		prebattagliaView.getAvvioPartita().setOnAction(actionEvent -> {
			mainDefinitivo=new MainDefinitivo();
			try {
				mainDefinitivo.avvioPartita(prebattagliaView.getSelectedDifecolta()[0], prebattagliaView.getSelectedDifecolta()[1], 
						prebattagliaView.getUserAccount().getUsername(), prebattagliaView.getUserAccount().getMny());
					partitaStage=new PartitaStage(mainDefinitivo,
							(Base)mainDefinitivo.getTabellone().trovaBase(new Utente(userAccount.getUsername(),userAccount.getMny())),
							prebattagliaView.getSelectedDifecolta()[1]*1000) {
						
						@Override
						public void fineGioco() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void doOnClic() {
							// TODO Auto-generated method stub
							
						}
					};
					partitaStage.disponiPannelli();
					partitaStage.show();
				prebattagliaView.close();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}

	public PartitaStage getPartitaStage() {
		return partitaStage;
	}

	public void setPartitaStage(PartitaStage partitaStage) {
		this.partitaStage = partitaStage;
	}

	public PrebattagliaView getPrebattagliaView() {
		return prebattagliaView;
	}

	public void setPrebattagliaView(PrebattagliaView prebattagliaView) {
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

