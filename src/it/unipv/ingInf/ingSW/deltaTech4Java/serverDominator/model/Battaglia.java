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
	@SuppressWarnings("unused")
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
		
		sel_attaccanti= new Software[3];
		sel_difensori= new Software[3];
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
		
		sel_attaccanti[1]= new Virus(attaccante.getStats_software_creati()[1].getLivello(), quantita_v);
		sel_attaccanti[2]= new Rootcrash(attaccante.getStats_software_creati()[2].getLivello(), quantita_r);
		
		sel_difensori[0]= new Antivirus(difensore.getStats_software_creati()[0].getLivello(), difensore.getStats_software_creati()[0].getQuantita());
		
		if (attaccante.getStats_software_creati()[2].getQuantita()>1 && quantita_r==1) {
			sel_attaccanti[2].setQuantita(quantita_r);
			
			attaccante.getStats_software_creati()[2].setQuantita(attaccante.getStats_software_creati()[2].getQuantita()-1);
			
		}
		if(attaccante.getStats_software_creati()[1].getQuantita()>=quantita_v) {
			sel_attaccanti[1].setQuantita(quantita_v);
			
			attaccante.getStats_software_creati()[1].setQuantita(attaccante.getStats_software_creati()[1].getQuantita()-quantita_v);
		}
		
		attaccante.setSoftware_disponibile(attaccante.getSoftware_disponibile()-(quantita_v+ quantita_r) );
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
		
		int attacco, difesa, difesa1, difesa2;
		int temp;
		
		boolean successo=false;
		attacco= sel_attaccanti[1].getQuantita() * sel_attaccanti[1].getVal_atk();
		difesa1=aggiorna_firewall();
		difesa2=(sel_difensori[0].getVal_def()*sel_difensori[0].getQuantita());
		
		difesa= difesa1+difesa2;
		
		/*per test
		System.out.println("attacco valore" + attacco);
		System.out.println("difesa valore" +difesa);
		*/
		
		if(attacco>difesa) {
			successo=true;
			sel_difensori[0].setQuantita(0);
			
		} 
		else {
			if(attacco<difesa) {
				if(sel_difensori[0].getLivello()!=0) {
					temp= (difesa2/sel_difensori[0].getVal_def()) - attacco;
					
					/*System.out.println(temp); 
					 */
					difensore.getStats_software_creati()[0].setQuantita(temp);
				}
			} else if(attacco==difesa) {
				difensore.getStats_software_creati()[0].setQuantita(0); 
			}
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
			report=" Ha conquistato il nodo di "+difensore.getPossessore().getNome();
		}else report=" Non ha conquistato il nodo di "+difensore.getPossessore().getNome();
		
		return report;
	}
	
	/**aggiorna lo stato del nodo bersaglio con i dati del giocatore attaccante 
	 * in caso di vittoria del giocatore attaccante, inoltre se il nodo bersaglio
	 * e' tipo cloud, potenzia le risorse del nodo base del giocatore attaccante
	 * in funzione alle risorse che possiede il nodo cloud
	 */
	public void aggiornastati() {
		//difensore.setDist_base(partenza.getDist_base()+1);
		
		
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
			//attaccante.getPossessore().setBasi_prese(attaccante.getPossessore().getBasi_prese()+1);
			difensore.getPossessore().setBasi_prese(difensore.getPossessore().getBasi_prese()-1);
			if(difensore.getPossessore().getBasi_prese()==0) {
				difensore.getPossessore().setLife(false);
			}
			
		}
		difensore.setPossessore(attaccante.getPossessore());
		attaccante.getPossessore().aggiornaPunteggio(10);
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