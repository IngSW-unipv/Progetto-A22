package serverDominator.config.model.fileExtention;

public class UnixStrategy implements IFileExtensionStrategy {
	
	public UnixStrategy() {
		super();
	}
	
	@Override
	public String getFileExtension() {
		return ".sh";
	}

}
