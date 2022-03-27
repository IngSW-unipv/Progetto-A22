package serverDominator.config.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * classe con utililità per la creazione ed esecuzione di uno script per lanciare un jar con java Fx
 * @author TawaHabib
 */
public class ScriptCreator {
	private static String COMAND_EXECUTE_JAR="java --module-path replace1 --add-modules javafx.controls,javafx.base,javafx.graphics -jar replace2.jar";
	private static String SCRIPT_NAME="ServerDominatorScript";
	static String JAR_NAME="ServerDominator";
	
	 public static boolean createScript(String fxLib) {
		 return createScript(fxLib,JAR_NAME,SCRIPT_NAME);
	 }
	 
	 
	 public static boolean createScript(String fxLib,String jarName) {
		 return createScript(fxLib,jarName,SCRIPT_NAME);
	 }
	 
	 
	public static boolean createScript(String fxLib,String jarName, String fileName) {
		boolean result=true;
		String cmd=new String(COMAND_EXECUTE_JAR);
		String cmd1=cmd.toString().replaceAll("replace1".toString(), fxLib);
		cmd1=cmd1.toString().replaceAll("replace2".toString(), jarName);
	    BufferedWriter writer=null;
		try {
			File file=new File(fileName+getScriptFileExtension() );
			if(file.exists()) {
				file.delete();
			}
			fileName.replaceAll(".", "");
			writer = new BufferedWriter(new FileWriter(fileName+getScriptFileExtension() ));
			writer.write(cmd1);
		} catch (IOException e) {
			result=false;
			e.printStackTrace();
		}finally {
			if(writer!=null) {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static void runShellScript() throws IOException {
		runShellScript(SCRIPT_NAME);
	}
	
	public static void runShellScript(String ScriptPath) throws IOException {
		String[] cmd=new String[]{ScriptPath};
		ProcessBuilder process=new ProcessBuilder(cmd);
		@SuppressWarnings("unused")
		Process p=process.start();
	}
	
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
	
	public static void main(String[] args) throws IOException {
		createScript( "fxLib", "jarNam",  "fileName");

	}
}
