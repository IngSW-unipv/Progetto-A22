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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		return new Pair<>(yMax, xMax);
	}

	public static void main(String[] args) {
		launch(args); // fa partire la funzione start di JavaFX
	}

	public void addType(HBox controls, String titolo, EventHandler<? super MouseEvent> event) { 
		
		Button button = new Button(titolo);
		button.setStyle("-fx-background-color: #e51400; "
				+ "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;"
				+ "-fx-font-size: 1.1em; ");
		//button.setStyle("-fx-background-color:linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");
		button.setOnMouseClicked(event);

		controls.getChildren().add(button); 
	}

	
	public void selectMalware(HBox hBox) {
		addType(hBox, "attack", (event -> {

			popUp.selectMalware();

		}));
		hBox.setSpacing(0); // spazio tra un bottone e l'altro
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setPadding(STANDARD_PADDING); // margini del testo dentro al bottone
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
	}

	public void development(HBox hBox) {
		addType(hBox, "develop new software", (event -> {
			
			popUp.development();

			// qui va inserita l'azione da compiere quando click on "development"

		}));
	
	}

	public void powerUp(HBox hBox) {
		addType(hBox, "power-up!", (event -> {

			popUp.powerUp();

		}));
		hBox.setSpacing(0); 
		hBox.setPadding(STANDARD_PADDING); 
	}

	public void market(HBox hBox) {
		addType(hBox, "market", (event -> {
			
			PopUp.market();

			// qui va inserita l'azione da compiere quando click on "market"

		}));

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

		// MenuBar
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem openItem = new MenuItem("Open");
		fileMenu.getItems().addAll(saveItem, openItem);
		Menu stateMenu = new Menu("State");
		MenuItem addStateItem = new MenuItem("Add");
		// addStateItem.setOnAction(event -> addState(politicalControls));
		stateMenu.getItems().add(addStateItem);
		menuBar.getMenus().addAll(fileMenu, stateMenu);

		// finestra di gioco
		VBox holder = new VBox();
		BorderPane borderPane = new BorderPane();

		// Canvass = Sfondo rettangolare su cui vengono disegnati rettangoli
		double bordoLarghezza, bordoAltezza, larghezzaE, altezzaE;
		bordoLarghezza = 8;
		bordoAltezza = 0;
		larghezzaE = 44.5;
		altezzaE = 39.4;
		Canvas basicCanvas = new Canvas(dimensioneMappa.getValue() * larghezzaE + bordoLarghezza,
				dimensioneMappa.getKey() * altezzaE + bordoAltezza);

		GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
		basicGC.setFill(null);

		// Adding Canvases to BorderPane
		ScrollPane centerPane = new ScrollPane();
		centerPane.setContent(basicCanvas);
		centerPane.setStyle("-fx-background:rgb(20,20,20);-fx-background-radius:1em");
		centerPane.setBackground(
				new Background(new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		centerPane.setPadding(STANDARD_PADDING);



		// sottoMappa : HBox che contiene le VBox battaglie e action sotto alla mappa

		HBox sottoMappa = new HBox();

		// Pane statsNode che contiene le proprietà del nodo

		Pane statsNode = new Pane();
		statsNode.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 10, 0, 0))));

		VBox statsNodeVbox = new VBox();
		statsNodeVbox.setSpacing(5);
		statsNodeVbox.setPadding(STANDARD_PADDING);

		HBox statsNodeTitle = new HBox();
			statsNodeTitle.setAlignment(Pos.BASELINE_CENTER);
			statsNodeTitle.setPadding(STANDARD_PADDING);
			statsNodeTitle.setBackground(
					new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
			
			Label titleL = new Label("Info Nodo");
			titleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			titleL.setTextFill(Color.DARKGREEN);
		statsNodeTitle.getChildren().add(titleL);
		
		
		HBox gridBox = new HBox();
		GridPane gDx = new GridPane();

		gDx.setVgap(5);
		gDx.setHgap(20);
		gDx.setAlignment(Pos.BASELINE_LEFT);

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
		attack.setPadding(new Insets(20, 10, 10, 10));
		Label attackL = new Label();
		attack.getChildren().add(attackL);
		selectMalware(attack);
		
		HBox.setHgrow(statsNodeTitle, Priority.ALWAYS);
		HBox.setHgrow(gridBox, Priority.ALWAYS);
		HBox.setHgrow(attack, Priority.ALWAYS);
		
		statsNodeVbox.getChildren().addAll(statsNodeTitle, gridBox, attack);
		statsNode.getChildren().addAll(statsNodeVbox); /* ,nodeInfo,ownerDistance,firewall */
		
		// ------------------------------------------ //
		
		// Pane battle che contiene le battaglie

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

		// ------------------------------------------ //
		
		// Pane action - contiene le azioni di gioco

		Pane actionPane = new Pane();

		actionPane.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		actionPane.setPadding(STANDARD_PADDING);

		VBox controlli = new VBox();
		controlli.setPadding(STANDARD_PADDING);
		
		HBox actionTitle = new HBox();
		actionTitle.setPadding(STANDARD_PADDING);
		actionTitle.setAlignment(Pos.CENTER);
		actionTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label actionTitleL = new Label("Action");
		actionTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		actionTitleL.setTextFill(Color.DARKGREEN);
		
		//actionTitleL.setBackground(
			//	new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));

		actionTitle.getChildren().add(actionTitleL);
		
		HBox actionMarket = new HBox();
		actionMarket.setPadding(STANDARD_PADDING);
		Label actionMarketL = new Label();
		actionMarket.getChildren().add(actionMarketL);
		market(actionMarket);
		
		HBox powerUp = new HBox();
		powerUp.setPadding(STANDARD_PADDING);
		Label powerUpL = new Label();
		powerUp.getChildren().add(powerUpL);
		powerUp(powerUp);
		
		HBox dev = new HBox();
		dev.setPadding(STANDARD_PADDING);
		Label develop = new Label();
		dev.getChildren().add(develop);
		development(dev);
		
		controlli.getChildren().addAll(actionTitle, actionMarket, powerUp, dev);
		actionPane.getChildren().add(controlli);

		// ------------------------------------------ //
		
		// Pane yourBase a destra della mappa 

		Pane yourBasePane = new Pane();
		yourBasePane.setBackground(
				new Background(new BackgroundFill(Color.web("#e1d5e7"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		yourBasePane.setPadding(STANDARD_PADDING);

		VBox yb = new VBox();
		yb.setPadding(STANDARD_PADDING);

		HBox ybTitle = new HBox();
		Label ybTitleL = new Label("MARKET");

		ybTitle.getChildren().add(ybTitleL);
		yb.getChildren().add(ybTitleL);
		yourBasePane.getChildren().add(yb); 
		
// ------------------------------------------ //		
		
		HBox.setHgrow(statsNode, Priority.ALWAYS);
		HBox.setHgrow(actionPane, Priority.ALWAYS);
		HBox.setHgrow(battlePane, Priority.ALWAYS);
		sottoMappa.getChildren().addAll(statsNode, battlePane, actionPane);

// ------------------------------------------ //

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

		Sistema sistema = new Sistema();
		sistema.colore = Colore.GRIGIO;
		Utente utente = new Utente("user");
		utente.colore = Colore.GIALLO;

		MainDefinitivo main = new MainDefinitivo();
		
		try {
			main.avvioPartita(30, 20, "Matteo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Giocatore[] giocatori = main.getGiocatori();

		MappaDefinitiva mappa = new MappaDefinitiva(30, 20, giocatori);
		mappa.assegnamento(10, giocatori);

		// definiamo i nodi base

		/*
		 * Rilevo il numero di basi da piazzare numBasi Nel ciclo di creazione del
		 * vettore nodi verifico se un nodo ha le coordinate di una base Se si allora
		 * recupero il nome del giocatore Giocatore.getNome(giocatore); di quella base
		 * Definisco il nodo come base del Giocatore giocatore Assegno il colore al nodo
		 * i,j nodo[i][j].colore = getColorePossessore(giocatore);
		 */

		// creo vettore di nodi X TEST!

		// !!!! Occorre normalizzare le coordinate !!!!
		Nodo[][] nodiTest = mappa.getMap();
		

		MapData mapData = new MapData(nodiTest);

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
			
			if (data != null) { 			// controllo se il click avviene fuori dagli esagoni
				Nodo nodo = data.nodo;

				titleL.setText("Stats Node: " + est.x + " , " + est.y);
				owner.setText("Owner: " + nodo.getPossessore().getNome());
				distance.setText("Base distance: " + nodo.getDist_base());
				energy.setText("Energy: " + nodo.getE_disponibile());
				fwLvl.setText("Firewall Level: " + String.valueOf(nodo.getLvl_firewall()));
				ramLvl.setText("Ram Level: " + String.valueOf(nodo.getLvl_ram()));
				cpuLvl.setText("CPU Level: " + String.valueOf(nodo.getLvl_cpu()));
				
				//" SWdisp: " + nodo.getSoftware_disponibile());

				

				// isDistanceL.setText(String.valueOf(nodo.getDist_base()));

				basicMap.drawMap(est);

			}
		});

		primaryStage.setScene(new Scene(holder));
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