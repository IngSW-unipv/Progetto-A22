package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

/**
 * @author Luca Casto 
 * @versione 1.0
 * @since 1.0
 * estensione della classe astratta software.
 * la classe virus � pensata come unit� offensiva del gioco
 * server dominator. Il valore offensivo � indicato come val_atk ed � 
 * uguale al valore attribuito al livello.
 * Invece il valore difensivo val_def � 0.
 */
public class Virus extends Software{
	private int val_def;
	private int val_atk;
		
	/**
	 * costruttore che crea un oggetto Virus con un livello e una quantita pari a quelle passate come parametro
	 * @param livello
	 * livello Virus
	 * @param quantita
	 * quantita Virus
	 */
	public Virus (int livello, int quantita) {
		super(livello);
		super.setMax_lvl(5);
		super.setNome("Virus");
		super.setTemp_richiesto(2);
		val_def=0;
		val_atk= super.getLivello();
		super.setQuantita(quantita);
		
	}
//---------------getter and setter------------//
	/**
	 * returna il valore della difesa del Virus
	 * @return
	 * valore difesa
	 */
	public int getVal_def() {
		return val_def;
	}
	/**
	 * setta il valore dell' attacco del Virus
	 * @param
	 * valore attacco
	 */
	public void setVal_def(int val_def) {
		this.val_def = val_def;
	}
	/**
	 * returna il valore dell'attacco del Virus
	 * @return
	 * valore attacco
	 */
	public int getVal_atk() {
		return val_atk;
	}

	
}
