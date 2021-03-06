package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo;

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
	/**
	 * Costruttore per creare un oggetto Cloud il cui possessore sara' quello passato come parametro
	 * @param 
	 * possessore
	 */
	public Cloud (Giocatore possessore) {
		super.setTipologia("cloud");
		super.setDist_base(0);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		super.setSoftware_max(20);
		risorse= new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
		stats_software_creati= new Software[1];
		stats_software_creati[0]= new Antivirus(0, 0);
	}
	/**
	 * Costruttore per creare un oggetto Cloud vuoto
	 */
	public Cloud () {
		super.setTipologia("cloud");
		super.setDist_base(0);
		super.setSoftware_disponibile(0);
		super.setSoftware_max(20);
		risorse= new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
		stats_software_creati= new Software[1];
		stats_software_creati[0]= new Antivirus(0, 0);
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
		if(!nome.equalsIgnoreCase("firewall")) {
			System.out.println("risorsa non potenziabile");
		}else {
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
		if(!nome.equalsIgnoreCase("Antivirus")) {
			System.out.println("software non disponibile in nodo cloud");
		}else {
			n_soft=quantita+super.getSoftware_disponibile();
			if(n_soft<=super.getSoftware_max()) {
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
	 * nome della risorsa di cui si vuole sapere il tempo di potenziamento (per il nodo cloud ??? disponibile solo Firewall)
	 */
	public int getTempoRisorsa(String nome) {
		int tempo=-1;
		if(!nome.equalsIgnoreCase("firewall")) {
			return tempo;
		} else {
			tempo=risorse[3].getTempo_richiesto();
		}
		return tempo;
	}
	
	/** ritorna valore intero che rappresenta il tempo di attesa per la creazione dei software selezionata
	 * @param nome
	 * nome del software di cui si vuole sapere il tempo di potenziamento (per il nodo cloud ??? disponibile solo Antivirus)
	 */
	public int getTempoSoftware(String nome) {
		int tempo=-1;
		if(!nome.equalsIgnoreCase("Antivirus")) {
			return tempo;
		} else {
			tempo=stats_software_creati[0].getTemp_richiesto();
		}
		return tempo;
	}
//-------------getter and setter------------//
	/**
	 * returna il livello della Cpu del nodo
	 * @return
	 * livello Cpu
	 */		
	public int getLvl_cpu() {
		return risorse[0].getLivello_risorsa();
	}
	/**
	 * returna gli Antivirus disponibili del nodo
	 * @return
	 * Software Antivirus
	 */
	public Software getAntivirus() {
		return stats_software_creati[0];
	}
	/**
	 * returna il livello della Ram del nodo
	 * @return
	 * livello Ram
	 */
	public int getLvl_ram() {
		return risorse[1].getLivello_risorsa();
	}
	/**
	 * returna l'energia disponibile del nodo
	 * @return
	 * energia disponibile
	 */
	public int getE_disponibile() {
		return risorse[2].getStat1();
	}
	/**
	 * returna il livello del Firewall del nodo
	 * @return
	 * livello Ram
	 */
	public int getLvl_firewall() {
		return risorse[3].getLivello_risorsa();
	}
	/**
	 * returna il bonus difensivo dato dal Firewall del nodo
	 * @return
	 * bonus difensivo del Firewall
	 */
	public int getBonus_def() {
		return risorse[3].getStat1();
	}
	/**
	 * setta il bonus difensivo dato dal Firewall del nodo
	 * @param
	 * bonus difensivo del Firewall
	 */
	public void setBonus_def(int bonus_def) {
		risorse[3].setStat1(bonus_def);
	}
	/**
	 * returna i software creati del nodo (0=Antivirus, 1=Virus, 2=Rootcrash)
	 * @return
	 * Software creati
	 */
	public Software[] getStats_software_creati() {
		return stats_software_creati;
	}
	/**
	 * returna le risorse disponibili del nodo(0=cpu, 1=ram, 2=energia, 3=firewall)
	 * @return
	 * risorse
	 */
	public Risorse[] getRisorse() {
		return risorse;
	}
	/**
	 * returna la quantita di antivirus disponibili
	 * @return
	 * Quantita' Antivirus
	 */
	public int getQnt_antivirus() {
		return super.getSoftware_disponibile();
	}
	
	
}
