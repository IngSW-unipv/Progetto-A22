package sreverDominator.persistence.prove;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.proxy.ProxyUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DataBase;

public class UserAccountProxyProva {
	public static void main(String[] args) {
		DataBase.createDataBase("localhost", "3306", "root", "12345678");
		IUserAccountDAO us= new ProxyUserAccountDAO();
		UserAccount uss=us.getUserAccountById(new UserAccount("Gio","123456789"));
		System.out.println(uss.toString());
	}
	
}
