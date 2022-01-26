package giocatore;

public class Bot extends Giocatore{
	
	/**Permette di creare un oggetto Bot passando come parametro il suo nome*/
	public Bot(String nome) {
		super(nome);
	}
	/** Permette di creare un oggetto Bot il cui nome sarà "botX" dove X è il numero passato come parametro*/
	public Bot(int num) {

		super("bot"+String.format("%03d",num));
	}
	/** in fase di sviluppo*/
	public void logicaAttacco() {
	}


}
