package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.thread;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;

public class CreazioneThread {

	private Nodo nodo;
	private String nome;
	private int quantita;
	private int check;
	
	public CreazioneThread(Nodo nodo, String nome, int quantita) {
		this.nodo=nodo;
		this.nome=nome;
		this.quantita=quantita;
		
		
		check=0;
		
	}
	
	public void countdown() {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
        int tempo=checknodo();
            public void run() {
                tempo=-1;
                if (tempo < 0) {
                	if(check==0) {
            			crea_softwareBase();	
            		} else 
            			crea_softwareCloud();
                       	scheduler.shutdown();
            	}
                   
             }
            
        };
        scheduler.scheduleAtFixedRate(runnable, checknodo(), 1, SECONDS);
	}
	
	/**controllo se il nodo passato è base o cloud per
	 * calcolare il giusto tempo richiesto
	 */
	public int checknodo() {
		int i;
		
		if (nodo.getTipologia().equalsIgnoreCase("base")) {
			check=0;
			for(i=0;i<3;i++) {
				if(nodo.getStats_software_creati()[i].getNome().equalsIgnoreCase(nome)) {
					return (nodo.getStats_software_creati()[i].getTemp_richiesto() * quantita);
				}
			}
		} else
			check=1;
			return (nodo.getStats_software_creati()[0].getTemp_richiesto() * quantita);
			
	}
	
	/**chiama il metodo per la creazione del software 
	 * di un  nodo base
	 */
	public void crea_softwareBase() {
		
		nodo.crea_software(nome, quantita);
	}
	
	/**chiama il metodo per la creazione del software
	 * di un nodo cloud
	 */
	public void crea_softwareCloud() {
		
		nodo.crea_software(nome, quantita);
	}

}
