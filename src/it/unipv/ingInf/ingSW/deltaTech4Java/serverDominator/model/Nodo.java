package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.risorse.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.software.*;

public abstract class Nodo implements INodo{
	private int x, y;
	private int dist_base;
	private Giocatore possessore;
	private Risorse cpu, ram, firewall, energia;
	private final int TIPI_SOFTWARE=2;
	private Software[] stats_software_creati;
	private int software_totali;
	private boolean[] ostili;
	private final int n_vicini=5;
	
	
	public Nodo (int x, int y) {
		this.x= x;
		this.y=y;
		possessore= null;
		cpu= new Cpu(0);
		ram= new Ram(0);
		energia= new Energia(0);
		firewall= new Firewall(0);
		stats_software_creati = new Software[TIPI_SOFTWARE];
		ostili= new boolean[n_vicini];
}
	
	public void potenzia_risorsa(Risorse risorsa) {
		boolean check=false;
		int en_usata;
		if(risorsa.getE_richiesta()<= energia.getStat1()) {
			en_usata= energia.getStat1()-risorsa.getE_richiesta();
			energia.setStat1(en_usata);
//fare partire un timer basato su risorsa.getTempo_richiesto();
			check= risorsa.potenziamento(); 
		}
		if(check) {
			System.out.println("potenziamento eseguito");
		} else System.out.println("potenziamento fallito");
	}
	public void crea_software(Software software, int quantità) {
		boolean check=false;
		software_totali= software_totali + quantità;
		if(software_totali<=ram.getStat1()) {
			switch(software.getId()) {
			case 0: 
				stats_software_creati[0]= new Antivirus(cpu.getStat1(), quantità);
				break;
			case 1: 
				stats_software_creati[1]= new Virus(cpu.getStat2(), quantità);
				break;
			case 2:
				stats_software_creati[2] = new Rootcrash(cpu.getStat3(), quantità);
				break;
			}
			check= true;
		} else System.out.println("spazio non disponibile in ram");
		if(check){
			System.out.println("successo");
		} else System.out.println("azione non eseguita");
		
	}
	
	public boolean attaccabile(Nodo vicino) {
		boolean check=true;
		int x_vicino=vicino.getX();
		int y_vicino=vicino.getY();
		if(vicino.getPossessore()== possessore) {
			check=false;
		}
		return check;
		
	}
	public boolean conquista() {
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
	
	
}