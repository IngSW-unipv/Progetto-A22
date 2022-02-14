package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAOFactory;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.UserAccountDAO;

public class UserAccountDAOprova {

	public static void main(String[] args) {
		UserAccountDAO a=new UserAccountDAO("resources/config/persistence/dataBase/connWith_sd_sys");
		//a.insetUserAccount(new UserAccount("provaUser","provaPass"));
		UserAccount b=a.getUserAccountById(new UserAccount("Tawa","123456789"));
		if(b!=null) {
			System.out.println(b.getUsername()+"\n"+b.getPassw()+"\n"+String.valueOf(b.getMny()));
			
		}else {
			System.out.println("fail");
		}
	}

}
