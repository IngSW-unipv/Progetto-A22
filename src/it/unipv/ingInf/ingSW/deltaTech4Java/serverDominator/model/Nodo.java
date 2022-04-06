package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

/**
 * @author Luca Casto 
 * v1.0
 * prima versione classe astratta dei nodi base e cloud.
 */

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.Software;

public abstract class Nodo implements INodo{
	private int dist_base;
	private Giocatore possessore;
	private int software_disponibile;
	private int software_max;
	private int bonus_def;
	private int e_disponibile;
	private int lvl_cpu, lvl_ram, lvl_firewall;
	protected Timer time1, time2; 
/**time1 usato per risorse, time 2 usato per software*/
	
	public Nodo () {
		software_disponibile=0;
		this.time1=new Timer();
		this.time2= new Timer();
		
}
		
/**getter and setter*/
	
	public int getDist_base() {
		return dist_base;
	}

	public void setDist_base(int dist_base) {
		this.dist_base = dist_base;
	}

	public Giocatore getPossessore() {
		return possessore;
	}

	public void setPossessore(Giocatore possessore) {
		this.possessore = possessore;
	}

	public int getSoftware_disponibile() {
		return software_disponibile;
	}
	
	public void setSoftware_disponibile(int software_totali) {
		this.software_disponibile = software_totali;
	}
	
	public int getSoftware_max() {
		return software_max;
	}
	
	public void setSoftware_max(int software_max) {
		this.software_max = software_max;
	}
	
	public int getBonus_def() {
		return bonus_def;
	}
	
	public void setBonus_def(int bonus_def) {
		this.bonus_def = bonus_def;
	}
	
	public int getE_disponibile() {
		return e_disponibile;
	}
	
	public void setE_disponibile(int e_disponibile) {
		this.e_disponibile = e_disponibile;
	}
	
	public int getLvl_cpu() {
		return lvl_cpu;
	}
	
	public void setLvl_cpu(int lvl_cpu) {
		this.lvl_cpu = lvl_cpu;
	}
	
	public int getLvl_ram() {
		return lvl_ram;
	}
	
	public void setLvl_ram(int lvl_ram) {
		this.lvl_ram = lvl_ram;
	}
	
	public int getLvl_firewall() {
		return lvl_firewall;
	}
	
	public void setLvl_firewall(int lvl_firewall) {
		this.lvl_firewall = lvl_firewall;
	}
/**getter astratto per restituire i valori dei software delle classi specializzate*/
	public abstract Software[] getStats_software_creati(); 
		
}