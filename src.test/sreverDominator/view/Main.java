package sreverDominator.view;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.main.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa.MappaDefinitiva;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.PlayTable;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa.BasicMap;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa.MapData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ActionPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.BaseStatsPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ProgressBarConteiner;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.StatsNodePane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.util.menu.SDMenuBar;

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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Main extends Application {

	enum LivelloDiGioco {
		Easy, Medium, Hard

	}

	public final Insets STANDARD_PADDING = new Insets(10);
	PopUpFacade popUp = new PopUpFacade();

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

	int click = 0;
	
	
	@Override
	public void start(Stage primaryStage) { 		// start della costruzione grafica
		primaryStage.setTitle("Server Dominator");
		primaryStage.setIconified(true); 			// se TRUE lo avvia ridotto a icona
		primaryStage.setFullScreen(false); 			// apre in full screen
		//primaryStage.setX(0); 						// definisce la coordinata X dell'angolo in alto a sinistra della finestra
		//primaryStage.setY(0); 						// definisce la coordinata Y dell'angolo in alto a sinistra della finestra
		//primaryStage.centerOnScreen();
		
		Pair<Integer, Integer> dimensioneMappa = getDimensioniMappa(LivelloDiGioco.Hard);
		
		MainDefinitivo mainView = new MainDefinitivo();
		try {
			mainView.avvioPartita(dimensioneMappa.getKey(), dimensioneMappa.getValue(), "Matteo", 10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		VBox holder = new VBox();
		BorderPane borderPane = new BorderPane();
		
		Giocatore[] gioc = mainView.getGiocatori();
		Nodo baseU = mainView.getTabellone().trovaBase(gioc[1]);
		Base bU = new Base(baseU.getPossessore());
		
		MappaDefinitiva mappa = new MappaDefinitiva(dimensioneMappa.getKey(), dimensioneMappa.getValue(), gioc);
		mappa.assegnamento(gioc.length-1, gioc);
		Nodo[][] nodiView = mappa.getMap();
		MapData mapData = new MapData(nodiView);
		
		int ray = mapData.getRay();
		

// ------ Tavolo di gioco ----------//
		
		ScrollPane centerPane = new ScrollPane();
		PlayTable pt = new PlayTable(dimensioneMappa);
		centerPane = pt.getPlayTable(dimensioneMappa, mapData, ray);
		
		Canvas basicCanvas = pt.getBasicCanvas();
		//GraphicsContext basicGC = pt.getBasicCanvas();
		BasicMap basicMap = new BasicMap(mapData, basicCanvas.getGraphicsContext2D());
		
		basicMap.setSelected(true);

		//ArrayList<Map> mapOrder = new ArrayList<>();
		//mapOrder.add(basicMap);

		StatsNodePane sPane = new StatsNodePane();
		//offset scroll risolto
		centerPane.setOnMouseClicked(event -> {
			double xMouse=event.getX();
			double yMouse=event.getY();
			double xBase=pt.getBasicCanvas().getWidth()-(pt.getScrollPane().getViewportBounds().getMaxX()-pt.getScrollPane().getViewportBounds().getMinX());
			double yBase=pt.getBasicCanvas().getHeight()-(pt.getScrollPane().getViewportBounds().getMaxY()-pt.getScrollPane().getViewportBounds().getMinY());
			double xOffset=(pt.getScrollPane().getHvalue()/pt.getScrollPane().getHmax())*xBase;
			double yOffset=(pt.getScrollPane().getVvalue()/pt.getScrollPane().getVmax())*yBase;
			Hexagon est = mapData.pixelToHex(new Point(xMouse+xOffset,yMouse+yOffset));
			HexData data = mapData.getHexData(est);
			
			if (data != null) { 			// controllo se il click avviene fuori dagli esagoni
				
				Nodo nodo = data.nodo;
					
		// ---- Valori in Stats Node ---------//
				
			
				
				sPane.getTitleL().setText("Stats Node: " + est.getX() + " , " + est.getY());
				sPane.getOwner().setText("Owner: " + nodo.getPossessore().getNome());
				sPane.getEnergy().setText("Energy: " + nodo.getE_disponibile());
				sPane.getFwLvl().setText("Firewall Level: " + String.valueOf(nodo.getLvl_firewall()));
				sPane.getRamLvl().setText("Ram Level: " + String.valueOf(nodo.getLvl_ram()));
				sPane.getCpuLvl().setText("CPU Level: " + String.valueOf(nodo.getLvl_cpu()));
				
				basicMap.drawMap(est);

			}
		});
		
		
		
		
// ----------- Men?? ----------- //
		
		SDMenuBar sdM = new SDMenuBar();
		MenuBar sdMenuBar = new MenuBar();
		sdMenuBar = sdM.getMenuBar();		
		
// -------- sottoMappa ------------//

		HBox sottoMappa = new HBox();

		
		Pane statsNodePane = new Pane();
		statsNodePane = sPane.getSnPane();
		Pane battlePane = new ProgressBarConteiner();
		/*
		BattlePane bPane = new BattlePane(bU);
		Pane battlePane = new Pane();
		battlePane = bPane.getBattlePain(bU);
		*/
		ActionPane aPane = new ActionPane();
		Pane actionPane = new Pane();
		actionPane = aPane.getActionPane();

// ------------------------------------------ //		
		
		HBox.setHgrow(statsNodePane, Priority.ALWAYS);
		HBox.setHgrow(battlePane, Priority.ALWAYS);
		HBox.setHgrow(actionPane, Priority.ALWAYS);
		
		sottoMappa.getChildren().addAll(statsNodePane, battlePane, actionPane);

// ------------------------------------------ //
		
	// Pane yourBase a destra della mappa 

		BaseStatsPane rPane = new BaseStatsPane(bU);
		Pane yourBasePane = new Pane();
		yourBasePane = rPane.getBsPane(bU);

	// Setting BorderPane

		borderPane.setTop(sdMenuBar);
		borderPane.setCenter(centerPane);
		BorderPane.setMargin(centerPane, new Insets(10));
		borderPane.setBottom(sottoMappa);
		BorderPane.setMargin(sottoMappa, new Insets(0, 10, 10, 10));
		borderPane.setRight(yourBasePane);
		BorderPane.setMargin(yourBasePane, new Insets(10, 10, 10, 0));

	// Adding MenuBar and BorderPane to a VBox Holder
		
		holder.getChildren().addAll(sdMenuBar, borderPane);
		
// ------------------------------------------ //		

		/**
		 * Rilevo il numero di basi da piazzare numBasi Nel ciclo di creazione del
		 * vettore nodi verifico se un nodo ha le coordinate di una base Se si allora
		 * recupero il nome del giocatore Giocatore.getNome(giocatore); di quella base
		 * Definisco il nodo come base del Giocatore giocatore Assegno il colore al nodo
		 * i,j nodo[i][j].colore = getColorePossessore(giocatore);
		 **/

		/*
		 * Map Checkbox Listeners - SOLO PER ESEMPIO
		 * terrainCheck.selectedProperty().addListener((ov, old, newValue) -> {
		 * terrainMap.selected = newValue; mapChange = true; });
		 */
		basicMap.drawMap();
	
		Scene scena = new Scene(holder);
		scena.getStylesheets().add("application.css");
		primaryStage.setScene(scena);
		primaryStage.show();
		
		/*new AnimationTimer() {
			@Override
			public void handle(long now) {

				// pulisco la mappa
				basicCanvas.getGraphicsContext2D().clearRect(0, 0, basicCanvas.getWidth(), basicCanvas.getHeight());

				// disegno la mappa
				basicMap.drawMap();
				
			}
		}.start();*/
	}
}