package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * v1.0
 * astrazione delle risorse usate nel gioco server dominator, 
 * usata astrazione per risorse aggiuntive in versioni successive
 */


public abstract class Risorse {
	private int livello_risorsa;
	private String nome;
	protected final int MAX_LVL=10;
	private int e_richiesta;
	private int tempo_richiesto;
	private int stat1, stat2, stat3;
	
	public Risorse(int livello_risorsa) {
		if(livello_risorsa<=MAX_LVL) {
			this.livello_risorsa=livello_risorsa;
		}
		e_richiesta=0;
		tempo_richiesto=0;
	}
/**aggiorna il livello delle risorse, generico per tutte le risorse */
	
	public boolean potenziamento() {
		boolean powerup=false;
		int i;
		i=livello_risorsa+1;
		if(i<=MAX_LVL) {
			livello_risorsa= livello_risorsa+1;
			this.effetto();
			powerup=true;
		}
		// per test 
		//else System.out.println("Massimo livello raggiunto!");
		return powerup;
	}
	public abstract void effetto();

	
/** getter and setter*/
	
	public int getLivello_risorsa() {
		return livello_risorsa;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setLivello_risorsa(int livello_risorsa) {
		this.livello_risorsa = livello_risorsa;
	}

	public int getE_richiesta() {
		return e_richiesta;
	}

	public void setE_richiesta(int e_richiesta) {
		this.e_richiesta = e_richiesta;
	}

	public int getTempo_richiesto() {
		return tempo_richiesto;
	}

	public void setTempo_richiesto(int tempo_richiesto) {
		this.tempo_richiesto = tempo_richiesto;
	}
/** ATTENZIONE: le stat rappresentano le variabili che le risorse specifiche
 * devono riportate ad altre classi, nelle sottoclassi questi metodi saranno
 * specializzati.
 */
	public int getStat1() {
		return stat1;
	}

	public void setStat1(int stat1) {
		this.stat1 = stat1;
	}

	public int getStat2() {
		return stat2;
	}

	public void setStat2(int stat2) {
		this.stat2 = stat2;
	}

	public int getStat3() {
		return stat3;
	}

	public void setStat3(int stat3) {
		this.stat3 = stat3;
	}
	
	public int getMAX_LVL() {
		return MAX_LVL;
	}
	
	
}
