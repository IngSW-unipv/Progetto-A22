package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;
/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * estensione della classe astratta software.
 * la classe antivirus � pensata come unit� difensiva del gioco
 * server dominator. Il valore difensivo � indicato come val_def ed � 
 * uguale al valore attribuito al livello.
 * Invece il valore di attacco val_atk � 0.
 */
public class Antivirus extends Software{
	private int val_atk;
	private int val_def;
	/**
	 * costruttore che crea un oggetto Antivirus con un livello e una quantita pari a quelle passate come parametro
	 * @param livello
	 * livello dell'Antivirus
	 * @param quantita
	 * quantita dell'Antivirus
	 */
	public Antivirus(int livello, int quantita) {
		super(livello);
		val_atk= 0;
		val_def= super.getLivello();
		super.setMax_lvl(5);
		super.setNome("Antivirus");
		super.setTemp_richiesto(2);
		super.setQuantita(quantita);
	}
	
//--------getter and setter-------//
	/**
	 * returna il valore della difesa dell'Antivirus
	 * @return
	 * valore difesa
	 */
	public int getVal_def() {
		return val_def;
	}
	/**
	 * setta il valore della difesa dell'Antivirus
	 * @param
	 * valore difesa
	 */
	public void setVal_def(int val_def) {
		this.val_def = val_def;
	}
	/**
	 * returna il valore dell'attacco dell'Antivirus
	 * @return
	 * valore attacco
	 */
	public int getVal_atk() {
		return val_atk;
	}
		
}
