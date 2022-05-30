package sreverDominator.model;

import java.util.Hashtable;
import java.util.Map;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.mappa.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Cloud;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.nodo.Nodo;

public class Test3 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<int[], Nodo> map;
		Nodo[] nodi;
	//	Coordinate[] posizioni;
	//	posizioni= new Coordinate[x_max*y_max];
		
		int x_max=15;
		int y_max=10;
		int i,j;
		int k=0;
		Coordinate c1;
		Nodo n;
		Giocatore g,s;
		s= new Utente("sistema",0);
		g= new Utente("giorgiooooo",10);
		c1= new Coordinate(0,0);
		n= new Base(g);
		
		map= new Hashtable<int[], Nodo>();
		nodi= new Nodo[x_max*y_max];
/*		
		for(k=0;k<x_max*y_max; k++) {
			nodi[k]= new Cloud(s);
		}
		k=0;

		nodi[3]= new Base(g);
*/		
		Nodo[][] posizioni;
		posizioni=new Nodo[x_max][y_max];
				
		for(i=0; i<x_max; i++) {
			for(j=0; j<y_max; j++ ) {
				posizioni[i][j]= new Cloud(s);
			}
		}
		
		posizioni[3][1] = new Base(g);
/*		
		for(k=0; k<(x_max*y_max); k++) {
			map.put(posizioni[k], nodi[k] );
		}
*/		
	//	map.put(posizioni[0], nodi[0]);
	//	map.put(posizioni[1], nodi[1]);
	//	map.replace(c1, n);
		
	//	System.out.println("test possessore:"+ map.get(c1).getPossessore().getNome());
		
		System.out.println("il possessore del cloud �:"+ posizioni[0][0].getPossessore());
		System.out.println("invece quello della base �:"+posizioni[3][1].getPossessore().getNome());
		
	}

}
