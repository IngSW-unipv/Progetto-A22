package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ComponentCreator {
	private static ComponentCreator istance=null;
	
	public static ComponentCreator getIstance() {
		if (istance==null) {
			istance=new ComponentCreator();
		}
		return istance;
	}
	
	public  Button createButton(String name, Pos alignmant) {
		Button b= new Button(name);
		b.setAlignment(alignmant);
		b.getStyleClass().add("redbutton");
		b.setPrefWidth(150);
		b.setStyle("-fx-background-color: #FF0000; -fx-background-radius: 15px; -fx-text-fill: #ffffff");
		return b;
	}
	
	public HBox createHbox(Pos alignmant) {
		HBox bA = new HBox();
		bA.setFillHeight(false);	
		bA.setMaxWidth(200);
		bA.setAlignment(alignmant);
		bA.autosize();
		return bA;
	}
}
