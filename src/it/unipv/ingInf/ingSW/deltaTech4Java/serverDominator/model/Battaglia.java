package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

public class Battaglia {
	private Base attaccante;
	private Nodo difensore;
	private Software virus;
	private Software rootcrash;
	
	public Battaglia(Base attaccante, Nodo difensore) {
		this.attaccante=attaccante;
		this.difensore=difensore;
		
	}
	public void selezione() {
		
	}
	public boolean calcola_vincitore() {
		
	}
	public int aggiorna_firewall() {
		difensore.getBonus_def();
		
	}
	public String stampa_report() {
		
	}
	
}