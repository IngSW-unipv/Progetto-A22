package sreverDominator.persistence.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.obiettivi.DBObiettiviDOAFactory;

public class ProvaDBObiettiviFactoryDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Obiettivo ob=new Obiettivo();
		ObPunteggio obp=new ObPunteggio();
		Obiettivo obc=(Obiettivo)obp;
		
		System.out.println(DBObiettiviDOAFactory.getIObiettiviDAO(obc, "resources/config/persistence/dataBase/connWith_sd_sys").getClass().getName());
		System.out.println(DBObiettiviDOAFactory.getIObiettiviDAO(ob, "resources/config/persistence/dataBase/connWith_sd_sys").getClass().getName());
	}

}
