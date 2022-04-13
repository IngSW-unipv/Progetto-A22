package serverDominator.config.model.fileExtention;

public class UnixStrategy implements IFileExtensionStrategy {
	
	public UnixStrategy() {
		super();
	}
	public UnixStrategy(String s) {
		super();
	}
	@Override
	public String getFileExtension() {
		return ".sh";
	}

}
