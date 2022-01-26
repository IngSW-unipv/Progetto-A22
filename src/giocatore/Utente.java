package giocatore;

public class Utente extends Giocatore implements Comparable<Utente>{

	int punteggio;
	/**Permette di creare un oggetto di tipo Utente il cui nome sarà quello passato come parametro*/
	public Utente(String nome) {
		super(nome);
		this.punteggio=0;
	}
	/**Restituisce il punteggio dell'Utente*/
	public int getPunteggio() {
		return punteggio;
	}
	/**Aggiorna il punteggio dell'Utente con il valore passato come parametro*/
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	/**Aggiunge al punteggio dell'Utente il valore passato come parametro*/
	public void aggiornaPunteggio(int punteggio) {
		this.punteggio+=punteggio;
	}
	/**Restituisce la differenza di punteggio tra l'Utente e l'Utente passato come parametro*/
	public int compareTo(Utente obj) {
		return this.punteggio-obj.getPunteggio();
	}

}
