package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class NodoObserver implements PropertyChangeListener {
	private PartitaStage partitaStage;

	public NodoObserver(PartitaStage partitaStage) {
		super();
		this.partitaStage = partitaStage;
	}

	/**
	 * Permette agli altri oggetti di vedere se ci sono stati aggiornamenti al nodo scelto
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		partitaStage.drowMappa();
	}

}
