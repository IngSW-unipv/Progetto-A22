package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;

public class HexData  { // definisce le caratteristiche dell'esagono
			
	public Nodo nodo;

	public HexData(Nodo nodo) {
		super();
		this.nodo = nodo;
	}

	public boolean isBase() {
    	if(nodo instanceof Base) {
    		return true;
    	}
    	return false;
	}


}
