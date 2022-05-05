package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nodo b1,b2,c1,c2;
		Giocatore giallo, rosso;
			
		giallo=new Utente("giallo", 5);
		rosso=new Utente("rosso", 10);
		b1=new Base(giallo);
		b2= new Base(rosso);
		
		b1.crea_software("Rootcrash", 1);
		System.out.println("software disp è"+b1.getSoftware_disponibile());
		
		b1.crea_software("Antivirus", 1);
		System.out.println("adesso soft disp"+b1.getSoftware_disponibile());
		
		b1.potenzia_risorsa("Ram");
		
		System.out.println("ora puoi farne"+b1.getSoftware_max());
		
		System.out.println("perchè il lvl ram è "+b1.getLvl_ram());
		
		b1.crea_software("Virus", 1);
		System.out.println("software disp ora è " +b1.getSoftware_disponibile());
	}

}
