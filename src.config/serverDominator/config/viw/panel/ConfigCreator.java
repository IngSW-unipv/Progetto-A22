package serverDominator.config.viw.panel;


import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.util.Properties;

import javax.swing.ButtonGroup;

/**
 * Panello per creazione del DataBase e per aquisire il percorso di una cartella
 * @author TawaHabib
 * @version 1.0
 */
public class ConfigCreator extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CFG_CREATOR_buttonIndietro_KEY_IN_FILE="config.creator.bottone.indietro";
	public static final String CFG_CREATOR_buttonAvanti_KEY_IN_FILE="config.creator.bottone.avanti";
	public static final String CFG_CREATOR_buttonNavigateInFileSys_KEY_IN_FILE="config.creator.bottone.naviga";
	
	public static final String CFG_CREATOR_lableTipoJRE_KEY_IN_FILE="config.creator.lable.scegliereTipologiaDiJre";
	public static final String CFG_CREATOR_lablePathToJavaFXLib_KEY_IN_FILE="config.creator.lable.pathToJavaFxLib";
	public static final String CFG_CREATOR_lableUrlToDataBase_KEY_IN_FILE="config.creator.lable.dataBaseUrl";
	public static final String CFG_CREATOR_lableUserName_KEY_IN_FILE="config.creator.lable.username";
	public static final String CFG_CREATOR_lablePassword_KEY_IN_FILE="config.creator.lable.password";
	public static final String CFG_CREATOR_lableTitle_KEY_IN_FILE="config.creator.lable.titoloPanel";

	public static final String CFG_CREATOR_radioButtonjreFull_KEY_IN_FILE="config.creator.JRadioButton.jreFull";
	public static final String CFG_CREATOR_radioButtonJavaFx_KEY_IN_FILE="config.creator.JRadioButton.jreAndFX";
	
	private JTextField textUrlToDataBase;
	private JTextField textUserName;
	private JTextField textToJavaFxLibPath;
	
	private JPasswordField textPassword;
	
	private JSeparator separator;
	
	private JButton buttonIndietro;
	private JButton buttonAvanti;
	private JButton buttonNavigateInFileSys ;
	
	private JLabel lableTipoJRE;
	private JLabel lablePathToJavaFXLib ;
	private JLabel lableUrlToDataBase;
	private JLabel lableUserName;
	private JLabel lablePassword;
	private JLabel lableTitle;
	private ButtonGroup buttonGroup;
	private JRadioButton radioButtonjreFull;
	private JRadioButton radioButtonJavaFx;
	
	public ConfigCreator() {
		super();
		this.init(FilesLanguageManager.getCurrentLanguage());
	}
	/**
	 * Create the panel.
	 */
	public ConfigCreator(String lingua) {
		super();
		this.init(lingua);
	}
	public void init(String lingua) {
		Properties propLang=new Properties();
		 try {
			propLang=PropertiesFile.loadPropertiesFromFile(FilesLanguageManager.getLanguageFilePath(lingua));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.buttonGroup = new ButtonGroup();
		
		this.textUrlToDataBase = new JTextField();
		this.textUrlToDataBase.setColumns(10);
		
		this.textUserName = new JTextField();
		this.textUserName.setColumns(10);
		
		this.textPassword = new JPasswordField();
		
		this.textToJavaFxLibPath = new JTextField();
		this.textToJavaFxLibPath.setColumns(10);
		
		this.buttonNavigateInFileSys = new JButton(propLang.getProperty(CFG_CREATOR_buttonNavigateInFileSys_KEY_IN_FILE, "Naviga"));
		this.lableTitle = DefaultComponentFactory.getInstance().createTitle(propLang.getProperty(CFG_CREATOR_lableTitle_KEY_IN_FILE,
				"BENVENUTO IN SERVER DOMINATOR"));
		this.lableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.separator = new JSeparator();
		this.separator.setToolTipText("");
		
		this.lableTipoJRE = DefaultComponentFactory.getInstance().createLabel(propLang.getProperty(CFG_CREATOR_lableTipoJRE_KEY_IN_FILE,
				"Scegli la tipologia della JRE utilizzata"));
		this.lableTipoJRE.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.lableTipoJRE.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.lableTipoJRE.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.lablePathToJavaFXLib = DefaultComponentFactory.getInstance().createLabel(propLang.getProperty(CFG_CREATOR_lablePathToJavaFXLib_KEY_IN_FILE,
				"Percorso alla cartella \"lib\" di JavaFX"));
		this.lablePathToJavaFXLib.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.lablePathToJavaFXLib.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.lableUrlToDataBase = DefaultComponentFactory.getInstance().createLabel(propLang.getProperty(CFG_CREATOR_lableUrlToDataBase_KEY_IN_FILE ,
				"URL al dataBase"));
		this.lableUrlToDataBase.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.lableUrlToDataBase.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.lableUserName = DefaultComponentFactory.getInstance().createLabel(propLang.getProperty(CFG_CREATOR_lableUserName_KEY_IN_FILE,
				"Nome Utente"));
		this.lableUserName.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.lableUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.lablePassword = DefaultComponentFactory.getInstance().createLabel(propLang.getProperty(CFG_CREATOR_lablePassword_KEY_IN_FILE,
				"Password"));
		this.lablePassword.setHorizontalAlignment(SwingConstants.RIGHT);
		
		this.buttonIndietro = new JButton(propLang.getProperty(CFG_CREATOR_buttonIndietro_KEY_IN_FILE,"Indietro"));
		
		this.buttonAvanti = new JButton(propLang.getProperty(CFG_CREATOR_buttonAvanti_KEY_IN_FILE,"Avanti"));
		
		this.radioButtonjreFull = new JRadioButton(propLang.getProperty(CFG_CREATOR_radioButtonjreFull_KEY_IN_FILE,"JRE completa"));
		
		this.radioButtonJavaFx = new JRadioButton(propLang.getProperty(CFG_CREATOR_radioButtonJavaFx_KEY_IN_FILE,"JRE 11+ con JavaFx"));
		
		this.buttonGroup.add(this.radioButtonjreFull);
		this.buttonGroup.add(this.radioButtonJavaFx);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lableTitle, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lablePassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lableUserName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lableUrlToDataBase, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lableTipoJRE, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
											.addComponent(buttonIndietro))
										.addComponent(lablePathToJavaFXLib, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textUrlToDataBase, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
												.addComponent(textUserName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
												.addComponent(textPassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(radioButtonjreFull)
													.addPreferredGap(ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
													.addComponent(radioButtonJavaFx))
												.addComponent(textToJavaFxLibPath, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(buttonNavigateInFileSys)
											.addGap(22))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(buttonAvanti)
											.addGap(77)))))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 741, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lableTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lableTipoJRE)
						.addComponent(radioButtonjreFull)
						.addComponent(radioButtonJavaFx))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textToJavaFxLibPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonNavigateInFileSys)
						.addComponent(lablePathToJavaFXLib))
					.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textUrlToDataBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lableUrlToDataBase))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lableUserName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lablePassword))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonIndietro)
						.addComponent(buttonAvanti))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	public JTextField getTextUrlToDataBase() {
		return textUrlToDataBase;
	}

	public void setTextUrlToDataBase(JTextField textUrlToDataBase) {
		this.textUrlToDataBase = textUrlToDataBase;
	}

	public JTextField getTextUserName() {
		return textUserName;
	}

	public void setTextUserName(JTextField textUserName) {
		this.textUserName = textUserName;
	}

	public JTextField getTextToJavaFxLibPath() {
		return textToJavaFxLibPath;
	}

	public void setTextToJavaFxLibPath(JTextField textToJavaFxLibPath) {
		this.textToJavaFxLibPath = textToJavaFxLibPath;
	}

	public JPasswordField getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(JPasswordField textPassword) {
		this.textPassword = textPassword;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JButton getButtonIndietro() {
		return buttonIndietro;
	}

	public void setButtonIndietro(JButton buttonIndietro) {
		this.buttonIndietro = buttonIndietro;
	}

	public JButton getButtonAvanti() {
		return buttonAvanti;
	}

	public void setButtonAvanti(JButton buttonAvanti) {
		this.buttonAvanti = buttonAvanti;
	}

	public JButton getButtonNavigateInFileSys() {
		return buttonNavigateInFileSys;
	}

	public void setButtonNavigateInFileSys(JButton buttonNavigateInFileSys) {
		this.buttonNavigateInFileSys = buttonNavigateInFileSys;
	}

	public JLabel getLableTipoJRE() {
		return lableTipoJRE;
	}

	public void setLableTipoJRE(JLabel lableTipoJRE) {
		this.lableTipoJRE = lableTipoJRE;
	}

	public JLabel getLablePathToJavaFXLib() {
		return lablePathToJavaFXLib;
	}

	public void setLablePathToJavaFXLib(JLabel lablePathToJavaFXLib) {
		this.lablePathToJavaFXLib = lablePathToJavaFXLib;
	}

	/**
	 * @return
	 */
	public JLabel getLableUrlToDataBase() {
		return lableUrlToDataBase;
	}

	public void setLableUrlToDataBase(JLabel lableUrlToDataBase) {
		this.lableUrlToDataBase = lableUrlToDataBase;
	}

	public JLabel getLableUserName() {
		return lableUserName;
	}

	public void setLableUserName(JLabel lableUserName) {
		this.lableUserName = lableUserName;
	}

	public JLabel getLablePassword() {
		return lablePassword;
	}

	public void setLablePassword(JLabel lablePassword) {
		this.lablePassword = lablePassword;
	}

	public JLabel getLableTitle() {
		return lableTitle;
	}

	public void setLableTitle(JLabel lableTitle) {
		this.lableTitle = lableTitle;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	public JRadioButton getRadioButtonjreFull() {
		return radioButtonjreFull;
	}

	public void setRadioButtonjreFull(JRadioButton radioButtonjreFull) {
		this.radioButtonjreFull = radioButtonjreFull;
	}

	public JRadioButton getRadioButtonJavaFx() {
		return radioButtonJavaFx;
	}

	public void setRadioButtonJavaFx(JRadioButton radioButtonJavaFx) {
		this.radioButtonJavaFx = radioButtonJavaFx;
	}

}
