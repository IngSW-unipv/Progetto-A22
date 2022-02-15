package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Timer {
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
	
	public void countdown(int temp) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
        int tempo=temp;
            public void run() {
                System.out.println(tempo);
                tempo--;

                if (tempo < 0) {
                    System.out.println("Tempo scaduto");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
	}
}
