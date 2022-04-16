package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

/**
 * @author Matteo Para 
 * v0.1
 * Esecuzione interfaccia grafica
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class Main extends Application {

	enum LivelloDiGioco {
		Easy, Medium, Hard
		
	}

	private final Insets STANDARD_PADDING = new Insets(10);
	
	
	private Pair<Integer, Integer> getDimensioniMappa(LivelloDiGioco livello) {
		int xMax, yMax;
		switch (livello) {
		
		case Easy:
			xMax = 15; yMax = 10;
			break;
		
		case Medium:
			xMax = 20; yMax = 15;
			break;
		
		default:
			xMax = 30; yMax = 20;
			break;
		}
		return new Pair<>(yMax,xMax);
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

	// costruisce la HBOX con i controlli di gioco
	public void setUpControls(HBox setUpControls) {
		addType(setUpControls, "Attacca!", (event -> {
			// qui da inserire l'azione che si deve compiere quando click on "Attacca!"
		}));
		setUpControls.setSpacing(10); 				// spazio tra un bottone e l'altro
        setUpControls.setPadding(STANDARD_PADDING); 	// margini del testo dentro al bottone
	}

	@Override
	public void start(Stage primaryStage) { // start della costruzione grafica
		primaryStage.setTitle("Server Dominator");
		Pair<Integer, Integer> dimensioneMappa = getDimensioniMappa(LivelloDiGioco.Easy);

		VBox holder = new VBox();
		BorderPane borderPane = new BorderPane();

		// Canvass = Sfondo rettangolare su cui vengono disegnati rettangoli
		int bordo , larghezzaE , altezzaE;
		bordo = 0;
		larghezzaE = 45;
		altezzaE = 40; 
		Canvas basicCanvas = new Canvas(dimensioneMappa.getValue()*larghezzaE+bordo, dimensioneMappa.getKey()*altezzaE+bordo);
		GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
		
		
		
		// VBox che contiene le HBox sotto alla mappa
		VBox controls = new VBox();
		HBox terrainControls = new HBox();
		HBox datiNodo = new HBox();
		Label coordinate = new Label();
		datiNodo.getChildren().add(coordinate);
		datiNodo.setPadding(STANDARD_PADDING);
		
		
		controls.getChildren().addAll(datiNodo, terrainControls); // aggiungo le HBox alla VBox

		setUpControls(terrainControls); // aggiunge le spaziature, i margini, etc...

		Sistema sistema = new Sistema();
		Utente utente = new Utente("matte");
		utente.setColore(Colore.AZZURRO);
		
		
		
		System.out.println(dimensioneMappa.getKey() + " " + dimensioneMappa.getValue());
		
		// creo vettore di nodi X TEST!
		Nodo[][] nodiTest = new Nodo[dimensioneMappa.getKey()][dimensioneMappa.getValue()];
		for (int i = 0; i < nodiTest.length; i++) {
		//	System.out.println(i);
			for (int j = 0; j < nodiTest[i].length; j++) {
		//		System.out.println(j);
				if(i == 3 && j ==1 ) {
					nodiTest[i][j] = new Base(utente);
				}
				else {
					nodiTest[i][j] = new Cloud(sistema);
				}
				
			}
		}

		MapData mapData = new MapData(nodiTest);

		// Always Will be rendered basically - Ci si riporta sempre alla mappa di base

		BasicMap basicMap = new BasicMap(mapData, basicGC);

		basicMap.selected = true;

		ArrayList<Map> mapOrder = new ArrayList<>();
		mapOrder.add(basicMap);

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

		// Adding Canvases to BorderPane
		Pane pane = new Pane();
		pane.getChildren().addAll(basicCanvas);
		
		//definisco: colore sfondo di pane; arrotondamento angoli; 
		pane.setBackground(new Background(new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(-10))) ); 
		pane.setPadding(STANDARD_PADDING);

		// Adding Checkboxes to a VBox on Right
		VBox controlloBattaglia = new VBox();
		controlloBattaglia.setSpacing(5);
		controlloBattaglia.setPadding(STANDARD_PADDING);
		
		// aggiungiamo progressBar nella VBOX
		HBox progress1 = new HBox();
		progress1.setSpacing(5);
		ProgressBar pb1 = new ProgressBar(0);
		Label batt1 = new Label("Battaglia 1");
		progress1.getChildren().addAll(batt1,pb1);
		controlloBattaglia.getChildren().add(progress1);
		

		// Setting BorderPane
		BorderPane.setMargin(pane, new Insets(20));
		borderPane.setTop(menuBar);
		borderPane.setCenter(pane);
		borderPane.setBottom(controls);
		borderPane.setRight(controlloBattaglia);
		
		
		// Adding MenuBar and BorderPane to a VBox Holder
		holder.getChildren().addAll(menuBar, borderPane);

		/*
		 * Map Checkbox Listeners - SOLO PER ESEMPIO
		 * terrainCheck.selectedProperty().addListener((ov, old, newValue) -> {
		 * terrainMap.selected = newValue; mapChange = true; });
		 */

		
		pane.setOnMouseClicked(event -> {
			Hexagon est = mapData.pixelToHex(new Point(event.getX(), event.getY()));
			HexData data = mapData.getHexData(est);
			Nodo nodo = data.nodo;

			coordinate.setText("Coordinate: " + est.x + " , " + est.y + " Possessore: " + nodo.getPossessore().getNome() + " SWdisp: " + nodo.getSoftware_disponibile());
			
			basicMap.drawMap(est);
		});

		primaryStage.setScene(new Scene(holder));
		primaryStage.show();

		new AnimationTimer() {
			@Override
			public void handle(long now) {
		
		//pulisco la mappa
				basicGC.clearRect(0, 0, basicCanvas.getWidth(), basicCanvas.getHeight());
		
		// disegno la mappa
				basicMap.drawMap();
			}
		}.start();
	}
}