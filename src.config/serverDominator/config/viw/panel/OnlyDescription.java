package serverDominator.config.viw.panel;

import java.awt.Label;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;

public class OnlyDescription extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String KEY_LABLE="OnlyDescription.lable.descrizione";
	private Label label ;
	/**
	 * Create the panel.
	 */
	public OnlyDescription() {
		super();
		this.init();

	}
	public void init() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		Properties p=FilesLanguageManager.getPropertiesLanguage(ILanguageManager.getCurrentLanguage());
		
		label = new Label(p.getProperty(KEY_LABLE,"Gioco configurato con sucesso"));
		springLayout.putConstraint(SpringLayout.NORTH, label, 122, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, label, 740, SpringLayout.WEST, this);
		label.setAlignment(Label.CENTER);
		add(label);
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	
}

