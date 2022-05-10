package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;
/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * estensione della classe astratta software.
 * la classe antivirus è pensata come unità difensiva del gioco
 * server dominator. Il valore difensivo è indicato come val_def ed è 
 * uguale al valore attribuito al livello.
 * Invece il valore di attacco val_atk è 0.
 */
public class Antivirus extends Software{
	private int val_atk;
	private int val_def;
	
	public Antivirus(int livello, int quantita) {
		super(livello);
		val_atk= 0;
		val_def= super.getLivello();
		super.setMax_lvl(5);
		super.setNome("Antivirus");
		super.setTemp_richiesto(20);
		super.setQuantita(quantita);
	}
	
//--------getter and setter-------//
	
	public int getVal_def() {
		return val_def;
	}
	public void setVal_def(int val_def) {
		this.val_def = val_def;
	}
	public int getVal_atk() {
		return val_atk;
	}
		
}
