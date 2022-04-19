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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Bot;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Sistema;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;

public class Main extends Application {
	public static String ATTACCO_KEY="serverDominator.viw.map.attacco";
	public static String FILE_KEY="serverDominator.viw.map.file";
	public static String SAVE_KEY="serverDominator.viw.map.save";
	public static String OPEN_KEY="serverDominator.viw.map.open";
	public static String COORDINATE_KEY="serverDominator.viw.map.coordinate";
	public static String SW_DISP_KEY="serverDominator.viw.map.swDisp";
	public static String STATE_KEY="serverDominator.viw.map.state";
	public static String ADD_KEY="serverDominator.viw.map.add";
	public static String BATTAGLIA_KEY="serverDominator.viw.map.battaglia";
	public static String POSSESSORE_KEY="serverDominator.viw.map.possessore";
	
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

	public void addType(HBox controls, String titolo, EventHandler<? super MouseEvent> event) { // HBOX ï¿½ un contenitore
																								// orizzontale (VBOX
																								// verticale)
		Button button = new Button(titolo); // do al bottone il nome
		button.setOnMouseClicked(event);

		controls.getChildren().add(button); // aggiunge il bottone al layout grafico
	}

	// costruisce la HBOX con i controlli di gioco
	public void setUpControls(HBox setUpControls) {
<<<<<<< HEAD
		addType(setUpControls, "Attacca!", (event -> {
			
			// qui va inserita l'azione da compiere quando click on "Attacca!"
			
=======
		addType(setUpControls, PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(ATTACCO_KEY, "Attacca!"), (event -> {
			// qui da inserire l'azione che si deve compiere quando click on "Attacca!"
>>>>>>> refs/remotes/origin/main
		}));
		setUpControls.setSpacing(10); 					// spazio tra un bottone e l'altro
        setUpControls.setPadding(STANDARD_PADDING); 	// margini del testo dentro al bottone
	}

	@Override
	public void start(Stage primaryStage) { 			// start della costruzione grafica
		primaryStage.setTitle("Server Dominator");
		primaryStage.setIconified(false);				// se TRUE lo avvia ridotto a icona
		primaryStage.setFullScreen(false);				// apre in full screen
		primaryStage.setX(0);							// definisce la coordinata X dell'angolo in alto a sinistra della finestra
		primaryStage.setY(0);							// definisce la coordinata Y dell'angolo in alto a sinistra della finestra
		
		Pair<Integer, Integer> dimensioneMappa = getDimensioniMappa(LivelloDiGioco.Hard);

		VBox holder = new VBox();
		BorderPane borderPane = new BorderPane();

		// Canvass = Sfondo rettangolare su cui vengono disegnati rettangoli
		double bordoLarghezza , bordoAltezza , larghezzaE , altezzaE;
		bordoLarghezza = 8;
		bordoAltezza = 0;
		larghezzaE = 44.5;
		altezzaE = 39.4; 
		Canvas basicCanvas = new Canvas(dimensioneMappa.getValue()*larghezzaE+bordoLarghezza, dimensioneMappa.getKey()*altezzaE+bordoAltezza);
		GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
				
		// VBox che contiene le HBox sotto alla mappa
		VBox controls = new VBox();
		HBox terrainControls = new HBox();
		HBox datiNodo = new HBox();
		Label coordinate = new Label();
		datiNodo.getChildren().add(coordinate);
		datiNodo.setPadding(STANDARD_PADDING);
		
		
		controls.getChildren().addAll(datiNodo, terrainControls); // aggiungo le HBox datiNodo, terrainControls alla VBox controls

		setUpControls(terrainControls); // aggiunge le spaziature, i margini, etc...

		Sistema sistema = new Sistema(); sistema.colore = Colore.GRIGIO;
		Utente utente = new Utente("user"); utente.colore = Colore.GIALLO;
		
		MainDefinitivo main = new MainDefinitivo();
		main.avvioPartita(30, 20, "Matteo");
		Giocatore[] giocatori = main.getGiocatori();
		
		Mappa mappa = new Mappa(30, 20, giocatori);
		mappa.assegnamento(10, giocatori);
		
		
		
		
		// definiamo i nodi base
		
		/* Rilevo il numero di basi da piazzare numBasi
		 * Nel ciclo di creazione del vettore nodi verifico se un nodo ha le coordinate di una base
		 * Se si allora recupero il nome del giocatore Giocatore.getNome(giocatore); di quella base
		 * Definisco il nodo come base del Giocatore giocatore
		 * Assegno il colore al nodo i,j
		 * nodo[i][j].colore = getColorePossessore(giocatore);
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

		// MenuBar
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu(
				PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(FILE_KEY,  "File")
				);
		MenuItem saveItem = new MenuItem(
				PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(SAVE_KEY,"Save")
				);
		MenuItem openItem = new MenuItem(
				PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(OPEN_KEY,"Open")
				);
		fileMenu.getItems().addAll(saveItem, openItem);

		Menu stateMenu = new Menu("State");
		MenuItem addStateItem = new MenuItem(
				PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(ADD_KEY,"Add")
				);
		// addStateItem.setOnAction(event -> addState(politicalControls));
		stateMenu.getItems().add(addStateItem);

		menuBar.getMenus().addAll(fileMenu, stateMenu);

		// Adding Canvases to BorderPane
		Pane pane = new Pane();
		pane.getChildren().addAll(basicCanvas);
		
		//definisco: colore sfondo di pane; arrotondamento angoli; 
		pane.setBackground(new Background(new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(-10,-10,0,-10))) ); 
		//pane.setPadding(STANDARD_PADDING);

		// Adding Checkboxes to a VBox on Right
		VBox controlloBattaglia = new VBox();
		controlloBattaglia.setSpacing(5);
		controlloBattaglia.setPadding(STANDARD_PADDING);
		
		// aggiungiamo progressBar nella VBOX
		HBox progress1 = new HBox();
		progress1.setSpacing(5);
		ProgressBar pb1 = new ProgressBar(0);
		Label batt1 = new Label(
				PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(BATTAGLIA_KEY,"Battaglia")+
				//TODO: Qui bisogna rifare di modo da rendere il numero di battagli dinamico (giocatore--> più battaglie attive)
				""
				);
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

			coordinate.setText(
						PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(COORDINATE_KEY,"Coordinate: " )+
						est.x +
						" , " + 
						est.y + 
						PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(POSSESSORE_KEY," Possessore: ") +
						nodo.getPossessore().getNome() +
						PersistenceFacade.getInstance().getLanguegeList(FilesLanguageManager.getCurrentLanguage()).getProperty(SW_DISP_KEY," SWdisp: ") +
						nodo.getSoftware_disponibile()
					);
			
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