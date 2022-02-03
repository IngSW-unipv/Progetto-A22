package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione dell'interfaccia dei nodi base e cloud
 */
public interface INodo {
	
	public boolean conquista();
	public void potenzia_risorsa();
	public boolean attaccabile();
	public void crea_software();
}
