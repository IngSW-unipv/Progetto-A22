package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;

public class Main {
	
	public static void main(int x_max, int y_max, String utente) {
		
		int n_basi;
		Giocatore[] giocatori;
		Mappa tabellone;
		Battaglia fight;
		
		n_basi= 3;
		giocatori= new Giocatore[n_basi+1];
		
		switch(x_max) {
		case 15:
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			break;
		case 20:
			n_basi=5;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			break;
		case 30:
			n_basi=10;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			giocatori[6]= new Bot("jupiter");
			giocatori[7]= new Bot("alex");
			giocatori[8]= new Bot("lonfo");
			giocatori[9]= new Bot("max");
			giocatori[10]=new Bot("alice");
			break;
		}
		tabellone= new Mappa(x_max, y_max, giocatori);
		tabellone.assegnamento(n_basi, giocatori);
		 tabellone.dist_minima(3,4, giocatori[1]).getDist_base();
	}
//solo per studio metodo
	
	public boolean avvioBattaglia(Giocatore attaccante, int x, int y,int quantitaV, int quantitaR) {
		int punti, valuta; //dovranno essere inizializati correttamente tramite un metodo del controllore che restituisce il numero di virus e rootcrash che l'utente seleziona in fase di attacco dall'interfaccia grafica
		
		Nodo bersaglio=tabellone.getNodo(x,y);
		Nodo partenza;
		
		boolean esito=tabellone.attaccabile(tabellone.trovaBase(attaccante),bersaglio);
		if(esito) {
			
			//per il timer usare il seguente metodo
			// tempo timer=t_unitario*tabellone.dist_minima(x,y, attaccante).getDist_base();
			
			fight=new Battaglia(tabellone.trovaBase(attaccante), bersaglio);
			fight.selezione(quantitaV, quantitaR);
			//timer
			esito=fight.calcola_vincitore();
			tabellone.aggiornastati(bersaglio, partenza);
			if(esito)
				
				bersaglio.setPossessore(tabellone.trovaBase(attaccante).getPossessore());
		}
		if(attaccante.getNome().equals(utente.getNome())) {
			if(esito)
				utente.aggiornaPunteggio(punti);
				utente.aggiornaValuta(valuta);
			//else? si tolgono punti in caso di sconfitta?
		}
		return esito;
	}

}
