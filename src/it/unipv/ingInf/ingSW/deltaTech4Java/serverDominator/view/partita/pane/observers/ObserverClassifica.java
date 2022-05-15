package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;

public class ObserverClassifica implements PropertyChangeListener {
	private ClassificaPane classificaPane;
	
	public ObserverClassifica(ClassificaPane classificaPane) {
		super();
		this.classificaPane=classificaPane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		classificaPane.dispone();
	}
}
