package sreverDominator.persistence.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBObiettiviDOAFactory;

public class ProvaDBObiettiviFactoryDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Obiettivi ob=new Obiettivi();
		ObPunteggio obp=new ObPunteggio();
		Obiettivi obc=(Obiettivi)obp;
		
		System.out.println(DBObiettiviDOAFactory.getIObiettiviDAO(obc, "resources/config/persistence/dataBase/connWith_sd_sys").getClass().getName());
		System.out.println(DBObiettiviDOAFactory.getIObiettiviDAO(ob, "resources/config/persistence/dataBase/connWith_sd_sys").getClass().getName());
	}

}
