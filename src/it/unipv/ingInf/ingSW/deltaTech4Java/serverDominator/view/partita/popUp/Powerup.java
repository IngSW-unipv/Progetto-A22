package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Powerup {
	private Nodo NodoUtente;
	
	private static int sX = 400;
	private static int sY = 400;	
	private static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	private Stage stage;
	private Button buttonPowerUp;
	private int cpuAdd, fwAdd,  ramAdd;
	//Luca: attenzione che si deve potenziare anche l'energia
	//Habib: aggiunta
	public Powerup(Nodo NodoUtente) {
		this.NodoUtente=NodoUtente;
		cpuAdd = 0;  fwAdd = 0;  ramAdd = 0; 
		buttonPowerUp = ComponentCreator.getIstance().createButton("powerUp!", Pos.BASELINE_CENTER);
		
	}
	public Powerup() {
		cpuAdd = 0;  fwAdd = 0; ; ramAdd = 0;
		buttonPowerUp = ComponentCreator.getIstance().createButton("powerUp!", Pos.BASELINE_CENTER);

		
	}
	public void powerUp(Nodo NodoUtente) {
		this.NodoUtente=NodoUtente;
		
	}
	
	/**
	 * Interfaccia pop up per scegliere la risorsa da potenziare
	 */
	
	public void powerUp() {
		
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);
		
		VBox powerUp = new VBox();
		powerUp.setAlignment(Pos.TOP_CENTER);
		
		
		HBox powerUpTitle = new HBox();
		powerUpTitle.setPadding(STANDARD_PADDING);
		powerUpTitle.setAlignment(Pos.CENTER);
		powerUpTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label powTitleL = new Label("Empower your Nodo !");
		//powTitleL.getStylesheets().add("redbutton");
		powTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		powTitleL.setTextFill(Color.DARKGREEN);
		powerUpTitle.getChildren().add(powTitleL);
		
		HBox availableE = new HBox();
		availableE.setAlignment(Pos.BASELINE_LEFT);
		availableE.setPadding(new Insets(10, 0, 10, 10));
		Label eDisp = new Label("Available Energy: " + String.valueOf(NodoUtente.getE_disponibile()));
		availableE.getChildren().add(eDisp);
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);

		Label cpuPlus = new Label("Actual CPU level: " + NodoUtente.getLvl_cpu());
		HBox cpuAdjust = new HBox();
		Button cpuInc = new Button("CPU+1 ");
		Button cpuDec = new Button("CPU-1 ");
		Label cpuResult = new Label();
		cpuInc.setOnAction(e -> {
			cpuAdd=1 ;
			cpuResult.setText(" add: " + cpuAdd );
		});
		
		cpuDec.setOnAction(e -> {
			cpuAdd=0 ;
			cpuResult.setText(" add: " + cpuAdd );
		});
		cpuAdjust.getChildren().addAll(cpuInc, cpuDec, cpuResult);
		cpuAdjust.setSpacing(8.0);
		
		
		Label fwPlus= new Label("Actual FWL level: " + NodoUtente.getLvl_firewall());
		HBox fwAdjust = new HBox();
		Button fwInc = new Button("FWL+1 ");
		Button fwDec = new Button("FWL-1 ");
		Label fwResult = new Label();
		fwInc.setOnAction(e -> {
			fwAdd=1 ;
			fwResult.setText(" add: " + fwAdd );
		});
		
		fwDec.setOnAction(e -> {
			fwAdd=0;
			fwResult.setText(" add: " + fwAdd );
		});
		
		fwAdjust.getChildren().addAll(fwInc, fwDec, fwResult);
		fwAdjust.setSpacing(8.0);
		
		Label ramPlus = new Label("Actual RAM level: " + NodoUtente.getLvl_ram());
		HBox ramAdjust = new HBox();
		Button ramInc = new Button("RAM+1"); //ramInc.setPrefSize(xS, yS);
		Button ramDec = new Button("RAM-1"); //ramDec.setPrefSize(xS, yS);
		Label ramResult = new Label();	
		ramInc.setOnAction(e -> {
			ramAdd=1 ;
			ramResult.setText(" add: " + ramAdd );
		});
		
		ramDec.setOnAction(e -> {
			ramAdd=0 ;
			ramResult.setText(" add: " + ramAdd);
		});
		
		ramAdjust.getChildren().addAll(ramInc, ramDec, ramResult);
		ramAdjust.setSpacing(8.0);

		
		HBox pUButton = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		
		pUButton.getChildren().add(buttonPowerUp);
		pUButton.setPadding(new Insets(10, 10, 10, 10));
		
		layout.add(cpuPlus, 0, 0);
		layout.add(cpuAdjust, 1, 0);
		layout.add(ramPlus, 0, 1);
		layout.add(ramAdjust, 1, 1);
		layout.add(fwPlus, 0, 2);
		layout.add(fwAdjust, 1, 2);
		
		powerUp.getChildren().addAll(powerUpTitle, availableE, layout, pUButton);
		
		Scene scene = new Scene(powerUp, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Power up");
		stage.setScene(scene);
		stage.showAndWait();
	}
	public void initAllZero() {
		cpuAdd = 0;  fwAdd = 0;  ramAdd = 0;
	}
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Nodo getNodoUtente() {
		return NodoUtente;
	}

	public int getCpu() {
		return cpuAdd;
	}

	public int getFirewall() {
		return fwAdd;
	}

	public int getRam() {
		return ramAdd;
	}

	public Button getButtonPowerUp() {
		return buttonPowerUp;
	}

	public void setButtonPowerUp(Button buttonPowerUp) {
		this.buttonPowerUp = buttonPowerUp;
	}
	public static int getsX() {
		return sX;
	}
	public static void setsX(int sX) {
		Powerup.sX = sX;
	}
	public static int getsY() {
		return sY;
	}
	public static void setsY(int sY) {
		Powerup.sY = sY;
	}
	public int getCpuAdd() {
		return cpuAdd;
	}
	public void setCpuAdd(int cpuAdd) {
		this.cpuAdd = cpuAdd;
	}
	public int getFwAdd() {
		return fwAdd;
	}
	public void setFwAdd(int fwAdd) {
		this.fwAdd = fwAdd;
	}
	public int getRamAdd() {
		return ramAdd;
	}
	public void setRamAdd(int ramAdd) {
		this.ramAdd = ramAdd;
	}
	
	public static Insets getStandardPadding() {
		return STANDARD_PADDING;
	}
	public void setNodoUtente(Nodo nodoUtente) {
		NodoUtente = nodoUtente;
	}
	
	
	
}
