package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
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
	
	/**
	 * costruttore che crea un oggetto abstract Risorsa con un livello pari a quello passato come parametro
	 * @param livello_risorsa
	 * livello della risorsa
	 */
	public Risorse(int livello_risorsa) {
		if(livello_risorsa<=MAX_LVL) {
			this.livello_risorsa=livello_risorsa;
		}
		e_richiesta=0;
		tempo_richiesto=0;
	}
	
	/**aggiorna il livello delle risorse, generico per tutte le risorse 
	 * @return
	 * risultato potenziamento
	 * */
	public boolean potenziamento() {
		
		boolean powerup=false;
		int i;
		i=livello_risorsa+1;
		if(i<=MAX_LVL) {
			livello_risorsa= livello_risorsa+1;
			this.effetto();
			powerup=true;
		}
		return powerup;
	}
	public abstract void effetto();

	
//--------getter and setter-------//	
	/**
	 * Returna il livello della risorsa
	 * @return
	 * livello risorsa
	 */
	public int getLivello_risorsa() {
		return livello_risorsa;
	}
	/**
	 * Returna il nome della risorsa
	 * @return
	 * nome risorsa
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Setta il nome della risorsa con quella passata come parametro
	 * @param
	 * nome risorsa
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Setta il livello della risorsa con quella passata come parametro
	 * @param
	 * livello risorsa
	 */
	public void setLivello_risorsa(int livello_risorsa) {
		this.livello_risorsa = livello_risorsa;
	}
	/**
	 * Returna il livello di energia necessaria alla risorsa per essere potenziata
	 * @return
	 * energia richiesta per il potenziamento
	 */
	public int getE_richiesta() {
		return e_richiesta;
	}
	/**
	 * Setta il livello di energia necessaria alla risorsa per essere potenziata
	 * @param
	 * energia richiesta per il potenziamento
	 */
	public void setE_richiesta(int e_richiesta) {
		this.e_richiesta = e_richiesta;
	}
	/**
	 * Returna il tempo necessario alla risorsa per essere potenziata
	 * @return
	 * tempo richiesto per il potenziamento
	 */
	public int getTempo_richiesto() {
		return tempo_richiesto;
	}
	/**
	 * returna il livello massimo della risorsa
	 * @return
	 * livello massimo
	 */
	public int getMAX_LVL() {
		return MAX_LVL;
	}
	/**
	 * Setta il tempo necessario alla risorsa per essere potenziata
	 * @param
	 * tempo richiesto per il potenziamento
	 */
	public void setTempo_richiesto(int tempo_richiesto) {
		this.tempo_richiesto = tempo_richiesto;
	}
	
/* ATTENZIONE: le stat rappresentano le variabili che le risorse specifiche
 * devono riportate ad altre classi, nelle sottoclassi questi metodi saranno
 * specializzati.
 */
	/**
	 * returna Stat1
	 * @return stat1
	 */
	public int getStat1() {
		return stat1;
	}
	/**
	 * setta stat1
	 * @param stat1
	 */
	public void setStat1(int stat1) {
		this.stat1 = stat1;
	}
	/**
	 * returna Stat2
	 * @return stat2
	 */
	public int getStat2() {
		return stat2;
	}
	/**
	 * setta stat2
	 * @param stat2
	 */
	public void setStat2(int stat2) {
		this.stat2 = stat2;
	}
	/**
	 * returna Stat3
	 * @return stat3
	 */
	public int getStat3() {
		return stat3;
	}
	/**
	 * setta stat3
	 * @param stat3
	 */
	public void setStat3(int stat3) {
		this.stat3 = stat3;
	}
	
	
}
