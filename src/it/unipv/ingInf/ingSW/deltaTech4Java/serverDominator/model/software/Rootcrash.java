package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

/**
 * @author Luca Casto 
 * @versione 1.0
 * @since 1.0
 * estensione della classe astratta software.
 * la classe Rootcrash � pensata come unit� speciale del gioco
 * server dominator, per ridurre il livello del firewall avversario
 * Il valore offensivo � indicato come val_atk � 3 volte
 * il valore del livello, questo valore in fase di battaglia sar� 
 * confrontato con il livello del firewall del bersaglio
 * Invece il valore difensivo val_def � 0.
 */
public class Rootcrash extends Software {
	private int val_def;
	private int val_atk;
	/**
	 * costruttore che crea un oggetto Rootcrash con un livello e una quantita pari a quelle passate come parametro
	 * @param livello
	 * livello Rootcrash
	 * @param quantita
	 * quantita Rootcrash
	 */
	public Rootcrash (int livello, int quantita) {
		super(livello);
		super.setMax_lvl(3);
		super.setNome("Rootcrash");
		if(super.getLivello()> super.getMax_lvl() ) {
			super.setLivello(3);
		}
		val_atk= 3*super.getLivello();
		val_def= 0;
		super.setTemp_richiesto(4);
		super.setQuantita(quantita);
	}
//--------getter and setter-------//	
	/**
	 * returna il valore della difesa del Rootcrash
	 * @return
	 * valore difesa
	 */
	public int getVal_def() {
		return val_def;
	}
	/**
	 * returna il valore dell'attacco del Rootcrash
	 * @return
	 * valore attacco
	 */
	public int getVal_atk() {
		return val_atk;
	}
	/**
	 * setta il valore dell' attacco del Rootcrash
	 * @param
	 * valore attacco
	 */
	public void setVal_atk(int val_atk) {
		this.val_atk = val_atk;
	}
	
	
}
