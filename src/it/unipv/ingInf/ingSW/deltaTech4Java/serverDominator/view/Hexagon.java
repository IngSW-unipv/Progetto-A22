package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import java.io.Serializable;
import java.util.Objects;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;

public class Hexagon  {
    
    public int x;
	public int y;
	int z;

    public Hexagon(int x, int y) {
        this.x = x;
        this.y = y;
        z = -x - y;
    }

    @Override
    public boolean equals(Object obj) {  // confronto tra oggetti di tipo esagono
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