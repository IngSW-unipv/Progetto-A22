package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
				Label titoloBattaglie = new Label("BATTAGLIE IN CORSO");
				battleTitle.getChildren().add(titoloBattaglie);
				
				HBox batt1 = new HBox();
				batt1.setSpacing(0);
				batt1.setPadding(STANDARD_PADDING);
				ProgressBar pb1 = new ProgressBar(0);
				Label batt1Lb = new Label("Battaglia 1");
				batt1.getChildren().addAll(batt1Lb, pb1);
				
				battaglie.getChildren().addAll(battleTitle, batt1);
				battlePane.getChildren().add(battaglie);
		
		return battlePane;
	}
}

