package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

public class BattlePane {
	
	Base bU = new Base();
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	public BattlePane(Base bU) {
	
		this.bU = bU;
	
	}
	
	
	
	public Pane getBattlePain(Base bU) {		// Pane battle che contiene le battaglie //
	
				Pane battlePane = new Pane();

				battlePane.setBackground(
						new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
				battlePane.setPadding(STANDARD_PADDING);
				
				VBox battaglie = new VBox();
				battaglie.setSpacing(0);
				battaglie.setPadding(STANDARD_PADDING);
				battaglie.setMaxWidth(390);
				
				HBox battleTitle = new HBox();
				battleTitle.setPadding(STANDARD_PADDING);
				battleTitle.setAlignment(Pos.CENTER);
				battleTitle.setBackground(
						new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
				Label titoloBattaglie = new Label("BATTAGLIE IN CORSO");
				titoloBattaglie.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				titoloBattaglie.setTextFill(Color.DARKGREEN);
				
				battleTitle.getChildren().add(titoloBattaglie);
				
				
				GridPane battles = new GridPane();
				battles.setAlignment(Pos.BASELINE_CENTER);
				battles.setPadding(STANDARD_PADDING);
				battles.setVgap(5);
				
				Label title1 = new Label("nodoA (2 ,19) vs nodoD (1, 18)");
				title1.setTextFill(Color.web("FFFF00"));
				HBox hbB1 = new HBox();
				hbB1.setPadding(new Insets(0,0,10,0));
				ProgressBar battle1 = new ProgressBar(0.75);
				hbB1.getChildren().add(battle1);
				battles.add(title1, 0, 0);
				battles.add(hbB1, 0, 1);
				
				Label title2 = new Label("nodoA (2 ,7) vs nodoD (1, 8)");
				title2.setTextFill(Color.web("FFFF00"));
				HBox hbB2 = new HBox();
				hbB2.setPadding(new Insets(0,0,10,0));
				ProgressBar battle2 = new ProgressBar(0.5);
				hbB2.getChildren().add(battle2);
				battles.add(title2, 0, 2);
				battles.add(hbB2, 0, 3);
				
				Label title3 = new Label("nodoA (8 ,5) vs nodoD (7, 6)");
				title3.setTextFill(Color.web("FFFF00"));
				HBox hbB3 = new HBox();
				hbB3.setPadding(new Insets(0,0,10,0));
				ProgressBar battle3 = new ProgressBar(0.25);
				hbB3.getChildren().add(battle3);
				battles.add(title3, 0, 4);
				battles.add(hbB3, 0, 5);

				
				battaglie.getChildren().addAll(battleTitle, battles);
				battlePane.getChildren().add(battaglie);
				battlePane.setBackground(new Background(new BackgroundFill(Color.web("#262626"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
				
		
		return battlePane;
	}
}

