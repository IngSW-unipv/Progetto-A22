package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Battaglia;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Cloud;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Mappa;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;

public class mappaProva {
	private Nodo[][] map;
	private int x_max, y_max;
	private Giocatore[] giocatori;
	
	public mappaProva(int x_max, int y_max, int n_basi, String utente) {
		this.x_max= x_max;
		this.y_max=y_max;
		int i,j;
		
		map= new Nodo[x_max][y_max];
		giocatori= new Giocatore[n_basi+1];
		giocatori=this.creazioneGiocatori(utente, x_max, n_basi);
		for(i=0; i<x_max; i++) {
			for(j=0; j<y_max; j++ ) {
				map[i][j]= new Cloud(giocatori[0]);
			} 
		}
		this.assegnamento(n_basi, giocatori);
	}
	
	public Giocatore[] creazioneGiocatori(String utente, int x_max, int n_basi) {
		switch(x_max) {
		case 15:
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]=new Bot("bob");
			giocatori[3]= new Bot("sandra");
			break;
		case 20:
			n_basi=5;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			break;
		case 30:
			n_basi=10;
			giocatori= new Giocatore[n_basi+1];
			giocatori[0]= new Sistema();
			giocatori[1]= new Utente(utente);
			giocatori[2]= new Bot("bob");
			giocatori[3]= new Bot("sandra");
			giocatori[4]= new Bot("roger");
			giocatori[5]= new Bot("max");
			giocatori[6]= new Bot("jupiter");
			giocatori[7]= new Bot("alex");
			giocatori[8]= new Bot("lonfo");
			giocatori[9]= new Bot("max");
			giocatori[10]=new Bot("alice");
			break;
		}
		return giocatori;
	}
	public void assegnamento(int n_basi, Giocatore[] giocatori) {
	/** metodo usato per assegnare le basi ai giocatori/bot, nella mappa di gioco
	 * selezionando in base alla difficoltà scelta
	 */
		switch(n_basi) {
		case(3):
			map[3][1]= new Base(giocatori[1]);
			map[11][1]= new Base(giocatori[2]);
			map[7][8]= new Base(giocatori[3]);
			break;
		case(5):
			map[4][2]=new Base(giocatori[1]);
			map[12][2]= new Base(giocatori[2]);
			map[1][7]= new Base(giocatori[3]);
			map[16][7]= new Base(giocatori[4]);
			map[8][12]= new Base(giocatori[5]);
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
			break;
		}
	}
	public Giocatore[] getListaGiocatori(){
		return giocatori;
	}
	public boolean attaccabile(int x, int y, Giocatore player) {
	/**metodo con il quale si controlla se due nodi sono prossimi
	 * tra loro, ossia se nelle vicinanze del nodo bersaglio esiste almeno 
	 * un nodo posseduto dal giocatore.
	 */
		boolean prox=false;
		int i;
		String[] vicini;
		vicini= new String[6];
		
		vicini[0]=map[x-1][y].getPossessore().getNome();
		vicini[1]=map[x+1][y].getPossessore().getNome();
		vicini[2]=map[x][y+1].getPossessore().getNome();
		vicini[3]=map[x][y-1].getPossessore().getNome();
		vicini[4]=map[x+1][y-1].getPossessore().getNome();
		vicini[5]=map[x-1][y+1].getPossessore().getNome();
		
		for(i=0;i<6;i++) {
			if(vicini[i]== player.getNome() ) {
				prox=true;
			}
		}
		return prox;
	}
}
