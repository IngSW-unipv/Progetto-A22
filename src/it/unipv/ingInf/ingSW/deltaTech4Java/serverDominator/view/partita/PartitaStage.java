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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PartitaStage extends Stage{
	
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
	//TODO: FACCIATA ALLA PARTITA
	//CLASSE CON RESPONSABITLIA' DI: CREARE I PANELLI DELLA PARTITA, ESPORRE
	//I METODI DI UTILITà PER I CONTROLLER, INIZIALIZZARE LEI LO SCHEDULING PER I VARI PROGRESS BAR??
	//DA VEDERE SE FUNZIONA; EVENTUALMENTE METTIAMO UN TREAD PURE FABRICATION CHE SI PRENDE STA RESPONSABILITà
	//
	
	public PartitaStage(Classifica classifica, Base baseUtente,Nodo[][] nodi, Integer nodiAltezzaMappa,Integer nodiLarghezzaMappa) {
		super();
		this.statsNodePane=new StatsNodePane();
		this.battleBox=new ProgressBarConteiner();
		this.poweUpBox=new ProgressBarConteiner();
		this.actionPane=new ActionPane();
		this.classificaPane=new ClassificaPane(classifica);
		this.baseStatsPane=new BaseStatsPane(baseUtente);
		this.selectedBase=baseUtente;
		this.dimensioni=new Pair<Integer, Integer>(nodiAltezzaMappa, nodiLarghezzaMappa);
		this.scrollPaneMaker();
		this.log=new TextBox(baseUtente, logScrollPane);
		this.playTableMaker(nodi);
		
	}
	
	public PartitaStage(MainDefinitivo main,Base baseUtente) {
		this(main.getClassifica(),baseUtente,main.getTabellone().getMap(),main.getX_max(),main.getY_max());
	}
	
	private void scrollPaneMaker() {
		logScrollPane=new ScrollPane();
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
				selectedNode=data.nodo;
				selectedHexagon=est;
				selectedPoint=new Point(Math.floor(est.getX()+(est.getY()/2)) , est.getY());
				basicMap.drawMap(est);
				setSelectedNodeProperty();

			}
		});
	}
	/**
	 * Costruisce la basic map
	 * @param mappa
	 * Dati della mappa da rappresentare
	 * @param contestoGrafico
	 * COntesto grafico
	 */
	private void basicMapMaker(MapData mappa,GraphicsContext contestoGrafico) {
		 basicMap = new BasicMap(mappa, contestoGrafico);
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
		statsNodePane.getTitleL().setText("Stats Node: " + selectedHexagon.getX() + " , " + selectedHexagon.getY());
		statsNodePane.getOwner().setText("Owner: " + selectedNode.getPossessore().getNome());
		statsNodePane.getDistance().setText("Base distance: " + selectedNode.getDist_base());
		statsNodePane.getEnergy().setText("Energy: " + selectedNode.getE_disponibile());
		statsNodePane.getFwLvl().setText("Firewall Level: " + String.valueOf(selectedNode.getLvl_firewall()));
		statsNodePane.getRamLvl().setText("Ram Level: " + String.valueOf(selectedNode.getLvl_ram()));
		statsNodePane.getCpuLvl().setText("CPU Level: " + String.valueOf(selectedNode.getLvl_cpu()));
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
	
	
}
