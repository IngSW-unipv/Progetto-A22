package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class StatsNodePane {
	
	public StatsNodePane() {
	}	
	//TODO: IMPORTANTE:
	//TODO: DA RIVEDERE CON GLI ALTRI LE COSE DA MOSTRARE E LE COSE DA NASCONDERE 
	//TODO: RICORDARSIII!!!!
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	private Label titleL = new Label("Info Nodo");
	private Label owner = new Label("Node owner");
	private Label energy = new Label("Energy");
	private Label fwLvl = new Label("Firewall Level");
	private Label ramLvl = new Label("Ram Level");
	private Label cpuLvl = new Label("CPU Level");
	private Button buttonAttacca;
	private Pane statePane;
	
	/**
	 * Metodo che genera la zone dove sono visibili i dettagli del nodo selezionato
	 * @return
	 */
	public Pane getSnPane() {

		Label titleL = this.titleL;
		titleL.setFont(Font.font("Cambria", 22));
		
		Label owner =  this.owner;
		owner.setFont(Font.font("Cambria", 18));
		owner.setOpacity(0.9);
		
		Label energy = this.energy;
		energy.setFont(Font.font("Cambria", 18));
		energy.setOpacity(0.9);
		
		Label fwLvl = this.fwLvl;
		fwLvl.setFont(Font.font("Cambria", 18));
		fwLvl.setOpacity(0.9);
		
		Label ramLvl =this.ramLvl;
		ramLvl.setFont(Font.font("Cambria", 18));
		ramLvl.setOpacity(0.9);
		
		Label cpuLvl = this.cpuLvl;
		cpuLvl.setFont(Font.font("Cambria", 18));
		cpuLvl.setOpacity(0.9);
		
		buttonAttacca =ComponentCreator.getIstance().createButton("attack", Pos.BASELINE_CENTER);
		buttonAttacca.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	PopUpFacade p=new PopUpFacade();
            	p.avviaSelectMalware();
            }
		});
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
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
		titleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		titleL.setTextFill(Color.DARKGREEN);
		statsNodeTitle.getChildren().add(titleL);

		HBox gridBox = new HBox();
		GridPane gDx = new GridPane();

		gDx.setVgap(5);
		gDx.setHgap(20);
		gDx.setAlignment(Pos.CENTER);
		gDx.setPadding(STANDARD_PADDING);

		gDx.add(owner, 0, 0);
		gDx.add(energy, 0, 1);
		gDx.add(fwLvl, 0, 2);
		gDx.add(ramLvl, 0, 3);
		gDx.add(cpuLvl, 0, 4);

		gridBox.getChildren().add(gDx);

		HBox attack =ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		attack.setPadding(STANDARD_PADDING);
		attack.getChildren().add(buttonAttacca);


		statsNodeVbox.getChildren().addAll(statsNodeTitle, gridBox, attack);
		statsNodeHbox.getChildren().add(statsNodeVbox);
		statsNode.getChildren().addAll(statsNodeHbox);
		this.statePane=statsNode;
		return statePane;
	}
	
	public void setTitle(Label title) {
		title.setText(null);
		this.titleL = title ;
	}

	public Label getTitleL() {
		return titleL;
	}

	public void setTitleL(Label titleL) {
		this.titleL = titleL;
	}

	/**
	 * Restituisce nome del possessore del nodo selezionato
	 * @return
	 */
	public Label getOwner() {
		return owner;
	}

	public void setOwner(Label owner) {
		this.owner = owner;
	}

	/**
	 * Restituisce dati di energia del nodo selezionato
	 * @return
	 */
	public Label getEnergy() {
		return energy;
	}

	public void setEnergy(Label energy) {
		this.energy = energy;
	}

	/**
	 * Restituisce dati del firewall del nodo selezionato
	 * @return
	 */
	public Label getFwLvl() {
		return fwLvl;
	}

	public void setFwLvl(Label fwLvl) {
		this.fwLvl = fwLvl;
	}

	/**
	 * Restituisce dati della ram del nodo selezionato
	 * @return
	 */
	public Label getRamLvl() {
		return ramLvl;
	}

	public void setRamLvl(Label ramLvl) {
		this.ramLvl = ramLvl;
	}

	/**
	 * Restituisce dati della cpu del nodo selezionato
	 * @return
	 */
	public Label getCpuLvl() {
		return cpuLvl;
	}

	public void setCpuLvl(Label cpuLvl) {
		this.cpuLvl = cpuLvl;
	}

	/**
	 * Restituisce pulsante per attaccare il nodo selezionato
	 * @return
	 */
	public Button getButtonAttacca() {
		return buttonAttacca;
	}

	public void setButtonAttacca(Button buttonAttacca) {
		this.buttonAttacca = buttonAttacca;
	}

	public Pane getStatePane() {
		return statePane;
	}

	public void setStatePane(Pane statePane) {
		this.statePane = statePane;
	}
	
}
