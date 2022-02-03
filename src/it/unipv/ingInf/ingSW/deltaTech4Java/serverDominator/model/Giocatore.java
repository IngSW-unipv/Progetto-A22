package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model;

public abstract class Giocatore {
	private int valuta_temp;
	private String nome;

	public Giocatore(String nome) {
		this.nome=nome;
		valuta_temp=0;
	}
	/**Restituisce il valore della valuta temporanea*/
	public int getValuta_temp() {
		return valuta_temp;
	}
	/**Aggiorna la valuta temporanea con il valore passato come parrametro*/
	public void setValuta_temp(int valuta_temp) {
		this.valuta_temp = valuta_temp;
	}
	/**Restituisce il nome del Giocatore*/
	public String getNome() {
		return nome;
	}
	/**Aggiorna il nome con la stringa passata come parrametro*/
	public void setNome(String nome) {
		this.nome = nome;
	}
}