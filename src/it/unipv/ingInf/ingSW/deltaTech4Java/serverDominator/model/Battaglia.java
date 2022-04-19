package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Timer;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione dell'oggetto battaglia. Si occupa di calcolare il vincitore di uno scontro.
 */

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;


public class Battaglia implements Runnable {
	private Nodo attaccante;
	private Nodo difensore;
	private Software[] sel_attaccanti;
	private Software[] sel_difensori;
	private Timer time;
	private int t_timer;
	private boolean esito;
	private String report;
	
	public Battaglia(Nodo attaccante, Nodo difensore, int t_timer) {
		this.attaccante=attaccante;
		this.difensore=difensore;
		sel_attaccanti= attaccante.getStats_software_creati();
		sel_difensori=difensore.getStats_software_creati();
		time= new Timer();
		this.t_timer= t_timer;
		esito= false;
	}
		
	public void selezione(int quantita_v, int quantita_r) {
		
		/**necessario selezionare i software da mandare all'attacco del nodo bersaglio.
		 * questo metodo prepara i dati necessari al calcolo del vincitore
		 * @param quantit�_v: rappresenta la quantit� selezionata dal giocatore di Virus da mandare in attacco
		 * @param quantit�_r: rappresenta la quantit� selezionata dal giocatore di Rootcrash da mandare all'attacco, max 1.
		 */

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
	
	public int aggiorna_firewall() {
		/**metodo per calcolare gli effetti del software Rootcrash
		 */
		Firewall temp;
		temp= new Firewall(difensore.getLvl_firewall()-sel_attaccanti[2].getVal_atk());
		return temp.getStat1();
	}	
	
	public boolean calcola_vincitore() {
		/**calcola il vincitore dello scontro confrontando i valori di attacco e di difesa
		 * dei nodi coinvolti. L'attacco parte sempre dal nodo base, anche se l'attacco � possibile
		 * solo se il nodo difensore � vicino ad almeno un nodo dell'attaccante.
		 */
		int attacco, difesa;
		boolean successo=false;
		attacco= sel_attaccanti[1].getQuantita() * sel_attaccanti[1].getVal_atk();
		difesa=aggiorna_firewall()+sel_difensori[0].getVal_def();
		if(attacco>difesa) {
			successo=true;
		}
		return successo;
	}
	
	public String stampa_report() {
		/**stampa il report di fine battaglia
		*/
		String report;
		
		if(calcola_vincitore()) {
			report="Hai conquistato il nodo";
		}else report="Non hai conquistare il nodo";
		
		return report;
	}
	
	public void run() {
		this.aggiorna_firewall();
		this.time.countdown(t_timer);
		this.time.timer(t_timer);
		esito=this.calcola_vincitore();
		if(esito) {
			report= stampa_report();
		}
	}

	public boolean getEsito() {
		return esito;
	}

	public String getReport() {
		return report;
	}

	
}