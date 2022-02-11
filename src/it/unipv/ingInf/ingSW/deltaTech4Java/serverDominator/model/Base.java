package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.Rootcrash;

public class Base extends Nodo{
	private Software[] stats_software_creati;
	private final int TIPI_SOFTWARE=3;
	private Risorse[] risorse;
	private final int TIPI_RISORSE=4;
	
	public Base (Coordinate coordinate, Giocatore possessore) {
		super(coordinate);
		super.setDist_base(1);
		super.setPossessore(possessore);
		super.setSoftware_disponibile(0);
		stats_software_creati= new Software[TIPI_SOFTWARE];
		this.inizializza_software();
		risorse=new Risorse[TIPI_RISORSE];
		this.inizializza_risorse();
	}

	public void inizializza_software() {
		/** inizializza un vettore di Software  */
		stats_software_creati[0]=new Antivirus(0,0);
		stats_software_creati[1]=new Virus(0,0);
		stats_software_creati[2]=new Rootcrash(0,0);
	}

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
	
	public void potenzia_risorsa(String nome) {
		boolean check=false;
		int en_usata, i;
		for(i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome()==nome) {
				if(risorse[i].getE_richiesta()<= risorse[2].getStat1()) {
			en_usata= risorse[2].getStat1()-risorse[i].getE_richiesta();
			risorse[2].setStat1(en_usata);
//fare partire un timer basato su risorsa.getTempo_richiesto();
			check= risorse[i].potenziamento(); 
				}
			}
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
	}
	
	public void crea_software(String nome, int quantit�) {
		boolean check=false;
		int n_soft;
		int i;
		
		n_soft=super.getSoftware_disponibile() + quantit�;
		if(n_soft<=risorse[1].getStat1()) {
			for(i=0;i<TIPI_SOFTWARE;i++) {
				if(stats_software_creati[i].getNome()==nome) {
					switch(i) {
					case 0: 
						stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantit�);
						break;
					case 1: 
						stats_software_creati[1]= new Virus(risorse[0].getStat2(), quantit�);
						break;
					case 2:
						stats_software_creati[2] = new Rootcrash(risorse[0].getStat3(), quantit�);
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
	
}