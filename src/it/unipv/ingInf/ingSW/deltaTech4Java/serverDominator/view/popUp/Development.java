package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.NumberSpinner;
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
	private Base baseUtente;
	
	public Development(Base baseUtente) {
		this.baseUtente=baseUtente;
	}
	
	public void development(Base baseUtente) {
		
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);

		VBox dev = new VBox();
		dev.setAlignment(Pos.TOP_CENTER);
		dev.setPadding(STANDARD_PADDING);
		
		HBox devTitle = new HBox();
		devTitle.setPadding(STANDARD_PADDING);
		Label devTitleL = new Label("Develop your Base!");
		devTitle.getChildren().add(devTitleL);
		
		
		HBox devG = new HBox();
		devG.setPadding(STANDARD_PADDING);
		devG.setAlignment(Pos.BASELINE_LEFT);
		
		GridPane layout = new GridPane();
		layout.setPadding(STANDARD_PADDING);
		layout.setVgap(5);
		layout.setHgap(5);

		Label rcQty = new Label("Own " + String.valueOf(baseUtente.getQnt_rootcrash()) + " rootcrash, dev ");
		Label vrQty = new Label("Own " + String.valueOf(baseUtente.getQnt_virus()) + " virus, dev ");
		Label avQty = new Label("Own " + String.valueOf(baseUtente.getQnt_antivirus()) + " antivirus, dev ");
		NumberSpinner buyRcNs = new NumberSpinner();
		NumberSpinner buyVrNs = new NumberSpinner();
		NumberSpinner buyAvNs = new NumberSpinner();
		
		HBox devB = new HBox();
		Button button = new Button("Develop!");
		button.setPrefSize(200, 20);
		button.setAlignment(Pos.BASELINE_CENTER);
		button.setPadding(STANDARD_PADDING);
		button.getStyleClass().add("redbutton");
		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		
		layout.add(avQty, 0	, 0);
		layout.add(buyAvNs, 1, 0);
		layout.add(vrQty, 0, 1);
		layout.add(buyVrNs, 1, 1);
		layout.add(rcQty, 0, 2);
		layout.add(buyRcNs, 1, 2);
		
		devG.getChildren().add(layout);
		devB.getChildren().add(button);
		dev.getChildren().addAll(devTitle, devG, devB);
		
		Scene scene = new Scene(dev, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Development");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}

}
