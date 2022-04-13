package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.mappaProva;

public class MainDefinitivo {
	private MappaDefinitiva tabellone;
	private int n_basi;
	private Giocatore[] giocatori;
	private Battaglia fight;
	private Mercato mercato;
	private Utente utente;
	private int t_unitario; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//selezione utente
		//selezione lingua
	public void avvioPartita(int x_max, int y_max) {
		t_unitario=10;
		n_basi= 3;
		giocatori= new Giocatore[n_basi+1];
		giocatori=this.creazioneGiocatori(utente.getNome(), x_max, y_max);
		tabellone= new MappaDefinitiva(x_max, y_max, giocatori);
		mercato=new Mercato();
	}
	
	public void potenziamento(String risorsa){
		tabellone.trovaBase(utente).potenzia_risorsa(risorsa);
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
	
	public boolean avvioBattaglia(Giocatore attaccante, int x, int y,int quantitaV, int quantitaR) {
		/** il seguente metodo, gestisce le operazioni preliminari e successive alla battaglia
		 * dati due interi, le coordinate del nodo bersaglio, e il Giocatore attaccante.		
		 */
				int punti, valuta; 
		//le quantità di software dovranno essere inizializati correttamente tramite un metodo
		//del controllore che restituisce il numero di virus e rootcrash che l'utente seleziona in fase di attacco dall'interfaccia grafica
				int t_timer;

				boolean esito=tabellone.attaccabile(x,y, attaccante);
				if(esito) {
					t_timer=t_unitario*tabellone.dist_minima(x,y, attaccante).getDist_base();
					//	fight=new Battaglia(tabellone.trovaBase(attaccante), bersaglio);
					fight=new Battaglia(tabellone.trovaBase(attaccante), tabellone.getNodo(x,y));
					fight.selezione(quantitaV, quantitaR);
					
		//timer
					
					esito=fight.calcola_vincitore();
					if(esito) {
						tabellone.aggiornastati(tabellone.getNodo(x,y), tabellone.dist_minima(x,y,attaccante));
				// richiamo metodo per sommare le risorse del nodo bersaglio al nodo base attaccante
						
					}
						
				}
				
				// if sottostante necessario??? 
				if(attaccante.getNome().equals(utente.getNome())) {
					if(esito)
						utente.aggiornaPunteggio(punti);
						utente.aggiornaValuta(valuta);
				//else? si tolgono punti in caso di sconfitta?
				}
				return esito;
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
}
