package serverDominator.config.model.scripts;

import java.io.IOException;
/**
 * classe responsabile del lancio di uno script shell
 * @author TawaHabib
 * @version 1.0
 */
public class ScriptRunner {
	
	/**
	 * Metodo per l'esecuzione di uno script
	 * @param ScriptPath
	 * percordo dello sript
	 * @throws IOException
	 */
	public static void runShellScript(String ScriptPath) throws IOException {
		String[] cmd=new String[]{ScriptPath};
		ProcessBuilder process=new ProcessBuilder(cmd);
		@SuppressWarnings("unused")
		Process p=process.start();
	}
}
