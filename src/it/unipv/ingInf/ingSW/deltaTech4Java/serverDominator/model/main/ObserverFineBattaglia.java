package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public class ObserverFineBattaglia implements PropertyChangeListener {
	Giocatore attaccante;
	int x,y;
	MainDefinitivo main;
	
	public ObserverFineBattaglia(Giocatore attaccante, int x, int y, MainDefinitivo main) {
		this.attaccante=attaccante;
		this.x=x;
		this.y=y;
		this.main=main;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("observer attivo...");
		main.fineBattaglia(attaccante, x, y);
		
	}

}
