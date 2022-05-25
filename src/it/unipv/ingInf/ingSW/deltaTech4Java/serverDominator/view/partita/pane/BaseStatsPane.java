package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BaseStatsPane extends Pane implements IDrawable {

	
	private Nodo nodeBase;
	private Button buttonTitle;
	private Button buttonBack;
	private Button buttonNext;
	private Label ybEnergy = new Label();
	private Label ybFwLvl = new Label();
	private Label ybRamLvl = new Label();
	private Label ybCpuLvl = new Label();
	private Label ybAv = new Label();
	private Label ybVr = new Label();
	private Label ybRc = new Label();
	private Label avQy = new Label();
	private Label vrQy = new Label();
	private Label rcQy = new Label();
	public final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	
	public  BaseStatsPane(Nodo bU) {
		super();
		super.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		super.setPadding(STANDARD_PADDING);
		this.nodeBase = bU;
		this.buttonBack=ComponentCreator.getIstance().createButton("<<", Pos.TOP_CENTER);
		this.buttonNext=ComponentCreator.getIstance().createButton(">>", Pos.TOP_CENTER);
		this.ybEnergy = new Label();
		this.ybFwLvl = new Label();
		this.ybRamLvl = new Label();
		this.ybCpuLvl = new Label();
		this.ybAv = new Label();
		this.ybVr = new Label();
		this.ybRc = new Label();
		this.avQy = new Label();
		this.vrQy = new Label();
		this.rcQy = new Label();
	}
		
	public Pane getBsPane(Nodo bU) {
		nodeBase=bU;
		make();
		return this;
	}
	
	/**
	 * Metodo che genera la zona della view della partita con i dati aggiornati della base selezionata dal giocatore 
	 * con i pulsanti per scegliere quale base posseduta dal giocatore visualizzare
	 */
	public void make() {
		super.getChildren().clear();
		VBox yb = new VBox();
		yb.setPadding(STANDARD_PADDING);
		yb.setTranslateX(10);
		
		HBox ybTitle = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		buttonTitle = ComponentCreator.getIstance().createButton("Your Base Stats", Pos.BASELINE_CENTER);
		buttonTitle.setStyle("-fx-background-color:  #FAF9F6; -fx-background-radius: 15px; -fx-text-fill: #006400 ;");
		ybTitle.getChildren().add(buttonTitle);
//Color.DARKGREEN.get;
		HBox hBg = new HBox();
		hBg.setPadding(STANDARD_PADDING);
		GridPane ybG = new GridPane();
		ybG.setVgap(5);
		ybG.setHgap(20);
		ybG.setAlignment(Pos.BASELINE_LEFT);

		 ybEnergy = new Label();
		 ybFwLvl = new Label();
		 ybRamLvl = new Label();
		 ybCpuLvl = new Label();
		 ybAv = new Label();
		 ybVr = new Label();
		 ybRc = new Label();
		 avQy = new Label();
		 vrQy = new Label();
		 rcQy = new Label();

		ybEnergy.setText("Energy: ");
		ybFwLvl.setText("Firewall Lvl: ");
		ybRamLvl.setText("Ram Lvl: ");
		ybCpuLvl.setText("CPU Lvl: ");
		ybAv.setText("Antivirus disp: ");
		ybVr.setText("Virus disp: ");
		ybRc.setText("Rootcrash disp: ");
		avQy.setText("" + nodeBase.getSoftware_disponibile());
		vrQy.setText("" + nodeBase.getSoftware_disponibile());
		rcQy.setText("" + nodeBase.getSoftware_disponibile());

		ybG.add(ybEnergy, 0, 0);
		ybG.add(new Label("" + nodeBase.getE_disponibile()), 1, 0);
		ybG.add(ybFwLvl, 0, 1);
		ybG.add(new Label("" + nodeBase.getLvl_firewall()), 1, 1);
		ybG.add(ybRamLvl, 0, 2);
		ybG.add(new Label("" + nodeBase.getLvl_ram()), 1, 2);
		ybG.add(ybCpuLvl, 0, 3);
		ybG.add(new Label("" + nodeBase.getLvl_cpu()), 1, 3);
		ybG.add(ybAv, 0, 4);
		ybG.add(new Label("" + nodeBase.getStats_software_creati()[0].getQuantita()), 1, 4);
		ybG.add(ybVr, 0, 5);
		ybG.add(new Label("" + nodeBase.getStats_software_creati()[1].getQuantita()), 1, 5);
		ybG.add(ybRc, 0, 6);
		ybG.add(new Label("" + nodeBase.getStats_software_creati()[2].getQuantita()), 1, 6);

		hBg.getChildren().add(ybG);
		hBg.setAlignment(Pos.CENTER);
		HBox hb=new HBox();
		hb.setPadding(STANDARD_PADDING);
		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER);
		buttonBack.setPrefWidth(50);
		
		HBox color=new HBox();
		color.setBackground(new Background(new BackgroundFill(Paint.valueOf(nodeBase.getColore()), null, STANDARD_PADDING)));
		color.setPrefWidth(50);
		color.setPrefHeight(50);
		color.setAlignment(Pos.CENTER);
		
		buttonNext.setPrefWidth(50);
		hb.getChildren().addAll(buttonBack,color,buttonNext);
		yb.getChildren().addAll(ybTitle, hBg,hb);
		super.getChildren().add(yb);
	}
	/**
	 * Restituisce nodo base
	 * @return
	 */
	public Nodo getNodeBase() {
		return nodeBase;
	}

	public void setNodeBase(Nodo bU) {
		this.nodeBase = bU;
	}

	public Button getButtonTitle() {
		return buttonTitle;
	}

	public void setButtonTitle(Button buttonTitle) {
		this.buttonTitle = buttonTitle;
	}

	/**
	 * Restituisce il pulsante per passare alla base precedente a quella attualmente selezionata
	 * @return
	 */
	public Button getButtonBack() {
		return buttonBack;
	}

	public void setButtonBack(Button buttonBack) {
		this.buttonBack = buttonBack;
	}
	/**
	 * Restituisce il pulsante per passare alla prossima base a quella attualmente selezionata
	 * @return
	 */

	public Button getButtonNext() {
		return buttonNext;
	}

	public void setButtonNext(Button buttonNext) {
		this.buttonNext = buttonNext;
	}

	@Override
	public void drow() {
		make();
		
	}
	

}
