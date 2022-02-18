package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public class Bot extends Giocatore{
	
	/**Permette di creare un oggetto Bot passando come parametro il suo nome*/
	public Bot(String nome) {
		super(nome);
	}
	/** Permette di creare un oggetto Bot il cui nome sarà "botX" dove X è il numero passato come parametro*/
	public Bot(int num) {

		super("bot"+String.format("%03d",num));
	}
	/** in fase di sviluppo*/
	public void logicaAttacco() {
	}
	/**Migliora le risorse del bot progressivamente al giocatore quindi la somma complessiva dei livelli 
	 * delle risorse del giocatore sarà pari la somma complessiva dei livelli delle risorse del bot.
	 * Le logiche di miglioramento possibili sono 3 e viene decisa casualmente.
	 * Prima logica: decide casualmente cosa potenziare (quindi può presentarsi una notevole differenza tra i livelli delle risorse)
	 * Seconda logica: l'ordine di potenziamento delle risorse è casuale ma i loro livelli saranno lineari (se
	 *  	cpu=1, ram=1, frw=1 deciderà casualmente cosa portare al livello 2 e il miglioramento successivo verrà 
	 *  	scelto solo tra le due risorse rimaste al livello 1 per poi potenziare infine l'ultima risorsa all'1 
	 *  	per poi ricominciare la logica allo stesso modo).
	 *  Terza logica: i miglioramenti del bot copiano i miglioramenti eseguiti dall'utente
	 *  Bisogna passare come parametri: valore int per indicare ogni quanti secondi il bot deve confrontare la 
	 *  somma complessiva dei livelli; Base bot; Base Utente*/
	public void logicaMiglioramento(int tempo, Base bot, Base utente) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final int logica=(int)(Math.random()*3);
		int statsTotalit=0;
		statsTotalit+=bot.getLvl_max_cpu();
		statsTotalit+=bot.getLvl_max_ram();
		statsTotalit+=bot.getLvl_max_firewall();
		final int statsTotali=statsTotalit;
		
		switch(logica) {
		case 0:{
	        	final Runnable runnable = new Runnable() {
	        	int statsUtenteTotali, statsBotTotali;
	            public void run() {
	                statsUtenteTotali=0;
	            	statsUtenteTotali+=utente.getLvl_cpu();
	                statsUtenteTotali+=utente.getLvl_ram();
	                statsUtenteTotali+=utente.getLvl_firewall();
	                statsBotTotali=0;
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if(statsUtenteTotali>statsBotTotali) {
	                	for(;statsBotTotali>statsUtenteTotali;statsBotTotali++) {
	                		bot.potenzia_risorsa("energia");
	                		int casualita=(int)(Math.random()*3);
	                		switch(casualita) {
	                		case 0:bot.potenzia_risorsa("ram");
	                		case 1:bot.potenzia_risorsa("cpu");
	                		case 2:bot.potenzia_risorsa("firewall");
	                		}
	                	}
	                }
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
			
		case 1:{
	        	final Runnable runnable = new Runnable() {
	        	int statsUtenteTotali, statsBotTotali;
	            public void run() {
	                statsUtenteTotali=0;
	            	statsUtenteTotali+=utente.getLvl_cpu();
	                statsUtenteTotali+=utente.getLvl_ram();
	                statsUtenteTotali+=utente.getLvl_firewall();
	                statsBotTotali=0;
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if(statsUtenteTotali>statsBotTotali) {
	                	for(;statsBotTotali>statsUtenteTotali;statsBotTotali++) {
	                		bot.potenzia_risorsa("energia");
	                		if(bot.getLvl_cpu()==bot.getLvl_ram()&&bot.getLvl_ram()==bot.getLvl_firewall()) {
	                			int casualita=(int)(Math.random()*3);
	                			switch(casualita) {
	                			case 0:bot.potenzia_risorsa("ram");
	                			case 1:bot.potenzia_risorsa("cpu");
	                			case 2:bot.potenzia_risorsa("firewall");
	                			}
	                		}
	                		else {
	                			if(bot.getLvl_cpu()==bot.getLvl_ram())
	                				if(bot.getLvl_ram()>bot.getLvl_firewall())
	                					bot.potenzia_risorsa("firewall");
	                				else {
	                					int casualita=(int)(Math.random()*2);
	                        			switch(casualita) {
	                        			case 0:bot.potenzia_risorsa("cpu");
	                        			case 1:bot.potenzia_risorsa("ram");
	                        			}
	                				}
	                			else {
	                				if(bot.getLvl_cpu()==bot.getLvl_firewall())
	                					if(bot.getLvl_cpu()>bot.getLvl_ram())
	                						bot.potenzia_risorsa("ram");
	                					else {
	                    					int casualita=(int)(Math.random()*2);
	                            			switch(casualita) {
	                            			case 0:bot.potenzia_risorsa("cpu");
	                            			case 1:bot.potenzia_risorsa("firewall");
	                            			}
	                    				}
	                				else {
	                					if(bot.getLvl_ram()>bot.getLvl_cpu())
	                						bot.potenzia_risorsa("cpu");
	                					else {
	                						int casualita=(int)(Math.random()*2);
	                            			switch(casualita) {
	                            			case 0:bot.potenzia_risorsa("ram");
	                            			case 1:bot.potenzia_risorsa("firewall");
	                            			}
	                					}
	                				}
	                			}
	                		}
	                	}
	                }
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
			
		case 2:{
	        	final Runnable runnable = new Runnable() {
	        	int statsBotTotali;
	            public void run() {
	            	statsBotTotali=0;
	            	bot.setLvl_cpu(utente.getLvl_cpu());
	                bot.setLvl_ram(utente.getLvl_ram());
	                bot.setLvl_firewall(utente.getLvl_firewall());
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
		}
	}
	/**Migliora le risorse del bot progressivamente al giocatore quindi la somma complessiva dei livelli 
	 * delle risorse del giocatore sarà pari la somma complessiva dei livelli delle risorse del bot.
	 * Le logiche di miglioramento possibili sono 3 e viene decisa in base al valore intero dato in input.
	 * Prima logica: decide casualmente cosa potenziare (quindi può presentarsi una notevole differenza tra i livelli delle risorse)
	 * Seconda logica: l'ordine di potenziamento delle risorse è casuale ma i loro livelli saranno lineari (se
	 *  	cpu=1, ram=1, frw=1 deciderà casualmente cosa portare al livello 2 e il miglioramento successivo verrà 
	 *  	scelto solo tra le due risorse rimaste al livello 1 per poi potenziare infine l'ultima risorsa all'1 
	 *  	per poi ricominciare la logica allo stesso modo).
	 *  Terza logica: i miglioramenti del bot copiano i miglioramenti eseguiti dall'utente
	 *  Bisogna passare come parametri: valore int per indicare ogni quanti secondi il bot deve confrontare la 
	 *  somma complessiva dei livelli; Base bot; Base Utente; valore intero per indicare la logica di miglioramento scelta*/
	public void logicaMiglioramento(int tempo, Base bot, Base utente, int logica) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		int statsTotalit=0;
		statsTotalit+=bot.getLvl_max_cpu();
		statsTotalit+=bot.getLvl_max_ram();
		statsTotalit+=bot.getLvl_max_firewall();
		final int statsTotali=statsTotalit;
		
		switch(logica) {
		case 1:{
	        	final Runnable runnable = new Runnable() {
	        	int statsUtenteTotali, statsBotTotali;
	            public void run() {
	                statsUtenteTotali=0;
	            	statsUtenteTotali+=utente.getLvl_cpu();
	                statsUtenteTotali+=utente.getLvl_ram();
	                statsUtenteTotali+=utente.getLvl_firewall();
	                statsBotTotali=0;
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if(statsUtenteTotali>statsBotTotali) {
	                	for(;statsBotTotali>statsUtenteTotali;statsBotTotali++) {
	                		bot.potenzia_risorsa("energia");
	                		int casualita=(int)(Math.random()*3);
	                		switch(casualita) {
	                		case 0:bot.potenzia_risorsa("ram");
	                		case 1:bot.potenzia_risorsa("cpu");
	                		case 2:bot.potenzia_risorsa("firewall");
	                		}
	                	}
	                }
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
			
		case 2:{
	        	final Runnable runnable = new Runnable() {
	        	int statsUtenteTotali, statsBotTotali;
	            public void run() {
	                statsUtenteTotali=0;
	            	statsUtenteTotali+=utente.getLvl_cpu();
	                statsUtenteTotali+=utente.getLvl_ram();
	                statsUtenteTotali+=utente.getLvl_firewall();
	                statsBotTotali=0;
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if(statsUtenteTotali>statsBotTotali) {
	                	for(;statsBotTotali>statsUtenteTotali;statsBotTotali++) {
	                		bot.potenzia_risorsa("energia");
	                		if(bot.getLvl_cpu()==bot.getLvl_ram()&&bot.getLvl_ram()==bot.getLvl_firewall()) {
	                			int casualita=(int)(Math.random()*3);
	                			switch(casualita) {
	                			case 0:bot.potenzia_risorsa("ram");
	                			case 1:bot.potenzia_risorsa("cpu");
	                			case 2:bot.potenzia_risorsa("firewall");
	                			}
	                		}
	                		else {
	                			if(bot.getLvl_cpu()==bot.getLvl_ram())
	                				if(bot.getLvl_ram()>bot.getLvl_firewall())
	                					bot.potenzia_risorsa("firewall");
	                				else {
	                					int casualita=(int)(Math.random()*2);
	                        			switch(casualita) {
	                        			case 0:bot.potenzia_risorsa("cpu");
	                        			case 1:bot.potenzia_risorsa("ram");
	                        			}
	                				}
	                			else {
	                				if(bot.getLvl_cpu()==bot.getLvl_firewall())
	                					if(bot.getLvl_cpu()>bot.getLvl_ram())
	                						bot.potenzia_risorsa("ram");
	                					else {
	                    					int casualita=(int)(Math.random()*2);
	                            			switch(casualita) {
	                            			case 0:bot.potenzia_risorsa("cpu");
	                            			case 1:bot.potenzia_risorsa("firewall");
	                            			}
	                    				}
	                				else {
	                					if(bot.getLvl_ram()>bot.getLvl_cpu())
	                						bot.potenzia_risorsa("cpu");
	                					else {
	                						int casualita=(int)(Math.random()*2);
	                            			switch(casualita) {
	                            			case 0:bot.potenzia_risorsa("ram");
	                            			case 1:bot.potenzia_risorsa("firewall");
	                            			}
	                					}
	                				}
	                			}
	                		}
	                	}
	                }
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
			
		case 3:{
	        	final Runnable runnable = new Runnable() {
	        	int statsBotTotali;
	            public void run() {
	            	statsBotTotali=0;
	            	bot.setLvl_cpu(utente.getLvl_cpu());
	                bot.setLvl_ram(utente.getLvl_ram());
	                bot.setLvl_firewall(utente.getLvl_firewall());
	                statsBotTotali+=bot.getLvl_cpu();
	                statsBotTotali+=bot.getLvl_ram();
	                statsBotTotali+=bot.getLvl_firewall();
	                if (statsBotTotali==statsTotali) {
	                    scheduler.shutdown();
	                	}
	            	}
	        	};
	        scheduler.scheduleAtFixedRate(runnable, 0, tempo, SECONDS);
			}
		}
	}
}

