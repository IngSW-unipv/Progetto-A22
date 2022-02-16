package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 *coordinate di un nodo
 */

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
/**getter and setter*/

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
