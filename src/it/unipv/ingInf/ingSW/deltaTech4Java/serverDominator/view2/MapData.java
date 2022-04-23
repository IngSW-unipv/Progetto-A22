package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.*;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;

/**
 * @author para
 *
 */

public class MapData {
    HashMap<Hexagon, HexData> data;  //  l'hashMap che contiene tutti gli esagoni ed il rispettivo tipo
    
    Orientation layout_pointy = new Orientation(Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0,  Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5);
    List<Hexagon> directions = Arrays.asList(new Hexagon(1,0), new Hexagon(1,-1), new Hexagon(0,-1), new Hexagon(-1,0), new Hexagon(-1,1), new Hexagon(0,1)); // direzioni dall'esagono 0,0 
    Layout layout = new Layout(layout_pointy, new Point(25,25), new Point(25,25));  // definisco orientation, size e origin
    
    
   // System.out.println("layout = " );

    public MapData(Nodo[][] nodi){
        data = new HashMap<>();

   // Creazione delle coordinate per una matrice di tipo Nodo[i][j]
       System.out.println(nodi.length); 
        for (int r = 0; r < nodi[0].length; r++) { 							//nodi.length è il valore massimo di i
        																	// nodi[i].length è il valore massimo di j
            int r_offset = (int) Math.floor(r/2);							// Math.floor(r/2) = parte intera del valore di r/2 
            for (int q = -r_offset; q < nodi.length - r_offset; q++) { 	// costruzione esagoni orizzontali con coordinata x sfasata
            	data.put(new Hexagon(q, r), new HexData(nodi[q + r_offset][r]));
            }
        }
    }

    public HashMap<Hexagon, HexData> getData() {
        return data;
    }

    public void setData(HashMap<Hexagon, HexData> data) {
        this.data = data;
    }


    Point hex_to_pixel(Hexagon h) {  			// metodo per calcolare il pixel corrispondente al centro di un determinato esagono
        Orientation M = layout.orientation;
        double x = (M.f0 * h.x + M.f1 * h.y) * layout.size.x;
        double y = (M.f2 * h.x + M.f3 * h.y) * layout.size.y;
        return new Point(x + layout.origin.x, y + layout.origin.y);
    }

    Point hex_corner_offset(int corner) { // restituisce un punto 
        Point size = layout.size;
        double angle = 2.0 * Math.PI * (layout.orientation.start_angle + corner) / 6; 	// angle = 1/6 angolo giro (2*pi/6)
        																				// start_angle = 0.5*2*pi = 180°
        return new Point(size.x * Math.cos(angle), size.y * Math.sin(angle));			// corner 0 ha size.x = -25 ; size.y = 0
        																				// corner 1 ha size.x = -25 ; size.y = 0
    }

    List<Point> getPoints(Hexagon h) {											// creo lista dei 6 vertici dell'esagono h
        List<Point> corners = new ArrayList<>();
        Point center = hex_to_pixel(h);
        for (int i = 0; i < 6; i++) {
            Point offset = hex_corner_offset(i);
            corners.add(new Point(center.x + offset.x,center.y + offset.y));
        }
        return corners;
    }

    public Hexagon pixelToHex(Point p){				// trasforma coordinate in pixel, in coordinate puntuali (25,25) -> (0,0)
        Orientation M = layout.orientation;

        Point pt = new Point((p.getX() - layout.origin.x) / layout.size.x,
                (p.getY() - layout.origin.y) / layout.size.y);

        double q = M.b0 * pt.x + M.b1 * pt.y;
        double r = M.b2 * pt.x + M.b3 * pt.y;

        return new Hexagon((int) Math.round(q), (int) Math.round(r));
    }

    public HexData getHexData(Hexagon a){
        return data.get(a);
    }

    Hexagon add(Hexagon a, Hexagon b){
        return new Hexagon(a.x + b.x, a.y + b.y);
    }

    Hexagon subtract(Hexagon a, Hexagon b){
        return new Hexagon(a.x - b.x, a.y - b.y);
    }

    Hexagon multiply(Hexagon a, Hexagon b){
        return new Hexagon(a.x - b.x, a.y - b.y);
    }

    int length(Hexagon a){
        return (Math.abs(a.x) + Math.abs(a.y) + Math.abs(a.z) / 2);
    }

    int distance(Hexagon a, Hexagon b){
        return length(subtract(a,b));
    }

    public boolean getIfHexExists(Hexagon a){
        return data.containsKey(a);
    }

    public Hexagon neighbor(Hexagon a, int direction){
        return add(a, directions.get(direction));
    }

    // elenco degli esagoni vicini
    
    public ArrayList<Hexagon> getNeighbors(Hexagon a){
        ArrayList<Hexagon> neighbors = new ArrayList<>();
        for(int x = 0; x < 6; x++){
            if(getIfHexExists(neighbor(a, x))){
                neighbors.add(neighbor(a, x));
            }
        }
        return neighbors;
    }

    public static class Layout {
        Orientation orientation;
        Point size;
        Point origin;

        public Layout(Orientation orientation, Point size, Point origin) {
            this.orientation = orientation;
            this.size = size;
            this.origin = origin;
        }
    }

    public static class Orientation {
        double f0, f1, f2, f3;
        double b0, b1, b2, b3;

        double start_angle;

        public Orientation(double f0, double f1, double f2, double f3, double b0, double b1, double b2, double b3, double start_angle) {
            this.f0 = f0;
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.b0 = b0;
            this.b1 = b1;
            this.b2 = b2;
            this.b3 = b3;
            this.start_angle = start_angle;
        }
    }
}
