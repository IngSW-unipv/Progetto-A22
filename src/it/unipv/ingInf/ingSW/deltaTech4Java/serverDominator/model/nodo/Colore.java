package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * variabile interna alla classe Giocatore.
 * Usata per identificare graficamente il nodo di ogni giocatore. 
 * attribuisce a variabili dei valori esadecimali, 
 * restituiti poi da un getter nella classe Giocatore.
 */
public class Colore {

	
	private final String GRIGIO = "#D4D4D4";
	private final String GIALLO = "#FFFF00";
	private final String ARANCIO = "#FF8000";
	private final String VERDE = "#00FF00";
	private final String ROSSO = "#FF0000";
	private final String VIOLA = "#4000FF";
	private final String VERDE_SCURO = "#006633";
	private final String ROSA = "#FF4ADB";
	private final String ROSA_SCURO = "#B5739D";
	private final String AZZURRO = "#00FFFF";
	private final String BLU = "#3399FF";
	
	private ArrayList<String> colori;
	/**
	 * Costruttore per inizializzare e utilizzare i colori
	 */
	public Colore() {
		colori= new ArrayList<String>( Arrays.asList(GIALLO , ARANCIO , VERDE , ROSSO , VIOLA , VERDE_SCURO , ROSA , ROSA_SCURO , AZZURRO , BLU));
	}
	
	/**
	 * returna il valore esadecimale corrispondente al colore grigio
	 * @return
	 * grigio in esadecimale
	 */
	public  String getGrigio() {
		return GRIGIO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore giallo
	 * @return
	 * giallo in esadecimale
	 */
	public  String getGiallo() {
		return GIALLO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore arancio
	 * @return
	 * arancio in esadecimale
	 */
	public  String getArancio() {
		return ARANCIO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore verde
	 * @return
	 * verde in esadecimale
	 */
	public  String getVerde() {
		return VERDE;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore rosso
	 * @return
	 * rosso in esadecimale
	 */
	public  String getRosso() {
		return ROSSO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore viola
	 * @return
	 * viola in esadecimale
	 */
	public  String getViola() {
		return VIOLA;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore verde scuro
	 * @return
	 * verde scuro in esadecimale
	 */
	public  String getVerdeScuro() {
		return VERDE_SCURO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore rosa
	 * @return
	 * rosa in esadecimale
	 */
	public  String getRosa() {
		return ROSA;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore Rosa Scuro
	 * @return
	 * rosa scuro in esadecimale
	 */
	public  String getRosaScuro() {
		return ROSA_SCURO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore azzurro
	 * @return
	 * azzurro in esadecimale
	 */
	public  String getAzzurro() {
		return AZZURRO;
	}
	/**
	 * returna il valore esadecimale corrispondente al colore blu
	 * @return
	 * blu in esadecimale
	 */
	public  String getBlu() {
		return BLU;
	}
	/**
	 * returna l'array di stringhe i colori in esadecimale
	 * @return
	 * colori in esadecimale(0=Giallo, 1=Arancio, 2=Verde, 3=Rosso, 4=Viola, 5=Verde scuro, 6=Rosa, 7=Rosa scuro, 8=Azzurro, 9=Blu)
	 */
	public  ArrayList<String> getColori() {
		return colori;
	}
	
	
}
