package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.exeptions;

/**
 * @author TawaHabib
 *
 */
public class DataBaseException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String stpErr = "Problemi con il data base, riprova tra un po'";

	public DataBaseException(){
		super(String.format(stpErr));
	}
}
                                    