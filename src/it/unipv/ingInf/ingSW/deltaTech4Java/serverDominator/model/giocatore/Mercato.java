package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;

public class Mercato {
	private int prezzoRam, prezzoCpu, prezzoEnergia, prezzoVirus;
	private int prezzoAntivirus;
	private int prezzoRootcrash;
	private int prezzoFirewall;
	
	
	public Mercato() {
		prezzoRam=10;
		prezzoCpu=10; 
		prezzoEnergia=10;
		prezzoFirewall=10;
		prezzoVirus=5;
		prezzoAntivirus=5;
		prezzoRootcrash=5;
		
	}
	public Mercato(int prezzoRam,int prezzoCpu, int prezzoEnergia,int prezzoVirus, int prezzoAntivirus,  int prezzoRootcrash) {
		this.prezzoRam=prezzoRam;
		this.prezzoCpu=prezzoCpu; 
		this.prezzoEnergia=prezzoEnergia;
		this.prezzoVirus=prezzoVirus;
		this.prezzoAntivirus=prezzoAntivirus;
		this.prezzoRootcrash=prezzoRootcrash;
		
	}
	
	public void compraSoftware(Giocatore user, Nodo nodo, int quantita, String software) {
		int valuta;
		if (quantita>nodo.getSpazio_Ram()) {
			quantita=nodo.getSpazio_Ram();
		}
		if(software.equalsIgnoreCase("virus")) {	
			if(user.getValuta()>=prezzoVirus*quantita);
				if(nodo.compra_software(software, quantita) ) {
					valuta=user.getValuta()-prezzoVirus*quantita;
					user.setValuta(valuta);
				}
				
		}
		if(software.equalsIgnoreCase("antivirus")) {
			if(user.getValuta()>=prezzoAntivirus*quantita);
			if(nodo.compra_software(software, quantita) ) {
				valuta=user.getValuta()-prezzoAntivirus*quantita;
				user.setValuta(valuta);
			}
			
		}
		if(software.equalsIgnoreCase("rootcrash")) {
			if(user.getValuta()>=prezzoRootcrash*quantita);
			if(nodo.compra_software(software, quantita) ) {
				valuta=user.getValuta()-prezzoRootcrash*quantita;
				user.setValuta(valuta);
			}
			
		}
	}
	
	public void compraRisorse(Giocatore user, Nodo nodo, String risorsa) {
		int valuta;
		
		if(risorsa.equalsIgnoreCase("ram")) {
			if(user.getValuta()>=prezzoRam);
				if(nodo.compra_risorsa(risorsa) ) {
					valuta=user.getValuta()-prezzoRam;
					user.setValuta(valuta);
				}
				
		}
		if(risorsa.equalsIgnoreCase("cpu")) {
			if(user.getValuta()>=prezzoCpu);
			if(nodo.compra_risorsa(risorsa) ) {
				valuta=user.getValuta()-prezzoCpu;
				user.setValuta(valuta);
			}
			
		}
		if(risorsa.equalsIgnoreCase("energia")) {
			if(user.getValuta()>=prezzoEnergia);
			if(nodo.compra_risorsa(risorsa) ) {
				valuta=user.getValuta()-prezzoEnergia;
				user.setValuta(valuta);
			}
			
		}
		if(risorsa.equalsIgnoreCase("Firewall")) {
			if(user.getValuta()>=prezzoFirewall);
				if(nodo.compra_risorsa(risorsa) ) {
					valuta=user.getValuta()-prezzoFirewall;
					user.setValuta(valuta);
				}
				
		}
	}
	public int getCostoRam(int quantita) {
		return prezzoRam*quantita;
	}
	public int getCostoCpu(int quantita) {
		return prezzoCpu*quantita;
	}
	public int getCostoEnergia(int quantita) {
		return prezzoEnergia*quantita;
	}
	public int getCostoVirus(int quantita) {
		return prezzoVirus*quantita;
	}
	public int getCostoAntivirus(int quantita) {
		return prezzoAntivirus*quantita;
	}
	public int getCostoRootcrash(int quantita) {
		return prezzoRootcrash*quantita;
	}
	/**
	 * Recupera il prezzo unitario della ram
	 * @return
	 * prezzoRam
	 */
	public int getPrezzoRam() {
		return prezzoRam;
	}
	/**
	 * Recupera il prezzo unitario della cpu
	 * @return
	 * prezzoCpu
	 */
	public int getPrezzoCpu() {
		return prezzoCpu;
	}
	/**
	 * Recupera il prezzo unitario della energia
	 * @return
	 * prezzoEnergia
	 */
	public int getPrezzoEnergia() {
		return prezzoEnergia;
	}
	/**
	 * Recupera il prezzo unitario di Virus
	 * @return
	 * prezzoVirus
	 */
	public int getPrezzoVirus() {
		return prezzoVirus;
	}
	/**
	 * Recupera il prezzo unitario di antivirus
	 * @return
	 * prezzoAntivirus
	 */
	public int getPrezzoAntivirus() {
		return prezzoAntivirus;
	}
	/**
	 * Recupera il prezzo unitario di Rootcrash
	 * @return
	 * prezzoRootcrash
	 */
	public int getPrezzoRootcrash() {
		return prezzoRootcrash;
	}
	/**recupera il prezzo unitario di Firewall
	 * @return
	 * prezzoFirewall
	 */
	public int getPrezzoFirewall() {
		return prezzoFirewall;
	}
	
}
