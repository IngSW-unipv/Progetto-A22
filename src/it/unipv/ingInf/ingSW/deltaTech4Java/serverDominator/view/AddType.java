package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.PopUpFacade;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class AddType {
	
	public final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	Button button;
	String titolo;
	HBox hB ;
	PopUpFacade pU = new PopUpFacade() ;
	Base bU ;
	
	public AddType() {
		
	}
	

	public void addType(HBox controls, String titolo, EventHandler<? super MouseEvent> event) { 
		
		Button button = new Button(titolo);
		button.getStyleClass().add("redbutton");
		//button.setPadding(new Insets(11,11,11,11));
		button.setOnMouseClicked(event);

		controls.getChildren().add(button); 
		controls.setPadding(STANDARD_PADDING);
		controls.setAlignment(Pos.CENTER);
		controls.setSpacing(5);
	}
	
	public void launchMalware(HBox hBox, Base bU) {
		addType(hBox, "attack", (event -> {

			pU.avviaSelectMalware(bU);

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), new Insets(8, 8, 8, 8))));
	}
	
	public void launchDevelopment(HBox hBox, Base bU) {
		addType(hBox, "develop software", (event -> {
			
			pU.avviaDevelopment(bU);


		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}
	
	public void launchPowerUp(HBox hBox, Base bU) {
		addType(hBox, "power-up!", (event -> {

			pU.avviaPowerUp(bU);

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}
	
	public void launchMarket(HBox hBox, Base bU) {
		addType(hBox, "market", (event -> {
			
			pU.avviaMarket(bU);

		}));
		hBox.setSpacing(0); 
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(STANDARD_PADDING); 
		hBox.setBackground(new Background(new BackgroundFill(Color.web("#e51400"), new CornerRadii(10), STANDARD_PADDING)));
	}


}
