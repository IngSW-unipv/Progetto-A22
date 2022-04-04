package serverDominator.config.viw;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.FileToString;
import serverDominator.config.viw.panel.ConfigCreator;
import serverDominator.config.viw.panel.InitLinguaChooser;
import serverDominator.config.viw.panel.OnlyDescription;
import serverDominator.config.viw.panel.RunPanel;

public class ConfigFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String LINGUE_DISPONIBILI_FILE_PATH = "resources/language/LingueDisponibili";
	private InitLinguaChooser panelBenvenuto=null;
	private ConfigCreator panelConfig=null;
	private RunPanel runPanel=null;
	private OnlyDescription onlyDescriptionPanel=null;


	/**
	 * Create the frame.
	 */
	public ConfigFrame() {
		super();
		setName("frame52");
		setTitle("Server Dominator Config");
		this.initFrame();
		this.loadFirstPanel();
		this.setVisible(true);
	}
	
	public void initFrame() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 783, 333);
		this.firstPanelCreator();
		this.secondPanelCreator();
		this.runPanelCreator();
		this.onlyDescriptionPanelCreator();
	}
	
	public void firstPanelCreator() {
		ArrayList<String> myList=new ArrayList<String>();
		File fls=new File(LINGUE_DISPONIBILI_FILE_PATH);
		if(fls.exists()) {
			try {
				ArrayList<String> list = new ArrayList<String>(Arrays.asList(FileToString.transformFileToString(LINGUE_DISPONIBILI_FILE_PATH).split("\n")));
				myList=list;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		panelBenvenuto = new InitLinguaChooser(myList, FilesLanguageManager.getCurrentLanguage());
	}
	
	public void runPanelCreator() {
		runPanel=new RunPanel();
	}
	
	public void onlyDescriptionPanelCreator() {
		onlyDescriptionPanel=new OnlyDescription();
	}
	
	public void secondPanelCreator() {
		panelConfig=new ConfigCreator();
	}
	
	public void loadFirstPanel() {
		if(panelBenvenuto==null) {
			firstPanelCreator();
		}
		changePanel(panelBenvenuto);
	}

	public void loadSecondPanel() {
		if(panelConfig==null) {
			secondPanelCreator();
		}
		changePanel(panelConfig);
	}
	public void loadRunPanel() {
		if(runPanel==null) {
			runPanelCreator();
		}
		changePanel(runPanel);
	}
	
	public void loadDescriptionPanel() {
		if(onlyDescriptionPanel==null) {
			onlyDescriptionPanelCreator();
		}
		changePanel(onlyDescriptionPanel);
	}
	
	public void restartFirstPanel() {
		if(panelBenvenuto==null) {
			firstPanelCreator();
		}
		changePanel(panelBenvenuto);
	}
	
	public void restartSecondPanel() {
		panelConfig=new ConfigCreator();
		panelConfig.repaint();
		this.repaint();
	}

	public InitLinguaChooser getPanelBenvenuto() {
		return panelBenvenuto;
	}

	public void setPanelBenvenuto(InitLinguaChooser panelBenvenuto) {
		this.panelBenvenuto = panelBenvenuto;
	}

	public ConfigCreator getPanelConfig() {
		return panelConfig;
	}

	public void setPanelConfig(ConfigCreator panelConfig) {
		this.panelConfig = panelConfig;
	}
	
	public RunPanel getRunPanel() {
		return runPanel;
	}

	public void setRunPanel(RunPanel runPanel) {
		this.runPanel = runPanel;
	}

	public OnlyDescription getOnlyDescriptionPanel() {
		return onlyDescriptionPanel;
	}

	public void setOnlyDescriptionPanel(OnlyDescription onlyDescriptionPanel) {
		this.onlyDescriptionPanel = onlyDescriptionPanel;
	}
	
	public void changePanel(JPanel panel) {
		try {
			this.getContentPane().removeAll();
			this.getContentPane().add(panel);
			this.revalidate();
			panel.repaint();
			this.repaint();
		}catch(Exception ex) {
			ex.printStackTrace();
		}catch(Error e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ConfigFrame frame = new ConfigFrame();
					frame.setVisible(true);
					//frame.loadSecondPanel();
					//frame.loadFirstPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
