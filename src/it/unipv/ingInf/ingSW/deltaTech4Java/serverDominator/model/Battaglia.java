package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

import java.beans.PropertyChangeSupport;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Timer;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione dell'oggetto battaglia. Si occupa di calcolare il vincitore di uno scontro.
 */
public class Battaglia extends Thread{
	private Nodo attaccante;
	private Nodo difensore;
	private Software[] sel_attaccanti;
	private Software[] sel_difensori;
	
	private boolean esito;
	private Timer time;
	private int t_timer;
	private String report;
	private Nodo partenza;
	private boolean life=false;
	private PropertyChangeSupport changes;
	public static final String BATTLE_PROP="battaglia";
	
	
	/**Permette di creare un oggetto di tipo Battaglia
	 * @param Nodo attaccante
	 * base che fa partire l'attacco
	 * @param Nodo difensore
	 * nodo che viene attaccato
	 * @param timer
	 * tempo necessario per eseguire l'attacco
	 */
	public Battaglia(Nodo attaccante, Nodo difensore, int t_timer) {
		this.attaccante=attaccante;
		this.difensore=difensore;
		this.t_timer= t_timer;
		sel_attaccanti= attaccante.getStats_software_creati();
		sel_difensori=difensore.getStats_software_creati();
		time= new Timer();
		life=true;
		changes= new PropertyChangeSupport(this);
		
	}

	/**usato per selezionare quante unita software
	 * si vogliono inviare in battaglia contro un nodo selezionato
	 * @param quantita_v
	 * quantita di unita software virus che si vogliono inviare
	 * @param quantita_r
	 * quantita di unita software rootcrash che si vogliono inviare max 1
	 */
	public void selezione(int quantita_v, int quantita_r) {
		
		int[] disp;
		int q_root, q_virus;
		disp=new int [2];
		disp[0]= sel_attaccanti[1].getQuantita();
		disp[1]= sel_attaccanti[2].getQuantita();
		
		if (disp[1]>=quantita_r|| quantita_r<=1) {
			sel_attaccanti[2].setQuantita(quantita_r);
			q_root=disp[1]-1;
			attaccante.getStats_software_creati()[2].setQuantita(q_root);
			
		}
		if(disp[0]>=quantita_v) {
			sel_attaccanti[1].setQuantita(quantita_v);
			q_virus= disp[0]-quantita_v;
			attaccante.getStats_software_creati()[1].setQuantita(q_virus);
		}
	}
	

	/**aggiorna il firewall del nodo bersaglio considerando il suo livello e il livello del
	 * software rootcrash che l'attaccante ha inviato
	 * @return
	 * il valore aggiornato di bonus difensivo dato dal firewall
	 */
	public int aggiorna_firewall() {
		
		Firewall temp;		
		temp= new Firewall(difensore.getLvl_firewall()-sel_attaccanti[2].getVal_atk());
		return temp.getStat1();
	}		

	/**usando i valori dei software di attacco e i valori di difesa calcola il vincitore della battaglia
	 * 
	 * @return
	 * true se l'attaccante ha vinto la battaglia
	 * false se l'attaccante non ha vinto la battaglia
	 */
	public boolean calcola_vincitore() {
		
		int attacco, difesa;
		int temp;
		
		boolean successo=false;
		attacco= sel_attaccanti[1].getQuantita() * sel_attaccanti[1].getVal_atk();
		difesa=aggiorna_firewall()+(sel_difensori[0].getVal_def()*sel_difensori[0].getQuantita());
		
		if(attacco>difesa) {
			sel_difensori[0].setQuantita(0);
			successo=true;
		} else {
			temp= (difesa-attacco) / (sel_difensori[0].getLivello() );
			sel_difensori[0].setQuantita(temp);
		}
		return successo;
	}
	

	/**stampa il report di fine battaglia
	 * @return
	 * report("Hai conquistato il nodo difensore"/"Non hai conquistato il nodo difensore")
	 * */
	public String stampa_report(boolean esito) {
		
		String report;
		if(esito) {
			report="Hai conquistato il nodo"+difensore.getPossessore().getNome();
		}else report="Non hai conquistare il nodo"+difensore.getPossessore().getNome();
		
		return report;
	}
	
	/**aggiorna lo stato del nodo bersaglio con i dati del giocatore attaccante 
	 * in caso di vittoria del giocatore attaccante, inoltre se il nodo bersaglio
	 * e' tipo cloud, potenzia le risorse del nodo base del giocatore attaccante
	 * in funzione alle risorse che possiede il nodo cloud
	 */
	public void aggiornastati() {
		difensore.setDist_base(partenza.getDist_base()+1);
		difensore.setPossessore(partenza.getPossessore());
		attaccante.getPossessore().aggiornaPunteggio(10);
		
		if (difensore.getTipologia().equals("cloud")) {
			
			attaccante.compra_risorsa("Energia");
			
			if(difensore.getLvl_cpu()>0) {
				attaccante.compra_risorsa("Cpu");
			}
			if(difensore.getLvl_ram()>0) {
				attaccante.compra_risorsa("Ram");
			}
			
		}
		else {
			attaccante.getPossessore().setBasi_prese(attaccante.getPossessore().getBasi_prese()+1);
			difensore.getPossessore().setBasi_prese(difensore.getPossessore().getBasi_prese()-1);
			
		}
	}
	
	/**esecuzione della battaglia */
	public void run() {
		while(life) {
			time.timer(t_timer);
			esito= this.calcola_vincitore();
			report=stampa_report(esito);
			if(esito) {
				this.aggiornastati();
				
			}
			changes.firePropertyChange(BATTLE_PROP, 0, 1);
			life=false;
		}
		
	}

//--------------getter and setter------------//
	/**
	 * returna l'esito della battaglia
	 * @return
	 * true=l'attacco ha avuto successo, false=attacco fallito
	 */
	public boolean getEsito() {
		return esito;
	}
	/**
	 * returna il report della battaglia
	 * @return
	 * report("Hai conquistato il nodo difensore"/"Non hai conquistato il nodo difensore")
	 */
	public String getReport() {
		return report;
	}
	/**
	 * setta il nodo da cui parte l'attacco
	 * @param
	 * Nodo da cui parte l'attacco
	 */
	public void setPartenza(Nodo partenza) {
		this.partenza = partenza;
	}

	public Nodo getAttaccante() {
		return attaccante;
	}

	public Nodo getDifensore() {
		return difensore;
	}

	public PropertyChangeSupport getChanges() {
		return changes;
	}
	
	
}