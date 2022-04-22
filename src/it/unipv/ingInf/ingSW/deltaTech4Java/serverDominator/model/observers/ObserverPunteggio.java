package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public class ObserverPunteggio implements PropertyChangeListener {
	private UserAccount osOsservato;
	
	
	public ObserverPunteggio(UserAccount osOsservato) {
		super();
		this.osOsservato = osOsservato;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getSource().getClass().isAssignableFrom(Giocatore.class)) {
			PersistenceFacade.getInstance().updateUserAccountPunteggio(osOsservato, (int)evt.getNewValue());
			osOsservato=PersistenceFacade.getInstance().getUserAccountById(osOsservato);
			Giocatore g=(Giocatore)evt.getSource();
			g.aggiornaPunteggio(osOsservato.getMny()-g.getValuta());
		}
		
	}
}
