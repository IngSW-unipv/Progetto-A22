package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.mappa;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.HexData;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes.Hexagon;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util.Point;

/**
 * @author para
 *
 */

public class MapData {
    private HashMap<Hexagon, HexData> data;  //  l'hashMap che contiene tutti gli esagoni ed il rispettivo dato
    
    private int ray = 25;
    
    private Orientation layout_pointy = new Orientation(Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0,  
    		Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5);
    private List<Hexagon> directions = Arrays.asList(new Hexagon(1,0), new Hexagon(1,-1), 
    		new Hexagon(0,-1), new Hexagon(-1,0), new Hexagon(-1,1), new Hexagon(0,1)); // direzioni dall'esagono 0,0 
    private Layout layout = new Layout(layout_pointy, new Point(ray,ray), new Point(ray,ray));  // definisco orientation, size e origin
    
    
   // System.out.println("layout = " );

    public int getRay() {
    	return this.ray;
    }
    
    public void setRay(int ray) {
    	this.ray = ray;
    }
    
    /**
     * creazione delle coordinate per una matrice di tipo Nodo [i][j]
     * @param nodi
     */
    public MapData(Nodo[][] nodi){
        data = new HashMap<>();

   
        for (int r = 0; r < nodi[0].length; r++) { 							//nodi.length è il valore massimo di i
        																	// nodi[i].length è il valore massimo di j
            int r_offset = (int) Math.floor(r/2);							// Math.floor(r/2) = parte intera del valore di r/2 
            for (int q = -r_offset; q < nodi.length - r_offset; q++) { 	// costruzione esagoni orizzontali con coordinata x sfasata
            	data.put(new Hexagon(q, r), new HexData(nodi[q + r_offset][r]));
            }
        }
    }

    /**
     * Restituisce le coordinate della matrice del tavolo di gioco
     * @return
     */
    public HashMap<Hexagon, HexData> getData() {
        return data;
    }

    public void setData(HashMap<Hexagon, HexData> data) {
        this.data = data;
    }


    /**
     *  metodo per calcolare il pixel corrispondente al centro di un determinato esagono
     * @param h
     * @return
     */
    public Point hex_to_pixel(Hexagon h) {  			
        Orientation M = layout.orientation;
        double x = (M.f0 * h.getX() + M.f1 * h.getY()) * layout.size.getX();
        double y = (M.f2 * h.getX() + M.f3 * h.getY()) * layout.size.getY();
        return new Point(x + layout.origin.getX(), y + layout.origin.getY());
    }

    /**
     * restituisce un punto 
     * @param corner
     * @return
     */
    public Point hex_corner_offset(int corner) { // 
        Point size = layout.size;
        double angle = 2.0 * Math.PI * (layout.orientation.start_angle + corner) / 6; 	// angle = 1/6 angolo giro (2*pi/6)
        																				// start_angle = 0.5*2*pi/6 = 30°
        return new Point(size.getX() * Math.cos(angle), size.getY() * Math.sin(angle));			// corner 0 ha size.getX() = 21.65 ; size.getY() = 12.5
        																				// corner 1 ha size.getX() = 0 ; size.getY() = 25
    }

    public List<Point> getPoints(Hexagon h) {											// creo lista dei 6 vertici dell'esagono h
        List<Point> corners = new ArrayList<>();
        Point center = hex_to_pixel(h);
        for (int i = 0; i < 6; i++) {
            Point offset = hex_corner_offset(i);
            corners.add(new Point(center.getX() + offset.getX(),center.getY() + offset.getY()));
        }
        return corners;
    }

    /**
     * trasforma coordinate in pixel, in coordinate puntuali (25,25) -> (0,0)
     * @param p
     * @return
     */
    public Hexagon pixelToHex(Point p){				// 
        Orientation M = layout.orientation;

        Point pt = new Point((p.getX() - layout.origin.getX()) / layout.size.getX(),
                (p.getY() - layout.origin.getY()) / layout.size.getY());

        double q = M.b0 * pt.getX() + M.b1 * pt.getY();
        double r = M.b2 * pt.getX() + M.b3 * pt.getY();

        return new Hexagon((int) Math.round(q), (int) Math.round(r));
    }

    public HexData getHexData(Hexagon a){
        return data.get(a);
    }

    public Hexagon add(Hexagon a, Hexagon b){
        return new Hexagon(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public Hexagon subtract(Hexagon a, Hexagon b){
        return new Hexagon(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public Hexagon multiply(Hexagon a, Hexagon b){
        return new Hexagon(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public int length(Hexagon a){
        return (Math.abs(a.getX()) + Math.abs(a.getY()) + Math.abs(a.getZ()) / 2);
    }

    public int distance(Hexagon a, Hexagon b){
        return length(subtract(a,b));
    }

    public boolean getIfHexExists(Hexagon a){
        return data.containsKey(a);
    }

    public Hexagon neighbor(Hexagon a, int direction){
        return add(a, directions.get(direction));
    }

    // 
    
    /**
     * elenco degli esagoni vicini
     * @param a
     * @return
     */
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
        private Orientation orientation;
        private Point size;
        private Point origin;

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

	public Orientation getLayout_pointy() {
		return layout_pointy;
	}

	public void setLayout_pointy(Orientation layout_pointy) {
		this.layout_pointy = layout_pointy;
	}

	public List<Hexagon> getDirections() {
		return directions;
	}

	public void setDirections(List<Hexagon> directions) {
		this.directions = directions;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
    
}
