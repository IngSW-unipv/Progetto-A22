package serverDominator.config;


import serverDominator.config.controllers.Controller;
import serverDominator.config.viw.ConfigFrame;

public class Main {
	public static void main(String[] args) {
		ConfigFrame f=new ConfigFrame();
		@SuppressWarnings("unused")
		Controller conn=new Controller( f);
	}
	
}
