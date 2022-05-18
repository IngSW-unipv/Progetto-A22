package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;

public class ClassificaObserver implements PropertyChangeListener {
	private ClassificaPane classificaPane;
	
	public ClassificaObserver(ClassificaPane classificaPane) {
		super();
		this.classificaPane=classificaPane;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		classificaPane.dispone();
	}
}