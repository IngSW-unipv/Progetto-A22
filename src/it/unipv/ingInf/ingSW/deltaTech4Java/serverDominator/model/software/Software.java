package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software;

/**
 * @author Luca Casto 
 * @version 1.0
 * @since 1.0
 * astrazione delle unita da combattimento
 * usate nel gioco server dominator, 
 * usata astrazione per software aggiuntivi in versioni successive
 */
public abstract class Software {
	private int livello;
	private int max_lvl;
	private String nome;
	private int quantita;
	private int temp_richiesto;
	/**
	 * costruttore che crea un oggetto abstract Software con un livello pari a quello passato come parametro
	 * @param livello
	 * livello Software
	 */
	public Software(int livello) { 
		this.livello = livello;
	}

	public abstract int getVal_def();
	public abstract int getVal_atk();
	
//-------------getter and setter-----//
	/**
	 * returna il livello del software
	 * @return
	 * livello
	 */
	public int getLivello() {
		return livello;
	}
	/**
	 * setta il livello del software con valore pari a quello passato come parametro
	 * @param
	 * valore livello
	 */
	public void setLivello(int livello) {
		this.livello = livello;
	}
	/**
	 * returna il livello massimo del software
	 * @return
	 * livello massimo
	 */
	public int getMax_lvl() {
		return max_lvl;
	}
	/**
	 * setta il livello massimo del software
	 * @param
	 * livello massimo
	 */
	public void setMax_lvl(int max_lvl) {
		this.max_lvl = max_lvl;
	}
	/**
	 * returna il nome del software
	 * @return
	 * nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * setta il nome del software
	 * @param
	 * nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * returna la quantita disponibile del software
	 * @return
	 * quantita' software
	 */
	public int getQuantita() {
		return quantita;
	}
	/**
	 * setta la quantita' del software con valore pari a quello passato come parametro
	 * @param
	 * quantita'
	 */
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	/**
	 * returna il tempo richiesto per creare il software
	 * @return
	 * tempo
	 */
	public int getTemp_richiesto() {
		return temp_richiesto;
	}
	/**
	 * setta il tempo richiesto per creare il software con valore pari a quello passato come parametro
	 * @param
	 * tempo
	 */
	public void setTemp_richiesto(int temp_richiesto) {
		this.temp_richiesto = temp_richiesto;
	}
	
	

}
