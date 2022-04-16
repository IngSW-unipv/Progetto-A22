package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 * variabile interna alla classe Giocatore.
 * Usata per identificare graficamente il nodo di ogni giocatore. 
 * attribuisce a variabili dei valori esadecimali, 
 * restituiti poi da un getter nella classe Giocatore.
 */

public class Colore {

	private int grigio, giallo, arancione;
	private int verde, rosso, viola;
	private int verde_scuro, rosa, rosa_scuro;
	private int azzurro, blu;
	
	public Colore() {
		grigio=0xD4D4D4;
		giallo=0XFFFF00;
		arancione=0xFF8000;
		verde=0x00FF00;
		rosso=0xFF0000;
		viola=0x4000FF;
		verde_scuro=0x006633;
		rosa=0xFF4ADB;
		rosa_scuro=0xB5739D;
		azzurro=0x00FFFF;
		blu=0x3399FF;
	}
	
	public static final String GRIGIO = "#D4D4D4";
	public static final String GIALLO = "#FFFF00";
	public static final String ARANCIO = "#FF8000";
	public static final String VERDE = "#00FF00";
	public static final String ROSSO = "#FF0000";
	public static final String VIOLA = "#4000FF";
	public static final String VERDE_SCURO = "#006633";
	public static final String ROSA = "#FF4ADB";
	public static final String ROSA_SCURO = "#B5739D";
	public static final String AZZURRO = "#00FFFF";
	public static final String BLU = "#3399FF";
	
}
