package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;

abstract class Map {
	MapData mapData;
	boolean selected = false;
	GraphicsContext gc;

	public Map(MapData mapData, GraphicsContext gc) {
		this.mapData = mapData;
		this.gc = gc;
	}

	abstract void drawMap();
}

class BasicMap extends Map {

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
				drawHex(mapData.getPoints(entry.getKey()), entry.getValue().nodo.getColore(), Color.WHITE);
			}
		}
	}

	public void drawHex(List<Point> points, Color colore, Color bordo) {
		double[] x = points.stream().mapToDouble(Point::getX).toArray();
		double[] y = points.stream().mapToDouble(Point::getY).toArray();

		gc.setStroke(bordo);
		gc.setFill(colore != null ? colore : Color.WHITE);
		gc.strokePolygon(x, y, 6);
		gc.fillPolygon(x, y, 6);

	}
}



class HexInfoMap extends Map {
	public HexInfoMap(MapData mapData, GraphicsContext gc) {
		super(mapData, gc);
	}

	public void drawMap() {
		for (HashMap.Entry<Hexagon, HexData> entry : mapData.data.entrySet()) {
			drawHex(entry.getKey(), mapData.hex_to_pixel(entry.getKey()));
		}
	}

	public void drawHex(Hexagon a, Point center) {
		gc.setFill(Color.BLACK);
		gc.fillText("" + a.x, center.getX() - 14, center.getY() - 6);
		gc.fillText("" + a.y, center.getX() + 6, center.getY() + 5);
		gc.fillText("" + a.z, center.getX() - 14, center.getY() + 14);
	}
}
