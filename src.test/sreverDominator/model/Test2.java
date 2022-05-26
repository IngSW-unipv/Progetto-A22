package sreverDominator.model;

import java.util.Hashtable;
import java.util.Map;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Coordinate;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;

public class Test2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<Coordinate, Nodo> map1;
		Map<Coordinate, String> map2;
		Coordinate c1, c2;
		Nodo n;
		Giocatore pluto, pippo;
	
		map1= new Hashtable<Coordinate, Nodo>();
		map2= new Hashtable<Coordinate, String>();
		
		c1=new Coordinate(0,1);
		c2= new Coordinate (2,2);
		
		pluto=new Utente("pluto",20);

		
		n= new Base(pluto);
		
		map1.put(c1, n);
		map2.put(c2, n.getPossessore().getNome() );
		System.out.println("che la mappa stampi:"+ map1.get(c1).getPossessore().getNome());
		
		String test = map2.get(c2);
		System.out.println(test);
	
		
	}
}
