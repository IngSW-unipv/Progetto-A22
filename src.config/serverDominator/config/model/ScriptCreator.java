package serverDominator.config.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

/**
 * <h5>Classe con utililità per la creazione ed esecuzione di uno script per lanciare un jar con java Fx</h5>
 * @author TawaHabib
 * @version 1.0
 */
public class ScriptCreator {
	                                                                                                                                             
	/**
	 * Nome dello script di default 
	 */
	private static String SCRIPT_NAME="ServerDominatorScript";
	
	/**
	 * Nome di default del jar da eseguire 
	 */
	private static String JAR_NAME="./sdMap";

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
		 
		 String result="java --module-path "+
				 "\""+Paths.get(pathToFxLib).toString()+"\""+
				 " --add-modules javafx.controls,javafx.base,javafx.graphics -jar "+
				 Paths.get(pathToJarApp).toString()+".jar";
		 //System.out.println(result);
		 return result;
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
		boolean result=true;
		
	    BufferedWriter writer=null;
		try {
			File file=new File(fileName+getScriptFileExtension() );
			if(file.exists()) {
				file.delete();
			}
			fileName.replaceAll(".", "");
			writer = new BufferedWriter(new FileWriter(fileName+getScriptFileExtension()));
			String cmd1=new String(cmd.getBytes(Charset.forName("utf-8")));
			//System.out.println(cmd1.toString());
			writer.write(cmd1);
		} catch (IOException e) {
			result=false;
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try {
					writer.flush();
					writer.close();
					File file=new File(fileName+getScriptFileExtension() );
					file.setExecutable(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * Esegue lo script di default
	 * @throws IOException
	 */
	public static void runShellScript() throws IOException {
		runShellScript(SCRIPT_NAME+getScriptFileExtension());
	}
	
	/**
	 * Esegue uno script 
	 * @param ScriptPath 
	 * percorso dello script
	 * @throws IOException
	 */
	public static void runShellScript(String ScriptPath) throws IOException {
		String[] cmd=new String[]{ScriptPath};
		ProcessBuilder process=new ProcessBuilder(cmd);
		@SuppressWarnings("unused")
		Process p=process.start();
	}
	
	/**
	 * Metodo per conosecere l'estensione dello script 
	 * in base al sistema operativo;
	 * </br>.bat --> win</br>.sh --> unix
	 * @return
	 * Estensione dello script 
	 */
	public static String getScriptFileExtension() {
		String result="";
		String os=System.getProperty("os.name");
		if(os.startsWith("Windo")||os.startsWith("windo")) {
			result=".bat";
		}
		if(os.contains("MAC")||os.contains("mac")||os.contains("Linu")||os.contains("linu")) {
			result=".sh";
		}
		return result;
	}
	
	//@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		//ScriptCreator m = new ScriptCreator();
		File fls=new File("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
		if(fls.exists())
			ScriptCreator.createScript("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib");
	/*synchronized (m) {
		      m.wait(300);
		    }*/
		ScriptCreator.runShellScript();
		//System.out.println(Paths.get("C:/Users/TAWADROS/eclipse-workspace/provaJar/lib/java fx/win/lib").toString());
	}
}
