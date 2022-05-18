package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import javafx.scene.Node;

public class CheckButtonController {

	/**
	 * 
	 * @param nodoA
	 * @param nodoB
	 * @return
	 * Metodo che controlla chi è il possessore del Nodo selezionato
	 */
	public static boolean isOwner(Nodo nodoA, Nodo nodoB) {
		
		if(nodoA.getPossessore().getNome().equals(nodoB.getPossessore().getNome())) {
			return  true;
		}
		
		return false;
	}
	
	
}
