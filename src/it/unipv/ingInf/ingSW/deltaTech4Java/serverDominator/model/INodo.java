package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * prima versione dell'interfaccia dei nodi base e cloud
 */
public interface INodo {
	
	public void potenzia_risorsa(String nome);
	public void crea_software(String nome, int quantita);
}
