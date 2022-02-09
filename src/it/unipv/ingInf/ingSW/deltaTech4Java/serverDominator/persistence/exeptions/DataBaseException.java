package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.exeptions;

public class DataBaseException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String stpErr = "Impossibile reperire informazioni dalla piattaforma, riprovare più tardi.";

	public DataBaseException(){
		super(String.format(stpErr));
	}
}
