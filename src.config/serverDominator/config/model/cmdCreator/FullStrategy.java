package serverDominator.config.model.cmdCreator;

import java.nio.file.Paths;

/**
 * JRE completa 
 * @author TawaHabib
 * @version 1.0
 */
public class FullStrategy implements ICmdCreatorStrategy {
	private String jarPath;
	/*public FullStrategy(String jarPath) {
		this.jarPath=jarPath;
	}*/
	
	public FullStrategy(String fxpath,String jarPath) {
		this.jarPath=jarPath;
	}
	
	@Override
	public String createCmd() {
		 String result="java -jar "+Paths.get(jarPath).toString()+".jar";
		 return result;
	}

	public String getJarPath() {
		return jarPath;
	}

	public void setJarPath(String jarPath) {
		this.jarPath = jarPath;
	}

}
