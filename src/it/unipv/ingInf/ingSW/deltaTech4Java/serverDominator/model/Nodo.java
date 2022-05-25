package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;


/**
 * @author Luca Casto 
 * v1.0
 * prima versione classe astratta dei nodi base e cloud.
 */

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.Risorse;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.Software;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public abstract class Nodo implements INodo{
	private int dist_base;
	private Giocatore possessore;
	private int software_disponibile;
	private int software_max;
	private int bonus_def;
	private int e_disponibile;
	private int lvl_cpu, lvl_ram, lvl_firewall, E_lvl;
	private String tipologia;
	private PropertyChangeSupport changes;
	public static final String NOME_POSS_PROP="possessore.nome";
	
	/**
	 * Costruttore per creare un oggetto astratto Nodo vuoto
	 * @param 
	 * possessore
	 */
	public Nodo () {
		software_disponibile=0;
		this.changes= new PropertyChangeSupport(this);
		this.possessore=new Sistema();
	}
	/**
	 * Costruttore per creare un oggetto astratto Nodo il cui possessore sara' quello passato come parametro
	 * @param 
	 * possessore
	 */
	public Nodo (Giocatore possessore) {
		if (possessore==null||possessore.getNome()==null)
			possessore=new Sistema();
		this.possessore= possessore;
		this.software_disponibile=0;
		this.software_max=0;
		this.dist_base=0;
		this.changes= new PropertyChangeSupport(this);
		
	}
	/**
	 * Costruttore per creare un oggetto astratto Nodo uguale a quello passato come parametro
	 * @param 
	 * nodo
	 */
	public Nodo (Nodo nodo) {
		if (possessore==null||possessore.getNome()==null)
			possessore=new Sistema();
		this.possessore= nodo.getPossessore();
		this.software_disponibile=nodo.getSoftware_disponibile();
		this.software_max=nodo.getSoftware_max();
		this.dist_base=nodo.getDist_base();
		this.changes= new PropertyChangeSupport(this);
	}
		/**metodi da usare per il mercato, al momento specializzati 
		 * solo per il nodo base. Metodo usato anche per il potenziamento
		 * immediato dopo la conquista di un nodo cloud, fatto override.
		 * @param
		 * nome risorsa
		 */
	public boolean compra_risorsa(String nome) {
		boolean check=false;
		
		return check;
	}
	
	/**metodi da usare per il mercato, al momento specializzati 
	 * solo per il nodo base, fatto override.
	 * @param
	 * nome software
	 * @param
	 * quantita' software
	 */
	public boolean compra_software(String nome, int quantita) {
		boolean check=false;
		return check;
	}
		
	//-------------getter and setter--------//
	/**
	* setta la tipologia di nodo(base o cloud)
	* @param tipologia
	*/
	public void setTipologia(String tipologia) {
		this.tipologia=tipologia;
	}
	/**
	 * returna la tipologia del nodo
	 * @return
	 * tipologia
	 */
	public String getTipologia() {
		return tipologia;
	}
	/**
	 * returna la distanza del nodo dalla base del giocatore
	 * @return
	 * distanza dalla base
	 */
	public int getDist_base() {
		return dist_base;
	}
	/**
	 * setta la distanza del nodo dalla base del giocatore
	 * @param
	 * distanza dalla base
	 */
	public void setDist_base(int dist_base) {
		this.dist_base = dist_base;
	}
	/**
	 * returna il possessore del nodo
	 * @return
	 * possessore
	 */
	public Giocatore getPossessore() {
		return possessore;
	}
	/**
	 * setta il possessore del nodo
	 * @param
	 * possessore
	 */
	public void setPossessore(Giocatore possessore) {
		Giocatore old=new Giocatore(this.possessore) {};
		this.possessore = possessore;
		changes.firePropertyChange(NOME_POSS_PROP, old, this.getPossessore());
	}
	/**
	 * returna la quantita' di software disponibile
	 * @return
	 * quantita dei software
	 */
	public int getSoftware_disponibile() {
		return software_disponibile;
	}
	/**
	 * setta la quantita' di software disponibile
	 * @param
	 * quantita dei software
	 */
	public void setSoftware_disponibile(int software_totali) {
		this.software_disponibile = software_totali;
	}
	/**
	 * returna la quantita' massima di software creabili
	 * @return
	 * quantita massima dei software creabili
	 */
	public int getSoftware_max() {
		return software_max;
	}
	/**
	 * setta la quantita' massima di software creabili
	 * @param
	 * quantita massima dei software creabili
	 */
	public void setSoftware_max(int software_max) {
		this.software_max = software_max;
	}
	/**
	 * returna il bonus difensivo dato dal firewall del nodo
	 * @return
	 * bonus difensivo del firewall
	 */
	public int getBonus_def() {
		return bonus_def;
	}
	/**
	 * setta il bonus difensivo dato dal firewall del nodo
	 * @param
	 * bonus difensivo del firewall
	 */
	public void setBonus_def(int bonus_def) {
		this.bonus_def = bonus_def;
	}
	/**
	 * returna la quantita di energia disponibile del nodo
	 * @return
	 * quantita di energia disponibile
	 */
	public int getE_disponibile() {
		return e_disponibile;
	}
	/**
	 * setta la quantita di energia disponibile del nodo
	 * @param
	 * quantita di energia disponibile
	 */
	public void setE_disponibile(int e_disponibile) {
		this.e_disponibile = e_disponibile;
	}
	/**
	 * returna il livello di energia del nodo
	 * @return
	 * livello energia
	 */
	public int getE_lvl() {
		return E_lvl;
	}
	/**
	 * setta il livello di energia del nodo
	 * @param
	 * livello energia
	 */
	public void setE_lvl(int e_lvl) {
		E_lvl = e_lvl;
	}
	/**
	 * returna il livello della Cpu del nodo
	 * @return
	 * livello Cpu
	 */
	public int getLvl_cpu() {
		return lvl_cpu;
	}
	/**
	 * setta il livello della Cpu del nodo
	 * @param
	 * livello Cpu
	 */
	public void setLvl_cpu(int lvl_cpu) {
		this.lvl_cpu = lvl_cpu;
	}
	/**
	 * returna il livello della Ram del nodo
	 * @return
	 * livello Ram
	 */
	public int getLvl_ram() {
		return lvl_ram;
	}
	/**
	 * setta il livello della Ram del nodo
	 * @param
	 * livello Ram
	 */
	public void setLvl_ram(int lvl_ram) {
		this.lvl_ram = lvl_ram;
	}
	/**
	 * returna il livello della Firewall del nodo
	 * @return
	 * livello Firewall
	 */
	public int getLvl_firewall() {
		return lvl_firewall;
	}
	/**
	 * setta il livello della Firewall del nodo
	 * @param
	 * livello Firewall
	 */
	public void setLvl_firewall(int lvl_firewall) {
		this.lvl_firewall = lvl_firewall;
	}
	/**
	 * returna il colore del nodo
	 * @return
	 * colore del nodo
	 */
	public String getColore() {
		return possessore.getColore();
	}
	/**
	 * returna lo spazio disponibile nella Ram del nodo per creare nuovi Software
	 * @return
	 * spazio libero Ram
	 */
	public  int getSpazio_Ram() {
		return this.getSoftware_max()-this.getSoftware_disponibile();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Nodo other = (Nodo) obj;
		return Objects.equals(possessore.getNome(), other.possessore.getNome());
	}
	
	public PropertyChangeSupport getChanges() {
		return changes;
	}


/**getter astratto per restituire i valori dei software delle classi specializzate*/
	public abstract Software[] getStats_software_creati();
	public abstract Risorse[] getRisorse();
	public abstract int getTempoRisorsa(String nome);
	public abstract int getTempoSoftware(String nome);
	
}