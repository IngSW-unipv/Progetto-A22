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
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

	public void addType(HBox controls, String titolo, EventHandler<? super MouseEvent> event) { // HBOX � un contenitore
																								// orizzontale (VBOX
																								// verticale)
		Button button = new Button(titolo); // do al bottone il nome
		button.setOnMouseClicked(event);

		controls.getChildren().add(button); // aggiunge il bottone al layout grafico
	}

	// botte ATTACCA! con l'evento mouseOnClick da aggiungere
	
	public void setUpControls(HBox setUpControls) {
		addType(setUpControls, "Attacca!", (event -> {

			// qui va inserita l'azione da compiere quando click on "Attacca!"

		}));
		setUpControls.setSpacing(10); // spazio tra un bottone e l'altro
		setUpControls.setPadding(STANDARD_PADDING); // margini del testo dentro al bottone
	}
	
	public void firewallAction(HBox firewallAction) {
		addType(firewallAction, "+Firewall", (event -> {

			// qui va inserita l'azione da compiere quando click on "+Firewall"

		}));
		//firewallAction.setSpacing(0); // spazio tra un bottone e l'altro
		//firewallAction.setPadding(STANDARD_PADDING); // margini del testo dentro al bottone
	}
	
	public void ramAction(HBox ramActionButton) {
		addType(ramActionButton, "+RAM", (event -> {

			// qui va inserita l'azione da compiere quando click on "+RAM"

		}));
		//ramActionButton.setSpacing(0); // spazio tra un bottone e l'altro
		//ramActionButton.setPadding(STANDARD_PADDING); // margini del testo dentro al bottone
	}
	
	public void cpuAction(HBox cpuActionButton) {
		addType(cpuActionButton, "+RAM", (event -> {

			// qui va inserita l'azione da compiere quando click on "+CPU"

		}));
		
	}


	@Override
	public void start(Stage primaryStage) { // start della costruzione grafica
		primaryStage.setTitle("Server Dominator");
		primaryStage.setIconified(false); // se TRUE lo avvia ridotto a icona
		primaryStage.setFullScreen(false); // apre in full screen
		primaryStage.setX(0); // definisce la coordinata X dell'angolo in alto a sinistra della finestra
		primaryStage.setY(0); // definisce la coordinata Y dell'angolo in alto a sinistra della finestra

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

		// sottoMappa : HBox che contiene le VBox battaglie e action sotto alla mappa
		HBox sottoMappa = new HBox();
		
		// VBox che contiene le battaglie
		VBox battaglie = new VBox();
		battaglie.setSpacing(5);
		battaglie.setPadding(STANDARD_PADDING);
		Label titoloBattaglie = new Label("BATTAGLIE IN CORSO");
		HBox batt1 = new HBox();
		batt1.setSpacing(5);
		ProgressBar pb1 = new ProgressBar(0);
		Label batt1Lb = new Label("Battaglia 1");
		batt1.getChildren().addAll(batt1Lb, pb1);
		battaglie.getChildren().addAll(titoloBattaglie,batt1);

		// VBox "controlli" che contiene le azioni di gioco
		
		VBox controlli = new VBox();
		Label azione = new Label("AZIONI DI GIOCO");
		azione.setMinWidth(100);
		HBox attaccoDifesa = new HBox();
		attaccoDifesa.setMinWidth(100);		
		HBox datiNodo = new HBox();
		Label coordinate = new Label();
		datiNodo.setPadding(STANDARD_PADDING);
		
		
		datiNodo.getChildren().add(coordinate);
		controlli.getChildren().addAll(azione, datiNodo, attaccoDifesa); // aggiungo le HBox datiNodo, attaccoDifesa alla VBox controlli 
		setUpControls(attaccoDifesa); // aggiunge bottone

		sottoMappa.getChildren().addAll(battaglie,controlli);

		// Adding Canvases to BorderPane
		Pane pane = new Pane();
		pane.getChildren().addAll(basicCanvas);

		// definisco: colore sfondo di pane; arrotondamento angoli;
		pane.setBackground(new Background(
				new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(-10, -10, 0, -10))));
		// pane.setPadding(STANDARD_PADDING);

		// VBox destraMappa
		
		VBox destraMappa = new VBox();
		destraMappa.setSpacing(5);
		destraMappa.setPadding(STANDARD_PADDING);
		
		HBox title = new HBox();
		title.setAlignment(Pos.BASELINE_CENTER);
		Label Title = new Label("Info Nodo");
		title.getChildren().add(Title);
		
		/*
		HBox nodeInfo = new HBox();
		nodeInfo.setSpacing(5);
		nodeInfo.setMinWidth(200);
		nodeInfo.setAlignment(Pos.BASELINE_CENTER);
		nodeInfo.setPadding(STANDARD_PADDING);
		

		HBox ownerDistance = new HBox();
		Label nodeInfoL = new Label("Node Info");
		//ownerDistance.minWidth(200);
		
		
		VBox nodeOwner = new VBox();
		HBox hNodeOwner = new HBox();
		hNodeOwner.setAlignment(Pos.BASELINE_CENTER);
		hNodeOwner.minWidth(100);
		hNodeOwner.setPadding(STANDARD_PADDING);
		Label owner = new Label("Node owner");
		HBox isOwner = new HBox();
		isOwner.setAlignment(Pos.BASELINE_CENTER);
		Label isOwnerL = new Label();
		
		
		VBox distance = new VBox();
		HBox hDistance = new HBox();
		hDistance.setAlignment(Pos.BASELINE_CENTER);
		hDistance.setPadding(STANDARD_PADDING);
		hDistance.setMinWidth(100);
		Label Distance = new Label("Distance");
		HBox isDistance = new HBox();
		isDistance.setAlignment(Pos.BASELINE_CENTER);
		Label isDistanceL = new Label();
		
		
		HBox firewall = new HBox();
		//firewall.setPadding(STANDARD_PADDING);
		VBox fwLVL = new VBox();
		HBox hFwLVL = new HBox();
		hFwLVL.setPadding(STANDARD_PADDING);
		hFwLVL.setMinWidth(100);
		hFwLVL.setMaxWidth(100);
		hFwLVL.setAlignment(Pos.BOTTOM_LEFT);
		Label FwLvlL = new Label("Firewall LVL");
		//FwLVL.setAlignment(Pos.TOP_RIGHT); questo allineamento non incide
		
		 
		
		
		VBox fwAction = new VBox();
		HBox hFwAction = new HBox();
		hFwAction.setPadding(STANDARD_PADDING);
		hFwAction.setSpacing(0);
		hFwAction.setMinWidth(100);
		hFwAction.setAlignment(Pos.BASELINE_LEFT);
		firewallAction(hFwAction);
		*/
		
		
		GridPane gDx = new GridPane();
		gDx.setPrefSize(200, 300);
		gDx.setPadding(new Insets(0,10,10,10));
		gDx.setVgap(5);
		gDx.setHgap(20);
		gDx.setAlignment(Pos.BASELINE_LEFT);
		
			
		Label title1 = new Label("-----------");
		Label title2 = new Label("-----------");
		Label owner = new Label("Node owner:");
		Label isOwnerL = new Label();
		Label distance = new Label("Distance:");
		Label isDistance = new Label("NO DATA");
		Label fwLVL = new Label("Firewall LVL");
		HBox hFwAction = new HBox();
		hFwAction.setAlignment(Pos.BASELINE_LEFT);
		firewallAction(hFwAction);
		Label ramLvl = new Label("Ram LVL");
		HBox ramActionBox = new HBox();
		ramActionBox.setAlignment(Pos.BASELINE_LEFT);
		ramAction(ramActionBox);
		Label cpuLvl = new Label("CPU LVL");
		HBox cpuActionBox = new HBox();
		cpuActionBox.setAlignment(Pos.BASELINE_LEFT);
		ramAction(cpuActionBox);
		
		gDx.add(owner, 0, 0);
		gDx.add(isOwnerL, 1, 0);
		gDx.add(distance, 0, 1);
		gDx.add(isDistance, 1, 1);
		
		gDx.add(fwLVL, 0, 2);
		gDx.add(hFwAction, 1, 2);
		gDx.add(ramLvl, 0, 3);
		gDx.add(ramActionBox, 1, 3);
		gDx.add(cpuLvl, 0, 4);
		gDx.add(cpuActionBox, 1, 4);
		
		
		/*
		nodeInfo.getChildren().add(new Label("Label Test"));
		hNodeOwner.getChildren().add(new Label("NULL"));
		isOwner.getChildren().add(isOwnerL);
		nodeOwner.getChildren().addAll(hNodeOwner,isOwner);
		hDistance.getChildren().add(Distance);
		distance.getChildren().add(hDistance);
		ownerDistance.getChildren().addAll(nodeOwner,distance);
		hFwLVL.getChildren().add(FwLVL);
		fwLVL.getChildren().add(hFwLVL);
		fwAction.getChildren().add(hFwAction);
		firewall.getChildren().addAll(fwLVL,fwAction);
		*/
		
		destraMappa.getChildren().addAll(title,gDx); /*,nodeInfo,ownerDistance,firewall*/
		


		// Setting BorderPane
		BorderPane.setMargin(pane, new Insets(20));
		borderPane.setTop(menuBar);
		borderPane.setCenter(pane);
		borderPane.setBottom(sottoMappa);
		borderPane.setRight(destraMappa);

		// Adding MenuBar and BorderPane to a VBox Holder
		holder.getChildren().addAll(menuBar, borderPane);

		Sistema sistema = new Sistema();
		sistema.colore = Colore.GRIGIO;
		Utente utente = new Utente("user");
		utente.colore = Colore.GIALLO;

		MainDefinitivo main = new MainDefinitivo();
		main.avvioPartita(30, 20, "Matteo");
		Giocatore[] giocatori = main.getGiocatori();

		Mappa mappa = new Mappa(30, 20, giocatori);
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

		pane.setOnMouseClicked(event -> {
			Hexagon est = mapData.pixelToHex(new Point(event.getX(), event.getY()));
			HexData data = mapData.getHexData(est);
			Nodo nodo = data.nodo;

			coordinate.setText("Coordinate: " + est.x + " , " + est.y + " Possessore: " + nodo.getPossessore().getNome()
					+ " SWdisp: " + nodo.getSoftware_disponibile());
			
			isOwnerL.setText(nodo.getPossessore().getNome());
			
			//isDistanceL.setText(String.valueOf(nodo.getDist_base()));

			basicMap.drawMap(est);
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