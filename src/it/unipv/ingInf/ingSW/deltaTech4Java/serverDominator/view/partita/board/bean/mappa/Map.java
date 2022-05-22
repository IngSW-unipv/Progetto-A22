package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa;

import javafx.scene.canvas.GraphicsContext;

public abstract class Map {
	private MapData mapData;
	private  boolean selected = false;
	private GraphicsContext gc;

	public Map(MapData mapData, GraphicsContext gc) {
		this.mapData = mapData;
		this.gc = gc;
	}

	/**
	 * Restituisce la mappa, il tavolo di gioco
	 * @return
	 */
	public MapData getMapData() {
		return mapData;
	}

	public void setMapData(MapData mapData) {
		this.mapData = mapData;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public abstract void drawMap();
}





