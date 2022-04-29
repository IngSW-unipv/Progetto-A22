package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

/**
 * @author Matteo Para 
 * v0.1
 * Esecuzione interfaccia grafica
 * 
 * La mappa viene ricreata ogni volta che cambia il possessore di un qualsiasi nodo.
 * 
 * Le progressBar vengono create ad ogni avvio di battaglia
 * 
 * I log vengono scritti ad ogni creazione, avvio battaglia, conclusione battaglia 
 * 
 * 
 */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2.*;

public class Main extends Application {

	enum LivelloDiGioco {
		Easy, Medium, Hard

	}

	private final Insets STANDARD_PADDING = new Insets(10);
	PopUp popUp = new PopUp();

	private Pair<Integer, Integer> getDimensioniMappa(LivelloDiGioco livello) {
		int xMax, yMax;
		switch (livello) {

		case Easy:
			xMax = 15;
			yMax = 10;
			break;

		case Medium:
			xMax = 20;
			yMax = 15;
			break;

		default:
			xMax = 30;
			yMax = 20;
			break;
		}
		return new Pair<>(xMax, yMax);
	}
	

	public static void main(String[] args) {
		launch(args); // fa partire la funzione start di JavaFX
	}

	public void addType(HBox controls, String titolo, EventHandler<? super MouseEvent> event) { 
		
		Button button = new Button(titolo);
		button.getStyleClass().add("redbutton");
		//button.setPadding(new Insets(11,11,11,11));
		button.setOnMouseClicked(event);

		controls.getChildren().add(button); 
		controls.setPadding(STANDARD_PADDING);
		controls.setAlignment(Pos.CENTER);
		controls.setSpacing(5);
	}

	
	public void selectMalware(HBox hBox, Base bU) {
		addType(hBox, "attack", (event -> {

			popUp.selectMalware(bU);

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(8, 8, 8, 8))));
	}

	public void development(HBox hBox, Base bU) {
		addType(hBox, "develop software", (event -> {
			
			popUp.development(bU);

			// qui va inserita l'azione da compiere quando click on "development"

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}

	public void powerUp(HBox hBox, Base bU) {
		addType(hBox, "power-up!", (event -> {

			popUp.powerUp(bU);

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}

	public void market(HBox hBox, Base bU) {
		addType(hBox, "market", (event -> {
			
			popUp.market(bU);

			// qui va inserita l'azione da compiere quando click on "market"

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}

	@Override
	public void start(Stage primaryStage) { // start della costruzione grafica
		primaryStage.setTitle("Server Dominator");
		primaryStage.setIconified(false); // se TRUE lo avvia ridotto a icona
		primaryStage.setFullScreen(false); // apre in full screen
		primaryStage.setX(0); // definisce la coordinata X dell'angolo in alto a sinistra della finestra
		primaryStage.setY(0); // definisce la coordinata Y dell'angolo in alto a sinistra della finestra
		//primaryStage.centerOnScreen();
		
		Pair<Integer, Integer> dimensioneMappa = getDimensioniMappa(LivelloDiGioco.Hard);
		MainDefinitivo mainView = new MainDefinitivo();
		try {
			mainView.avvioPartita(dimensioneMappa.getKey(), dimensioneMappa.getValue(), "Matteo");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		Giocatore[] gioc = mainView.getGiocatori();
		Nodo baseU = mainView.getTabellone().trovaBase(gioc[1]);
		Base bU = new Base(baseU.getPossessore());
		MappaDefinitiva mappa = new MappaDefinitiva(dimensioneMappa.getKey(), dimensioneMappa.getValue(), gioc);
		mappa.assegnamento(gioc.length-1, gioc);
		Nodo[][] nodiView = mappa.getMap();
		MapData mapData = new MapData(nodiView);
		int ray = mapData.getRay();
		
		// MenuBar
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem openItem = new MenuItem("Open");
		fileMenu.getItems().addAll(saveItem, openItem);
		Menu stateMenu = new Menu("State");
		MenuItem addStateItem = new MenuItem("Add");
		stateMenu.getItems().add(addStateItem);
		menuBar.getMenus().addAll(fileMenu, stateMenu);

		// finestra di gioco
		
		VBox holder = new VBox();
		BorderPane borderPane = new BorderPane();

		// Canvass = Sfondo rettangolare su cui vengono disegnati rettangoli
		
		double bordoLarghezza, bordoAltezza, hexWidth, hexHight;
				
		hexWidth = (dimensioneMappa.getKey() * ray * Math.sqrt(3.0)) + ray;
		hexHight = ray;
		
		if (dimensioneMappa.getValue() % 2 ==0 ) {
			hexHight = dimensioneMappa.getValue() / 2 * ray * 3;
		}
		
		else if ((dimensioneMappa.getValue() % 2 !=0) && (dimensioneMappa.getValue() > 1)){
			hexHight = (dimensioneMappa.getValue() - 1) * ray * 3 + 0.5 * ray; 
		}
		
		else {
			hexHight = ray * 2;
		}
		
		Canvas basicCanvas = new Canvas(hexWidth ,(hexHight + 12));

		GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
		basicGC.setFill(null);

		// Adding Canvase to BorderPane
		ScrollPane centerPane = new ScrollPane();
		centerPane.setContent(basicCanvas);
		centerPane.setStyle("-fx-background:rgb(20,20,20);-fx-background-radius:1em");  
		centerPane.setBackground(
				new Background(new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		centerPane.setPadding(STANDARD_PADDING);
		//centerPane.setFitToHeight(true);
		//centerPane.setFitToWidth(true);
		//System.out.println("" + centerPane.getBaselineOffset());



// sottoMappa : HBox che contiene le VBox battaglie e action sotto alla mappa

		HBox sottoMappa = new HBox();

	// Pane statsNode che contiene le proprietà del nodo

		Pane statsNode = new Pane();
		statsNode.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 10, 0, 0))));

		HBox statsNodeHbox = new HBox();
		statsNodeHbox.setAlignment(Pos.BASELINE_CENTER);
		
		VBox statsNodeVbox = new VBox();
		statsNodeVbox.setPadding(STANDARD_PADDING);

		HBox statsNodeTitle = new HBox();
			statsNodeTitle.setAlignment(Pos.BASELINE_CENTER);
			statsNodeTitle.setPadding(STANDARD_PADDING);
			statsNodeTitle.setBackground(
					new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5,5,5,5))));
			
			Label titleL = new Label("Info Nodo");
			titleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			titleL.setTextFill(Color.DARKGREEN);
		statsNodeTitle.getChildren().add(titleL);
		
		HBox gridBox = new HBox();
		GridPane gDx = new GridPane();

		gDx.setVgap(5);
		gDx.setHgap(20);
		gDx.setAlignment(Pos.CENTER);
		gDx.setPadding(STANDARD_PADDING);

		Label owner = new Label("Node owner");
		Label distance = new Label("Distance");
		Label energy = new Label("Energy");
		Label fwLvl = new Label("Firewall Level");
		Label ramLvl = new Label("Ram Level");
		Label cpuLvl = new Label("CPU Level");
		
		gDx.add(owner, 0, 0);
		gDx.add(distance, 0, 1);
		gDx.add(energy, 0, 2);
		gDx.add(fwLvl, 1, 0);
		gDx.add(ramLvl, 1, 1);
		gDx.add(cpuLvl, 1, 2);

		gridBox.getChildren().add(gDx);
		
		HBox attack = new HBox();
		attack.setPadding(STANDARD_PADDING);
		Label attackL = new Label();
		attack.getChildren().add(attackL);
		
		selectMalware(attack, bU);
		
		statsNodeVbox.getChildren().addAll(statsNodeTitle, gridBox, attack);
		statsNodeHbox.getChildren().add(statsNodeVbox);
		statsNode.getChildren().addAll(statsNodeHbox); 
		
	// Pane battle che contiene le battaglie //

		Pane battlePane = new Pane();

		battlePane.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
		battlePane.setPadding(STANDARD_PADDING);

		VBox battaglie = new VBox();
		battaglie.setSpacing(0);
		battaglie.setPadding(STANDARD_PADDING);
		battaglie.setMaxWidth(390);
		Label titoloBattaglie = new Label("BATTAGLIE IN CORSO");
		HBox batt1 = new HBox();
		batt1.setSpacing(0);
		ProgressBar pb1 = new ProgressBar(0);
		Label batt1Lb = new Label("Battaglia 1");
		
		batt1.getChildren().addAll(batt1Lb, pb1);
		battaglie.getChildren().addAll(titoloBattaglie, batt1);
		battlePane.getChildren().add(battaglie);
		
	// Pane action - contiene le azioni di gioco

		
		Pane actionPane = new Pane();
		actionPane.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		
		AnchorPane anchor = new AnchorPane();
		
		AnchorPane.setTopAnchor(actionPane, 0d);
		
		VBox controlli = new VBox();
		controlli.setMinWidth(200.0);
		controlli.setPadding(STANDARD_PADDING);
		controlli.setAlignment(Pos.CENTER);
						
		HBox actionTitle = new HBox();
		actionTitle.setPadding(STANDARD_PADDING);
		//actionTitle.setPrefSize(200.0, 30.0);
		actionTitle.setAlignment(Pos.CENTER);
		actionTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5,5,5,5))));
		Label actionTitleL = new Label("Action");
		actionTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		actionTitleL.setTextFill(Color.DARKGREEN);

		actionTitle.getChildren().add(actionTitleL);
					
		HBox actionMarket = new HBox();
		actionMarket.setPadding(STANDARD_PADDING);
		actionMarket.setAlignment(Pos.TOP_CENTER);
		Label actionMarketL = new Label();
		actionMarket.getChildren().add(actionMarketL);
		
		market(actionMarket, bU);
		
		HBox powerUp = new HBox();
		powerUp.setPadding(STANDARD_PADDING);
		powerUp.setAlignment(Pos.BASELINE_CENTER);
		powerUp.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label powerUpL = new Label();
		powerUp.getChildren().add(powerUpL);
		
		powerUp(powerUp, bU);
		
		HBox dev = new HBox();
		dev.setPadding(STANDARD_PADDING);
		dev.setAlignment(Pos.BASELINE_CENTER);
		dev.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label develop = new Label();
		dev.getChildren().add(develop);
		
		development(dev, bU);
		
		controlli.getChildren().addAll(actionTitle, actionMarket, powerUp, dev);
		actionPane.getChildren().add(controlli);
		anchor.getChildren().add(actionPane);

// ------------------------------------------ //		
		
		HBox.setHgrow(statsNode, Priority.ALWAYS);
		HBox.setHgrow(battlePane, Priority.ALWAYS);
		HBox.setHgrow(anchor, Priority.ALWAYS);
		
		sottoMappa.getChildren().addAll(statsNode, battlePane, anchor);

// ------------------------------------------ //
		
	// Pane yourBase a destra della mappa 

		RightPane rPane = new RightPane(bU);
		Pane yourBasePane = new Pane();
		yourBasePane = rPane.getRightPane(bU);

	// Setting BorderPane

		borderPane.setTop(menuBar);
		borderPane.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(10));
		borderPane.setBottom(sottoMappa);
		BorderPane.setMargin(sottoMappa, new Insets(0, 10, 10, 10));
		borderPane.setRight(yourBasePane);
		BorderPane.setMargin(yourBasePane, new Insets(10, 10, 10, 0));

	// Adding MenuBar and BorderPane to a VBox Holder
		
		holder.getChildren().addAll(menuBar, borderPane);
		
// ------------------------------------------ //		


	// definiamo i nodi base

		/**
		 * Rilevo il numero di basi da piazzare numBasi Nel ciclo di creazione del
		 * vettore nodi verifico se un nodo ha le coordinate di una base Se si allora
		 * recupero il nome del giocatore Giocatore.getNome(giocatore); di quella base
		 * Definisco il nodo come base del Giocatore giocatore Assegno il colore al nodo
		 * i,j nodo[i][j].colore = getColorePossessore(giocatore);
		 **/

		// creo vettore di nodi X TEST!

		// !!!! Occorre normalizzare le coordinate !!!!
		
		

		// Always Will be rendered basically - Ci si riporta sempre alla mappa di base

		BasicMap basicMap = new BasicMap(mapData, basicGC);

		basicMap.selected = true;

		ArrayList<Map> mapOrder = new ArrayList<>();
		mapOrder.add(basicMap);

		/*
		 * Map Checkbox Listeners - SOLO PER ESEMPIO
		 * terrainCheck.selectedProperty().addListener((ov, old, newValue) -> {
		 * terrainMap.selected = newValue; mapChange = true; });
		 */

						
		centerPane.setOnMouseClicked(event -> {
			
			Hexagon est = mapData.pixelToHex(new Point(event.getX(), event.getY()));
			HexData data = mapData.getHexData(est);
			
	// ---------- sto provando a risolvere l'offset su scroll --------------//	
			double[] h =  new double[1000];
			double[] w = new double[1000];
			int click = 0;
			h[click] = centerPane.getHeight(); w[click] = centerPane.getWidth();
			
			if (data != null) {
				++click;
				
				h[click] = centerPane.getHeight(); w[click] = centerPane.getWidth();				
				
				System.out.println("Dal click " + (click-1) + " al " + click + " -> diffHeight(): " + (h[click] - h[click-1]));
				System.out.println("Dal click " + (click-1) + " al " + click + " -> diffWidth(): " + (w[click] - w[click-1]));
				
				System.out.println("centerPane.getWidth(): " + centerPane.getWidth() + "click: " + click);
				
			}
		
					
	// -----------------------------------------------------------------------//
			
			if (data != null) { 			// controllo se il click avviene fuori dagli esagoni
				Nodo nodo = data.nodo;
					
		// ---- Valori in Stats Node ---------//
				
				titleL.setText("Stats Node: " + est.x + " , " + est.y);
				owner.setText("Owner: " + nodo.getPossessore().getNome());
				distance.setText("Base distance: " + nodo.getDist_base());
				energy.setText("Energy: " + nodo.getE_disponibile());
				fwLvl.setText("Firewall Level: " + String.valueOf(nodo.getLvl_firewall()));
				ramLvl.setText("Ram Level: " + String.valueOf(nodo.getLvl_ram()));
				cpuLvl.setText("CPU Level: " + String.valueOf(nodo.getLvl_cpu()));
				basicMap.drawMap(est);

			}
		});

		Scene scena = new Scene(holder);
		scena.getStylesheets().add("application.css");
		primaryStage.setScene(scena);
		primaryStage.show();

		new AnimationTimer() {
			@Override
			public void handle(long now) {

				// pulisco la mappa
				basicGC.clearRect(0, 0, basicCanvas.getWidth(), basicCanvas.getHeight());

				// disegno la mappa
				basicMap.drawMap();
			}
		}.start();
	}
}