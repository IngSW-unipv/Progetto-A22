package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa;

import java.util.HashMap;
import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicMap extends Map {

	public BasicMap(MapData mapData, GraphicsContext gc) {
		super(mapData, gc);

	}

	private Hexagon hexagon;
	


	public Hexagon getHexagon() {
		return hexagon;
	}
	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public void drawMap(Hexagon hexagon) {
		this.hexagon = hexagon;
		drawMap();
	}
	@Override
	public void drawMap() {
		for (HashMap.Entry<Hexagon, HexData> entry : getMapData().getData().entrySet()) {
			if (entry.getKey().equals(this.hexagon)) {
				drawHex(getMapData().getPoints(entry.getKey()), entry.getValue().nodo.getColore(), Color.RED);
				
			} else {
				drawHex(getMapData().getPoints(entry.getKey()), entry.getValue().nodo.getColore(), null);
			}
		}
	}

	public void drawHex(List<Point> points, String colore, Color bordo) {
		double[] x = points.stream().mapToDouble(Point::getX).toArray();  //Point::getX prende tutte le x contenute in Point
		double[] y = points.stream().mapToDouble(Point::getY).toArray();
		Color fillSelection = colore != null ? Color.web(colore) : Color.WHITE;
		if(bordo != null) {
			fillSelection = new Color(fillSelection.getRed()*0.8, fillSelection.getGreen()*0.8, fillSelection.getBlue()*0.8, 1);
			
		}
		getGc().setStroke(Color.BLACK); 
		getGc().setFill(fillSelection);
		getGc().strokePolygon(x, y, 6); 
		getGc().fillPolygon(x, y, 6);
		

	}
}