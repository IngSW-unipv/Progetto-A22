package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.PopUpFacade;
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
	
	public StatsNodePane(Base bU) {
		this.bU = bU;
	}	
	
	private Base bU = new Base();
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	private Label titleL = new Label("Info Nodo");
	private Label owner = new Label("Node owner");
	private Label distance = new Label("Distance");
	private Label energy = new Label("Energy");
	private Label fwLvl = new Label("Firewall Level");
	private Label ramLvl = new Label("Ram Level");
	private Label cpuLvl = new Label("CPU Level");
	private Button buttonAttacca;
	
	public Pane getSnPane(Base bU) {

		Label titleL = this.titleL;
		Label owner =  this.owner;
		Label distance = new Label();
		distance = this.distance;
		Label energy = new Label();
		energy = this.energy;
		Label fwLvl = new Label();
		fwLvl = this.fwLvl;
		Label ramLvl = new Label();
		ramLvl = this.ramLvl;
		Label cpuLvl = new Label();
		cpuLvl = this.cpuLvl;
		buttonAttacca =ComponentCreator.getIstance().createButton("attack", Pos.BASELINE_CENTER);
		buttonAttacca.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	PopUpFacade p=new PopUpFacade();
            	p.selectMalware(bU);
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
		gDx.add(distance, 0, 1);
		gDx.add(energy, 0, 2);
		gDx.add(fwLvl, 1, 0);
		gDx.add(ramLvl, 1, 1);
		gDx.add(cpuLvl, 1, 2);

		gridBox.getChildren().add(gDx);

		HBox attack =ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		attack.setPadding(STANDARD_PADDING);
		attack.getChildren().add(buttonAttacca);


		statsNodeVbox.getChildren().addAll(statsNodeTitle, gridBox, attack);
		statsNodeHbox.getChildren().add(statsNodeVbox);
		statsNode.getChildren().addAll(statsNodeHbox);

		return statsNode;
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

	public Label getOwner() {
		return owner;
	}

	public void setOwner(Label owner) {
		this.owner = owner;
	}

	public Label getDistance() {
		return distance;
	}

	public void setDistance(Label distance) {
		this.distance = distance;
	}

	public Label getEnergy() {
		return energy;
	}

	public void setEnergy(Label energy) {
		this.energy = energy;
	}

	public Label getFwLvl() {
		return fwLvl;
	}

	public void setFwLvl(Label fwLvl) {
		this.fwLvl = fwLvl;
	}

	public Label getRamLvl() {
		return ramLvl;
	}

	public void setRamLvl(Label ramLvl) {
		this.ramLvl = ramLvl;
	}

	public Label getCpuLvl() {
		return cpuLvl;
	}

	public void setCpuLvl(Label cpuLvl) {
		this.cpuLvl = cpuLvl;
	}

	public Button getButtonAttacca() {
		return buttonAttacca;
	}

	public void setButtonAttacca(Button buttonAttacca) {
		this.buttonAttacca = buttonAttacca;
	}
	
}
