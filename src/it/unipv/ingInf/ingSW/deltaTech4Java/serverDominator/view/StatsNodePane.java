package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
	
	Base bU = new Base();
	HBox hB = new HBox();
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	Label titleL = new Label("Info Nodo");
	Label owner = new Label("Node owner");
	Label distance = new Label("Distance");
	Label energy = new Label("Energy");
	Label fwLvl = new Label("Firewall Level");
	Label ramLvl = new Label("Ram Level");
	Label cpuLvl = new Label("CPU Level");
	PopUp pU = new PopUp();

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
		Label attackL = new Label();

		AddType aT = new AddType();

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

		HBox attack = new HBox();
		attack.setPadding(STANDARD_PADDING);
		attack.getChildren().add(attackL);

		aT.launchMalware(attack, bU);

		statsNodeVbox.getChildren().addAll(statsNodeTitle, gridBox, attack);
		statsNodeHbox.getChildren().add(statsNodeVbox);
		statsNode.getChildren().addAll(statsNodeHbox);

		return statsNode;
	}
	
	public void setTitle(Label title) {
		title.setText(null);
		this.titleL = title ;
	}
}
