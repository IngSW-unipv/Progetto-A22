package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinePartitaObserver implements PropertyChangeListener, IDrawable {
	
	/**
	 * facciata modello
	 */
	private MainDefinitivo mainDefinitivoModello;
	/**
	 * partita (stage da chiudere)
	 */
	private Stage partitaStage;
	/**
	 * prepartita (stage da aprire)
	 */
	private Stage prePartitaStage;
	/**
	 * classifica da stampare
	 */
	private Pane classificaPane;
	


	public FinePartitaObserver(MainDefinitivo mainDefinitivoModello, Stage partitaStage, Stage prepartitaStage,
			Pane classificaPane) {
		super();
		this.mainDefinitivoModello = mainDefinitivoModello;
		this.partitaStage = partitaStage;
		this.prePartitaStage = prepartitaStage;
		this.classificaPane = classificaPane;
	}


	/**
	 * Permette agli altri oggetti di vedere se lo status della partita è finito
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if((Integer)evt.getNewValue()==0)
			this.finePartita();
		
	}
	
	/**
	 * Metodo che conclude la partita attuale
	 */
	public void finePartita() {
		try {
			this.mainDefinitivoModello.stopBot();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.partitaStage.close();
		this.prePartitaStage.show();
		Stage stageClassifica=new Stage();
		stageClassifica.initModality(Modality.APPLICATION_MODAL);
		Scene scena = new Scene(this.classificaPane);
		stageClassifica.setScene(scena);
		stageClassifica.show();
		
	}



	@Override
	public void drow() {
		finePartita();
		
	}
		
}
