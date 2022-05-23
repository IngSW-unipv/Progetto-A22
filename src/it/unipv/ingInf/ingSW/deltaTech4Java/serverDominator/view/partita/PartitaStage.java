package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.PlayTable;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa.BasicMap;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa.MapData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ActionPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.BaseStatsPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ClassificaPane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.ProgressBarConteiner;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.StatsNodePane;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.TextBox;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.util.ProgressStyle;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.util.menu.SDMenuBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

public abstract class PartitaStage extends Stage{
	
	/**
	 * Pannello d'azione che contiene i Pulzanti:
	 * mercato, produzioneSW, potenziamenti risorse
	 */
	private ActionPane actionPane;
	
	/**
	 * Pannello che vontiene informazione della base e 
	 * i pulsanti per cambiare base;
	 * 
	 */
	private BaseStatsPane baseStatsPane;
	
	/**
	 * pannello che condiene le indormazioni del nodo selezionato
	 * e il pulsante per attaccare
	 */
	private StatsNodePane statsNodePane;
	
	/**
	 * Pannello che contiene i progressBar 
	 * degli attacchi/difese del giocatore
	 */
	private ProgressBarConteiner battleBox;
	
	/**
	 * Pannello che contiene i progressBar 
	 * degli sviluppi sW/hw che sta facendo il giocatore
	 */
	private ProgressBarConteiner poweUpBox;
	
	/**
	 * Tavolo da gioco contenete gli esagoni
	 */
	private PlayTable playTable;
	
	/**
	 * Classifica
	 */
	private ClassificaPane classificaPane;
	
	/**
	 * Contenitore dei risultati, e la comunicazione con il giocatore
	 */
	private TextBox log;
	/**
	 * scrollpane che contiene i log {@link TextBox}
	 */
	private ScrollPane logScrollPane;
	
	/**
	 * Coordinate del punto selezionato (secondo la convenzione matriciale)
	 */
	private Point selectedPoint; 
	
	/**
	 * Esegono selezionato
	 */
	private Hexagon selectedHexagon; 
	
	/**
	 * Nodo selezionato
	 */
	private Nodo selectedNode;
	
	/**
	 * base le cui proprietà sono visualizzate 
	 */
	private Base selectedBase;
	
	/**
	 * dimensioni del tavolo da gioco
	 */
	private Pair<Integer, Integer> dimensioni;
	
	/**
	 * Disegnatrice di mappe
	 */
	private BasicMap basicMap;
	
	/**
	 * 
	 */
	private SDMenuBar menuBar;
	/**
	 * Pannello che contiene la progress bar di fine partirta
	 */
	private ProgressBarConteiner fineProgress;
	
	public PartitaStage(Classifica classifica, Base baseUtente,Nodo[][] nodi, Integer nodiAltezzaMappa,Integer nodiLarghezzaMappa,int durataPartitaSeconds) {
		super();
		super.setMinHeight(800);
		super.setMinWidth(1000);
		super.setAlwaysOnTop(false);
		this.menuBarCreator();
		this.statsNodePane=new StatsNodePane();
		this.battleBox=new ProgressBarConteiner();
		this.battleBox.setTitle("BATTAGLIE IN CORSO");
		this.poweUpBox=new ProgressBarConteiner();
		this.poweUpBox.setTitle("PRODUZIONI IN CORSO");
		this.fineProgress=new ProgressBarConteiner();
		
		this.actionPane=new ActionPane();
		this.classificaPaneCreator(classifica);
		this.baseStatsPane=new BaseStatsPane(baseUtente);
		this.selectedBase=baseUtente;
		this.dimensioni=new Pair<Integer, Integer>(nodiAltezzaMappa, nodiLarghezzaMappa);
		this.logScrollPaneMaker(baseUtente);
		this.playTableMaker(nodi);
		this.poweUpBox.addDrawable(baseStatsPane);
		this.initPlayTableListener();
		this.fineProgress.addElement("", durataPartitaSeconds*1000, ProgressStyle.BLACK_STYLE);
		this.fineProgress.setTitle("FINE PARTITA");
		this.disponiPannelli();
	}

	public PartitaStage(MainDefinitivo main,Base baseUtente,int durataPartitaSeconds) {
		this(main.getClassifica(),baseUtente,main.getTabellone().getMap(),main.getTabellone().getX_max(),main.getTabellone().getY_max(),durataPartitaSeconds);
	}
	
	public void classificaPaneCreator(Classifica c) {
		this.classificaPane=new ClassificaPane(c);
		this.classificaPane.setStyle("-fx-background-color: #000000; -fx-background-radius: 15px;");
	}
	
	/**
	 * metodocontenente le istruzioni aggiuntive che si eseguono al momento 
	 * di un clic sui nodi 
	 * (se il nodo selezionato è valido)
	 */
	public abstract void doOnClic();
	
	/**
	 * metodo che deve contenere le istuzoni per far finire il gioco
	 */
	public abstract void fineGioco();
	
	public void disponiPannelli(){
		Background black=new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), null));
		Background rose=new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), null));
		
		//Righet in the borderPane
		VBox vBaseClassificaBox=new VBox();
		ScrollPane gp=new ScrollPane(putPaneInAscrollableGridPane(
				this.baseStatsPane.getBsPane(selectedBase), rose,null));
		
		gp.setFitToWidth(true);
		VBox.setVgrow(gp, Priority.ALWAYS);
		
		GridPane finePa=putPaneInAscrollableGridPane(fineProgress, black,null);
		finePa.setMinHeight(5);
		finePa.setMinWidth(5);
		ScrollPane sp=new ScrollPane(finePa);
		sp.setFitToWidth(true);
		ScrollPane classificaPane=new ScrollPane(putPaneInAscrollableGridPane(this.classificaPane, null, null));
		classificaPane.setFitToWidth(true);
		VBox.setVgrow(classificaPane, Priority.ALWAYS);
		VBox.setVgrow(sp, Priority.ALWAYS);
		vBaseClassificaBox.setSpacing(10);
		vBaseClassificaBox.getChildren().addAll(gp,sp,classificaPane);
		
		//Center in the borderPane
		playTable.getScrollPane().setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		playTable.getScrollPane().setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		GridPane stateNodoPane=
				this.putPaneInAscrollableGridPane(this.statsNodePane.getSnPane(),
						rose,new Background(new BackgroundFill(Color.web("#f8cecc"), null, null))) ;
		
		ScrollPane battleBoxScrollpane=new ScrollPane(
				this.putPaneInAscrollableGridPane(this.battleBox,
						black,new Background(new BackgroundFill(Color.web("#000000"), null, null))));
		battleBoxScrollpane.setFitToWidth(true);
		ScrollPane powrUpScrollPane=new ScrollPane(this.putPaneInAscrollableGridPane(this.poweUpBox,black,new Background(new BackgroundFill(Color.web("#000000"), null, null))));
		powrUpScrollPane.setFitToWidth(true);
		HBox hProgressBox=new HBox();
		HBox.setHgrow(powrUpScrollPane, Priority.ALWAYS);
		HBox.setHgrow(battleBoxScrollpane, Priority.ALWAYS);

		hProgressBox.getChildren().addAll(battleBoxScrollpane,powrUpScrollPane);
		hProgressBox.setMinHeight(190);
		hProgressBox.setSpacing(10);
		hProgressBox.setMaxHeight(190);
		logScrollPane.setMaxHeight(40);
		
		VBox vComunicationBox=new VBox();
		VBox.setVgrow(vComunicationBox, Priority.ALWAYS);
		vComunicationBox.setSpacing(10);
		vComunicationBox.getChildren().addAll(hProgressBox,logScrollPane);
		
		GridPane actioPane=this.putPaneInAscrollableGridPane(this.actionPane.getActionPane(),rose,new Background(new BackgroundFill(Color.web("#f8cecc"), null, null)));
		//buttom in borderPane
		HBox hBottomBox=new HBox();
		hBottomBox.setSpacing(10);
		HBox.setHgrow(vComunicationBox, Priority.ALWAYS);
		HBox.setHgrow(stateNodoPane, Priority.ALWAYS);
		HBox.setHgrow(actioPane, Priority.ALWAYS);

		hBottomBox.getChildren().addAll(stateNodoPane,vComunicationBox,actioPane);
		hBottomBox.setMinHeight(250);
		hBottomBox.setMaxHeight(250);
		BorderPane borderPane = new BorderPane();;
		borderPane.setCenter(playTable.getScrollPane());
		BorderPane.setMargin(playTable.getScrollPane(), new Insets(10));
		borderPane.setRight(vBaseClassificaBox);
		BorderPane.setMargin(vBaseClassificaBox, new Insets(10));

		borderPane.setBottom(hBottomBox);
		BorderPane.setMargin(hBottomBox, new Insets(10));

		VBox vPartitaBox=new VBox();
		vPartitaBox.setSpacing(10);
		vPartitaBox.getChildren().addAll(menuBar.getMenuBar(),borderPane);
		Scene scena = new Scene(vPartitaBox,1000,800);
		scena.getStylesheets().add("application.css");
		super.setScene(scena);
		this.drowMappa();
	}

	public void menuBarCreator() {
		menuBar=new SDMenuBar();
		MenuItem finePartita=new MenuItem("Fine partita");
		finePartita.setOnAction(actionEvent ->{
			fineGioco();
		});
		menuBar.addItems(new Menu("Menu"), finePartita);
		
	}
	/**
	 * Cosctuisce il tavolo da gioco
	 * @param nodi
	 * Nodi di cui è costituita la mappa
	 */
	public void playTableMaker(Nodo[][] nodi) {
		MapData mapData = new MapData(nodi);
		playTable= new PlayTable(dimensioni);
		playTable.getPlayTable(dimensioni, mapData, mapData.getRay());
		
		this.basicMapMaker(playTable.getMapData(), playTable.getBasicCanvas().getGraphicsContext2D());
		basicMap.setSelected(true);
		basicMap.drawMap();
	}
	
	/**
	 * 
	 */
	public void initPlayTableListener() {
		playTable.getScrollPane().setOnMouseClicked(event -> {
			double xMouse=event.getX();
			double yMouse=event.getY();
			double xBase=playTable.getBasicCanvas().getWidth()-(playTable.getScrollPane().getViewportBounds().getMaxX()-playTable.getScrollPane().getViewportBounds().getMinX());
			double yBase=playTable.getBasicCanvas().getHeight()-(playTable.getScrollPane().getViewportBounds().getMaxY()-playTable.getScrollPane().getViewportBounds().getMinY());
			double xOffset=(playTable.getScrollPane().getHvalue()/playTable.getScrollPane().getHmax())*xBase;
			double yOffset=(playTable.getScrollPane().getVvalue()/playTable.getScrollPane().getVmax())*yBase;
			Hexagon est = playTable.getMapData().pixelToHex(new Point(xMouse+xOffset,yMouse+yOffset));
			HexData data = playTable.getMapData().getHexData(est);
			
			if (data != null) { 			// controllo se il click avviene fuori dagli esagoni
				this.selectedNode=data.nodo;
				this.selectedHexagon=est;
				this.selectedPoint=new Point(Math.floor(est.getX()+(est.getY()/2)) , est.getY());
				this.basicMap.drawMap(est);
				this.setSelectedNodeProperty();
				this.doOnClic();
			}
		});
	}
	
	/**
	 * 
	 */
	public void drowMappa() {
		basicMap.drawMap();
	}

	/**
	 * Metodo per settare la base attuale e le relative caratteristiche 
	 * all'internodel pannello
	 * @param nuovaBaseSelezionata
	 */
	public void changeSelectedBase(Base nuovaBaseSelezionata) {
		this.baseStatsPane.setNodeBase(nuovaBaseSelezionata);
		this.baseStatsPane.make();
		this.selectedBase=nuovaBaseSelezionata;
	}
	
	/**
	 * metodo per settare la visibilità dei buttoni 
	 * all'interno del pannello delle informazione della base attuale
	 * @param indietroButton
	 * buttone indietro
	 * @param avantiButton
	 * buttone avanti
	 */
	public void setButtonsVisibilityInActionPaneStatsPane(boolean indietroButton , boolean avantiButton) {
		this.baseStatsPane.getButtonBack().setDisable(!indietroButton);
		this.baseStatsPane.getButtonNext().setDisable(!avantiButton);
	}
	
	/**
	 * Metodo per settare le visibilità dei bottoni all'interno del
	 * pannello di azioni 
	 * @param mercatoBtn
	 * buttone del mercato
	 * @param sviluppoSoftwareBtn
	 * buttone di sviluppo software
	 * @param potenziaRisorseBtn
	 * bottone per potenziamento risorse
	 */
	public void setButtonsVisibilityInActionPane(boolean mercatoBtn,boolean sviluppoSoftwareBtn,boolean potenziaRisorseBtn) {
		this.actionPane.getDevelop().setDisable(!sviluppoSoftwareBtn);
		this.actionPane.getActionMarketL().setDisable(!mercatoBtn);
		this.actionPane.getPowerUpL().setDisable(!potenziaRisorseBtn);
	}
	
	/**
	 * aggiornamento delle proprietà del nodo selezionato
	 */
	public void setSelectedNodeProperty() {
		statsNodePane.getTitleL().setText("Stats Node:\t" + selectedHexagon.getX() + " , " + selectedHexagon.getY());
		statsNodePane.getOwner().setText("Owner:\t" + selectedNode.getPossessore().getNome());
		statsNodePane.getEnergy().setText("Energy:\t" + selectedNode.getE_disponibile());
		statsNodePane.getFwLvl().setText("Firewall Level:\t" + String.valueOf(selectedNode.getLvl_firewall()));
		statsNodePane.getRamLvl().setText("Ram Level:\t" + String.valueOf(selectedNode.getLvl_ram()));
		statsNodePane.getCpuLvl().setText("CPU Level:\t" + String.valueOf(selectedNode.getLvl_cpu()));
	}
	
	/**
	 * Metodo per inserire una stringa 
	 * di testo nell'apposito contenitore 
	 */
	public void addAvviso(String text) {
		this.log.insertText(text);
	}
	
	/**
	 * Aggiungere un attacco nel pannello delle battaglie
	 * @param titolo
	 * Titolo della battaglia
	 * @param durata
	 * durata della Battaglia
	 */
	public void addAttacco(String titolo,int durata) {
		battleBox.addElement(titolo, durata, ProgressStyle.GREEN_STYLE);
	}
	
	/**
	 * Aggiungere difesa pannello delle battaglie
	 * @param titolo
	 * Titolo della battaglia
	 * @param durata
	 * Durata della Battaglia
	 */
	public void addDifesa(String titolo,int durata) {
		battleBox.addElement(titolo, durata, ProgressStyle.RED_STYLE);
	}
	
	/**
	 * Aggiungere potenziamento risorsa in corso
	 * nel pannello dedicato ai potenziamento
	 * @param titolo
	 * Titolo della battaglia
	 * @param durata
	 * Durata del potenziamento
	 */
	public void addPtenziamentoRisorsa(String titolo,int durata) {
		poweUpBox.addElement(titolo, durata, ProgressStyle.ORANGE_STYLE);
	}
	
	/**
	 *Aggiungere sviluppo Software in corso
	 * nel pannello dedicato agli sviluppi
	 * @param titolo
	 * Titolo della battaglia
	 * @param durata
	 * durata della produzione 
	 */
	public void addProduzioneSoftware(String titolo,int durata) {
		poweUpBox.addElement(titolo, durata, ProgressStyle.YELLO_STYLE);
	}
	
	public ScrollPane getLogScrollPane() {
		return logScrollPane;
	}

	public void setLogScrollPane(ScrollPane logScrollPane) {
		this.logScrollPane = logScrollPane;
	}

	public Hexagon getSelectedHexagon() {
		return selectedHexagon;
	}

	public void setSelectedHexagon(Hexagon selectedHexagon) {
		this.selectedHexagon = selectedHexagon;
	}

	public Nodo getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(Nodo selectedNode) {
		this.selectedNode = selectedNode;
	}

	public Pair<Integer, Integer> getDimensioni() {
		return dimensioni;
	}

	public void setDimensioni(Pair<Integer, Integer> dimensioni) {
		this.dimensioni = dimensioni;
	}

	public BasicMap getBasicMap() {
		return basicMap;
	}

	public void setBasicMap(BasicMap basicMap) {
		this.basicMap = basicMap;
	}

	/**
	 * Recupera la proprità selectedBase
	 * @return
	 * base attuale
	 */
	public Base getSelectedBase() {
		return selectedBase;
	}
	
	/**
	 * Setta la proprità selectedBase
	 * @param selectedBase
	 * base attuale
	 */
	public void setSelectedBase(Base selectedBase) {
		changeSelectedBase(selectedBase);
	}

	/**
	 * Recupera il pannello actionPane
	 * @return
	 * actionPane
	 */
	public ActionPane getActionPane() {
		return actionPane;
	}
	
	/**
	 * Recupera il punto selezionato secondo le coordinate matriciali
	 * @return
	 * coordinate dell'esagono selezionato
	 */
	public Point getSelectedPoint() {
		return selectedPoint;
	}
	
	/**
	 * @param selectedPoint
	 */
	public void setSelectedPoint(Point selectedPoint) {
		this.selectedPoint = selectedPoint;
	}
	
	/**
	 * @param actionPane
	 */
	public void setActionPane(ActionPane actionPane) {
		this.actionPane = actionPane;
	}
	
	/**
	 * @return
	 */
	public BaseStatsPane getBaseStatsPane() {
		return baseStatsPane;
	}
	
	/**
	 * @param baseStatsPane
	 */
	public void setBaseStatsPane(BaseStatsPane baseStatsPane) {
		this.baseStatsPane = baseStatsPane;
	}
	
	/**
	 * @return
	 */
	public StatsNodePane getStatsNodePane() {
		return statsNodePane;
	}
	
	/**
	 * @param statsNodePane
	 */
	public void setStatsNodePane(StatsNodePane statsNodePane) {
		this.statsNodePane = statsNodePane;
	}
	
	/**
	 * @return
	 */
	public ProgressBarConteiner getBattleBox() {
		return battleBox;
	}
	
	/**
	 * @param batteBox
	 */
	public void setBattleBox(ProgressBarConteiner batteBox) {
		this.battleBox = batteBox;
	}
	
	/**
	 * @return
	 */
	public ProgressBarConteiner getPoweUpBox() {
		return poweUpBox;
	}
	
	/**
	 * @param poweUpBox
	 */
	public void setPoweUpBox(ProgressBarConteiner poweUpBox) {
		this.poweUpBox = poweUpBox;
	}
	
	/**
	 * @return
	 */
	public PlayTable getPlayTable() {
		return playTable;
	}
	
	/**
	 * @param playTable
	 */
	public void setPlayTable(PlayTable playTable) {
		this.playTable = playTable;
	}
	
	/**
	 * @return
	 */
	public ClassificaPane getClassificaPane() {
		return classificaPane;
	}
	
	/**
	 * Setta il pannello della classifica con quello passato
	 * @param classificaPane
	 * Pannello di classifica
	 */
	public void setClassificaPane(ClassificaPane classificaPane) {
		this.classificaPane = classificaPane;
	}
	
	/**
	 * Recupera la {@link TextBox} degli avvisi
	 * @return
	 * TextBox degli avvisi
	 */
	public TextBox getLog() {
		return log;
	}
	
	/**
	 * Setta la TextBox degli avvisi con l'argomento passato
	 * @param log
	 * TextBox da settare
	 */
	public void setLog(TextBox log) {
		this.log = log;
	}

	
	public SDMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(SDMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public ProgressBarConteiner getFineProgress() {
		return fineProgress;
	}

	public void setFineProgress(ProgressBarConteiner fineProgress) {
		this.fineProgress = fineProgress;
	}

	private void basicMapMaker(MapData mappa,GraphicsContext contestoGrafico) {
		 basicMap = new BasicMap(mappa, contestoGrafico);
	}

	private void logScrollPaneMaker(Base baseUtente) {
		this.logScrollPane= new ScrollPane();
		this.logScrollPane.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(10, 10, 10, 10))));
		this.log = new TextBox(null, logScrollPane);
	}
	private GridPane putPaneInAscrollableGridPane(Pane node,Background b,Background bPane) {
		GridPane gp=new GridPane();
		gp.setMinHeight(190);
		gp.setBackground(b);
		gp.setAlignment(Pos.TOP_CENTER);
		gp.add(node, 1, 1);
		node.setBackground(bPane);
		return gp;
	}
	
}
