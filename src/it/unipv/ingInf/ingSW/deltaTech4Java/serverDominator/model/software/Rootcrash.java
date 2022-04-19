package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

/**
 * @author Luca Casto 
 * v1.0
 * estensione della classe astratta software.
 * la classe Rootcrash è pensata come unità speciale del gioco
 * server dominator, per ridurre il livello del firewall avversario
 * Il valore offensivo è indicato come val_atk è 3 volte
 * il valore del livello, questo valore in fase di battaglia sarà 
 * confrontato con il livello del firewall del bersaglio
 * Invece il valore difensivo val_def è 0.
 */
public class Rootcrash extends Software {
	private int val_def;
	private int val_atk;
	
	public Rootcrash (int livello, int quantita) {
		super(livello);
		super.setMax_lvl(3);
		super.setNome("Rootcrash");
		if(super.getLivello()> super.getMax_lvl() ) {
			super.setLivello(3);
		}
		val_atk= 3*super.getLivello();
		val_def= 0;
		super.setTemp_richiesto(40);
		super.setQuantita(quantita);
	}
/**getter and setter*/
	
	public int getVal_def() {
		return val_def;
	}

	public int getVal_atk() {
		return val_atk;
	}

	public void setVal_atk(int val_atk) {
		this.val_atk = val_atk;
	}
	
	
}
