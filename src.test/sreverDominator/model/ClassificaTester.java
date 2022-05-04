package sreverDominator.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;

class ClassificaTester {
	private static ArrayList<Giocatore> list;
	private static Classifica classifica;

	@BeforeEach
	void setUp() throws Exception {
		list=new ArrayList<Giocatore>();
		Giocatore g;
		for(int i=0;i<10;i++) {
			g=new Giocatore("Giocatore"+i) {

			};
			g.setPunteggio(i*100);
			list.add(g);
		}
		classifica=new Classifica(list);
	}

	@AfterEach
	void tearDown() throws Exception {
		classifica.setClassifica(null);
	}

	@Test
	void testGetPosizioneGiocatore() {
		int p=classifica.getPosizione(new Giocatore("Giocatore9") {
		});
		assertEquals(0, p);
		assertNotEquals(1, p);
	}

	@Test
	void testGetPosizioneString() {
		int p=classifica.getPosizione("Giocatore7");
		classifica.aggiornaClassifica();
		assertEquals(2, p);
	}

	@Test
	void testAggiornaClassifica() {
		
		classifica.aggiornaGiocatore(new Giocatore("Giocatore9") {
		});
		classifica.aggiornaClassifica();
		assertNotEquals(list.toArray(), classifica.getClassifica().toArray());
	}

	@Test
	void testGetClassifica() {
		List<Giocatore> c=new ArrayList<Giocatore>(classifica.getClassifica());
		assertArrayEquals(c.toArray(), list.toArray());
	}

	@Test
	void testSetClassifica() {
		classifica.setClassifica(new ArrayList<Giocatore>(0));
		assertEquals(0,classifica.getLunghezzaClassifica());
	}

	@Test
	void testGetLunghezzaClassifica() {
		assertEquals(10, classifica.getLunghezzaClassifica());
	}

	@Test
	void testRemoveUtenteGiocatore() {
		classifica.removeUtente(new Giocatore("Giocatore9") {});
		assertNotEquals(list.toArray(), classifica.getClassifica().toArray());;
	}

	@Test
	void testRemoveUtenteInt() {
		classifica.removeUtente(5);
		list.remove(5);
		assertArrayEquals(list.toArray(), classifica.getClassifica().toArray());
	}

	@Test
	void testAggiornaGiocatore() {
		Giocatore g=new Giocatore("Giocatore1") {};
		g.setPunteggio(10000);
		classifica.aggiornaGiocatore(g);
		classifica.aggiornaClassifica();
		assertEquals(0, classifica.getPosizione("Giocatore1"));
	}

}
