package serverDominator.config.viw.panel;


import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Sceglitore di lingue
 * @author TawaHabib
 * @version 1.0
 */
public class InitLinguaChooser extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final String InitLinguaChooser_lableTtitolo_KEY_IN_FILE="InitLinguaChooser.lable.Titolo";
	private static final String InitLinguaChooser_buttonAvanti_KEY_IN_FILE="InitLinguaChooser.buttone.avanti";
	private static final String InitLinguaChooser_buttonIndietro_KEY_IN_FILE="InitLinguaChooser.buttone.indietro";
	private static final String InitLinguaChooser_linguaChooserTitolo_KEY_IN_FILE="InitLinguaChooser.listaLingue.Titolo";
	
	private JLabel lableTtitolo;
	private JSeparator delimiter;
	
	private JPanel intraPanel;
		private JScrollPane scroll;
			private JList<String> listaLingue;
			
	private JButton butonAvanti;
	private JButton buttonIndietro;
	
	/**
	 * Create the application.
	 * @wbp.parser.constructor
	 */
	public InitLinguaChooser(ArrayList<String> c) {
		super();
		this.init(c, ILanguageManager.getCurrentLanguage());
	}
	
	public InitLinguaChooser(ArrayList<String> c, String lingua) {
		super();
		this.init(c, lingua);
	}
	
	private void init(ArrayList<String> c, String Lingua) {
		setLayout(null);
		Properties poropLang=new Properties();
		try {
		//	System.out.println(FilesLanguageManager.getLanguageFilePath(Lingua));
			poropLang=PropertiesFile.loadPropertiesFromFile(FilesLanguageManager.getLanguageFilePath(Lingua));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.lableTtitolo = DefaultComponentFactory.getInstance().createTitle(new String(poropLang.getProperty(InitLinguaChooser_lableTtitolo_KEY_IN_FILE, 
				"BENVENUTO IN SERVER DOMINATR").getBytes(),StandardCharsets.UTF_8));
		this.lableTtitolo.setFont(new Font("italic", Font.BOLD, 13));
		this.lableTtitolo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lableTtitolo.setBounds(10, 11, 755, 14);
		add(this.lableTtitolo);
		
		this.delimiter = new JSeparator();
		this.delimiter.setBounds(36, 36, 700, 14);
		add(this.delimiter);
		
		this.intraPanel = new JPanel();
		this.intraPanel.setBounds(36, 70, 700, 158);
		add(this.intraPanel);
		this.intraPanel.setLayout(null);
		
		this.scroll = new JScrollPane();
		this.scroll.setBounds(0, 0, 700, 161);
		this.intraPanel.add(this.scroll);
		
		this.listaLingue = new JList<String>();
		this.listaLingue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.listaLingue.setBorder(new TitledBorder(null, poropLang.getProperty(InitLinguaChooser_linguaChooserTitolo_KEY_IN_FILE,
				"Scegli la lingua"), TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		this.listaLingue.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.listaLingue.setFont(new Font("Arial", this.listaLingue.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, this.listaLingue.getFont().getSize()));
		this.listaLingue.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listaLingue.setSelectedIndex(0);
		DefaultListModel<String> elements=new DefaultListModel<String>();
		if(!c.isEmpty()) {
			for(String s:c)	{
				try {
					elements.addElement(new String(s.getBytes(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		this.listaLingue.setModel(elements);
		this.scroll.setViewportView(this.listaLingue);
		
		this.butonAvanti = new JButton(poropLang.getProperty(InitLinguaChooser_buttonAvanti_KEY_IN_FILE,"Avnti"));
		this.butonAvanti.setBounds(597, 255, 89, 23);
		add(this.butonAvanti);
		
		this.buttonIndietro = new JButton(poropLang.getProperty(InitLinguaChooser_buttonIndietro_KEY_IN_FILE,"Indietro"));
		this.buttonIndietro.setBounds(72, 255, 89, 23);
		add(this.buttonIndietro);
	}
	public JLabel getTitolo() {
		return lableTtitolo;
	}
	public void setTitolo(JLabel titolo) {
		this.lableTtitolo = titolo;
	}
	public JSeparator getDelimiter() {
		return delimiter;
	}
	public void setDelimiter(JSeparator delimiter) {
		this.delimiter = delimiter;
	}
	public JPanel getIntraPanel() {
		return intraPanel;
	}
	public void setIntraPanel(JPanel intraPanel) {
		this.intraPanel = intraPanel;
	}
	public JScrollPane getScroll() {
		return scroll;
	}
	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}
	public JList<String> getListaLingue() {
		return listaLingue;
	}
	public void setListaLingue(JList<String> listaLingue) {
		this.listaLingue = listaLingue;
	}
	public JButton getAvantiBott() {
		return butonAvanti;
	}
	public void setAvantiBott(JButton avantiBott) {
		this.butonAvanti = avantiBott;
	}
	public JButton getIndietroBott() {
		return buttonIndietro;
	}
	public void setIndietroBott(JButton indietroBott) {
		this.buttonIndietro = indietroBott;
	}

}
