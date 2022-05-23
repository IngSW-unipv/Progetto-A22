package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class FactoryController {

	 PartitaStage partitaStage;
	MainDefinitivo mainDefinitivo;
	UserAccount userAccount;
	Base b;
	
	public FactoryController(PartitaStage partitaStage, MainDefinitivo mainDefinitivo, Base b, UserAccount userAccount) {
		super();
		this.partitaStage = partitaStage;
		this.mainDefinitivo = mainDefinitivo;
		this.userAccount = userAccount;
		this.b = b;
	}
	
	public static PartitaStage getPartitaStage(MainDefinitivo mainDefinitivo, Base b , UserAccount userAccount ) {
		PartitaStage partitaStage = new PartitaStage(mainDefinitivo, b, userAccount.getMny()) {
			
			@Override
			public void fineGioco() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void doOnClic() {
				// TODO Auto-generated method stub
				
			}
			
			
		};
		return partitaStage;
		
		
	}

}

