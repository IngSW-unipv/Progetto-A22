package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Mappa;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingsfw.thread.counter.mappaProva;

public class MainProva {

	private mappaProva tabellone;
	private int n_basi;
	private Giocatore[] giocatori;
	private Battaglia fight;
	private Mercato mercato;
	private Utente utente;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//avvio interfaccia grafica
	}
	
	//selezione utente
	//selezione lingua

	public void avvioPartita(int x_max, int y_max) {
		n_basi= 3;
		giocatori= new Giocatore[n_basi+1];
		tabellone= new mappaProva(x_max, y_max, n_basi, utente.getNome());
		giocatori=this.creazioneGiocatori(utente.getNome(), x_max, y_max);
		mercato=new Mercato();
		//avvio thread
	}
	
	public Giocatore[] creazioneGiocatori(String utente, int x_max, int n_basi) {
		switch(x_max) {
		case 15:
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]=new Bot("bob");
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
		return giocatori;
	}
	public void potenziamento(String risorsa){
		switch(risorsa) {
			tabellone.trovaBase(utente).potenzia_risorsa(risorsa);
		}
	}
	
	public void creazioneSoftware(String nome, int quantita) {
		tabellone.trovaBase(utente).crea_software(nome, quantita);
	}
	
	public void acquistoMercato(int quantita, String oggetto) {
		if(quantita==-1)
			mercato.compraRisorse(utente, tabellone.trovaBase(utente), oggetto);
		else
			mercato.compraSoftware(utente, tabellone.trovaBase(utente), quantita, oggetto);
	}
	
	public boolean avvioBattaglia(Giocatore attaccante, Nodo bersaglio,int quantitaV, int quantitaR) {
		int punti, valuta; //dovranno essere inizializati correttamente tramite un metodo del controllore che restituisce il numero di virus e rootcrash che l'utente seleziona in fase di attacco dall'interfaccia grafica
		boolean esito=tabellone.attaccabile(tabellone.trovaBase(attaccante), bersaglio);
		if(esito) {
			fight=new Battaglia(tabellone.trovaBase(attaccante), bersaglio);
			fight.selezione(quantitaV, quantitaR);
			esito=fight.calcola_vincitore();
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
	//opzioni

}
