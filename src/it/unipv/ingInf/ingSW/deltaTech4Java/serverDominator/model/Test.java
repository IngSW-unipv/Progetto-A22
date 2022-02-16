package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nodo b1,b2,c1,c2;
		Coordinate x1, x2,x3 ,x4;
		Giocatore giallo, rosso;
		
		x1=new Coordinate(0, 0);
		x2=new Coordinate(0,1);
		x3=new Coordinate(1, 0);
		x4=new Coordinate(-1, 1);
		
		giallo=new Utente("giallo");
		rosso=new Utente("rosso");
		b1=new Base(x1,giallo);
		b2= new Base(x4,rosso);
		
		
		
		b1.crea_software("Rootcrash", 9);
		System.out.println("software disp è"+b1.getSoftware_disponibile());
		
		b1.crea_software("Antivirus", 3);
		System.out.println("adesso soft disp"+b1.getSoftware_disponibile());
		
		b1.potenzia_risorsa("Ram");
		System.out.println("ora puoi farne"+b1.getSoftware_max());
		
		System.out.println("perchè il lvl ram è "+b1.getLvl_ram());
		
		b1.crea_software("Virus", 1);
		System.out.println("software disp ora è " +b1.getSoftware_disponibile());
	}

}
