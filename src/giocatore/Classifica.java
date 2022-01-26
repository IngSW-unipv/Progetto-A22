package giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Classifica {
	
	private List<Utente> lista;
	/** Permette di creare una classifica vuota*/
	public Classifica() {
		this.lista=new ArrayList<>();
	}
	/** Passando come parametro una List di Utenti ne fa la classifica per punteggio*/
	public Classifica(List<Utente> lista) {
		this.lista = new ArrayList<>();
		this.lista=lista;
		this.aggiornaClassifica();
	}
	/** Passando un Utente come parametro permette di aggiungerlo alla classifica. Una volta aggiunto la classifica si aggiorna.*/
	public void aggiungiUtente(Utente user) {
		this.lista.add(user);
		this.aggiornaClassifica();
	}
	/** Restituisce la posizione all'interno della classifica dell'Utente passato come parametro*/
	public int getPosizione(Utente user) {
		return this.lista.indexOf(user);
	}
	/** Restituisce la posizione all'interno della classifica dell'Utente il cui nome è stato passato come paramtro*/
	public int getPosizione(String user) {
		Utente prova=new Utente(user);
		return this.lista.indexOf(prova);
	}
	/** Aggiorna l'ordine della classifica*/
	public void aggiornaClassifica() {
		Collections.sort(lista);
	}
	/** restituisce la classifica*/
	public List<Utente> getClassifica() {
		return lista; 
	}
	/** Sostituisce la classifica salvata con quella della List di Utenti passata come parametro*/
	public void setClassifica(List<Utente> lista) {
		this.lista = lista;
	}
	/** Restituisce il numero di Utenti contenuti nella classifica*/
	public int getLunghezzaClassifica() {
		return lista.size();
	}
	/**Permette di rimuovere dalla classifica l'Utente passato come parametro*/
	public void removeUtente(Utente user) {
		lista.remove(user);
	}
	/**Permette di rimuovere dalla classifica l'Utente la cui posizione all'interno della classifica è stato passato come parametro*/
	public void removeUtente(int pos_user) {
		lista.remove(pos_user);
	}
	/** restituisce una stringa contenente la classifica*/
	@Override
	public String toString() {
		String s = "Classifica:\n";
        for (Utente i : lista) {
            if (i != null) {
                s += "Nome utente: "+i.getNome()+" Punteggio: "+i.getPunteggio()+"\n";
            } 
        }
        return s;
	}
	
	
}
