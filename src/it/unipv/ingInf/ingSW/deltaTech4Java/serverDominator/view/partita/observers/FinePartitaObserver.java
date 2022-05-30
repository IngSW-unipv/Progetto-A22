package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.observers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.main.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LobbyView;
import javafx.application.Platform;
import javafx.scene.Scene;
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
	private LobbyView prePartitaStage;
	
	private Stage stageClassifica=null;
	
	private UserAccount userAccount;
	
	
	public FinePartitaObserver(MainDefinitivo mainDefinitivoModello, PartitaStage partitaStage, LobbyView prepartitaStage) {
		super();
		this.mainDefinitivoModello = mainDefinitivoModello;
		this.partitaStage = partitaStage;
		this.prePartitaStage = prepartitaStage;
		
	}
	
	public FinePartitaObserver(MainDefinitivo mainDefinitivoModello, PartitaStage partitaStage, LobbyView prepartitaStage,
			UserAccount userAccount) {
		super();
		this.mainDefinitivoModello = mainDefinitivoModello;
		this.partitaStage = partitaStage;
		this.prePartitaStage = prepartitaStage;
		this.userAccount =userAccount;
		
	}

	/**
	 * Permette agli altri oggetti di vedere se lo status della partita è finito
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		boolean finePartita=false;
		try {
			if((Integer)evt.getNewValue()==0)
				finePartita=true;
			System.out.println(finePartita);
		
		} catch (Exception e) {
			try {
				if((boolean)evt.getNewValue()==false)
					finePartita=true;
			} catch (Exception e2) {
				e2.printStackTrace();
				this.finePartita();
			}
		}

		if(finePartita) {
			this.finePartita();			
		}
		
	}
	
	/**
	 * Metodo che conclude la partita attuale
	 */
	public void finePartita() {
		partitaStage.getFineProgress().setRunning(false);

		if(stageClassifica==null&&userAccount!=null) {
			userAccount.setMny(partitaStage.getSelectedBase().getPossessore().getValuta());
			if(mainDefinitivoModello.getClassifica().getVincitore().getNome().equalsIgnoreCase(userAccount.getUsername())) {
				//System.err.println("secondo if");
				userAccount.setPunteggio(userAccount.getPunteggio()+100);
			}
			if(PersistenceFacade.getInstance().updateUserAccount(userAccount)) {
				UserAccount us=PersistenceFacade.getInstance().getUserAccountById(userAccount);
				if(us!=null) {
					userAccount=us;
					prePartitaStage.setUserAccount(userAccount);
				}
			}
				
		}
		partitaStage.getFineProgress().setRunning(false);
		try {
			this.mainDefinitivoModello.stopBot();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		Platform.runLater(() -> {
			this.partitaStage.close();
			this.prePartitaStage.show();
			this.createClassificaStage();
			this.stageClassifica.showAndWait();
			this.stageClassifica.setAlwaysOnTop(true);
		});
		
		
	}

	private void createClassificaStage() {
		if(this.stageClassifica==null) {
			this.stageClassifica=new Stage();
			this.stageClassifica.setMinHeight(100);
			this.stageClassifica.setMinWidth(100);
			this.stageClassifica.setScene(null);
			this.stageClassifica.setScene(new Scene(new ClassificaPane(mainDefinitivoModello.getClassifica()),200,200));
			/*this.stageClassifica.showAndWait();
			this.stageClassifica.setAlwaysOnTop(true);*/
		}
		
	}
	
	@Override
	public void drow() {
		partitaStage.getFineProgress().setRunning(false);
		finePartita();
		
	}
		
}
