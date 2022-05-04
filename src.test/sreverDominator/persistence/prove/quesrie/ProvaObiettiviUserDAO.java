package sreverDominator.persistence.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.ObiettiviUserDAO;

public class ProvaObiettiviUserDAO {

	public static void main(String[] args) {
		ObiettiviUserDAO o=new ObiettiviUserDAO("resources/config/persistence/dataBase/connWith_sd_sys");
		o.updateStatoObiettiviUserbyId(new ObiettiviUser(new Obiettivi(3,"LEGA 3",400),new UserAccount("Tawa"),"Non completato"));
	}

}
