package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes;

// HexType serve per definire le caratteristiche dei layer di ogni esagono


public abstract class HexType {
    
    abstract String getName();			// restituisce il nome
    abstract String getStrokeColor(); 	// restituisce il colore del bordo
    abstract String getFillColor();		// restituisce il colore riempimento
}

