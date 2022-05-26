package sreverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class testBattaglia {

	public static void main(String[] args) {
		Nodo attaccante, difensore;
		Giocatore luca, sistema;
		int tempo= 10;
		Battaglia b;
		
		luca= new Utente("luca", 100);
		sistema= new Sistema();
		attaccante = new Base(luca);
		difensore= new Base(sistema);
		
		attaccante.crea_software("virus", 10);
		difensore.crea_software("antivirus", 9);
		
		b= new Battaglia(attaccante, difensore, tempo);
		b.selezione(3, 0);
		
		if(b.calcola_vincitore()) {
			System.out.println("vittoria");
		} else System.out.println("sconfitta");
		
		System.out.println(attaccante.getStats_software_creati()[1].getQuantita());
		
		System.out.println("fuffa");
		
		System.out.println(difensore.getStats_software_creati()[0].getQuantita());

	}

}
