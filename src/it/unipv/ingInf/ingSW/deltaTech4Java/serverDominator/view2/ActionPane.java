package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ActionPane {

	Base bU = new Base();
	HBox hB = new HBox();
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	public ActionPane(Base bU) {
		this.bU = bU;
	}

	public Pane getActionPane(Base bU) {
		
		AddType aT = new AddType();
		
		Pane actionPane = new Pane();
		actionPane.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));

		VBox controlli = new VBox();
		controlli.setMinWidth(200.0);
		controlli.setPadding(STANDARD_PADDING);
		controlli.setAlignment(Pos.CENTER);

		HBox actionTitle = new HBox();
		actionTitle.setPadding(STANDARD_PADDING);
		// actionTitle.setPrefSize(200.0, 30.0);
		actionTitle.setAlignment(Pos.CENTER);
		actionTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
		Label actionTitleL = new Label("Action");
		actionTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		actionTitleL.setTextFill(Color.DARKGREEN);

		actionTitle.getChildren().add(actionTitleL);

		HBox actionMarket = new HBox();
		actionMarket.setPadding(STANDARD_PADDING);
		actionMarket.setAlignment(Pos.TOP_CENTER);
		Label actionMarketL = new Label();
		actionMarket.getChildren().add(actionMarketL);

		aT.launchMarket(actionMarket, bU);

		HBox powerUp = new HBox();
		powerUp.setPadding(STANDARD_PADDING);
		powerUp.setAlignment(Pos.BASELINE_CENTER);
		powerUp.setBackground(
				new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label powerUpL = new Label();
		powerUp.getChildren().add(powerUpL);

		aT.launchPowerUp(powerUp, bU);

		HBox dev = new HBox();
		dev.setPadding(STANDARD_PADDING);
		dev.setAlignment(Pos.BASELINE_CENTER);
		dev.setBackground(
				new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label develop = new Label();
		dev.getChildren().add(develop);

		aT.launchDevelopment(dev, bU);

		controlli.getChildren().addAll(actionTitle, actionMarket, powerUp, dev);
		actionPane.getChildren().add(controlli);

		return actionPane;
	}
	
}
