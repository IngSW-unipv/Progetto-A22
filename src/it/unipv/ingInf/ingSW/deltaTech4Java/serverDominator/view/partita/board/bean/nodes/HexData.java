package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;

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
    	if(Base.class.isAssignableFrom(nodo.getClass())) {
    		return true;
    	}
    	return false;
	}


}
