package serverDominator.config.model.scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import serverDominator.config.model.fileExtention.FileExtentionFactory;

public class ScriptCreator {
	
	public static boolean createScript(String cmd, String fileName) {
		boolean result=true;
		
	    BufferedWriter writer=null;
		try {
			File file=new File(fileName+FileExtentionFactory.getIFileExtensionStrategy().getFileExtension() );
			if(file.exists()) {
				file.delete();
			}
			fileName.replaceAll(".", "");
			writer = new BufferedWriter(new FileWriter(fileName+FileExtentionFactory.getIFileExtensionStrategy().getFileExtension() ));
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
					File file=new File(fileName+FileExtentionFactory.getIFileExtensionStrategy().getFileExtension());
					file.setExecutable(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
