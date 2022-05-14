package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Quando una battaglia si conclude viene riportato l'esito in TextBox
 * 
 * @author para
 *
 */

public class TextBox {
	
	Base bU = new Base();
	ScrollPane sp = new ScrollPane();
	VBox text = new VBox();
	ArrayList<Log> logs = new ArrayList<Log>();
	
	public TextBox(Base bU, ScrollPane sp) {
		
		this.bU = bU;
		this.sp = sp;
		sp.setContent(text);
				
	}
	
	
	
	public void insertText(String text) {
		
		logs.add(0, new Log(text));  //il nuovo testo è inserito nella posizione 0, quindi è sempre il primo in alto
		this.text.getChildren().clear();  //pulizia di sicurezza, non è necessaria
		for (Log l : logs) {
			Label inserted = new Label();
			inserted.setPadding(new Insets(10,10,10,10));
			inserted.setText(l.displayText());
			this.text.getChildren().add(inserted);
			this.text.getChildren().add(new Separator());
		}
	}
	
	public void generateFile() {
		String desktop = System.getProperty("user.home");
		File f = new File("C:\\Users\\para\\Desktop" , "SDlog.txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			for (Log l : logs) {
				writer.write(l.displayText() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class Log {
		public String text;
		public Date logDate = new Date();
		
		public Log(String text) {
			this.text = text;
			
		}
		
		public String displayText() {
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return format.format(logDate) + " " + text;
		}
	}

}
