package serverDominator.config.model;

import java.io.File;
import java.io.IOException;

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
	public static String SCRIPT_NAME="ServerDominatorScript";
	
	/**
	 * Nome di default del jar da eseguire 
	 */
	public static String JAR_NAME="./sdMap";

	/**
	 * Crea uno script per con nome di default e con 
	 * dentro solo il comando per eseguire un jar il cui nome è quello di default
	 * @param fxLib
	 * Percorso alla cartella "lib" di javaFX
	 * @return
	 */
	public static boolean createScript(String fxLib) {
		 return createScript(createCMDToRunFxApp(fxLib,JAR_NAME),SCRIPT_NAME);
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
	public static String createCMDToRunFxApp(String pathToFxLib,String pathToJarApp){
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
	public static String createCMDToRunFxApp(String pathToJarApp){
		 return CmdCreatorFactory.getCmdCreator(CmdCreatorFactory.CMD_FULL, "", pathToJarApp).createCmd();
	 }
	
	/**
	 * Metodo necessario per la creazione di uno script shell
	 * @param cmd
	 * Stringa dei comandi da inserire nello script
	 * @param fileName
	 * Nome dello script shell (non deve a vere un'estensione in quando la crea lui)
	 * @return
	 * Vero se il file è stato creato </br>
	 * Flaso se c'è qualcosa che è andato storto
	 */
	public static boolean createScript(String cmd, String fileName) {
		return ScriptCreator.createScript(cmd, fileName);
	}
	
	/**
	 * Esegue lo script di default
	 * @throws IOException
	 */
	public static void runShellScript() throws IOException {
		ScriptRunner.runShellScript(SCRIPT_NAME+getScriptFileExtension());
	}
	
	/**
	 * Esegue uno script 
	 * @param ScriptPath 
	 * percorso dello script
	 * @throws IOException
	 */
	public static void runShellScript(String ScriptPath) throws IOException {
		ScriptRunner.runShellScript(ScriptPath);
	}
	
	/**
	 * Metodo per conosecere l'estensione dello script 
	 * in base al sistema operativo;
	 * </br>.bat --> win</br>.sh --> unix
	 * @return
	 * Estensione dello script 
	 */
	public static String getScriptFileExtension() {
		return FileExtentionFactory.getIFileExtensionStrategy().getFileExtension();
	}
	
	//@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		//ScriptCreator m = new ScriptCreator();
		File fls=new File("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
		if(fls.exists())
			ScriptsFacade.createScript("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
	/*synchronized (m) {
		      m.wait(300);
		    }*/
		ScriptsFacade.runShellScript();
		//System.out.println(Paths.get("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib").toString());
	}
}
