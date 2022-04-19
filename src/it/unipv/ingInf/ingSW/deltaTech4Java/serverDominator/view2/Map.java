package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;

public abstract class Map {
	MapData mapData;
	public boolean selected = false;
	GraphicsContext gc;

	public Map(MapData mapData, GraphicsContext gc) {
		this.mapData = mapData;
		this.gc = gc;
	}

	abstract void drawMap();
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
