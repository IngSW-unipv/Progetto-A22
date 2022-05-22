package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;

public class HexData  { // 
			
	public Nodo nodo;

	public HexData(Nodo nodo) {
		super();
		this.nodo = nodo;
	}

	/**
	 * definisce le caratteristiche dell'esagono
	 * @return
	 */
	public boolean isBase() {
    	if(nodo instanceof Base) {
    		return true;
    	}
    	return false;
	}


}
