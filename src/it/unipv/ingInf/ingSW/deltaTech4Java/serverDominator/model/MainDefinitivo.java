package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import java.beans.PropertyChangeSupport;
import java.util.Collections;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

/**@author Luca Casto 
 * @author Matteo Caprio
 * @version 1.0
 * @since 1.0
 * classe facade del modello, crea la partita, crea e avvia i bot.
 * classe con cui comunica il controller dell'interfaccia grafica
 */
public class MainDefinitivo extends Thread {
	private MappaDefinitiva tabellone;
	private int n_basi;
	private Giocatore[] giocatori;
	private Mercato mercato;
	private int t_unitario, t_timer;
	private Classifica classifica;
	private Colore colore;
	private boolean bot_vivi;
	private PropertyChangeSupport support;
	private Battaglia[] fight;
	private int maxbattle=7;
	private int count;
	public static String SUPPORT_BOT_VIVI="bot_vivi";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//prova 1:avvio partita
/*		MainDefinitivo main = new MainDefinitivo();
		try {
			main.avvioPartita(30, 20, "Matteo");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
	}
	public MainDefinitivo() {
		bot_vivi=true;
		support=new PropertyChangeSupport(this);
	}

	/** avvia la partita costruendo la mappa e creando i giocatori
	 * 
	 * @param x_max
	 * coordinata massima delle ascisse
	 * @param y_max
	 * corrdinata massima delle ordinate
	 * @param nomeUtente
	 * nome dell'utente
	 * @param valuta
	 * quantita di moneta di gioco posseduta dall'utente prima di iniziare una partita
	 * @throws InterruptedException
	 */
	public void avvioPartita(int x_max, int y_max, String nomeUtente, int valuta) throws InterruptedException {
		t_unitario=5;
		colore= new Colore();
		this.creazioneGiocatori(nomeUtente, x_max, valuta);
		tabellone = new MappaDefinitiva(x_max, y_max, giocatori);
		mercato=new Mercato();
		classifica= new Classifica(giocatori);
		fight= new Battaglia[maxbattle];
		maxbattle=6;
		
		this.avvioBot(); 
		
	}

//---------------metodi generici--------------------//
	
	/**usato nel caso l'utente abbia conquistato nodi base di altri giocatori
	 * serve per decidere da quale nodo base deve partire un attacco
	 * @param scelta
	 * scelta dell'utente di una base diversa da quella iniziale
	 */
	public void sceltabase(int scelta) {
		
		if(tabellone.getContabasi()>0) {
			tabellone.setScelta(scelta);
		} else 
			System.out.println("nessuna altra base conquistata");
		
	}

	/**inizializza il vettore dei giocatori partecipanti alla partita
	 * 
	 * @param utente
	 * @param x_max
	 * @param valuta
	 */
	public void creazioneGiocatori(String utente, int x_max, int valuta) {  
		Collections.shuffle(colore.getColori());
				
		switch(x_max) {
		case 15:
			n_basi=3;
			bot_vivi=true;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema(); 
			giocatori[0].setColore(colore.getGrigio()); 
			giocatori[1]= new Utente(utente, valuta); 
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			break;
		case 20:
			n_basi=5;
			bot_vivi=true;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[0].setColore(colore.getGrigio()); 
			giocatori[1]= new Utente(utente, valuta);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			break;
		case 30:
			n_basi=10;
			bot_vivi=true;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema(); 
			giocatori[0].setColore(colore.getGrigio()); 
			giocatori[1]= new Utente(utente, valuta);
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
			giocatori[i].setColore(colore.getColori().get(i-1)); 
		}
		
	}

	/**avviamento dei bot
	 * 
	 * @throws InterruptedException
	 */
	//@SuppressWarnings("static-access")
	public void avvioBot() throws InterruptedException {
		int i;
		for(i=2; i<=n_basi; i++) {
			giocatori[i].setMap(tabellone);
			giocatori[i].start();
		}
	}
	
	/**usato per spegnere i bot
	 * 
	 * @throws InterruptedException
	 */
	public void stopBot() throws InterruptedException {
		int i;
		for(i=2; i<=n_basi; i++) {
			giocatori[i].setLife(false);
		}
	}
	
//----------------metodi per potenziamento---------------//
	
	/**controlla se il nodo selezionato appartiene all'utente e abilita il pulsante power-up
	 * 
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @return
	 * true il nodo selezionato appartiene all'utente
	 * false il nodo selezionato non appartiene all'utente
	 */
	public boolean powerupCheck(int x, int y){
	
		boolean checkp= false;
		if(tabellone.getNodo(x, y).getPossessore().getNome().equalsIgnoreCase(giocatori[1].getNome())) {
			checkp=true;
		}
		return checkp;
	}
		
	/** usato per il potenziamento di una risorsa
	 * 
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @param risorsa
	 * tipologia di risorsa che si intende potenziare
	 */
	public void powerup(int x, int y, String risorsa) {
		
		PowerupThread powerupth;
			
			if(this.powerupCheck(x, y)) {
				powerupth= new PowerupThread(tabellone.getNodo(x, y), risorsa);
				powerupth.countdown();
				
			}
		
	}
	
	/** ritorna valore intero che rappresenta il tempo di attesa per il potenziamento della risorsa selezionata
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @param nome
	 * nome della risorsa di cui si vuole sapere il tempo di potenziamento (per il nodo cloud � disponibile solo Firewall)
	 */
	public int getTempoRisorsa(int x, int y, String risorsa) {
		int tempo=-1;
		tempo=tabellone.getNodo(x, y).getTempoRisorsa(risorsa);
		return tempo;
	}
	

//------------metodi per creazione software----------//
	
	/**controlla se un nodo appartiene all'utente e abilita il pulsante development
	 * 
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @return
	 * true se il nodo appartiene all'utente abilitando l'azione
	 * false se il nodo non appartiene all'utente inibendo l'azione
	 */
	public boolean softcheck(int x, int y) {
		
		boolean checks=false;
		if(tabellone.getNodo(x, y).getPossessore().equals(giocatori[1])) {
			checks=true;
		}
		return checks;
	}
	
	/**usato per selezionare le unita software da creare
	 * 
	 * @param nome
	 * tipologia di unita software da creare
	 * @param quantita
	 * quantita da creare
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 */
	public void creazioneSoftware(String nome, int quantita, int x, int y) {
		
		CreazioneThread creazioneth;
		
		if(this.softcheck(x, y)) {
			 creazioneth= new CreazioneThread(tabellone.getNodo(x, y), nome, quantita);
			 creazioneth.countdown();
		}
	}
	
	/** ritorna valore intero che rappresenta il tempo di attesa per la creazione dei software selezionata
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @param nome
	 * nome del software di cui si vuole sapere il tempo di potenziamento (per il nodo cloud � disponibile solo Antivirus)
	 */
	public int getTempoSoftware(int x, int y, String software) {
		int tempo=-1;
		tempo=tabellone.getNodo(x, y).getTempoSoftware(software);
		return tempo;
	}
//------------- metodi per mercato------------//
	
	/**controlla se il nodo selezionato appartiene all'utente e che sia un nodo base
	 * 
	 * @param x
	 * ascissa del nodo selezionato
	 * @param y
	 * ordinata del nodo selezionato
	 * @return
	 * true se nodo selezionato appartiene all'utente e tipo base
	 * false se nodo selezionato non appartiene all'utente oppure se 
	 * il tipo del nodo non e' base
	 */
	public boolean marketcheck(int x, int y) {
		
		boolean check=false;
		if(tabellone.getNodo(x, y).getTipologia().equalsIgnoreCase("base")) {
			if(tabellone.getNodo(x, y).getPossessore().equals(giocatori[1])) {
				check=true;
			}
		}
		return check;
	}
	
	/**Metodo per comprare software e potenziamenti dal mercato, in input bisogna passare il giocatore che vuole comprare il potenziamento, la quantita e il nome 
	 * dell'oggetto da potenziare. La quantita deve essere settata a -1 se si vuole acquistare un potenziamento risorsa
	 * @param utente
	 * colui che vuole acquistare
	 * @param quantita
	 * quantita che vuole acquistare
	 * @param oggetto
	 * cosa l'utente vuole acquistare
	 */
	public void acquistoMercato(Giocatore utente, int quantita, String oggetto) {
		if(quantita==-1)
			mercato.compraRisorse(utente, tabellone.trovaBase(utente), oggetto);
		else
			mercato.compraSoftware(utente, tabellone.trovaBase(utente), quantita, oggetto);
	}

//---------------metodi per battaglia-------------//
	
	/**controlla se un nodo � confinantee quindi attaccabile da un giocatore
	 * e se lo spazio disponibile per gli attacchi simultaneri non sia vuoto
	 * massimo 6 attacchi consentiti in simultanea
	 * @param attaccante
	 * giocatore che vuole attaccare
	 * @param x
	 * ascissa del nodo bersaglio
	 * @param y
	 * ordinata del nodo bersaglio
	 * @return
	 * true nodo selezionato attaccabile
	 * false nodo selezionato non attaccabile
	 */
	public boolean nodecheck(Giocatore attaccante, int x, int y) {
	
		boolean checkf=false;
		if(maxbattle>0) {
			if(tabellone.attaccabile(x,y, attaccante)) {
				checkf=true;
			}
		}
		
		return checkf;
	}
		
	/**gestisce le operazioni preliminari necessarie per la battaglia, quali la selezione delle unita
	 * software da inviare al nodo bersaglio
	 * 
	 * @param attaccante
	 * giocatore che attacca
	 * @param x
	 * ascissa del nodo bersaglio
	 * @param y
	 * ordinata del nodo bersaglio
	 * @param quantitaV
	 * quantita di unita software virus che il giocatore attaccante intende inviare
	 * @param quantitaR
	 * quantita di unita software rootcrash che il giocatore attacante intende inviare
	 * @return
	 * tempo necessario perla battagli
	 */
	public int battlecheck(Giocatore attaccante, int x, int y,int quantitaV, int quantitaR) {
		
		ObserverFineBattaglia finebattagliaobs;
		t_timer=0;
		
		if(this.nodecheck(attaccante, x, y)){
			//t_timer=t_unitario+(t_unitario*tabellone.dist_minima(x,y, attaccante).getDist_base() );
			t_timer= t_unitario;
			fight[maxbattle]=new Battaglia(tabellone.trovaBase(attaccante), tabellone.getNodo(x,y), t_timer);
			finebattagliaobs= new ObserverFineBattaglia(attaccante, x, y, this);
			fight[maxbattle].getChanges().addPropertyChangeListener(Battaglia.BATTLE_PROP, finebattagliaobs);
		//	fight[maxbattle].setPartenza(tabellone.dist_minima(x, y, attaccante));
			fight[maxbattle].selezione(quantitaV, quantitaR);
			count=maxbattle;
			
			maxbattle--;
			
		}
		return t_timer; 
	}
			
	/** avvia la battaglia
	 * 
	 * @param attaccante
	 * giocatore che attacca
	 * @param x
	 * ascissa nodo bersaglio
	 * @param y
	 * ordinata nodo bersaglio
	 */
	public void avvioBattaglia(Giocatore attaccante, int x, int y) {
		
		fight[count].start();
				
	}
	
	public void fineBattaglia(Giocatore attaccante, int x, int y) {
		
		if(fight[count].getEsito()) {
			if(tabellone.getNodo(x, y).getTipologia().equals("base")) {
	//		tabellone.aggiornabasi(x,y, attaccante);
	//		tabellone.checkbasi(attaccante);
			}
		}
		maxbattle++;
		classifica.aggiornaClassifica();
		System.out.println(attaccante.getNome() + fight[count].getReport() );
		this.checkbot();
		System.out.println("bot vivi? " + bot_vivi);
		support.firePropertyChange(SUPPORT_BOT_VIVI, true, bot_vivi);
	}
	
//-------------metodi di fine partita------------//
	
	/**usato alla fine della partita restituisce la classifica e stampa a video
	 * il nome del giocatore vincitore
	 * @return
	 * classifica giocatori
	 * @throws InterruptedException 
	 */
	public Classifica gameover() throws InterruptedException {
				
		Giocatore vincitore;
		
		this.stopBot();
		vincitore= classifica.getVincitore();
		
		System.out.println("il vincitore e': " + vincitore.getNome());
		
		return classifica;
	}
	
	/**controllo se in partita esistono bot che possiedono
	 * una base, se non ci sono partita finisce
	 */
	public void checkbot () {

		int i;
		int cont=0;
		
		for(i=2;i<giocatori.length;i++) {
			if (!giocatori[i].getLife()) {
				cont++;
			} else {
				bot_vivi=true;
				break;
			}
			
		/*	if(giocatori[i].getLife()==true) {
				bot_vivi=true;
				break;
			} else {
				cont++;
			}
		*/
			
		}
			
		if(cont== n_basi-1) {
			bot_vivi=false;
		}
		
		System.out.println("bot morti= "+ cont);
	}
	
//--------------getter and setter-------------//
	/**
	 * returna la x massima
	 * @return
	 * x massima
	 */
	public int getX_max() {
		return tabellone.getX_max();
	}
	/**
	 * returna la y massima
	 * @return
	 * y massima
	 */
	public int getY_max() {
		return tabellone.getY_max();
	}
	/**
	 * returna la mappa di gioco
	 * @return
	 * mappa di gioco
	 */
	public MappaDefinitiva getTabellone() {
		return tabellone;
	}
	/**
	 * returna la lista dei giocatori
	 * @return
	 * giocatori
	 */
	public Giocatore[] getGiocatori() {
		return giocatori;
	}
	/**
	 * returna la classifica di gioco
	 * @return
	 * classifica
	 */
	public Classifica getClassifica() {
		return classifica;
	}
	/**
	 * setta la classifica
	 * @param classifica
	 */
	public void setClassifica(Classifica classifica) {
		this.classifica = classifica;
	}
	/**
	 * returna il mercato
	 * @return
	 * mercato
	 */
	public Mercato getMercato() {
		return mercato;
	}

	public int getT_unitario() {
		return t_unitario;
	}

	public void setT_unitario(int t_unitario) {
		this.t_unitario = t_unitario;
	}
	public PropertyChangeSupport getSupport() {
		return support;
	}
	public void setSupport(PropertyChangeSupport support) {
		this.support = support;
	}
	
	
}
