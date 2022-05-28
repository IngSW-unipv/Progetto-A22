package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione risorsa energia, serve per potenziare le altre risorse
 * la costante incrementatore_en � il valore di energia che ogni livello
 * da come aggiunta.
 */
public class Energia extends Risorse{
	private final int INCREMENTATORE_EN= 75;
	private int e_disponibile;
	/**
	 * costruttore che crea un oggetto Energia con un livello pari a quello passato come parametro
	 * @param livello_risorsa
	 * livello dell'energia
	 */
	public Energia(int livello_risorsa) {
		super(livello_risorsa);
		if (livello_risorsa>= super.MAX_LVL) {
			super.setLivello_risorsa(10);
		}
		super.setNome("Energia");
		e_disponibile= INCREMENTATORE_EN *super.getLivello_risorsa();
		super.setE_richiesta(0);
		super.setTempo_richiesto(3);
	}
	
	/** aggiorna il valore di energia utilizzabile*/
	public void effetto() {
		
		e_disponibile= e_disponibile +INCREMENTATORE_EN;
		//per test
		//System.out.println("il valore di energia �:"+e_disponibile);
	}

//--------getter and setter-------// 
	
	/**stat1 usato per e_disponibile
	 * @return e_disponibile
	 */
	public int getStat1() {
		return e_disponibile;
	}
	/**imposta stat1 che viene usato per e_disponibile
	 * @param e_disponibile
	 * valore dell'energia
	 */
	public void setStat1(int e_disponibile) {
		this.e_disponibile = e_disponibile;
	}

}
