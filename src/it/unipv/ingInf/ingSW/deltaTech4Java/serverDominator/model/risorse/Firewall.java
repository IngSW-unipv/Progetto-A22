package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione risorsa firewall, serve per aumentare il valore di
 * difesa del nodo. 
 * in funzione del livello di questa risorsa, passando una variabile 
 * intera, alla classe nodo, la quale interagisce veramente con i software.
 */
public class Firewall extends Risorse{
	private final int MAX_FIREWALL=9;
	private int bonus_def;
	
	public Firewall(int livello_risorsa) {
		super(livello_risorsa);
		if (livello_risorsa>= super.MAX_LVL) {
			super.setLivello_risorsa(MAX_FIREWALL);
		}
		super.setNome("Firewall");
		this.effetto();
	}
	
	/**metodo che per ogni livello della risorsa specifica le sue caratteristiche
	* e il necessario per il livello successivo
	*/
	public void effetto() {
		
		
		switch ( super.getLivello_risorsa() ) {
		case 0: bonus_def=0;
				super.setE_richiesta(10);
				super.setTempo_richiesto(10);
				break;
		case 1: bonus_def=8;
				super.setTempo_richiesto(20);
				super.setE_richiesta(10);
				break;
		case 2:	bonus_def=12;
				super.setTempo_richiesto(20);
				super.setE_richiesta(10);
				break;
		case 3:	bonus_def=16;
				super.setTempo_richiesto(20);
				super.setE_richiesta(10);
		case 4: bonus_def=20;
				super.setE_richiesta(10);
				super.setTempo_richiesto(30);
				break;
		case 5: bonus_def=24;
				super.setE_richiesta(25);
				super.setTempo_richiesto(30);
				break;
		case 6: bonus_def=28;
				super.setE_richiesta(25);
				super.setTempo_richiesto(30);
				break;
		case 7: bonus_def=32;
				super.setTempo_richiesto(40);
				super.setE_richiesta(25);
				break;
		case 8: bonus_def=36;
				super.setTempo_richiesto(40);
				super.setE_richiesta(25);
				break;
		case 9: bonus_def=40;
				break;
		}
	}

//--------getter and setter-------//	
	/** stat1 usato per bonus_def
	 * @return bonus_def
	 */
	public int getStat1() {
		return bonus_def;
	}

	public void setStat1(int bonus_def) {
		this.bonus_def = bonus_def;
	}
	
	public int getMAX_LVL() {
		return MAX_FIREWALL;
	}
	
}
