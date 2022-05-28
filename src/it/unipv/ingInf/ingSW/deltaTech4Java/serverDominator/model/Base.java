package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;


/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione della classe Base, nodo principale di ogni giocatore.
 */
public class Base extends Nodo{
	private Software[] stats_software_creati;
	private final int TIPI_SOFTWARE=3;
	private Risorse[] risorse;
	private final int TIPI_RISORSE=4;
	/**
	 * Permette di creare un oggetto di tipo Base vuoto
	 */
	public Base() {
		super.setTipologia("base");
		super.setDist_base(0);
		super.setPossessore(null);
		super.setSoftware_disponibile(0);
		stats_software_creati= new Software[TIPI_SOFTWARE];
		this.inizializza_software();
		risorse=new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}
	/**Permette di creare un oggetto di tipo Base il cui possessore sara quello passato come parametro
	 * @param possessore
	 * possessore della base
	 */
	public Base(Giocatore possessore) {
		super.setTipologia("base");
		super.setDist_base(0);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		stats_software_creati= new Software[TIPI_SOFTWARE];
		this.inizializza_software();
		risorse=new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}
	
	/** inizializza le unita software del nodo base */
	public void inizializza_software() {
		
		stats_software_creati[0]=new Antivirus(0,0);
		stats_software_creati[1]=new Virus(0,0);
		stats_software_creati[2]=new Rootcrash(0,0);
	}
	
	/** inizializza le risorse iniziali di un nodo base */
	public void inizializza_risorse() {
		/* NB: 0=cpu, 1=ram, 2=energia,3=firewall */
		
		risorse[0]=new Cpu(1);
		super.setLvl_cpu(risorse[0].getLivello_risorsa());
		risorse[1]=new Ram(1);
		super.setLvl_ram(risorse[1].getLivello_risorsa());
		risorse[2]=new Energia(1);
		super.setE_disponibile(risorse[2].getStat1());
		risorse[3]=new Firewall(0);
		super.setLvl_firewall(risorse[3].getLivello_risorsa());
	}

	/**usato per il potenziamento di una risorsa tramite mercato, oppure 
	 * successivamente alla conquista di un nodo cloud
	 * @param nome 
	 * nome della risorsa che si intende potenziare tramite il mercato
	 */
	public boolean compra_risorsa(String nome) {
	
		boolean check=false;
		int i;
		for(i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome().equalsIgnoreCase(nome)) {
				check= risorse[i].potenziamento(); 
			}
		}
		
		return check;
	}

	/** usato per il potenziamento standard di una risorsa
	 *  @param nome
	 *  nome della risorsa che si vuole potenziare
	 */
public void potenzia_risorsa(String nome) {
		
		boolean check=false;
		int en_usata, i;
		for(i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome().equalsIgnoreCase(nome)) {
				if(risorse[i].getE_richiesta()<= risorse[2].getStat1()) {
						en_usata= risorse[2].getStat1()-risorse[i].getE_richiesta();
						risorse[2].setStat1(en_usata);
						check= risorse[i].potenziamento(); 
				}
			}
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
	}
	/** ritorna valore intero che rappresenta il tempo di attesa per il potenziamento della risorsa selezionata
	 * @param nome
	 * nome della risorsa di cui si vuole sapere il tempo di potenziamento
	 */
	public int getTempoRisorsa(String nome) {
		int tempo=-1;
		for(int i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome().equalsIgnoreCase(nome))
				tempo=risorse[i].getTempo_richiesto();
		}
		return tempo;
	}
	/** ritorna valore intero che rappresenta il tempo di attesa per la creazione dei software selezionata
	 * @param nome
	 * nome del software di cui si vuole sapere il tempo di potenziamento
	 */
	public int getTempoSoftware(String nome) {
		int tempo=-1;
		for(int i=0;i<TIPI_SOFTWARE;i++) {
			if(stats_software_creati[i].getNome().equalsIgnoreCase(nome))
				tempo=stats_software_creati[i].getTemp_richiesto();
		}
		return tempo;
	}
	/** usato per creare risorse comprandole dal mercato
	 * @param nome
	 * nome della unita software che si vuole creare tramite mercato
	 * @param quantita
	 * quantita di unita software che si vuole creare tramite mercato
	 */
	public boolean compra_software(String nome, int quantita) {

		boolean check=false;
		int n_soft;
		int i;
		
		if(quantita>risorse[1].getStat1()) {
			quantita=this.getSpazio_Ram();
		}
		for(i=0;i<TIPI_SOFTWARE;i++) {
			if(stats_software_creati[i].getNome().equalsIgnoreCase(nome)) {
				n_soft=super.getSoftware_disponibile()+quantita;
				quantita= stats_software_creati[i].getQuantita()+quantita;
				switch(i) {
				case 0: 
					stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantita);
					break;
				case 1: 
					stats_software_creati[1]= new Virus(risorse[0].getStat1(), quantita);
					break;
				case 2:
					stats_software_creati[2]= new Rootcrash(risorse[0].getStat1(), quantita);
					break;
				}
				super.setSoftware_disponibile(n_soft);
				check= true;
			}
		}
		
		return check;
	}
	
	/**creazione software standard
	 * @param nome
	 * nome della unita software che si vuole creare
	 * @param quantita
	 * quantita della unita software che si vuole creare
	 */
	public void crea_software(String nome, int quantita) {
	
		boolean check=false;
		int n_soft;
		int i;
		
		n_soft=super.getSoftware_disponibile() + quantita;
		if(n_soft<=risorse[1].getStat1()) {
			for(i=0;i<TIPI_SOFTWARE;i++) {
				if(stats_software_creati[i].getNome().equalsIgnoreCase(nome)) {
					quantita= stats_software_creati[i].getQuantita()+quantita;
					switch(i) {
					case 0: 
						if(risorse[0].getStat1()!=0) {
							if(risorse[1].getStat1()-quantita>=0) {
								stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantita);
							}
						}
						break;
					case 1: 
						if(risorse[0].getStat2()!=0) {
							if(risorse[1].getStat1()-quantita>=0) {
								stats_software_creati[1]= new Virus(risorse[0].getStat2(), quantita);
							}
						}
						break;
					case 2:
						if(risorse[0].getStat2()!=0) {
							if(risorse[1].getStat1()-quantita>=0) {
								stats_software_creati[2]= new Rootcrash(risorse[0].getStat3(), quantita);
							}
						}
						break;
					}
					super.setSoftware_disponibile(n_soft);
					check= true;
				}
			}
		}else System.out.println("spazio non disponibile in ram");
		if(check){
			System.out.println("software creati con successo");
		} else System.out.println("creazione non eseguita");	
	}
	
//---------------getter and setter--------------//
	/**
	 * ritorna la capacita' massima di stoccaggio dei software
	 * @return
	 * quantita massima software
	 */
	public int getSoftware_max() {
		return risorse[1].getStat1();
	}
	/**
	 * ritorna il valore della difesa data dal livello del Firewall
	 * @return
	 * bonus difesa del Firewall
	 */
	public int getBonus_def() {
		return risorse[3].getStat1();
	}
	/**
	 * setta il valore della difesa data dal livello del Firewall
	 * @param
	 * bonus difesa del Firewall
	 */
	public void setBonus_def(int bonus_def) {
		risorse[3].setStat1(bonus_def);;
	}
	/**
	 * ritorna l'energia disponibile della base
	 * @return
	 * energia disponibile
	 */
	public int getE_disponibile() {
		return risorse[2].getStat1();
	}
	/**
	 * ritorna il livello dell'energia della base
	 * @return
	 * livello energia
	 */
	public int getE_lvl() {
		return risorse[2].getLivello_risorsa();
	}
	/**
	 * ritorna il livello della Cpu della base
	 * @return
	 * livello Cpu
	 */
	public int getLvl_cpu() {
		return risorse[0].getLivello_risorsa();
	}
	/**
	 * ritorna il livello della Ram della base
	 * @return
	 * livello Ram
	 */
	public int getLvl_ram() {
		return risorse[1].getLivello_risorsa();
	}
	/**
	 * ritorna il livello della Firewall della base
	 * @return
	 * livello Firewall
	 */
	public int getLvl_firewall() {
		return risorse[3].getLivello_risorsa();
	}
	/**
	 * ritorna i software creati della base (0=Antivirus, 1=Virus, 2=Rootcrash)
	 * @return
	 * Software creati
	 */
	public Software[] getStats_software_creati() {
		return stats_software_creati;
	}
	/**
	 * ritorna le risorse disponibili del nodo (0=cpu, 1=ram, 2=energia, 3=firewall)
	 * @return
	 * risorse
	 */
	public Risorse[] getRisorse() {
		return risorse;
	}
	/**
	 * ritorna il livello massimo della Cpu della base
	 * @return
	 * livello massimo Cpu
	 */
	public int getLvl_max_cpu() {
		return risorse[0].getMAX_LVL();
	}
	/**
	 * ritorna il livello massimo della Ram della base
	 * @return
	 * livello massimo Ram
	 */
	public int getLvl_max_ram() {
		return risorse[1].getMAX_LVL();
	}
	/**
	 * ritorna lo spazio disponibile nella Ram della base per creare nuovi Software
	 * @return
	 * spazio libero Ram
	 */
	public int getSpazio_Ram() {
		return risorse[1].getStat1()-super.getSoftware_disponibile();
	}
	/**
	 * ritorna il livello massimo del Firewall della base
	 * @return
	 * livello massimo Firewall
	 */
	public int getLvl_max_firewall() {
		return risorse[3].getMAX_LVL();
	}
	/**
	 * ritorna il livello dei Virus della base
	 * @return
	 * livello Virus
	 */
	public int getLvl_virus() {
		return risorse[0].getStat2();
	}
	/**
	 * ritorna la quantita' dei Virus della base
	 * @return
	 * quantita' Virus
	 */
	public int getQnt_virus() {
		return stats_software_creati[1].getQuantita();
	}
	/**
	 * ritorna il livello degli Antivirus della base
	 * @return
	 * livello Antivirus
	 */
	public int getLvl_antivirus() {
		return risorse[0].getStat1();
	}
	/**
	 * ritorna la quantita' dei Antivirus della base
	 * @return
	 * quantita' Antivirus
	 */
	public int getQnt_antivirus() {
		return stats_software_creati[0].getQuantita();
	}
	/**
	 * ritorna il livello dei Rootcrash della base
	 * @return
	 * livello Rootcrash
	 */
	public int getLvl_rootcrash() {
		return risorse[0].getStat3();
	}
	/**
	 * ritorna la quantita' dei Rootcrash della base
	 * @return
	 * quantita' Rootcrash
	 */
	public int getQnt_rootcrash() {
		return stats_software_creati[2].getQuantita();
	}
	
}
