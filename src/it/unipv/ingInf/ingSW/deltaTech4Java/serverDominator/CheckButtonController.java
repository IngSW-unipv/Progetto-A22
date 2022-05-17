package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import javafx.scene.Node;

public class CheckButtonController {

	public static boolean isOwner(Nodo nodoA, Nodo nodoB) { //controlla possessore
		
		if(nodoA.getPossessore().getNome().equals(nodoB.getPossessore().getNome())) {
			return  true;
		}
		
		return false;
	}
	
	
}
