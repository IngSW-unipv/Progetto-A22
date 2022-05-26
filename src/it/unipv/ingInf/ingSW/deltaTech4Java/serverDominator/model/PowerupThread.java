package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PowerupThread {

	private Nodo nodo;
	private String risorsa;
	private int check;
	int i=0;
	public PowerupThread(Nodo nodo,  String risorsa) {
		
		this.nodo= nodo;
		this.risorsa= risorsa;
		check=0;
		
	}
	
	public void countdown() {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
        int tempo=checknodo();
            public void run() {
                tempo--;
                if (tempo < 0) {
                	if(check==0) {
            			potenzia_risorsaBase();	
            		} else 
            			potenzia_risorsaCloud();
                       	scheduler.shutdown();
            	}
                   
             }
            
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
	
	
	/**controllo se il nodo passato è base o cloud per
	 * calcolare il giusto tempo richiesto
	 */
	public int checknodo() {
		int i;
		
		if (nodo.getTipologia().equalsIgnoreCase("base")) {
			check=0;
			for(i=0;i<4;i++) {
				if(nodo.getRisorse()[i].getNome().equalsIgnoreCase(risorsa)) {
					return nodo.getRisorse()[i].getTempo_richiesto();
				}
			}
		} else
			check=1;
			return nodo.getRisorse()[3].getTempo_richiesto();
			
	}
	
	/** chiama il potenziamento del nodo base per qualsiasi
	 * risorsa passata
	 */
	public void potenzia_risorsaBase() {
		
		nodo.potenzia_risorsa(risorsa);
	}
	
	/** chiama il potenziamento del nodo cloud per la
	 * risorsa firewall
	 */
	public void potenzia_risorsaCloud() {
		
		nodo.potenzia_risorsa(risorsa);
	}


}

