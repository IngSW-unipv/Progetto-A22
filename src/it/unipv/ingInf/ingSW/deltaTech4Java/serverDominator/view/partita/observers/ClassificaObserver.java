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

	/**
	 * Permette agli altri oggetti di vedere gli update della classifica
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("eseguo observer classifica");
				classificaPane.dispone();
			
	}
}
