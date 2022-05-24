package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Matteo c
 * @version 1.0
 * @since 1.0
 */
public class Timer {
	/**
	 * fa avviare un timer che blocca la funzione chiamante
	 * @param tempo
	 * per quanto tempo si vuole bloccare la funzione chiamante
	 * @return
	 * true=e' passato il tempo richiesto, false=errore, non è passato il tempo richiesto
	 */
	public boolean timer(int tempo){
		if(tempo>0) {	
			try {
				TimeUnit.SECONDS.sleep(tempo);
				return true;
			} catch (InterruptedException ie) {
				System.out.println("errore timer");
				return false;
			}
		}
		System.out.println("errore timer");
		return false;
	}
	/**
	 * stampa un countodown per il tempo richiesto
	 * @param temp
	 */
	public void countdown(int temp) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
        int tempo=temp;
            public void run() {
                System.out.println(tempo);
                tempo--;

                if (tempo < 0) {
                	/*stampa tempo scaduto usata per test*/
                    System.out.println("Tempo scaduto");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
}
