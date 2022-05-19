package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.Rootcrash;


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
			if(risorse[i].getNome()==nome) {
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
	/** ritorna valore intero che rappresenta il tempo di attesa per il potenziamento della risorsa selezionata
	 * @param nome
	 * nome della risorsa di cui si vuole sapere il tempo di potenziamento
	 */
	public int getTempoRisorsa(String nome) {
		int tempo=-1;
		for(int i=0; i<TIPI_RISORSE;i++) {
			if(risorse[i].getNome()==nome)
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
			if(stats_software_creati[i].getNome()==nome)
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
		
		if(quantita>this.getSpazio_Ram()) {
			quantita=this.getSpazio_Ram();
		}
		for(i=0;i<TIPI_SOFTWARE;i++) {
			if(stats_software_creati[i].getNome()==nome) {
				n_soft=super.getSoftware_disponibile()+quantita;
				quantita=stats_software_creati[i].getQuantita()+quantita;
				switch(i) {
				case 0: 
					stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantita);
					break;
				case 1: 
					stats_software_creati[1]= new Virus(risorse[0].getStat2(), quantita);
					break;
				case 2:
					stats_software_creati[2] = new Rootcrash(risorse[0].getStat3(), quantita);
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
				if(stats_software_creati[i].getNome()==nome) {
					quantita=stats_software_creati[i].getQuantita()+quantita;
					switch(i) {
					case 0: 
						super.time2.countdown(stats_software_creati[0].getTemp_richiesto()* quantita);
						super.time2.timer(stats_software_creati[0].getTemp_richiesto()*quantita);
						if(this.getSpazio_Ram()-quantita>=0) {
							stats_software_creati[0]= new Antivirus(risorse[0].getStat1(), quantita);
						}
						
						break;
					case 1: 
						super.time2.countdown(stats_software_creati[1].getTemp_richiesto()*quantita);
						super.time2.timer(stats_software_creati[1].getTemp_richiesto()*quantita);
						if(this.getSpazio_Ram()-quantita>=0) {
							stats_software_creati[1]= new Virus(risorse[0].getStat2(), quantita);
						}
						break;
					case 2:
						super.time2.countdown(stats_software_creati[2].getTemp_richiesto()*quantita);
						super.time2.timer(stats_software_creati[2].getTemp_richiesto()*quantita);
						if(this.getSpazio_Ram()-quantita>=0) {
							stats_software_creati[2] = new Rootcrash(risorse[0].getStat3(), quantita);
						}
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
	
//---------------getter and setter--------------//
	
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
	public int getE_lvl() {
		return risorse[2].getLivello_risorsa();
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
	public int getSpazio_Ram() {
		return risorse[1].getStat1()-super.getSoftware_disponibile();
	}
	
	public int getLvl_max_firewall() {
		return risorse[3].getMAX_LVL();
	}
	
	public int getLvl_virus() {
		return risorse[0].getStat2();
	}
	
	public int getQnt_virus() {
		return stats_software_creati[1].getQuantita();
	}

	public int getLvl_antivirus() {
		return risorse[0].getStat1();
	}
	
	public int getQnt_antivirus() {
		return stats_software_creati[0].getQuantita();
	}
	public int getLvl_rootcrash() {
		return risorse[0].getStat3();
	}
	
	public int getQnt_rootcrash() {
		return stats_software_creati[2].getQuantita();
	}
	
}
