package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp;

import java.math.BigDecimal;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.NumberSpinner;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Development {

	public static int sX = 400;
	public static int sY = 400;	
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	private Nodo baseUtente;
	private NumberSpinner quantitaRootCrash;
	private NumberSpinner quantitaVirus;
	private NumberSpinner quantitaAntivirus;
	
	private Button buttonDevelop;
	Stage stage;
	public Development(Base baseUtente) {
		this.baseUtente=baseUtente;
	}
	public Development() {
		super();
	}
	
	public void development(Base baseUtente) {
		this.baseUtente=baseUtente;
		development();
		
	}
	public void development() {
		
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);

		VBox dev = new VBox();
		dev.setAlignment(Pos.TOP_CENTER);
		dev.setPadding(STANDARD_PADDING);
		
		HBox devTitle = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		Label devTitleL = new Label("Develop your Base!");
		devTitle.getChildren().add(devTitleL);
		
		
		HBox devG = new HBox();
		devG.setPadding(STANDARD_PADDING);
		devG.setAlignment(Pos.BASELINE_CENTER);
		
		GridPane layout = new GridPane();
		layout.setPadding(STANDARD_PADDING);
		layout.setVgap(5);
		layout.setHgap(5);

		Label rcQty = new Label(" Rootcrash\t");
		Label vrQty = new Label(" Virus\t");
		Label avQty = new Label(" Antivirus\t");
		quantitaRootCrash = new NumberSpinner(0, baseUtente.getSpazio_Ram());
		quantitaVirus = new NumberSpinner(0, baseUtente.getSpazio_Ram());
		quantitaAntivirus = new NumberSpinner(0, baseUtente.getSpazio_Ram());
		quantitaRootCrash.getNumberField().setDisable(true);
		quantitaVirus.getNumberField().setDisable(true);
		quantitaAntivirus.getNumberField().setDisable(true);
		int max=baseUtente.getSpazio_Ram();
		quantitaRootCrash.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaRootCrash.getStepWitdhProperty().get().intValue();
            	if((quantitaRootCrash.getNumber().intValue()+aumento)<=max) {
            		quantitaRootCrash.increment();
                    ae.consume();
            	}else {
            		int maxSelection=max-quantitaVirus.getNumber().intValue()-quantitaAntivirus.getNumber().intValue();
            		maxSelection=maxSelection>0?maxSelection:0;
            		quantitaRootCrash.setNumber(BigDecimal.valueOf(maxSelection));
            	}
        		quantitaVirus.setNumber(BigDecimal.valueOf(0));
        		quantitaAntivirus.setNumber(BigDecimal.valueOf(0));
            }
        });
        
		quantitaVirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaVirus.getStepWitdhProperty().get().intValue();
            	if((quantitaVirus.getNumber().intValue()+aumento)<=max) {
            		quantitaVirus.increment();
            		ae.consume();
            	}else {
            		int maxSelection=max;
            		maxSelection=maxSelection>0?maxSelection:0;
            		quantitaVirus.setNumber(BigDecimal.valueOf(maxSelection));
            	}
            	quantitaRootCrash.setNumber(BigDecimal.valueOf(0));
        		quantitaAntivirus.setNumber(BigDecimal.valueOf(0));
            }
        });
		
		quantitaAntivirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaAntivirus.getStepWitdhProperty().get().intValue();
            	if((quantitaAntivirus.getNumber().intValue()+aumento)<=max) {
            		quantitaAntivirus.increment();
                    ae.consume();
            	}else {
            		int maxSelection=max;
            		maxSelection=maxSelection>0?maxSelection:0;
            		quantitaAntivirus.setNumber(BigDecimal.valueOf(maxSelection));
            	}
        		quantitaRootCrash.setNumber(BigDecimal.valueOf(0));
        		quantitaVirus.setNumber(BigDecimal.valueOf(0));
            }
        });
		/*NumberSpinner listeners per max quantita*/ 
		
		HBox devB = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		buttonDevelop = ComponentCreator.getIstance().createButton("Develop!", Pos.BASELINE_CENTER);
			
		buttonDevelop.setOnAction(e -> {
			stage.close();
		});
		
		layout.add(avQty, 0	, 0);
		layout.add(quantitaAntivirus, 1, 0);
		layout.add(vrQty, 0, 1);
		layout.add(quantitaVirus, 1, 1);
		layout.add(rcQty, 0, 2);
		layout.add(quantitaRootCrash, 1, 2);
		
		devG.getChildren().add(layout);
		devB.getChildren().add(buttonDevelop);
		dev.getChildren().addAll(devTitle, devG, devB);
		
		Scene scene = new Scene(dev, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Development");
		stage.setScene(scene);
		stage.showAndWait();
	}

	public Nodo getBaseUtente() {
		return baseUtente;
	}

	public void setBaseUtente(Base baseUtente) {
		this.baseUtente = baseUtente;
	}

	public NumberSpinner getQuantitaRootCrash() {
		return quantitaRootCrash;
	}

	public NumberSpinner getQuantitaVirus() {
		return quantitaVirus;
	}

	public NumberSpinner getQuantitaAntivirus() {
		return quantitaAntivirus;
	}

	public Button getDevelopButton() {
		return buttonDevelop;
	}

	public void setButton(Button button) {
		this.buttonDevelop = button;
	}

	public Button getButtonDevelop() {
		return buttonDevelop;
	}

	public void setButtonDevelop(Button buttonDevelop) {
		this.buttonDevelop = buttonDevelop;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setQuantitaRootCrash(NumberSpinner quantitaRootCrash) {
		this.quantitaRootCrash = quantitaRootCrash;
	}

	public void setQuantitaVirus(NumberSpinner quantitaVirus) {
		this.quantitaVirus = quantitaVirus;
	}

	public void setQuantitaAntivirus(NumberSpinner quantitaAntivirus) {
		this.quantitaAntivirus = quantitaAntivirus;
	}

}
