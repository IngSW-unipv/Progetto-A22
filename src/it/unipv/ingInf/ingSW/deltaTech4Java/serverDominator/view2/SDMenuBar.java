package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;

public class SDMenuBar {
	
	public SDMenuBar() {
		
	}

	public MenuBar getMenuBar() {
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem openItem = new MenuItem("Open");
		fileMenu.getItems().addAll(saveItem, openItem);
		Menu stateMenu = new Menu("State");
		MenuItem addStateItem = new MenuItem("Add");
		stateMenu.getItems().add(addStateItem);
		menuBar.getMenus().addAll(fileMenu, stateMenu);
		
		
		return menuBar;
	}
}
