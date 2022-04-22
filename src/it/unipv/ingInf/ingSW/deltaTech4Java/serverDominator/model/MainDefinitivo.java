package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import java.util.Collections;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.mappaProva;

public class MainDefinitivo extends Thread{
	private MappaDefinitiva tabellone;
	private int n_basi;
	private Giocatore[] giocatori;
	private Mercato mercato;
	private Utente utente;
	private int t_unitario; 
	Thread threadBot[];
	
	private Battaglia[] fight;
	private int maxbattle=6;
	private int count;
	private int tempoAggiornamento;
	private boolean giocoAttivo=false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
	//selezione utente
		//selezione lingua
	
	public void avvioPartita(int x_max, int y_max, String nomeUtente) throws InterruptedException {
		giocoAttivo=true;
		t_unitario=10;
		n_basi= 3;
		giocatori = new Giocatore[n_basi+1];
		giocatori = this.creazioneGiocatori(nomeUtente, x_max);
		tabellone = new MappaDefinitiva(x_max, y_max, giocatori);
		mercato=new Mercato();
		
		fight= new Battaglia[maxbattle];
	
		this.avvioBot(tempoAggiornamento);
		
		
		//gioco finito
		giocoAttivo=false;
		this.stopBot();
	
	}
	
	public void potenziamento(String risorsa){
		tabellone.trovaBase(utente).potenzia_risorsa(risorsa);
	}
	
	public void creazioneSoftware(String nome, int quantita) {
		tabellone.trovaBase(utente).crea_software(nome, quantita);
	}
	
	public void acquistoMercato(int quantita, String oggetto) {
		//if(quantita==-1)
			//mercato.compraRisorse(utente, tabellone.trovaBase(utente), oggetto);
		//else
			//mercato.compraSoftware(utente, tabellone.trovaBase(utente), quantita, oggetto);
	}

		
	
	public void battlecheck(Giocatore attaccante, int x, int y,int quantitaV, int quantitaR) {
		/** il seguente metodo, gestisce le operazioni preliminari alla battaglia
		 * dati due interi, le coordinate del nodo bersaglio, e il Giocatore attaccante.
		 * le quantita di software dovranno essere inizializati correttamente tramite un metodo del controllore 
		 * che restituisce il numero di virus e rootcrash che l'utente seleziona 
		 * in fase di attacco dall'interfaccia grafica	
		 */
	
		int t_timer;
		if(maxbattle>0) {
			if(tabellone.attaccabile(x,y, attaccante)) {
				t_timer=t_unitario*tabellone.dist_minima(x,y, attaccante).getDist_base();
				fight[maxbattle]=new Battaglia(tabellone.trovaBase(attaccante), tabellone.getNodo(x,y), t_timer);
				fight[maxbattle].setPartenza(tabellone.dist_minima(x, y, attaccante));
				fight[maxbattle].selezione(quantitaV, quantitaR);
				count=maxbattle;
				maxbattle--;
			}
		}
		
	}
		
	public void avvioBattaglia(Giocatore attaccante, int x, int y) {
		fight[count].start();
		maxbattle++;
		fight[count].getReport();
	}
	
	public Giocatore[] creazioneGiocatori(String utente, int x_max) {  
		Collections.shuffle(Colore.colori);
		switch(x_max) {
		case 15:
			n_basi=3;
			giocatori[0]= new Sistema(); 
			giocatori[0].colore = Colore.GRIGIO;
			giocatori[1]= new Utente(utente); 
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			break;
		case 20:
			n_basi=5;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[0].colore = Colore.GRIGIO;
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
			giocatori[0].colore = Colore.GRIGIO;
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			giocatori[6]= new Bot("jupiter");
			giocatori[7]= new Bot("alex");
			giocatori[8]= new Bot("lonfo");
			giocatori[9]= new Bot("mara");
			giocatori[10]=new Bot("alice");
			break;
		}
		
		for (int i = 1; i < giocatori.length; i++) {
			giocatori[i].colore = Colore.colori.get(i-1);
			
		}
		return giocatori;
	
	}
	
	public int getX_max() {
		return tabellone.getX_max();
	}
	
	public int getY_max() {
		return tabellone.getY_max();
	}
	
	public MappaDefinitiva getTabellone() {
		return tabellone;
	}
	
	public Giocatore[] getGiocatori() {
		return giocatori;
	}
	
	@SuppressWarnings("static-access")
	public void avvioBot(int tempo) throws InterruptedException {
		int i;
		for(i=1; i<=n_basi; i++) {
			threadBot[i]=new Thread(giocatori[i]);
			threadBot[i].start();
		}
		while(giocoAttivo) {	
			for(i=1; i<=n_basi; i++) {
				threadBot[i].sleep(tempo);
			}
		}
	}
	
	public void stopBot() throws InterruptedException {
		int i;
		for(i=1; i<=n_basi; i++) {
			threadBot[i].interrupt();;
		}
	}
}
