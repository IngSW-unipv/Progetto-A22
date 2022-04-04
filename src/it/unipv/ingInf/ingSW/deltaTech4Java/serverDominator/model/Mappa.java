package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import java.util.*;

/**
 * @author Luca Casto 
 * v1.0
 * mappa di gioco, controlla il tabellone, istanzia e conosce i nodi.
 */

public class Mappa {
	private List<Nodo> listaNodi;
	private List<Coordinate> listaCoordinate;
	private Map<Coordinate, Nodo> map;
	
	
	public Mappa() {
		this.listaNodi=new ArrayList<>();
		this.listaCoordinate=new ArrayList<>();
		this.map=new HashMap<>();
	}
	
	public Mappa(List<Nodo> listaNodi, List<Coordinate> listaCoordinate, Map<Coordinate, Nodo> map) {
		this.listaNodi=new ArrayList<>();
		this.listaNodi=listaNodi;
		this.listaCoordinate=new ArrayList<>();
		this.listaCoordinate=listaCoordinate;
		this.map=new HashMap<>();
		this.map=map;
	}
	
}
