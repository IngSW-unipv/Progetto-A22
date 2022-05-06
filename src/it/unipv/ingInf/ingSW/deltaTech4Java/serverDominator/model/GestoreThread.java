package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;

public class GestoreThread {
	
	//prova
	public void avvioThread(Bot bot, Base baseUtente, Base baseBot){
		//bot.aggiornaBasi(baseUtente, baseBot);
		Thread t1 = new Thread(bot);
		t1.start();
	}
}
