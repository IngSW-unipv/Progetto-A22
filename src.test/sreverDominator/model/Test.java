package sreverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Nodo b1,b2,c1,c2;
		Giocatore giallo, rosso;
			
		giallo=new Utente("giallo", 5);
		rosso=new Utente("rosso", 10);
		b1=new Base(giallo);
		b2= new Base(rosso);
		
		b1.crea_software("Rootcrash", 1);
		System.out.println("software disp �"+b1.getSoftware_disponibile());
		
		b1.crea_software("Antivirus", 1);
		System.out.println("adesso soft disp"+b1.getSoftware_disponibile());
		
		b1.potenzia_risorsa("Ram");
		
		System.out.println("ora puoi farne"+b1.getSoftware_max());
		
		System.out.println("perch� il lvl ram � "+b1.getLvl_ram());
		
		b1.crea_software("Virus", 1);
		System.out.println("software disp ora � " +b1.getSoftware_disponibile());
	}

}
