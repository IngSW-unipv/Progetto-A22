package serverDominator.config.model.scripts;

import java.io.IOException;

public class ScriptRunner {
	
	public static void runShellScript(String ScriptPath) throws IOException {
		String[] cmd=new String[]{ScriptPath};
		ProcessBuilder process=new ProcessBuilder(cmd);
		@SuppressWarnings("unused")
		Process p=process.start();
	}
}
