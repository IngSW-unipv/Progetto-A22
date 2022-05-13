package serverDominator.config.model.fileExtention;

public interface IFileExtensionStrategy {
	/**
	 * metodo per recuperare l'estensione  di uno script shell
	 * @return
	 * estensione del file 
	 */
	public String getFileExtension();
}
