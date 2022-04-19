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
				drawHex(mapData.getPoints(entry.getKey()), entry.getValue().nodo.getColore(), Color.BLACK);
			}
		}
	}

	public void drawHex(List<Point> points, String colore, Color bordo) {
		double[] x = points.stream().mapToDouble(Point::getX).toArray();
		double[] y = points.stream().mapToDouble(Point::getY).toArray();

		gc.setStroke(bordo);
		gc.setFill(colore != null ? Color.web(colore) : Color.WHITE);
		gc.strokePolygon(x, y, 6);
		gc.fillPolygon(x, y, 6);
		

	}
}
