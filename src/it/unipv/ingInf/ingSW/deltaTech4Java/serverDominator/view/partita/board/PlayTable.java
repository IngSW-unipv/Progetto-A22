package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board;

import java.util.Set;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa.MapData;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class PlayTable {
	
	private Pair<Integer, Integer> dimTable;
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	private Canvas basicCanvas;
	private ScrollPane scrollPane;
	private MapData mapData;
	
	public PlayTable(Pair<Integer, Integer> dimensioni) {
		this.dimTable = dimensioni;
	}
	
	public ScrollPane getPlayTable(Pair<Integer, Integer> dimensioni, MapData mapData, int ray) {
		this.mapData=mapData;
		ScrollPane centerPane = new ScrollPane(){
			private ScrollBar horizontal;
			private ScrollBar vertical;
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
               if (horizontal == null) {
                	horizontal=findScrollBar(lookupAll(".scroll-bar"),Orientation.HORIZONTAL);
                	if(horizontal!=null) {
                		horizontal.visibleProperty().addListener((obs, old, val) -> updateHval(val));
                		updateHval(horizontal.isVisible());
                	}
                    
                }
               if (vertical == null) {
            	   vertical=findScrollBar(lookupAll(".scroll-bar"),Orientation.VERTICAL);
               	if(vertical!=null) {
               		vertical.visibleProperty().addListener((obs, old, val) -> updateVval(val));
               		updateVval(vertical.isVisible());
               	}
                   
               }
            }
        };
		
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
		scrollPane=centerPane;
		return scrollPane;
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
	  private void updateHval(boolean scrollBarVisible) {
	        if(!scrollBarVisible) {
	        	scrollPane.setHvalue(0);
	        }
	    }
	  private void updateVval(boolean scrollBarVisible) {
	        if(!scrollBarVisible) {
	        	scrollPane.setVvalue(0);
	        }
	    }
	  
	  private ScrollBar findScrollBar(Set<Node> set , Orientation orientation) {

	        for (Node node : set) {
	            ScrollBar bar = (ScrollBar) node;
	            if (bar.getOrientation() == orientation) {
	                return bar;
	            }
	        }

	        return null;
	    }
	public Pair<Integer, Integer> getDimTable() {
		return dimTable;
	}

	public void setDimTable(Pair<Integer, Integer> dimTable) {
		this.dimTable = dimTable;
	}

	public void setBasicCanvas(Canvas basicCanvas) {
		this.basicCanvas = basicCanvas;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public Canvas getBasicCanvas() {
		return basicCanvas;
	}

	public MapData getMapData() {
		return mapData;
	}

	public void setMapData(MapData mapData) {
		this.mapData = mapData;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
}
