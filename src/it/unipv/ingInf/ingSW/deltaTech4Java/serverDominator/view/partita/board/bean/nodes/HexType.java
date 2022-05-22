package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes;

// HexType serve per definire le caratteristiche dei layer di ogni esagono


public abstract class HexType {
    
	/**
	 * restituisce il nome
	 * @return
	 */
    abstract String getName();		
    
    /**
     *  restituisce il colore del bordo
     * @return
     */
    abstract String getStrokeColor(); 
    
    /**
     * restituisce il colore riempimento
     * @return
     */
    abstract String getFillColor();	 
}

