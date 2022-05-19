package sreverDominator.persistence.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.UserAccountDAO;

public class ProvaUserAccountDAO {

	public static void main(String[] args) {
		DataBase.forceDataBaseCreation("jdbc:mysql://localhost:3306/serverdomdb","root","12345678");
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
