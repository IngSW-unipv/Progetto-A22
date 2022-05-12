package sreverDominator.persistence.prove.quesrie;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAOFactory;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;

public class ProvaObiettiviDAO {
	public static void main(String[] args) {
		Obiettivo o=new Obiettivo();
		o.setIdObiettivo(1);
		IObiettiviDAO ob=IObiettiviDAOFactory.getIObiettiviDAO(o);
		o=ob.selectObiettiviById(o);
		System.out.println(o.toString());
		
	}
}
