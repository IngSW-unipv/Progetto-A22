package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

/**
 * @author Luca Casto 
 * v1.0
 * estensione della classe astratta software.
 * la classe virus è pensata come unità offensiva del gioco
 * server dominator. Il valore offensivo è indicato come val_atk ed è 
 * uguale al valore attribuito al livello.
 * Invece il valore difensivo val_def è 0.
 */
public class Virus extends Software{
	private int val_def;
	private int val_atk;
		
	public Virus (int livello, int quantità) {
		super(livello);
		super.setMax_lvl(5);
		super.setNome("Virus");
		super.setTemp_richiesto(20);
		val_def=0;
		val_atk= super.getLivello();
		super.setQuantità(quantità);
		
	}

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
