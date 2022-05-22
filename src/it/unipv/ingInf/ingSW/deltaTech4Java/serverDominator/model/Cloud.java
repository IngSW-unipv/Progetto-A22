package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione della classe Cloud, nodo necessario al miglioramento
 * delle risorse del nodo base.
 */
public class Cloud extends Nodo {
	private Software[] stats_software_creati;
	private Risorse[] risorse;
	private final int TIPI_RISORSE=4;
	
	public Cloud (Giocatore possessore) {
		super.setTipologia("cloud");
		super.setDist_base(0);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		super.setSoftware_max(20);
		risorse= new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
		stats_software_creati= new Software[1];
	}
	
	public Cloud () {
		super.setTipologia("cloud");
		super.setDist_base(0);
		super.setSoftware_disponibile(0);
		super.setSoftware_max(20);
		risorse= new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
		stats_software_creati= new Software[1];
	}

	/**inizializza le risorse del nodo cloud */
	public void inizializza_risorse() {
		/* NB: 0=cpu, 1=ram, 2=energia,3=firewall*/	
		
		risorse[0]=new Cpu((int)(Math.random()*2));
		super.setLvl_cpu(risorse[0].getLivello_risorsa());
		risorse[1]=new Ram((int)(Math.random()*2));
		super.setLvl_ram(risorse[1].getLivello_risorsa());
		risorse[2]=new Energia(1);
		super.setE_disponibile(risorse[2].getStat1());
		risorse[3]=new Firewall((int)(Math.random()*2));
		super.setLvl_firewall(risorse[3].getLivello_risorsa());
	}

	/**potenziamento di una risorsa
	 * @param nome
	 * nome della risorsa che si intede potenziare
	 */
	public void potenzia_risorsa(String nome) {
				
		boolean check=false;
		if(nome!="Firewall") {
			System.out.println("risorsa non potenziabile");
		}else {
			/*start timer per potenziamento firewall*/
			super.time1.countdown(risorse[3].getTempo_richiesto());
			super.time1.timer(risorse[3].getTempo_richiesto());
			check=risorse[3].potenziamento();
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
		
	}

	/** crea unita software
	 * @param nome
	 * nome della unita software che si vuole creare
	 * @param quantita
	 * quantita della unita software che si vuole creare	
	 */
	public void crea_software(String nome, int quantita) {
			
		boolean check=false;
		int n_soft;
		if(nome!="Antivirus") {
			System.out.println("software non disponibile in nodo cloud");
		}else {
			n_soft=quantita+super.getSoftware_disponibile();
			if(n_soft<=super.getSoftware_max()) {
			/*start timer per creazione software*/
				super.time2.countdown(stats_software_creati[0].getTemp_richiesto()*quantita);
				super.time2.timer(stats_software_creati[0].getTemp_richiesto()*quantita);
				stats_software_creati[0]= new Antivirus(1,n_soft);
				check=true;
				super.setSoftware_disponibile(n_soft);
			}else System.out.println("spazio non sufficiente per la creazione");
		}
		if(check){
			System.out.println("successo");
		} else System.out.println("azione non eseguita");
		
	}
	/** ritorna valore intero che rappresenta il tempo di attesa per il potenziamento della risorsa selezionata
	 * @param nome
	 * nome della risorsa di cui si vuole sapere il tempo di potenziamento (per il nodo cloud è disponibile solo Firewall)
	 */
	public int getTempoRisorsa(String nome) {
		int tempo=-1;
		if(nome!="Firewall") {
			return tempo;
		} else {
			tempo=risorse[3].getTempo_richiesto();
		}
		return tempo;
	}
	
	/** ritorna valore intero che rappresenta il tempo di attesa per la creazione dei software selezionata
	 * @param nome
	 * nome del software di cui si vuole sapere il tempo di potenziamento (per il nodo cloud è disponibile solo Antivirus)
	 */
	public int getTempoSoftware(String nome) {
		int tempo=-1;
		if(nome!="Antivirus") {
			return tempo;
		} else {
			tempo=stats_software_creati[0].getTemp_richiesto();
		}
		return tempo;
	}
//-------------getter and setter------------//
		
	public int getLvl_cpu() {
		return risorse[0].getLivello_risorsa();
	}
	
	public Software getAntivirus() {
		return stats_software_creati[0];
	}

	public int getLvl_ram() {
		return risorse[1].getLivello_risorsa();
	}

	public int getE_disponibile() {
		return risorse[2].getStat1();
	}
	
	public int getLvl_firewall() {
		return risorse[3].getLivello_risorsa();
	}
	public int getBonus_def() {
		return risorse[3].getStat1();
	}
	
	public void setBonus_def(int bonus_def) {
		risorse[3].setStat1(bonus_def);
	}
	
	public Software[] getStats_software_creati() {
		return stats_software_creati;
	}
	
	public int getQnt_antivirus() {
		return super.getSoftware_disponibile();
	}
	
	
}
