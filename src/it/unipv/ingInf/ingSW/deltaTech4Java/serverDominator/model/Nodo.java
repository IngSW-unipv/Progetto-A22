package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.*;

public abstract class Nodo implements INodo{
	private Coordinate coordinate;
	private int dist_base;
	private Giocatore possessore;
	private int software_disponibile;
	private int software_max;
	private int bonus_def;
	private int val_def, val_atkv, val_atkr;
	private int e_disponibile;
	private int lvl_cpu, lvl_ram, lvl_firewall;
	protected Timer time1, time2; 
/**time1 usato per risorse, time 2 usato per software*/
	
	public Nodo (Coordinate coordinate) {
		this.coordinate=coordinate;
		software_disponibile=0;
		this.time1=new Timer();
		this.time2= new Timer();
}
		
/**getter and setter*/
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

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

	public int getVal_def() {
		return val_def;
	}

	public void setVal_def(int val_def) {
		this.val_def = val_def;
	}

	public int getVal_atkv() {
		return val_atkv;
	}

	public void setVal_atkv(int val_atkv) {
		this.val_atkv = val_atkv;
	}

	public int getVal_atkr() {
		return val_atkr;
	}

	public void setVal_atkr(int val_atkr) {
		this.val_atkr = val_atkr;
	}
	
	
}