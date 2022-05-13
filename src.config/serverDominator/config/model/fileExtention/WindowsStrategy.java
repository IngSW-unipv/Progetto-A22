package serverDominator.config.model.fileExtention;

/**
 * @author TAWADROS
 *
 */
public class WindowsStrategy implements IFileExtensionStrategy {
	
	public WindowsStrategy(){
		super();
	}
	
	@Override
	public String getFileExtension() {
		return ".bat";
	}

}
