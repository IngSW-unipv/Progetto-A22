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
	private Utente utente; //ATTENZIONE: non viene inizializzato da nessuna parte 
	private int t_unitario, t_timer;
	Thread threadBot[];
	
	private Battaglia[] fight;
	private int maxbattle=7;
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

	@SuppressWarnings("static-access")
	public void avvioBot(int tempo) throws InterruptedException {
		int i;
		for(i=2; i<=n_basi; i++) {
			threadBot[i]=new Thread(giocatori[i]);
			threadBot[i].start();
		}
		while(giocoAttivo) {	
			for(i=2; i<=n_basi; i++) {
				threadBot[i].sleep(tempo);
			}
		}
	}
	
	public void stopBot() throws InterruptedException {
		int i;
		for(i=2; i<=n_basi; i++) {
			threadBot[i].interrupt();;
		}
	}
	
//----------------metodi per potenziamento---------------//
	
	public boolean powerupCheck(int x, int y){
		/** metodo chiamato dopo che l'utente ha selezionato un nodo di
		 * sua proprietà, abilita il pulsante per eventuali potenziamenti risorse.
		 */
		boolean checkp= false;
		if(tabellone.getNodo(x, y).getPossessore()==giocatori[1]) {
			checkp=true;
		}
		return checkp;
	}
		
	public void powerup(int x, int y, String risorsa) {
		/** metodo lanciato dopo aver selezionato la risorsa da potenziare
		 * dall'interfaccia grafica.
		 */
		if(this.powerupCheck(x, y)) {
			tabellone.getNodo(x, y).potenzia_risorsa(risorsa);
		}
	}

//------------metodi per creazione software----------//
	
	public boolean softcheck(int x, int y) {
		/** il metodo viene lanciato quando l'utente clicca su un nodo
		 * di sua proprietà, e abilita il pulsante per la creazione software.
		 */
		boolean checks=false;
		if(tabellone.getNodo(x, y).getPossessore()==giocatori[1]) {
			checks=true;
		}
		return checks;
	}
	
	public void creazioneSoftware(String nome, int quantita, int x, int y) {
		/** metodo usato dopo la selezione del software da parte dell'utente
		 * 
		 */
		if(this.softcheck(x, y)) {
			tabellone.getNodo(x, y).crea_software(nome, quantita);
		}
	}
		
//------------- metodi per mercato------------//
		
	public void acquistoMercato(int quantita, String oggetto) {
		//if(quantita==-1)
			//mercato.compraRisorse(utente, tabellone.trovaBase(utente), oggetto);
		//else
			//mercato.compraSoftware(utente, tabellone.trovaBase(utente), quantita, oggetto);
	}

//---------------metodi per battaglia-------------//
		
	public boolean nodecheck(Giocatore attaccante, int x, int y) {
		/** metodo per il controllo se un nodo è attaccabile,
		 * controlla anche se lo spazio disponibile per gli attacchi simultanei
		 * non è vuoto.
		 * NB: un utente può eseguire 6 attacchi simultaneamente.
		 * metodo eseguito quando un utente clicca su un nodo
		 */
		boolean checkf=false;
		if(maxbattle>0) {
			if(tabellone.attaccabile(x,y, attaccante)) {
				checkf=true;
			}
		}
		
		return checkf;
	}
		
	public void battlecheck(Giocatore attaccante, int x, int y,int quantitaV, int quantitaR) {
		/** il seguente metodo, gestisce le operazioni preliminari alla battaglia
		 * dati due interi, le coordinate del nodo bersaglio, e il Giocatore attaccante.
		 * le quantita di software dovranno essere inizializati correttamente tramite un metodo del controllore 
		 * che restituisce il numero di virus e rootcrash che l'utente seleziona 
		 * in fase di attacco dall'interfaccia grafica.
		 * il metodo viene lanciato quando il giocatore clicca sul pulsante attacca
		 */
	
		if(this.nodecheck(attaccante, x, y)){
			t_timer=t_unitario*tabellone.dist_minima(x,y, attaccante).getDist_base();
			fight[maxbattle]=new Battaglia(tabellone.trovaBase(attaccante), tabellone.getNodo(x,y), t_timer);
			fight[maxbattle].setPartenza(tabellone.dist_minima(x, y, attaccante));
			fight[maxbattle].selezione(quantitaV, quantitaR);
			count=maxbattle;
			maxbattle--;
		}		
	}
			
	public void avvioBattaglia(Giocatore attaccante, int x, int y) {
		/** metodo che lancia il thread relativo alla singola battaglia, 
		 * successivo ai controlli di prossimità e numeri di attacchi contemporanei,
		 * questo metodo viene lanciato quando l'utente clicca su conferma, per confermare i
		 * software da inviare in battaglia.
		 */
		fight[count].start();
		/*le due istruzioni seguenti sono da lanciare una volta terminato l'esecuzione
		 * del thread di battaglia
		 */
		maxbattle++;
		System.out.println( fight[count].getReport() );
	}
			
/**getter and setter */
	
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
	
	
}
