package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sreverDominator.view.Main;

public class PrebattagliaView {
	
	private Stage stagePrebattaglia;
	public Button easyGame = new Button("EASY");
	public Button mediumGame = new Button("MEDIUM");
	public Button hardGame = new Button("HARD");
	
	public Label gameTitle = new Label();
	public Label easyDescription = new Label("Bots fairly easy to beat");
	public Label mediumDescription = new Label("Bots are trouble");
	public Label hardDescription = new Label("Rip and tear... until it's done");
	private Main mainView;
	private MainDefinitivo mainModello;
	
	public Button getEasyGame() {
		return easyGame;
	}
	
	public Button getMediumGame() {
		return mediumGame;
	}
	
	public Button getHardGame() {
		return hardGame;
	}
	
	public Label getEasyDescription() {
		return easyDescription;
	}
	
	public Label getMediumDescription() {
		return mediumDescription;
	}
	
	public Label getHardDescription() {
		return hardDescription;
	}
	
	private Stage getStagePreBattaglia() {
		return stagePrebattaglia;
	}
	
	
	public PrebattagliaView(PrebattagliaView preBattagliaView, Main mainView, MainDefinitivo mainModello) {
		istance(new PrebattagliaView(), mainView, mainModello);
	}
	
	public PrebattagliaView() {
		super();
	}
	
	private void istance(PrebattagliaView prebattagliaView, Main mainView, MainDefinitivo mainModello) {
		// TODO Auto-generated method stub
		this.mainView = mainView;
		
		
		this.mainModello = mainModello;
		
		//to define
	}

	//UI Design
	public void prebattagliaView() {
		Label gameTitle = ComponentCreator.getIstance().lableCreator(Pos.TOP_CENTER);
		setLabelText(gameTitle, "SERVER DOMINATOR : choose difficulty!");
		
		stagePrebattaglia = new Stage();
		stagePrebattaglia.initModality(Modality.APPLICATION_MODAL);
		
		VBox vPre = new VBox();
		vPre.setPrefWidth(800);
        
		//sezione Easy di scelta
        VBox easyPreBtl = new VBox();
        easyPreBtl.setPrefHeight(250);
		easyPreBtl.setPrefWidth(500);
		easyPreBtl.setPadding(new Insets(15));
		GridPane mPreBtl = new GridPane();
		mPreBtl.setVgap(5);
		mPreBtl.setHgap(5);
		easyPreBtl.getChildren().addAll(easyGame, easyDescription);
		
		//sezione Medium di scelta
		VBox mediumPreBtl = new VBox();
        easyPreBtl.setPrefHeight(250);
		easyPreBtl.setPrefWidth(500);
		easyPreBtl.setPadding(new Insets(15));
		GridPane nPreBtl = new GridPane();
		nPreBtl.setVgap(5);
		nPreBtl.setHgap(5);
		easyPreBtl.getChildren().addAll(mediumGame, mediumDescription);
		
		//sezione Hard di scelta
		VBox hardPreBtl = new VBox();
        hardPreBtl.setPrefHeight(250);
		hardPreBtl.setPrefWidth(500);
		hardPreBtl.setPadding(new Insets(15));
		GridPane bPreBtl = new GridPane();
		bPreBtl.setVgap(5);
		bPreBtl.setHgap(5);
		easyPreBtl.getChildren().addAll(hardGame, hardDescription);
		
		vPre.setAlignment(Pos.BASELINE_CENTER);
		vPre.getChildren().addAll(easyPreBtl, mediumPreBtl, hardPreBtl);
		
		Scene scenePre = new Scene(vPre);
		scenePre.getStylesheets().add("application.css");
		stagePrebattaglia.setTitle("Pre Battaglia");
		stagePrebattaglia.setScene(scenePre);
		stagePrebattaglia.showAndWait();
	}
	
	
	
	public void setLabelText(Label gameTitle, String stringTxt) {
		gameTitle.setText(stringTxt);
	}
	
	public void setEayButton(Button easyGame) {
		this.easyGame = easyGame;
	}
	
	public void setMediumButton(Button mediumGame) {
		this.mediumGame = mediumGame;
	}
	
	public void setHardButton(Button hardGame) {
		this.hardGame = hardGame;
	}
	
	

	public Stage getStage() {
		return stagePrebattaglia;
	}

	public void setStage(Stage stagePrebattaglia) {
		this.stagePrebattaglia = stagePrebattaglia;
	}
	
}
