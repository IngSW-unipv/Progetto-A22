package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import javafx.scene.layout.Pane;

public class PartitaPane extends Pane{
	private ActionPane actionPane;
	private BaseStatsPane baseStatsPane;
	private StatsNodePane statsNodePane;
	private ProgressBarConteiner batteBox;
	private ProgressBarConteiner developmentBox;
	private ProgressBarConteiner poweUpBox;
	//TODO: FACCIATA ALLA PARTITA
	//CLASSE CON RESPONSABITLIA' DI: CREARE I PANELLI DELLA PARTITA, ESPORRE
	//I METODI DI UTILITà PER I CONTROLLER, INIZIALIZZARE LEI LO SCHEDULING PER I VARI PROGRESS BAR??
	//DA VEDERE SE FUNZIONA; EVENTUALMENTE METTIAMO UN TREAD PURE FABRICATION CHE SI PRENDE STA RESPONSABILITà
	//
	
	
	
	
	
	
	public ActionPane getActionPane() {
		return actionPane;
	}
	public void setActionPane(ActionPane actionPane) {
		this.actionPane = actionPane;
	}
	public BaseStatsPane getBaseStatsPane() {
		return baseStatsPane;
	}
	public void setBaseStatsPane(BaseStatsPane baseStatsPane) {
		this.baseStatsPane = baseStatsPane;
	}
	public StatsNodePane getStatsNodePane() {
		return statsNodePane;
	}
	public void setStatsNodePane(StatsNodePane statsNodePane) {
		this.statsNodePane = statsNodePane;
	}
	public ProgressBarConteiner getBatteBox() {
		return batteBox;
	}
	public void setBatteBox(ProgressBarConteiner batteBox) {
		this.batteBox = batteBox;
	}
	public ProgressBarConteiner getDevelopmentBox() {
		return developmentBox;
	}
	public void setDevelopmentBox(ProgressBarConteiner developmentBox) {
		this.developmentBox = developmentBox;
	}
	public ProgressBarConteiner getPoweUpBox() {
		return poweUpBox;
	}
	public void setPoweUpBox(ProgressBarConteiner poweUpBox) {
		this.poweUpBox = poweUpBox;
	}
	
	
}
