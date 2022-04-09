package serverDominator.config.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;
import serverDominator.config.model.ScriptCreator;
import serverDominator.config.viw.ConfigFrame;

/**
 * Controller per la configuarazione del progetto
 * @author TawaHabib
 * @version 1.0
 */
public class Controller {
	public static final String LinguaNonSelezionata="Controller.err.LinguaNonSelezionata";
	public static final String NoDaaBaseCreate="Controller.err.dataBaseCreator";
	public static final String NoRunFileCreate="Controller.err.RunnerFileCreate";
	
	private ConfigFrame cfgFrame;
	
	public Controller(ConfigFrame cfgFrame) {
		this.cfgFrame = cfgFrame;
		initListeners();
		
	}
	
	private void initListeners() {
		this.initListenersPanelBenvenuto();
		this.initListenersPanelConfig();
		this.initListenersRunPanel();
		
	}
	
	private void initListenersPanelBenvenuto() {
		cfgFrame.getPanelBenvenuto().getIndietroBott().setEnabled(false);
		cfgFrame.getPanelBenvenuto().getListaLingue().setSelectedValue(FilesLanguageManager.getCurrentLanguage(), true);
		//String selectedItem = cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue();
		cfgFrame.getPanelBenvenuto().getAvantiBott().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue()!=null
						&&!cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue().isEmpty()) {
					
					FilesLanguageManager.setCurrentLanguege(
							cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue());
					cfgFrame.loadSecondPanel();
				}else {
					Properties p=new Properties();
					try {
						p=PropertiesFile.loadPropertiesFromFile(
								FilesLanguageManager.getLanguageFilePath(FilesLanguageManager.getCurrentLanguage())
								);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					JOptionPane.showMessageDialog(cfgFrame, p.getProperty(LinguaNonSelezionata, "Non è Stata Selezionata Una Lingua"));
				}				
			}
		});
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		           if(cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue() != null && 
		        		   !cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue().isEmpty())
		           {
		        	   	FilesLanguageManager.setCurrentLanguege(cfgFrame.getPanelBenvenuto().getListaLingue().getSelectedValue());
		        	   	cfgFrame.firstPanelCreator();
		        	   	cfgFrame.secondPanelCreator();
		        	   	cfgFrame.runPanelCreator();
		        	   	cfgFrame.onlyDescriptionPanelCreator();
		        	   	cfgFrame.loadFirstPanel();
		        	   	initListeners();
		        	   	cfgFrame.getPanelBenvenuto().getListaLingue().setSelectedValue(FilesLanguageManager.getCurrentLanguage(), true);
		           }
		         }
		    }
		};
		cfgFrame.getPanelBenvenuto().getListaLingue().addMouseListener(mouseListener);
	}
	
	private void initListenersPanelConfig() {
		cfgFrame.getPanelConfig().getRadioButtonJavaFx().setSelected(true);
		cfgFrame.getPanelConfig().getTextUrlToDataBase().setText("jdbc:mysql://<ip>:<port>");
		
		cfgFrame.getPanelConfig().getButtonIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cfgFrame.restartFirstPanel();;			
				}
			});
		
		cfgFrame.getPanelConfig().getButtonIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cfgFrame.restartFirstPanel();;			
				}
			});
		
		cfgFrame.getPanelConfig().getButtonNavigateInFileSys().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(1, 0, 500, 308);
				fileChooser.setBackground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setFileHidingEnabled(true);
				fileChooser.showOpenDialog(new JFrame());
				File dir = fileChooser.getSelectedFile();
				cfgFrame.getPanelConfig().getTextToJavaFxLibPath().setText(dir.getAbsolutePath().toString());
			}
			
		});
		
		cfgFrame.getPanelConfig().getRadioButtonjreFull().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cfgFrame.getPanelConfig().getButtonNavigateInFileSys().setEnabled(false);
				cfgFrame.getPanelConfig().getTextToJavaFxLibPath().setEditable(false);
				cfgFrame.getPanelConfig().getLablePathToJavaFXLib().setEnabled(false);
			}
			
		});
		
		cfgFrame.getPanelConfig().getRadioButtonJavaFx().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cfgFrame.getPanelConfig().getButtonNavigateInFileSys().setEnabled(true);
				cfgFrame.getPanelConfig().getTextToJavaFxLibPath().setEditable(true);
				cfgFrame.getPanelConfig().getLablePathToJavaFXLib().setEnabled(true);
			}
		});
		
		cfgFrame.getPanelConfig().getButtonAvanti().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				Properties p=FilesLanguageManager.getPropertiesLanguage(FilesLanguageManager.getCurrentLanguage());
				boolean result=DataBase.createDataBase(cfgFrame.getPanelConfig().getTextUrlToDataBase().getText(), 
						cfgFrame.getPanelConfig().getTextUserName().getText(), 
						String.valueOf(cfgFrame.getPanelConfig().getTextPassword().getPassword()));
				System.out.println(cfgFrame.getPanelConfig().getTextPassword().getPassword());
				if(!result) {
					JOptionPane.showMessageDialog(cfgFrame, p.getProperty(NoDaaBaseCreate, "Impossibile creare il dataBase.\n controlla i parametri inseriti"));
				}else {
					if(!cfgFrame.getPanelConfig().getRadioButtonjreFull().isSelected()) {
						if(!ScriptCreator.createScript(cfgFrame.getPanelConfig().getTextToJavaFxLibPath().getText())) {
							JOptionPane.showMessageDialog(cfgFrame, p.getProperty(NoRunFileCreate, "Impossibile creare lo Scipt."));
						}else {
							cfgFrame.loadRunPanel();
						}
					}else {
						if(!ScriptCreator.createScript(ScriptCreator.createCMDToRunFxApp(ScriptCreator.JAR_NAME), ScriptCreator.SCRIPT_NAME))
							JOptionPane.showMessageDialog(cfgFrame, p.getProperty(NoRunFileCreate, "Impossibile creare lo Scipt."));
						else
							cfgFrame.loadRunPanel();
					}
				}
			}	
		});
	}
	
	private void initListenersRunPanel() {
		cfgFrame.getRunPanel().getRunButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ScriptCreator.runShellScript();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
}
