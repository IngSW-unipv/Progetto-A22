package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.board.bean.util;

public class Point {
	private  double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    

    public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
    public int getIntX() {
		Double d=x;
		return d.intValue();
	}

	public int getIntY() {
		Double d=y;
		return d.intValue();
	}
	@Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}