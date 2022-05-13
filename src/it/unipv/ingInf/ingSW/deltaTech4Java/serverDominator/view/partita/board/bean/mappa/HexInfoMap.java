package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa;

import java.util.HashMap;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HexInfoMap extends Map {
	public HexInfoMap(MapData mapData, GraphicsContext gc) {
		super(mapData, gc);
	}

	public void drawMap() {
		for (HashMap.Entry<Hexagon, HexData> entry : getMapData().getData().entrySet()) {
			drawHex(entry.getKey(), getMapData().hex_to_pixel(entry.getKey()));
		}
	}

	public void drawHex(Hexagon a, Point center) {
		getGc().setFill(Color.BLACK);
		getGc().fillText("" + a.getX(), center.getX() - 14, center.getY() - 6);
		getGc().fillText("" + a.getY(), center.getX() + 6, center.getY() + 5);
		getGc().fillText("" + a.getZ(), center.getX() - 14, center.getY() + 14);
	}
}