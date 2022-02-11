package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

public class Cloud extends Nodo {
	private Software antivirus;
	private Risorse[] risorse;
	private final int TIPI_RISORSE=4;
	
	public Cloud (Coordinate coordinate, Giocatore possessore) {
		super(coordinate);
		super.setDist_base(0);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		super.setSoftware_max(20);
		risorse= new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}
	
	public void inizializza_risorse() {
		/** inizializza un vettore di Risorse
		 * NB: 0=cpu, 1=ram, 2=energia,3=firewall
		 */
//implementare funzione random tra 0 e 1 per ogni risorsa
		risorse[0]=new Cpu(0);
		super.setLvl_cpu(risorse[0].getLivello_risorsa());
		risorse[1]=new Ram(0);
		super.setLvl_ram(risorse[1].getLivello_risorsa());
		risorse[2]=new Energia(1);
		super.setE_disponibile(risorse[2].getStat1());
		risorse[3]=new Firewall(0);
		super.setLvl_firewall(risorse[3].getLivello_risorsa());
	}
	
	public void potenzia_risorsa(String nome) {
		boolean check=false;
		if(nome!="Firewall") {
			System.out.println("risorsa non potenziabile");
		}else {
//fare timer basato su risorse[3].getTempo_richiesto();
			check=risorse[3].potenziamento();
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
		
		
	}
	
	public void crea_software(String nome, int quantit�) {
		boolean check=false;
		int n_soft;
		if(nome!="Antivirus") {
			System.out.println("software non disponibile in nodo cloud");
		}else {
			n_soft=quantit�+super.getSoftware_disponibile();
			if(n_soft<=super.getSoftware_max()) {
				antivirus= new Antivirus(1,quantit�);
				check=true;
				super.setSoftware_disponibile(n_soft);
			}else System.out.println("spazio non sufficiente per la creazione");
		}
		if(check){
			System.out.println("successo");
		} else System.out.println("azione non eseguita");
		
	}
	
}