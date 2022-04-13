package serverDominator.config.model.cmdCreator;

import java.nio.file.Paths;

public class NotFullStrategy implements ICmdCreatorStrategy {
	private String pathToFxLib;
	private String jarPath;
	
	public NotFullStrategy(String pathToFxLib,String jarPath) {
		this.pathToFxLib=pathToFxLib;
		this.jarPath=jarPath;
	}
	
	
	@Override
	public String createCmd() {
		 String result="java --module-path "+
				 "\""+Paths.get(pathToFxLib).toString()+"\""+
				 " --add-modules javafx.controls,javafx.base,javafx.graphics javafx.fxml -jar"+
				 Paths.get(jarPath).toString()+".jar";
		 //System.out.println(result);
		 return result;
	}


	public String getPathToFxLib() {
		return pathToFxLib;
	}


	public void setPathToFxLib(String pathToFxLib) {
		this.pathToFxLib = pathToFxLib;
	}


	public String getJarPath() {
		return jarPath;
	}


	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

}
