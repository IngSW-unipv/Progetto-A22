package serverDominator.config.model;

import java.io.File;
import java.io.IOException;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;
import serverDominator.config.model.cmdCreator.CmdCreatorFactory;
import serverDominator.config.model.fileExtention.FileExtentionFactory;
import serverDominator.config.model.scripts.ScriptCreator;
import serverDominator.config.model.scripts.ScriptRunner;

/**
 * <h5>Classe con utililità per la creazione ed esecuzione di uno script per lanciare un jar con java Fx</h5>
 * @author TawaHabib
 * @version 1.0
 */
public class ScriptsFacade {
	                                                                                                                                             
	/**
	 * Nome dello script di default 
	 */
	private  final String SCRIPT_NAME="scriptsFacade.scriptName.toCreaste";
	
	/**
	 * Nome di default del jar da eseguire 
	 */
	private final String JAR_NAME="scriptsFacade.jarName.toRun";

	/**
	 * Nome di default del jar da eseguire 
	 */
	private final String PROP_FILE="resources/config/persistence/persistenceFactoryConfig";

	private static ScriptsFacade istance=null;
	
	private ScriptsFacade() {
		super();
	}
	
	public static ScriptsFacade getIstance() {
		if(istance!=null)
			return istance;
		istance=new ScriptsFacade();
		return istance;
	}
	
	/**
	 * Crea uno script per con nome di default e con 
	 * dentro solo il comando per eseguire un jar il cui nome è quello di default
	 * @param fxLib
	 * Percorso alla cartella "lib" di javaFX
	 * @return
	 */
	public boolean createScript(String fxLib) {
			return createScript(createCMDToRunFxApp(fxLib,getJarName()),getScriptName());
		 
	 }
	 
	/**
	 * Metodo che crea il comando per l'esecuzione di un Jar con argomenti 
	 * alla VM di JavaFx
	 * @param pathToFxLib
	 * Il percorso alla cartella "lib" di JavaFX
	 * @param pathToJarApp
	 * Il jar da eseguire; NON deve contenere l'estinzione '.jar' 
	 * @return 
	 * Il comando neccessario per l'esecuzione
	 */
	public String createCMDToRunFxApp(String pathToFxLib,String pathToJarApp){
		 return CmdCreatorFactory.getCmdCreator(CmdCreatorFactory.CMD_NOT_FULL, pathToFxLib, pathToJarApp).createCmd();
	 }
	
	/**
	 * Metodo che crea il comando per l'esecuzione di un Jar senza argomenti 
	 * alla VM di JavaFx
	 * @param pathToJarApp
	 * Il jar da eseguire; NON deve contenere l'estinzione '.jar' 
	 * @return 
	 * Il comando neccessario per l'esecuzione
	 */
	public String createCMDToRunFxApp(String pathToJarApp){
		 return CmdCreatorFactory.getCmdCreator(CmdCreatorFactory.CMD_FULL, "", pathToJarApp).createCmd();
	 }
	
	/**
	 * Metodo necessario per la creazione di uno script shell
	 * @param cmd
	 * Stringa dei comandi da inserire nello script
	 * @param fileName
	 * Nome dello script shell (non deve a vere un'estensione in quando la crea lui)
	 * @return
	 * Vero se il file � stato creato </br>
	 * Flaso se c'� qualcosa che è andato storto
	 */
	public boolean createScript(String cmd, String fileName) {
		return ScriptCreator.createScript(cmd, fileName+FileExtentionFactory.getIFileExtensionStrategy().getFileExtension());
	}
	
	/**
	 * Esegue lo script di default
	 * @throws IOException
	 */
	public void runShellScript() throws IOException {
		ScriptRunner.runShellScript(getScriptName()+getScriptFileExtension());
	}
	
	/**
	 * Esegue uno script 
	 * @param ScriptPath 
	 * percorso dello script
	 * @throws IOException
	 */
	public void runShellScript(String ScriptPath) throws IOException {
		ScriptRunner.runShellScript(ScriptPath+FileExtentionFactory.getIFileExtensionStrategy().getFileExtension());
	}
	
	/**
	 * Metodo per conosecere l'estensione dello script 
	 * in base al sistema operativo;
	 * </br>.bat --> win</br>.sh --> unix
	 * @return
	 * Estensione dello script 
	 */
	public String getScriptFileExtension() {
		return FileExtentionFactory.getIFileExtensionStrategy().getFileExtension();
	}
	
	/**
	 * Metodo per conoscere il nome delo script da creare 
	 * o da runnare 
	 * @return 
	 * percorso dello script 
	 */
	public String getScriptName() {
		try {
			return	PropertiesFile.getPropertieFromFile(SCRIPT_NAME, PROP_FILE);
		} catch (Exception e) {
			return "serverDominator_DEFAOUL";
		}
	}
	
	/**
	 * Metodo per conoscere il nome del jar da runnare 
	 * @return
	 * percorso del jar da runnare 
	 */
	public String getJarName() {
		try {
			return	PropertiesFile.getPropertieFromFile(JAR_NAME, PROP_FILE);
		} catch (Exception e) {
			return "serverDominator_script_DEFAULT";
		}
	}
	
	//@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		//ScriptCreator m = new ScriptCreator();
		File fls=new File("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
		if(fls.exists())
			ScriptsFacade.getIstance().createScript("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
	/*synchronized (m) {
		      m.wait(300);
		    }*/
		ScriptsFacade.getIstance().runShellScript();
		//System.out.println(Paths.get("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib").toString());
	}
}
