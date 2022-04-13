package serverDominator.config.model.cmdCreator;

/**
 * Interfaccia per la costruzione di un comando 
 * @author TawaHabib
 *
 */
public interface ICmdCreatorStrategy {
	
	/**
	 * Metodo che costruisce una comando 
	 * @return Comando 
	 */
	public String createCmd();
	
}
