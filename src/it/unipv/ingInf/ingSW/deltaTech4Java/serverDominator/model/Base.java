package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione della classe Base, nodo principale di ogni giocatore.
 */

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.Rootcrash;


public class Base extends Nodo{
	private Software[] stats_software_creati;
	private final int TIPI_SOFTWARE=3;
	private Risorse[] risorse;
	private final int TIPI_RISORSE=4;
	
	public Base() {
		super.setDist_base(0);
		super.setPossessore(null);
		super.setSoftware_disponibile(0);
		stats_software_creati= new Software[TIPI_SOFTWARE];
		this.inizializza_software();
		risorse=new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}
	
	public Base (Giocatore possessore) {
		super.setDist_base(1);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		stats_software_creati= new Software[TIPI_SOFTWARE];
		this.inizializza_software();
		risorse=new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}
/** inizializza un vettore di software*/
	
	public void inizializza_software() {
		/** inizializza un vettore di Software  */
		stats_software_creati[0]=new Antivirus(0,0);
		stats_software_creati[1]=new Virus(0,0);
		stats_software_creati[2]=new Rootcrash(0,0);
	}
/** inizializza un vettore di risorse*/
	
	public void inizializza_risorse() {
		/** inizializza un vettore di Risorse
		 * NB: 0=cpu, 1=ram, 2=energia,3=firewall
		 */
		risorse[0]=new Cpu(1);
		super.setLvl_cpu(risorse[0].getLivello_risorsa());
		risorse[1]=new Ram(1);
		super.setLvl_ram(risorse[1].getLivello_risorsa());
		risorse[2]=new Energia(1);
		super.setE_disponibile(risorse[2].getStat1());
		risorse[3]=new Firewall(0);
		super.setLvl_firewall(risorse[3].getLivello_risorsa());
	}

/** metodo usato per il potenziamento di una risorsa generica
 * aggiorna le statistiche della risorsa passata e i valori necessari
 * per il livello successivo
 */
	public void potenzia_risorsa(String nome) {
	/**metodo usato per potenziare le singole risorse passate da interfaccia*/
		
		boolean check=false;
		int en_usata, i;
		for(i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome()==nome) {
				if(risorse[i].getE_richiesta()<= risorse[2].getStat1()) {
			en_usata= risorse[2].getStat1()-risorse[i].getE_richiesta();
			risorse[2].setStat1(en_usata);
	/*start countdown per potenziamento risorsa*/
			super.time1.countdown(risorse[i].getTempo_richiesto()); //countdown per visualizzazione a schermo
			super.time1.timer(risorse[i].getTempo_richiesto());		//timer per attesa operazione
			check= risorse[i].potenziamento(); 
				}
			}
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
	}
	
	public void crea_software(String nome, int quantità) {
	/**metodo per la creazione di nuovo software, con aggiornamento della quantità totali*/
		
		boolean check=false;
		int n_soft;
		int i;
		
		n_soft=super.getSoftware_disponibile() + quantità;
		if(n_soft<=risorse[1].getStat1()) {
			for(i=0;i<TIPI_SOFTWARE;i++) {
				if(stats_software_creati[i].getNome()==nome) {
					quantità=stats_software_creati[i].getQuantità()+quantità;
					switch(i) {
					case 0: 
						super.time2.countdown(stats_software_creati[0].getTemp_richiesto()* quantità);
						super.time2.timer(stats_software_creati[0].getTemp_richiesto()*quantità);
						stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantità);
						break;
					case 1: 
						super.time2.countdown(stats_software_creati[1].getTemp_richiesto()*quantità);
						super.time2.timer(stats_software_creati[1].getTemp_richiesto()*quantità);
						stats_software_creati[1]= new Virus(risorse[0].getStat2(), quantità);
						break;
					case 2:
						super.time2.countdown(stats_software_creati[2].getTemp_richiesto()*quantità);
						super.time2.timer(stats_software_creati[2].getTemp_richiesto()*quantità);
						stats_software_creati[2] = new Rootcrash(risorse[0].getStat3(), quantità);
						break;
					}
					super.setSoftware_disponibile(n_soft);
					check= true;
				}
			}
		}else System.out.println("spazio non disponibile in ram");
		if(check){
			System.out.println("successo");
		} else System.out.println("azione non eseguita");	
	}
	
/**getter and setter*/
	
	public int getSoftware_max() {
		return risorse[1].getStat1();
	}
	
	public int getBonus_def() {
		return risorse[3].getStat1();
	}
	
	public void setBonus_def(int bonus_def) {
		risorse[3].setStat1(bonus_def);;
	}
	
	public int getE_disponibile() {
		return risorse[2].getStat1();
	}
	
	public int getLvl_cpu() {
		return risorse[0].getLivello_risorsa();
	}
	
	public int getLvl_ram() {
		return risorse[1].getLivello_risorsa();
	}
	
	public int getLvl_firewall() {
		return risorse[3].getLivello_risorsa();
	}

	public Software[] getStats_software_creati() {
		return stats_software_creati;
	}
	public int getLvl_max_cpu() {
		return risorse[0].getMAX_LVL();
	}
	
	public int getLvl_max_ram() {
		return risorse[1].getMAX_LVL();
	}
	
	public int getLvl_max_firewall() {
		return risorse[3].getMAX_LVL();
	}
}
