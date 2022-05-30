package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa;

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
	
	/**
	 * Costruttore per creare un oggetto coordinate
	 * @param x
	 * @param y
	 */
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	/**aggiunta nella @version 1.01
	 * Costruttore per creare un oggetto coordinate
	 * @param x
	 * @param y
	 * @param nome
	 * nome del giocatore posizionato in quelle coordinate*/
	public Coordinate(int x, int y, String nome) {
		this.x=x;
		this.y=y;
		this.nome= nome;
	}
	
//------------getter and setter---------------//
	/**
	 * Returna la coordinata x
	 * @return
	 * coordniata x
	 */
	public int getX() {
		return x;
	}
	/**
	 * setta la coordinata x
	 * @param x
	 * coordinata x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * returna la coordinata y
	 * @return y
	 * coordinata y
	 */
	public int getY() {
		return y;
	}
	/**
	 * setta la coordinata y
	 * @param x
	 * coordinata y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * returna il nome
	 * @return
	 * nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * setta il nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
