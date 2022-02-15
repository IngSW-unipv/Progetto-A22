package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.persistenceTest.prove;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;

public class PersistenceFacadeProva {

	public static void main(String[] args) {
		PersistenceFacade p=PersistenceFacade.getInstance();
		
		UserAccount tawa= new UserAccount("Tawa","3");
		tawa=p.getUserAccountById(tawa);//getuserAccount from db
		System.out.println(tawa.toString());
		for(ObiettiviUser o: tawa.getObiettiviUsers()) {
			System.out.println(o.toString());
		}
		for(AsetOwn a:tawa.getAsetOwns()) {
			System.out.println(a.toString());;
		}
		if(p.chengeUserAccountPassword(tawa, "3")) System.out.println("ok");;
	}

}
