package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * @version 1.01
 * @since 1.0
 * coordinate di una base, usate come indice per il veloce ritrovamento nella mappa
 */
public class Coordinate {
	private int x;
	private int y;
	private String nome;
	
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	/**aggiunta nella @version 1.01*/
	public Coordinate(int x, int y, String nome) {
		this.x=x;
		this.y=y;
		this.nome= nome;
	}
	
//------------getter and setter---------------//

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
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
