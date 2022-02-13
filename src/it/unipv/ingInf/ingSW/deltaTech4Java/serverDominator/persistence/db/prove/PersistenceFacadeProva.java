package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.prove;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

public class PersistenceFacadeProva {

	public static void main(String[] args) {
		PersistenceFacade p=PersistenceFacade.getInstance();
		UserAccount tawa= new UserAccount("Tawa","123456789");
		tawa=p.getUserAccountById(tawa);
		tawa.toString();
		for(ObiettiviUser o: tawa.getObiettiviUsers()) {
			o.toString();
		}
		for(AsetOwn a:tawa.getAsetOwns()) {
			a.toString();
		}
	}

}
