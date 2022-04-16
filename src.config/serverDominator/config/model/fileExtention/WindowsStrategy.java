package serverDominator.config.model.fileExtention;

public class WindowsStrategy implements IFileExtensionStrategy {
	
	public WindowsStrategy(){
		super();
	}
	
	@Override
	public String getFileExtension() {
		return ".bat";
	}

}
