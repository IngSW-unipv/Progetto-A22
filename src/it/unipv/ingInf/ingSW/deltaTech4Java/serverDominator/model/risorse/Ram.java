package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione risorsa ram, serve per aumentare il numero massimo 
 * delle classi software, il numero massimo dei software viene aggiornato
 * in funzione del livello di questa risorsa, passando una variabile 
 * intera, alla classe nodo, la quale interagisce veramente con i software.
 */
public class Ram extends Risorse{
	private int max_software;
	/**
	 * costruttore che crea un oggetto Ram con un livello pari a quello passato come parametro
	 * @param livello_risorsa
	 * livello della Ram
	 */
	public Ram (int livello_risorsa) {
		super(livello_risorsa);
		if (livello_risorsa>= super.MAX_LVL) {
			super.setLivello_risorsa(10);
		}
		super.setNome("Ram");
		this.effetto();
	}
	
	/**aggiorna, in funzione del livello risorsa, le caratteristiche
	* che vengono sbloccate e i requisiti per il livello successivo
	*/
	public void effetto() {
		
		
		switch ( super.getLivello_risorsa() ) {
		case 0: max_software=0;
				super.setE_richiesta(20);
				super.setTempo_richiesto(5);
				break;
		case 1: max_software=10;
				super.setTempo_richiesto(10);
				super.setE_richiesta(20);
				break;
		case 2:	max_software=15;
				super.setTempo_richiesto(10);
				super.setE_richiesta(20);
				break;
		case 3:	max_software=20;
				super.setTempo_richiesto(10);
				super.setE_richiesta(20);
		case 4: max_software=25;
				super.setE_richiesta(20);
				super.setTempo_richiesto(15);
				break;
		case 5: max_software=30;
				super.setE_richiesta(30);
				super.setTempo_richiesto(15);
				break;
		case 6: max_software=35;
				super.setE_richiesta(30);
				super.setTempo_richiesto(15);
				break;
		case 7: max_software=40;
				super.setTempo_richiesto(20);
				super.setE_richiesta(30);
				break;
		case 8: max_software=45;
				super.setTempo_richiesto(20);
				super.setE_richiesta(30);
				break;
		case 9: max_software=50;
				super.setE_richiesta(40);
				super.setTempo_richiesto(30);
				break;
		case 10:max_software=60;
				break;
		}
	}
	
//--------getter and setter-------//
	/**stat1 usato per max_software
	 * @return max_software
	 */
	public int getStat1() {
		return max_software;
	}
	
}
