package serverDominator.config.model.fileExtention;

public class WindowsStrategy implements IFileExtensionStrategy {
	
	public WindowsStrategy(){
		//super();
	}
	
	public WindowsStrategy(String s){
		super();
	}
	@Override
	public String getFileExtension() {
		return ".bat";
	}

}
