package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione risorsa energia, serve per potenziare le altre risorse
 * la costante incrementatore_en è il valore di energia che ogni livello
 * da come aggiunta.
 * NB: usato parametro della superclasse Risorse stat1 per restituire
 * il valore di energia disponibile.
 */

public class Energia extends Risorse{
	private final int INCREMENTATORE_EN= 75;
	private int e_disponibile;
	
	public Energia(int livello_risorsa) {
		super(livello_risorsa);
		if (livello_risorsa>= super.MAX_LVL) {
			super.setLivello_risorsa(10);
		}
		super.setNome("Energia");
		e_disponibile= INCREMENTATORE_EN *super.getLivello_risorsa();
		super.setE_richiesta(0);
		super.setTempo_richiesto(0);
	}
/** aggiorna il valore di energia utilizzabile*/
	
	public void effetto() {
		e_disponibile= e_disponibile +INCREMENTATORE_EN;
		//per test
		//System.out.println("il valore di energia è:"+e_disponibile);
	}

/** getter and setter sono ovverrides dei metodi chiamati dalla superclasse
 * Risorse, in questa classe, stat1 è l'energia disponibile.
 */
	
	public int getStat1() {
		return e_disponibile;
	}

	public void setStat1(int e_disponibile) {
		this.e_disponibile = e_disponibile;
	}

}
