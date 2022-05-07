package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public class MappaDefinitiva {
	private Nodo[][] map;
	private Coordinate[] basi;
	private Coordinate[] basiutente;
	private int contabasi;
	private int sceltabase;
	private int x_max, y_max, n_basi;
	private String[] vicini;
	private Coordinate[] confini; 
	private int xsup, xinf, ysup, yinf;

	public MappaDefinitiva(int x_max, int y_max, Giocatore[] giocatori) {
		this.x_max= x_max;
		this.y_max=y_max;
		int i,j;
		
		map= new Nodo[x_max][y_max];
		
		for(i=0; i<x_max; i++) {
			for(j=0; j<y_max; j++ ) {
				map[i][j]= new Cloud(giocatori[0]);
			} 
		}
		this.assegnamento(n_basi, giocatori);
		vicini= new String[6];
		confini= new Coordinate[6];
		basiutente= new Coordinate[n_basi];
		sceltabase=0;
		
	}
	
	public void assegnamento(int n_basi, Giocatore[] giocatori) {
		/** metodo usato per assegnare le basi ai giocatori/bot, nella mappa di gioco
		 * selezionando in base alla difficoltà scelta
		 */
			this.n_basi=n_basi;
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
	
	public void aggiornabasi(int x, int y, Giocatore attaccante) {
	
		int i;
		for(i=0;i<n_basi;i++) {
			if(basi[i].getX()==x && basi[i].getY()==y) {
				basi[i].setNome(attaccante.getNome());
			}
		}
	}
	
	public void checkbasi(Giocatore player) {
		int i;
		int x,y;
		contabasi=0;
		
		for(i=0;i<n_basi; i++) {
			if(basi[i].getNome().equals(player.getNome()) ) {
				x=basi[i].getX();
				y=basi[i].getY();	
				basiutente[contabasi]= new Coordinate(x, y, player.getNome());
				contabasi++;
			} 
		}
	}
	
	public Nodo trovaBase( Giocatore player) {
		/** metodo usato per la ricerca della base del giocatore attaccante
		 * nel caso di battaglia (memo: gli attacchi partono sempre da una base).
		 */
			int i;
			int x,y;
			x=3;
			y=1;
			if(sceltabase!=0) {
				x=basiutente[sceltabase].getX();
				y=basiutente[sceltabase].getY();
			}else 
				for(i=0;i<n_basi; i++) {
					if(basi[i].getNome().equals(player.getNome()) ) {
						x=basi[i].getX();
						y=basi[i].getY();	
					} 
				}
			
			return map[x][y];
		}
	
	public boolean attaccabile(int x, int y, Giocatore player) {
		/**metodo con il quale si controlla se due nodi sono prossimi
		 * tra loro, ossia se nelle vicinanze del nodo bersaglio esiste almeno 
		 * un nodo posseduto dal giocatore.
		 * Inoltre essendo i confini della mappa adiacenti tra loro, si controlla
		 * se le coordinate sforino dalla mappa per rientrare dal lato opposto.
		 */
			boolean prox=false;
			int i;
			
			if(x+1> x_max) {
				xsup= 0;
			} else xsup= x+1;
			
			if (x-1<0) {
				xinf=x_max;
			} else xinf=x-1;
			
			if(y+1> y_max) {
				ysup=0;
			} else ysup=y+1;
			
			if (y-1<0) {
				yinf=y_max;
			} else yinf= y-1;

			vicini[0]=map[xinf][y].getPossessore().getNome();	
			vicini[1]=map[xsup][y].getPossessore().getNome();
			vicini[2]=map[x][ysup].getPossessore().getNome();
			vicini[3]=map[x][yinf].getPossessore().getNome();
			vicini[4]=map[xsup][yinf].getPossessore().getNome();
			vicini[5]=map[xinf][ysup].getPossessore().getNome();
			
			for(i=0;i<6;i++) {
				if(vicini[i]== player.getNome() ) {
					prox=true;
				}
			}
			return prox;
		}
		
		public Nodo dist_minima(int x, int y, Giocatore player) {
		/**metodo per il calcolo della distanza minima dalla base
		 * usato per calcolare il tempo necessario per l'attacco.
		 */
			int i, temp, dist_min;

				confini[0]= new Coordinate(xinf,y, vicini[0]);
				confini[1]= new Coordinate(xsup,y, vicini[1]);
				confini[2]= new Coordinate(x, ysup, vicini[2]);
				confini[3]= new Coordinate(x, yinf, vicini[3]);
				confini[4]= new Coordinate(xsup,yinf, vicini[4]);
				confini[5]= new Coordinate(xinf,ysup, vicini[5]);	
			
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
		
		public void aggiornastati(Nodo bersaglio, Nodo partenza) {
			bersaglio.setDist_base(partenza.getDist_base()+1);
			bersaglio.setPossessore(partenza.getPossessore());
		}
		
		public Nodo getNodo(int x, int y) {
			return map[x][y];
		}
		
		public int getX_max() {
			return x_max;
		}
		
		public int getY_max() {
			return y_max;
		}

		public Nodo[][] getMap() {
			return map;
		}

		public Coordinate[] getBasi() {
			return basi;
		}

		public int getScelta() {
			return sceltabase;
		}

		public void setScelta(int scelta) {
			this.sceltabase = scelta;
		}

		public int getContabasi() {
			return contabasi;
		}

		public Coordinate[] getBasiutente() {
			return basiutente;
		}

		
}
