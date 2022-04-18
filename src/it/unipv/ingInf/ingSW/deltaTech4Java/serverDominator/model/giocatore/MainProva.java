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
	//il colore va al giocatore o al nodo? per ora � al nodo
	
	// MATTEO P : credo si debba assegnare il colore al possessore, poi dare due tonalità diverse se BASE o CLOUD
	//0=possessore, 2=colore, 3=tipologia nodo, 4=livello cpu, 5=livello ram, 6=livello energia, 7=livello firewall, 8=livello virus, 9=quantita virus, 10=livello antivirus, 11= quantita antivirus, 12= livello rootcrash, 13=quanita rootcrash
	public String[] getProprieta(int coordX, int coordY) {
		String[] proprieta;
		String tipo="cloud";
		proprieta=new String[13];
		proprieta[0]=tabellone.getNodo(coordX, coordY).getPossessore().getNome();
		proprieta[1]=tabellone.getNodo(coordX, coordY).getColore();
		proprieta[2]=tabellone.getNodo(coordX, coordY).getTipologia();
		proprieta[3]=(String) String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_cpu());
		proprieta[4]=(String) String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_ram());
		proprieta[5]=(String) String.valueOf(tabellone.getNodo(coordX, coordY).getE_disponibile());
		proprieta[6]=(String) String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_firewall());
		if("cloud".compareTo(tabellone.getNodo(coordX, coordY).getTipologia())) {
			proprieta[7]=String.valueOf(0);
			proprieta[8]=String.valueOf(0);
			proprieta[9]=String.valueOf(1);
			proprieta[10]=String.valueOf(tabellone.getNodo(coordX, coordY).getQnt_antivirus());
			proprieta[11]=String.valueOf(0);
			proprieta[12]=String.valueOf(0);
		}
		else {
			proprieta[7]=String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_virus());
			proprieta[8]=String.valueOf(tabellone.getNodo(coordX, coordY).getQnt_virus());
			proprieta[9]=String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_antivirus());
			proprieta[10]=String.valueOf(tabellone.getNodo(coordX, coordY).getQnt_antivirus());
			proprieta[11]=String.valueOf(tabellone.getNodo(coordX, coordY).getLvl_rootcrash());
			proprieta[12]=String.valueOf(tabellone.getNodo(coordX, coordY).getQnt_rootcrash());
		}
		return proprieta;
	}

}
