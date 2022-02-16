package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;


public class Battaglia {
	private Base attaccante;
	private Nodo difensore;
	private Software[] sel_attaccanti;
	private Software[] sel_difensori;
		
	public Battaglia(Base attaccante, Nodo difensore) {
		this.attaccante=attaccante;
		this.difensore=difensore;
		sel_attaccanti= attaccante.getStats_software_creati();
		sel_difensori=difensore.getStats_software_creati();
	}
	public void selezione(int quantit�_v, int quantit�_r) {
		int[] disp;
		disp=new int [2];
		disp[0]=sel_attaccanti[1].getQuantit�();
		disp[1]= sel_attaccanti[2].getQuantit�();
		
		if (disp[1]>=quantit�_r|| quantit�_r<=1) {
			sel_attaccanti[2].setQuantit�(quantit�_r);
		}
		if(disp[0]>=quantit�_v) {
			sel_attaccanti[1].setQuantit�(quantit�_v);
		}
	}
	public int aggiorna_firewall() {
		Firewall temp;
		temp= new Firewall(difensore.getLvl_firewall()-sel_attaccanti[2].getVal_atk());
		return temp.getStat1();
	}	
	public boolean calcola_vincitore() {
		int attacco, difesa;
		boolean successo=false;
		attacco= sel_attaccanti[1].getQuantit�() * sel_attaccanti[1].getVal_atk();
		difesa=aggiorna_firewall()+sel_difensori[0].getVal_def();
		if(attacco>difesa) {
			successo=true;
		}
		return successo;
	}
	public String stampa_report() {
		String report;
		if(calcola_vincitore()) {
			report="Hai conquistato il nodo";
		}else report="Non sei riuscitoa conquistare il nodo";
		
		return report;
	}
	
	public aggiornamento() {
		
	}
}