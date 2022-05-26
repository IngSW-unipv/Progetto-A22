package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FinePartitaObserver implements PropertyChangeListener, IDrawable {
	
	/**
	 * facciata modello
	 */
	private MainDefinitivo mainDefinitivoModello;
	/**
	 * partita (stage da chiudere)
	 */
	private PartitaStage partitaStage;
	/**
	 * prepartita (stage da aprire)
	 */
	private Stage prePartitaStage;
	
	private Stage stageClassifica=null;
	
	public FinePartitaObserver(MainDefinitivo mainDefinitivoModello, PartitaStage partitaStage, Stage prepartitaStage,
			Pane classificaPane) {
		super();
		this.mainDefinitivoModello = mainDefinitivoModello;
		this.partitaStage = partitaStage;
		this.prePartitaStage = prepartitaStage;
		
	}


	/**
	 * Permette agli altri oggetti di vedere se lo status della partita è finito
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if((Integer)evt.getNewValue()==0) {
			partitaStage.getFineProgress().setRunning(false);
			this.finePartita();

		}
		
	}
	
	/**
	 * Metodo che conclude la partita attuale
	 */
	public void finePartita() {
		partitaStage.getFineProgress().setRunning(false);
		
		try {
			this.mainDefinitivoModello.stopBot();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		this.partitaStage.close();
		this.prePartitaStage.show();
		
		this.createClassificaStage();
		
		
		
	}

	private void createClassificaStage() {
		if(this.stageClassifica==null) {
			this.stageClassifica=new Stage();
			this.stageClassifica.setScene(null);
			this.stageClassifica.setScene(new Scene(new ClassificaPane(mainDefinitivoModello.getClassifica())));
			this.stageClassifica.showAndWait();
		}
		
	}
	
	@Override
	public void drow() {
		partitaStage.getFineProgress().setRunning(false);
		finePartita();
		
	}
		
}
