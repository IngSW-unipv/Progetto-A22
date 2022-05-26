package sreverDominator.model;

import java.util.Collections;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Colore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class Test4 {

	/* test per verifica restituzione nodo base di una mappa qualsiasi
	 * 
	 */
	
	public static void main(String[] args) {
		MappaDefinitiva easy, medio, hard;
		Colore colore;
	
		
		Giocatore[] gfacile;
		Giocatore[] gmedio;
		Giocatore[] ghard;
		
		
		colore = new Colore();
		gfacile= new Giocatore[4];
		gmedio= new Giocatore[6];
		ghard= new Giocatore[11];
		
		gfacile[0]= new Sistema(); 
		gfacile[0].setColore(colore.getGrigio()); 
		gfacile[1]= new Utente("luca", 100); 
		gfacile[2]= new Bot("bob");
		gfacile[3]= new Bot("sandra");
		
		
		gmedio[0]= new Sistema();
		gmedio[0].setColore(colore.getGrigio()); 
		gmedio[1]= new Utente("luca", 10);
		gmedio[2]= new Bot("bob");
		gmedio[3]= new Bot("sandra");
		gmedio[4]= new Bot("roger");
		gmedio[5]= new Bot("max");
		
		ghard[0]= new Sistema(); 
		ghard[0].setColore(colore.getGrigio()); 
		ghard[1]= new Utente("luca", 5);
		ghard[2]= new Bot("bob");
		ghard[3]= new Bot("sandra");
		ghard[4]= new Bot("roger");
		ghard[5]= new Bot("max");
		ghard[6]= new Bot("jupiter");
		ghard[7]= new Bot("alex");
		ghard[8]= new Bot("lonfo");
		ghard[9]= new Bot("mara");
		ghard[10]=new Bot("alice");
		
		easy= new MappaDefinitiva(15, 10, gfacile);
		medio= new MappaDefinitiva(20, 15, gmedio);
		hard= new MappaDefinitiva(30, 20, ghard);
		
		System.out.println("tutto ok");
		
		
		System.out.println(medio.getNodo(1, 14));
		System.out.println(medio.trovaBase(gmedio[1]).getPossessore().getNome());
	
	}

}
