package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

/**@author Luca Casto 
 * @author Matteo Caprio
 * @version 1.0
 * @since 1.0
 * classe mappa, gestisce i nodi, li assegna ai giocatori, tiene aggiornati
 * i proprietari delle basi assegnate, verifica se due nodi sono prossimi
 */
public class MappaDefinitiva{
	private Nodo[][] map;
	private Coordinate[] basi;
	private Coordinate[] basiutente;
	private int contabasi;
	private int sceltabase;
	private int x_max, y_max, n_basi;
	private String[] vicini;
	@SuppressWarnings("unused")
	private Coordinate[] confini; 
	private int xsup, xinf, ysup, yinf;
	

	/**costruisce una mappa chiedendo in ingresso le coordinate massime
	 * scelte dall'utente e la lista giocatori
	 * @param x_max
	 * indica la coordinata massima delle ascisse
	 * @param y_max
	 * indica la coordinata massima delle ordinate
	 * @param giocatori
	 * lista dei giocatori partecipanti alla partita, usato per assegnare ad ogni giocatore
	 * il nodo base di partenza
	 */
	public MappaDefinitiva(int x_max, int y_max, Giocatore[] giocatori) {
		this.x_max= x_max;
		this.y_max=y_max;
		int i,j;
		
		map= new Nodo[x_max][y_max];
		
		switch(x_max) {
		case(15):
			n_basi=3;
			break;
		case(20):
			n_basi=5;
			break;
		case(30):
			n_basi=10;
			break;
		}
		
		this.assegnamento(n_basi, giocatori);
		
		for(i=0; i<x_max; i++) {
			for(j=0; j<y_max; j++ ) {
				if(map[i][j]==null) {
					map[i][j]= new Cloud(giocatori[0]);
				}
			} 
		}
		
		vicini= new String[6];
		confini= new Coordinate[6];
		basiutente= new Coordinate[n_basi];
		sceltabase=0;
		
	}
	
	/**assegna i giocatori ai nodi base iniziali
	 * 
	 * @param n_basi
	 * passato dalla selezione di difficolta indica anche il numero di giocatori
	 * @param giocatori
	 * vettore dei giocatori partecipanti alla partita
	 */
	public void assegnamento(int n_basi, Giocatore[] giocatori) {
		/** metodo usato per assegnare le basi ai giocatori/bot, nella mappa di gioco
		 * selezionando in base alla difficolt� scelta
		 */
		
			basi= new Coordinate[n_basi];
			
			switch(n_basi) {
			case(3):
				map[3][1]= new Base(giocatori[1]);
				map[11][1]= new Base(giocatori[2]);
				map[7][8]= new Base(giocatori[3]);
				
				basi[0]= new Coordinate(3,1,giocatori[1].getNome());
				basi[1]= new Coordinate(11,1, giocatori[2].getNome());
				basi[2]= new Coordinate(7,8,giocatori[3].getNome());
				break;
			case(5):
				map[4][2]=new Base(giocatori[1]);
				map[12][2]= new Base(giocatori[2]);
				map[1][7]= new Base(giocatori[3]);
				map[16][7]= new Base(giocatori[4]);
				map[8][12]= new Base(giocatori[5]);
				
				basi[0]= new Coordinate(4,2,giocatori[1].getNome());
				basi[1]= new Coordinate(12,2, giocatori[2].getNome());
				basi[2]= new Coordinate(1,7,giocatori[3].getNome());
				basi[3]=new Coordinate(16,7, giocatori[4].getNome());
				basi[4]=new Coordinate(8,12, giocatori[5].getNome());
				break;
			case(10):
				map[6][2]= new Base(giocatori[1]);
				map[15][3]= new Base(giocatori[2]);
				map[21][2]= new Base(giocatori[3]);
				map[4][11]= new Base(giocatori[4]);
				map[10][10]= new Base(giocatori[5]);
				map[19][12]= new Base(giocatori[6]);
				map[27][11]= new Base(giocatori[7]);
				map[7][18]= new Base(giocatori[8]);
				map[16][16]= new Base(giocatori[9]);
				map[23][17]= new Base(giocatori[10]);
				
				basi[0]= new Coordinate(6,2,giocatori[1].getNome());
				basi[1]= new Coordinate(15,3, giocatori[2].getNome());
				basi[2]= new Coordinate(21,2,giocatori[3].getNome());
				basi[3]= new Coordinate(4,11, giocatori[4].getNome());
				basi[4]= new Coordinate(10,10, giocatori[5].getNome());
				basi[5]= new Coordinate(19,12,giocatori[6].getNome());
				basi[6]= new Coordinate(27,11, giocatori[7].getNome());
				basi[7]= new Coordinate(7,18,giocatori[8].getNome());
				basi[8]= new Coordinate(16,16, giocatori[9].getNome());
				basi[9]= new Coordinate(23,17, giocatori[10].getNome());
				break;
			}
		}
	
	/**aggiorna il proprietario nel caso un giocatore perda la sua base in battaglia
	 * uscendo cosi dal gioco
	 * @param x
	 * ascissa del nodo attaccato, usato come indice
	 * @param y
	 * ordinata del nodo attaccato, usato come indice
	 * @param attaccante
	 * giocatore che sceglie di attaccare un altro nodo
	 */
/*	public void aggiornabasi(int x, int y, Giocatore attaccante) {
	
		int i;
		for(i=0;i<n_basi;i++) {
			if(basi[i].getX()==x && basi[i].getY()==y) {
				basi[i].setNome(attaccante.getNome());
			}
		}
	}
	*/
	
	/**controlla se un giocatore ha piu di un nodo base, 
	 * avendone magari conquistata una da un' altro giocatore
	 * @param player
	 * utente o giocatore in generale, che possiede piu di un nodo base
	 */
/*	public void checkbasi(Giocatore player) {
		int i;
		int x,y;
		contabasi=1;
		
		for(i=0;i<n_basi; i++) {
			if(basi[i].getNome().equals(player.getNome()) ) {
				x=basi[i].getX();
				y=basi[i].getY();	
				basiutente[contabasi-1]= new Coordinate(x, y, player.getNome());
				contabasi++;
			} 
		}
	}*/
	
	/** usato per individuare, dato il giocatore che intende attaccare, 
	 * il nodo base del giocatore attaccante
	 * @param player
	 * giocatore che intende attaccare
	 * @return
	 * nodo base, da cui parte l'attacco
	 */
	public Nodo trovaBase( Giocatore player) {
		
			int i;
			int x,y;
			x=3;
			y=1;
			/*if(sceltabase!=0) {
				x=basiutente[sceltabase].getX();
				y=basiutente[sceltabase].getY();
			}else*/ 
				for(i=0;i<n_basi; i++) {
					if(basi[i].getNome().equals(player.getNome()) ) {
						x=basi[i].getX();
						y=basi[i].getY();	
					} 
				}
			
			return map[x][y];
		}
	
	/**controlla se due nodi sono prossimi ossia confinanti, date le coordinate
	 * del nodo bersaglio e il giocatore attaccante 
	 * @param x
	 * ascissa del nodo attaccato/ bersaglio
	 * @param y
	 * ordinata del nodo attaccato / bersaglio
	 * @param player
	 * giocatore attaccante
	 * @return
	 * true se il nodo selezionato, bersaglio, � confinante con un nodo del giocatore attaccante, 
	 * false se il nodo selezionato, bersaglio,  non confina con un nodo del giocatore attacante
	 */
	public boolean attaccabile(int x, int y, Giocatore player) {
			
		y=this.checky(y);
		
		if(map[x][y].getPossessore().getNome().equalsIgnoreCase(player.getNome()))
			return false;
		
		boolean prox=false;
		int i;
		
		
		x= x - y/2;
		xsup= x+1;
		xinf=x-1;
		ysup=y+1;
		yinf= y-1;
		
		if(xsup>= x_max)
			xsup= 0;
			
		if (xinf<0) 
			xinf=x_max-1;
		if(ysup>= y_max) 
			ysup=0;
		if (y-1<0)
			yinf=y_max-1;

		vicini[0]=map[checkx(xinf+(y/2))][y].getPossessore().getNome();	
			
		vicini[1]=map[checkx(xsup+(y/2))][y].getPossessore().getNome();
	
		vicini[2]=map[checkx(x+(ysup/2))][ysup].getPossessore().getNome();
		
		vicini[3]=map[checkx(x+(yinf/2))][yinf].getPossessore().getNome();
		
		vicini[4]=map[checkx(xsup+(yinf/2))][yinf].getPossessore().getNome();
		
		vicini[5]=map[checkx(xinf+(ysup/2))][ysup].getPossessore().getNome();
		
		for(i=0;i<6;i++) {
			if(vicini[i].equalsIgnoreCase(player.getNome())) {
				prox=true;
				break;
			}
		}
		return prox;
	}
		
	private int checkx(int i) {

		if(i>= x_max)
			i= 0;
			
		if (i<0) 
			i=x_max-1;
		
		return i;
	}
	
	private int checky(int i) {

		if(i>= y_max)
			i= 0;
			
		if (i<0) 
			i=y_max-1;
		
		return i;
	}
	
	/*
	/**calcola la distanza minima partendo dal nodo base del giocatore attaccante fino
	 * al nodo selezionato come obbiettivo dell'attacco, necessario per calcolare il
	 * tempo necessario alla battaglia
	 * @param x
	 * ascissa del nodo bersaglio
	 * @param y
	 * ordinata del nodo bersaglio
	 * @param player
	 * giocatore attaccante
	 * @return
	 * nodo confinante al bersaglio il cui valore di distanza alla base � minore di tutti
	 * gli altri nodi del giocatore attaccante confinanti con il bersaglio
	 */
	/*
		public Nodo dist_minima(int x, int y, Giocatore player) {
		
			int i, temp, dist_min;

				confini[0]= new Coordinate(check(xinf+(y/2)),y, vicini[0]);
				confini[1]= new Coordinate(check(xsup+(y/2)),y, vicini[1]);
				confini[2]= new Coordinate(check(x+(ysup/2)), ysup, vicini[2]);
				confini[3]= new Coordinate(check(x+(yinf/2)), yinf, vicini[3]);
				confini[4]= new Coordinate(check(xsup+(yinf/2)),yinf, vicini[4]);
				confini[5]= new Coordinate(check(xinf+(ysup/2)),ysup, vicini[5]);	
				
			
			dist_min=10000;
			temp=0;
			
			for(i=0;i<6;i++) {
				if(confini[i].getNome()==player.getNome() ) {
					if(dist_min > map[confini[i].getX()][confini[i].getY()].getDist_base() ) {
						
						dist_min=map[confini[i].getX()][confini[i].getY()].getDist_base();
						temp= i;
					}
				}
			}
			
			return map[confini[temp].getX()][confini[temp].getY()];
		
		}
	*/
	//-------------getter and setter--------//
		/**
		 * returna il nodo identificato dalle coordinate passate come parametro
		 * @param x
		 * @param y
		 * @return
		 * nodo selezionato
		 */
		public Nodo getNodo(int x, int y) {
			y= this.checky(y);
			
			return map[x][y];
		}
		/**
		 * returna la x massima
		 * @return
		 * x massima
		 */
		public int getX_max() {
			return x_max;
		}
		/**
		 * returna la y massima
		 * @return
		 * y massima
		 */
		public int getY_max() {
			return y_max;
		}
		/**
		 * returna un array a doppia entrata rappresentante l'elenco dei nodi contenuti dentro la mappa i cui ingressi sono rispettivamente la coordinata x e la coordinata y assegnate al nodo
		 * @return
		 * nodi della mappa
		 */
		public Nodo[][] getMap() {
			return map;
		}
		/**
		 * returna un array di coordinate che sono associate alle basi della mappa
		 * @return
		 * nodi della mappa
		 */
		public Coordinate[] getBasi() {
			return basi;
		}
		/**
		 * returna il valore int a cui � associata la base scelta all'interno dell'array "basi" di tipo coordinate
		 * @return
		 * identificatore base scelta
		 */
		public int getScelta() {
			return sceltabase;
		}
		/**
		 * setta il valore int a cui � associata la base scelta all'interno dell'array "basi" di tipo coordinate
		 * @param
		 * identificatore base scelta
		 */
		public void setScelta(int scelta) {
			this.sceltabase = scelta;
		}
		/**
		 * returna la quantita di basi possedute dal giocatore
		 * @return
		 * numero di basi possedute
		 */
		public int getContabasi() {
			return contabasi;
		}
		/**
		 * returna l'array di coordinate contenenti le basi conquistate dal giocatore
		 * @return
		 * basi possedute
		 */
		public Coordinate[] getBasiutente() {
			return basiutente;
		}

		
}