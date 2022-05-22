package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

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
	
	private ArrayList<String> colori = new ArrayList( Arrays.asList(GIALLO , ARANCIO , VERDE , ROSSO , VIOLA , VERDE_SCURO , ROSA , ROSA_SCURO , AZZURRO , BLU));

	public Colore() {
	
	}
	
	
	public  String getGrigio() {
		return GRIGIO;
	}

	public  String getGiallo() {
		return GIALLO;
	}

	public  String getArancio() {
		return ARANCIO;
	}

	public  String getVerde() {
		return VERDE;
	}

	public  String getRosso() {
		return ROSSO;
	}

	public  String getViola() {
		return VIOLA;
	}

	public  String getVerdeScuro() {
		return VERDE_SCURO;
	}

	public  String getRosa() {
		return ROSA;
	}

	public  String getRosaScuro() {
		return ROSA_SCURO;
	}

	public  String getAzzurro() {
		return AZZURRO;
	}

	public  String getBlu() {
		return BLU;
	}

	public  ArrayList<String> getColori() {
		return colori;
	}
	
	
}
