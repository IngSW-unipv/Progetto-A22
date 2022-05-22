package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.nodes;

import java.util.Objects;

public class Hexagon  {
    
    private int x;
    private int y;
    private int z;

    public Hexagon(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = -x - y;
    }
    

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


	public int getZ() {
		return z;
	}


	public void setZ(int z) {
		this.z = z;
	}


	/**
	 * confronto tra oggetti di tipo esagono
	 */
	@Override
    public boolean equals(Object obj) {  // 
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Hexagon other = (Hexagon) obj;

        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Hexagon{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}