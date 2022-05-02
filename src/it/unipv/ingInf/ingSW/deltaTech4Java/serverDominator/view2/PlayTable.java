package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class PlayTable {
	
	Pair<Integer, Integer> dimTable;
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	Canvas basicCanvas;
	
	public PlayTable(Pair<Integer, Integer> dimensioni) {
		
		this.dimTable = dimensioni;
		
	}
	
	public ScrollPane getPlayTable(Pair<Integer, Integer> dimensioni, MapData mapData, Base bU, int ray) {
		
		ScrollPane centerPane = new ScrollPane();
		
		Canvas basicCanvas = getTableCanvas(dimensioni, ray);
		GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
				
		basicGC.setFill(Color.FORESTGREEN);
		
		

		centerPane.setContent(basicCanvas);
		centerPane.setStyle("-fx-background:rgb(20,20,20);-fx-background-radius:1em");  
		centerPane.setBackground(
				new Background(new BackgroundFill(Color.web("#b3c9ff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		centerPane.setPadding(STANDARD_PADDING);
		centerPane.setFitToHeight(true);
		centerPane.setFitToWidth(true);
		centerPane.setPannable(true);
		
		return centerPane;
	}
	
	public Canvas getTableCanvas(Pair<Integer, Integer> dimensioni, int ray) {
		
		double hexWidth, hexHight;
		
		hexWidth = (dimensioni.getKey() * ray * Math.sqrt(3.0)) + ray;
		hexHight = ray;
		
		if (dimensioni.getValue() % 2 ==0 ) {
			hexHight = dimensioni.getValue() / 2 * ray * 3;
		}
		
		else if ((dimensioni.getValue() % 2 !=0) && (dimensioni.getValue() > 1)){
			hexHight = (dimensioni.getValue() - 1) * ray * 3 + 0.5 * ray; 
		}
		
		else {
			hexHight = ray * 2;
		}
		
		basicCanvas = new Canvas( hexWidth , hexHight + 12 );
		
		return basicCanvas;
	}
	public Canvas getBasicCanvas() {
		return basicCanvas;
	}
}
