package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;

public class Mercato {
	int prezzoRam, prezzoCpu, prezzoEnergia, prezzoVirus;
	public int prezzoAntivirus;
	int prezzoRootcrash;
	int quantitaRam;
	int quantitaCpu;
	int quantitaEnergia;
	int quantitaVirus;
	int quantitaAntivirus;
	int quantitaRootcrash;
	
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
	}
	
	public void compraSoftware(Utente user, Base nodo, int quantita, String software) {
		int valuta;
		if (quantita>nodo.getSpazio_Ram()) {
			quantita=nodo.getSpazio_Ram();
		}
		if(software=="virus") {	
			if(user.getValuta()>=prezzoVirus*quantita&&quantitaVirus>0);
				if(nodo.compra_software(software, quantita) ) {
					valuta=user.getValuta()-prezzoVirus*quantita;
					user.setValuta(valuta);
				}
				quantitaVirus-=quantita;
		}
		if(software=="antivirus") {
			if(user.getValuta()>=prezzoAntivirus*quantita&&quantitaAntivirus>0);
			if(nodo.compra_software(software, quantita) ) {
				valuta=user.getValuta()-prezzoAntivirus*quantita;
				user.setValuta(valuta);
			}
			quantitaAntivirus-=quantita;
		}
		if(software=="rootcrash") {
			if(user.getValuta()>=prezzoRootcrash*quantita&&quantitaRootcrash>0);
			if(nodo.compra_software(software, quantita) ) {
				valuta=user.getValuta()-prezzoRootcrash*quantita;
				user.setValuta(valuta);
			}
			quantitaRootcrash-=quantita;
		}
	}
	
	public void compraRisorse(Utente user, Base nodo, String risorsa) {
		int valuta;
		
		if(risorsa=="ram") {
			if(user.getValuta()>=prezzoRam&&quantitaRam>0);
				if(nodo.compra_risorsa(risorsa) ) {
					valuta=user.getValuta()-prezzoRam;
					user.setValuta(valuta);
				}
				quantitaRam--;
		}
		if(risorsa=="cpu") {
			if(user.getValuta()>=prezzoCpu&&quantitaCpu>0);
			if(nodo.compra_risorsa(risorsa) ) {
				valuta=user.getValuta()-prezzoCpu;
				user.setValuta(valuta);
			}
			quantitaCpu--;
		}
		if(risorsa=="energia") {
			if(user.getValuta()>=prezzoEnergia&&quantitaEnergia>0);
			if(nodo.compra_risorsa(risorsa) ) {
				valuta=user.getValuta()-prezzoEnergia;
				user.setValuta(valuta);
			}
			quantitaEnergia--;
		}
	}
	
}
