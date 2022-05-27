package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.exeptions;

public class CryptoException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CryptoException() {
    }
 
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
