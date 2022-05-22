package serverDominator.config.controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;
import serverDominator.config.model.ScriptsFacade;
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
	public static final String NoLibSelected="Controller.err.libNoSelect";
	
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
		cfgFrame.getPanelBenvenuto().getListaLingue().setSelectedValue(ILanguageManager.getCurrentLanguage(), true);
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
								FilesLanguageManager.getLanguageFilePath(ILanguageManager.getCurrentLanguage())
								);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					try {
						JOptionPane.showMessageDialog(cfgFrame, new String(p.getProperty(LinguaNonSelezionata, "Non � Stata Selezionata Una Lingua").getBytes(),"UTF-8"));
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						JOptionPane.showMessageDialog(cfgFrame, "Non � Stata Selezionata Una Lingua");
					}
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
		        	   	cfgFrame.getPanelBenvenuto().getListaLingue().setSelectedValue(ILanguageManager.getCurrentLanguage(), true);
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
			
				Properties p=FilesLanguageManager.getPropertiesLanguage(ILanguageManager.getCurrentLanguage());
				boolean result=DataBase.createDataBase(cfgFrame.getPanelConfig().getTextUrlToDataBase().getText(), 
						cfgFrame.getPanelConfig().getTextUserName().getText(), 
						String.valueOf(cfgFrame.getPanelConfig().getTextPassword().getPassword()));
				System.out.println(cfgFrame.getPanelConfig().getTextPassword().getPassword());
				if(!result) {
					try {
						JOptionPane.showMessageDialog(cfgFrame, new String(p.getProperty(NoDaaBaseCreate, "Impossibile creare il dataBase.\n controlla i parametri inseriti").getBytes(),"UTF-8"));
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						JOptionPane.showMessageDialog(cfgFrame, "Impossibile creare il dataBase.\n controlla i parametri inseriti");

					}
				}else {
					if(!cfgFrame.getPanelConfig().getRadioButtonjreFull().isSelected()) {
						File fls=new File(cfgFrame.getPanelConfig().getTextToJavaFxLibPath().getText());
						if(!fls.exists()||!fls.isDirectory()) {
							try {
								JOptionPane.showMessageDialog(cfgFrame, new String(p.getProperty(NoLibSelected, "Attenzione: scegliere la cartella lib di java fx").getBytes(),"UTF-8"));
							} catch (HeadlessException | UnsupportedEncodingException e1) {
								JOptionPane.showMessageDialog(cfgFrame, "Attenzione: scegliere la cartella lib di java fx");

							}
						}else {
							if(!ScriptsFacade.getIstance().createScript(cfgFrame.getPanelConfig().getTextToJavaFxLibPath().getText())) {
								JOptionPane.showMessageDialog(cfgFrame, p.getProperty(NoRunFileCreate, "Impossibile creare lo Scipt."));
							}else {
								cfgFrame.loadRunPanel();
							}
						}
					}else {
						if(!ScriptsFacade.getIstance().createScript(ScriptsFacade.getIstance().createCMDToRunFxApp(ScriptsFacade.getIstance().getJarName()), ScriptsFacade.getIstance().getScriptName()))
							try {
								JOptionPane.showMessageDialog(cfgFrame, new String(p.getProperty(NoRunFileCreate, "Impossibile creare lo Scipt.").getBytes(),"Utf-8"));
							} catch (HeadlessException | UnsupportedEncodingException e1) {
								JOptionPane.showMessageDialog(cfgFrame,"Impossibile creare lo Scipt.");
							}
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
					ScriptsFacade.getIstance().runShellScript();
					cfgFrame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
}
