package serverDominator.config.viw.panel;

import javax.swing.JPanel;

import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panello per esecuzione
 * @author TawaHabib
 * @version 1.0
 */
public class RunPanel extends JPanel {

	private static final long serialVersionUID = 6071859716468192110L;
	
	public static String KEY_titoloLabel="RunPanel.lable.titolo";
	public static String KEY_runButton="RunPanel.button.run";

	private JLabel titoloLabel;
	private JButton runButton; 

	/**
	 * Create the panel.
	 */
	public RunPanel() {
		super();
		this.init();
	}
	
	private void init() {
		Properties prop=FilesLanguageManager.getPropertiesLanguage(FilesLanguageManager.getCurrentLanguage());
		titoloLabel = DefaultComponentFactory.getInstance().createTitle(prop.getProperty(KEY_titoloLabel, "Inizia a giocare"));
		titoloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		runButton = new JButton(prop.getProperty(KEY_runButton,"Esegui il gioco"));
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titoloLabel, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
						.addComponent(runButton, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(titoloLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(68)
					.addComponent(runButton)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public JLabel getTitoloLabel() {
		return titoloLabel;
	}

	public void setTitoloLabel(JLabel titoloLabel) {
		this.titoloLabel = titoloLabel;
	}

	public JButton getRunButton() {
		return runButton;
	}

	public void setRunButton(JButton runButton) {
		this.runButton = runButton;
	}
	
}
