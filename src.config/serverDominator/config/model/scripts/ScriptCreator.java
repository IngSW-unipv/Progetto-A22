package serverDominator.config.model.scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class ScriptCreator {
	
	/**
	 * Metodo per la creazione i uno script
	 * @param cmd
	 * Comandi da inserire nello script 
	 * @param fileName
	 * Nome dello script
	 * @return
	 * Vero se la creazione è andata a buon fine 
	 * </br> falso altriomenti
	 */
	public static boolean createScript(String cmd, String fileName) {
		boolean result=true;
		
	    BufferedWriter writer=null;
		try {
			File file=new File(fileName);
			if(file.exists()) {
				file.delete();
			}
			fileName.replaceAll(".", "");
			writer = new BufferedWriter(new FileWriter(fileName));
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
					File file=new File(fileName);
					file.setExecutable(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
