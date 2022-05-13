package serverDominator.config.model.fileExtention;

/**
 * stategia per unix
 * @author TawaHabib
 * @version 1.0
 */
public class UnixStrategy implements IFileExtensionStrategy {
	
	public UnixStrategy() {
		super();
	}
	
	@Override
	public String getFileExtension() {
		return ".sh";
	}

}
