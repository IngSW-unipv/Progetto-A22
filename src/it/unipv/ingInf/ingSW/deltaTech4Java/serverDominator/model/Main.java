package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		switch(x_max) {
		case 15:
			n_basi= 3;
			giocatori= new Giocatore[n_basi];
			giocatori[0].setNome(player);
			giocatori[1].setNome("lonfo");
			giocatori[2].setNome("bobby");
			
			c_basi= new Coordinate[n_basi];
			c_basi[0].setX(3);
			c_basi[0].setY(1);
			c_basi[1].setX(11);
			c_basi[1].setY(1);
			c_basi[2].setX(7);
			c_basi[2].setY(8);
			
			for(i=0; i<n_basi; i++) {
				nodi_mappa[i]= new Base(c_basi[i], giocatori[i]);
			}
			break;
		case 20:
			n_basi=5;
			break;
		case 30:
			n_basi=10;
			break;
		}
	}

}
