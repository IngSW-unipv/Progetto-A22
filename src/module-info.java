module CiaoFX {
	requires javafx.controls;
	
	opens it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2 to javafx.graphics, javafx.fxml;
	opens it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view to javafx.graphics, javafx.fxml;
}
