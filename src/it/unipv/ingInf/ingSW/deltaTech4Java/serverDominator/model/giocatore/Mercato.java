package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;

public class Mercato implements Runnable {
	int prezzoRam, prezzoCpu, prezzoEnergia, prezzoVirus, prezzoAntivirus;
	int prezzoRootcrash, quantitaRam, quantitaCpu, quantitaEnergia, quantitaVirus, quantitaAntivirus, quantitaRootcrash;
	int quantita;
	Base utente;
	
	public Mercato() {
		prezzoRam=0;
		prezzoCpu=0; 
		prezzoEnergia=0;
		prezzoVirus=0;
		prezzoAntivirus=0;
		prezzoRootcrash=0;
		quantitaRam=0;
		quantitaCpu=0;
		quantitaEnergia=0;
		quantitaVirus=0;
		quantitaAntivirus=0;
		quantitaRootcrash=0;
		
		utente= new Base();
	}
	
	public Mercato(int prezzoRam, int quantitaRam,int prezzoCpu, int quantitaCpu, int prezzoEnergia, int quantitaEnergia,int prezzoVirus, int quantitaVirus, int prezzoAntivirus, int quantitaAntivirus, int prezzoRootcrash, int quantitaRootcrash) {
		this.prezzoRam=prezzoRam;
		this.prezzoCpu=prezzoCpu; 
		this.prezzoEnergia=prezzoEnergia;
		this.prezzoVirus=prezzoVirus;
		this.prezzoAntivirus=prezzoAntivirus;
		this.prezzoRootcrash=prezzoRootcrash;
		this.quantitaRam=quantitaRam;
		this.quantitaCpu=quantitaCpu;
		this.quantitaEnergia=quantitaEnergia;
		this.quantitaVirus=quantitaVirus;
		this.quantitaAntivirus=quantitaAntivirus;
		this.quantitaRootcrash=quantitaRootcrash;
		
		utente = new Base();
	}
	
	public void compraSoftware(Utente user, Base nodo, int quantita, String software) {
		
		if(software=="virus") {
				if(user.getValuta()>=prezzoVirus*quantita&&quantitaVirus>0);
					nodo.crea_software(software, quantita);
					quantitaVirus-=quantita;
		}
		if(software=="antivirus") {
			if(user.getValuta()>=prezzoAntivirus*quantita&&quantitaAntivirus>0);
				nodo.crea_software(software, quantita);
				quantitaAntivirus-=quantita;
		}
		if(software=="rootcrash") {
			if(user.getValuta()>=prezzoRootcrash*quantita&&quantitaRootcrash>0);
				nodo.crea_software(software, quantita);
				quantitaRootcrash-=quantita;
		}
	}
	
	public void compraRisorse(Utente user, Base nodo, String risorsa) {
		if(risorsa=="ram") {
			if(user.getValuta()>=prezzoRam&&quantitaRam>0);
				nodo.potenzia_risorsa(risorsa);
				quantitaRam--;
		}
		if(risorsa=="cpu") {
			if(user.getValuta()>=prezzoCpu&&quantitaCpu>0);
				nodo.potenzia_risorsa(risorsa);
				quantitaCpu--;
		}
		if(risorsa=="energia") {
			if(user.getValuta()>=prezzoEnergia&&quantitaEnergia>0);
				nodo.potenzia_risorsa(risorsa);
				quantitaEnergia--;
		}
	}
	
	
	/* da main chiamato setquantita, poi controllo soldi, e avvio run;
	 * stesa logica di main definitivo per l'acquisto
	 */
	
	/* getprezzi, setquantita,  
	 * 
	 */
	public void run() {
		
	}
}
