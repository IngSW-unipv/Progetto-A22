package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione dell'oggetto battaglia. Si occupa di calcolare il vincitore di uno scontro.
 */

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;


public class Battaglia {
	private Base attaccante;
	private Nodo difensore;
	private Software[] sel_attaccanti;
	private Software[] sel_difensori;
		
	public Battaglia(Base attaccante, Nodo difensore) {
		this.attaccante=attaccante;
		this.difensore=difensore;
		sel_attaccanti= attaccante.getStats_software_creati();
		sel_difensori=difensore.getStats_software_creati();
	}
/**necessario selezionare i software da mandare all'attacco del nodo bersaglio.
 * questo metodo prepara i dati necessari al calcolo del vincitore
 * @param quantità_v: rappresenta la quantità selezionata dal giocatore di Virus da mandare in attacco
 * @param quantità_r: rappresenta la quantità selezionata dal giocatore di Rootcrash da mandare all'attacco, max 1.
 */
	public void selezione(int quantità_v, int quantità_r) {
		int[] disp;
		disp=new int [2];
		disp[0]=sel_attaccanti[1].getQuantità();
		disp[1]= sel_attaccanti[2].getQuantità();
		
		if (disp[1]>=quantità_r|| quantità_r<=1) {
			sel_attaccanti[2].setQuantità(quantità_r);
		}
		if(disp[0]>=quantità_v) {
			sel_attaccanti[1].setQuantità(quantità_v);
		}
	}
/**metodo per calcolare gli effetti del software Rootcrash
 */
	public int aggiorna_firewall() {
		Firewall temp;
		temp= new Firewall(difensore.getLvl_firewall()-sel_attaccanti[2].getVal_atk());
		return temp.getStat1();
	}	
/**calcola il vincitore dello scontro confrontando i valori di attacco e di difesa
 * dei nodi coinvolti. L'attacco parte sempre dal nodo base, anche se l'attacco è possibile
 * solo se il nodo difensore è vicino ad almeno un nodo dell'attaccante.
 */
	public boolean calcola_vincitore() {
		int attacco, difesa;
		boolean successo=false;
		
	// avvia timer dist_min*tempo unita;
		
		attacco= sel_attaccanti[1].getQuantità() * sel_attaccanti[1].getVal_atk();
		difesa=aggiorna_firewall()+sel_difensori[0].getVal_def();
		if(attacco>difesa) {
			successo=true;
		}
		return successo;
	}
	
/**stampa il report di fine battaglia
*/
	public String stampa_report() {
		String report;
		if(calcola_vincitore()) {
			report="Hai conquistato il nodo";
		}else report="Non hai conquistare il nodo";
		
		return report;
	}
	
}