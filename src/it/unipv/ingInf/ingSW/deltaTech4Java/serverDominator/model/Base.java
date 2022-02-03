package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

public class Base extends Nodo{
	
	
	public Base (int x, int y, Giocatore possessore) {
		super(x, y);
		super.setDist_base(1);
		super.setPossessore(possessore);
		
	}
	
}
