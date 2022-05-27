package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa;

import java.util.HashMap;
import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;
import javafx.application.Platform;
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
	
	/**
	 * genera la mappa, il tavolo di gioco con tutti gli esagoni 
	 */
	@Override
	public void drawMap() {
		//Platform.runLater(() -> {
		for (HashMap.Entry<Hexagon, HexData> entry : getMapData().getData().entrySet()) {
			if (entry.getKey().equals(this.hexagon)) {
				drawHex(getMapData().getPoints(entry.getKey()), entry.getValue().nodo.getColore(), Color.RED,entry.getValue().isBase());
				} else 
					drawHex(getMapData().getPoints(entry.getKey()),entry.getValue().nodo.getColore(), null,entry.getValue().isBase());
			}
			
		
		//});
	}
	/**
	 * Metodo che genera gli esagoni che rappresentano i nodi nel tavolo da gioco
	 * @param points
	 * @param colore
	 * @param bordo
	 */
	public void drawHex(List<Point> points, String colore, Color bordo, boolean isBase) {
		Platform.runLater(() -> {
		double[] x = points.stream().mapToDouble(Point::getX).toArray();  //Point::getX prende tutte le x contenute in Point
		double[] y = points.stream().mapToDouble(Point::getY).toArray();
		Color fillSelection = colore != null ? Color.web(colore) : Color.WHITE;
		if(bordo != null) {
			fillSelection = new Color(fillSelection.getRed()*0.8, fillSelection.getGreen()*0.8, fillSelection.getBlue()*0.8, 1);
		}
		if(!isBase) {
			fillSelection= new Color(fillSelection.getRed()*0.8*1.12, fillSelection.getGreen()*0.8*1.12, fillSelection.getBlue()*0.8*1.12, 1);
			fillSelection=fillSelection.desaturate();
		}
		getGc().setStroke(Color.BLACK); 
		getGc().setFill(fillSelection);
		getGc().strokePolygon(x, y, 6); 
		getGc().fillPolygon(x, y, 6);
		});
	}
}
