package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;

public class BotObserver implements PropertyChangeListener {
	private MainDefinitivo mainDefinitivo;
	private PartitaStage partitaStage;
	
	public BotObserver(MainDefinitivo mainDefinitivo, PartitaStage partitaStage) {
		super();
		this.mainDefinitivo = mainDefinitivo;
		this.partitaStage = partitaStage;
	}

	/**
	 * Permette agli altri oggetti di vedere gli update dello stato del bot
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(Coordinate.class.isAssignableFrom(evt.getNewValue().getClass())&& Coordinate.class.isAssignableFrom(evt.getOldValue().getClass())) {
			Coordinate attaccante=(Coordinate)evt.getNewValue();
			Coordinate difensore=(Coordinate)evt.getOldValue();;
			if(mainDefinitivo.getTabellone().getNodo(difensore.getX(), difensore.getY()).getPossessore().getNome().equals(mainDefinitivo.getGiocatori()[0].getNome())) {
				int tempoAttaco=mainDefinitivo.battlecheck(mainDefinitivo.getTabellone().getNodo(
						attaccante.getX(), attaccante.getY()).getPossessore(),difensore.getX(), difensore.getY(), 1, 0);
				partitaStage.addDifesa(mainDefinitivo.getTabellone().getNodo(attaccante.getX(), attaccante.getY()).getPossessore().getNome()+" ti sta attaccando"
						+"("+(difensore.getX()-difensore.getY()/2)+","+ difensore.getY()+")"
						, tempoAttaco);
			};
				
				
		}

	}

}
