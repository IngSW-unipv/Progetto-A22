package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import java.util.HashMap;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicMap extends Map {

	public BasicMap(MapData mapData, GraphicsContext gc) {
		super(mapData, gc);

	}

	Hexagon hexagon;
	


	public void drawMap(Hexagon hexagon) {
		this.hexagon = hexagon;
		drawMap();
	}

	public void drawMap() {
		for (HashMap.Entry<Hexagon, HexData> entry : mapData.data.entrySet()) {
			if (entry.getKey().equals(this.hexagon)) {
				drawHex(mapData.getPoints(entry.getKey()), entry.getValue().nodo.getColore(), Color.RED);
				
			} else {
				drawHex(mapData.getPoints(entry.getKey()), entry.getValue().nodo.getColore(), null);
			}
		}
	}

	public void drawHex(List<Point> points, String colore, Color bordo) {
		double[] x = points.stream().mapToDouble(Point::getX).toArray();  //Point::getX prende tutte le x contenute in Point
		double[] y = points.stream().mapToDouble(Point::getY).toArray();
		Color fillSelection = colore != null ? Color.web(colore) : Color.WHITE;
		if(bordo != null) {
			fillSelection = new Color(fillSelection.getRed()*0.8, fillSelection.getGreen()*0.8, fillSelection.getBlue()*0.8, 1);
			
			//System.out.println(fillSelection.getRed());
		}
		gc.setStroke(Color.BLACK); 
		gc.setFill(fillSelection);
		gc.strokePolygon(x, y, 6); 
		gc.fillPolygon(x, y, 6);
		

	}
}
