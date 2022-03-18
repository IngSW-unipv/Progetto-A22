package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util;

import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Classe con utilità per la cifrare/decifcare 
 * @author TAWADROS
 * @version  1.0
 */
public class CryptoUtil {
    
	private static final String ALGO = "AES";
    private static final String IV = "j1khb8BA6DB4H7N3";
    private static final String KEY = "LJKhsmbdJGVAFSGWjdhlADBhlajkhhrwGHB0AS8148"
    		+ "nwldfn7qAHGrèqAWRxzFNJKLAHDFOH13/$()%DABNKJQHnccv,mn<j"
    		+ "gQGDHOUZ<.,B KKçENçNccfdykb,mvkchhchnqKBhfdac.HSbadnòj";
    
    /**
     * Metodo per cifrare una stringa in mainera simmetrica con chiave e IV di default.
     * @param mssInChiaro
     * Testo in chiaro
     * @return Testo Cifrato
     * @throws Exception
     */
    public static String encrypt(String mssInChiaro) throws Exception {
    	return encrypt(KEY, IV,  mssInChiaro);
    }
    
    /**
     * Metodo per decifrare una stringa in mainera simmetrica con chiave e IV di default.
     * @param mssCrittato
     * Testo in cifrato
     * @return Testo Cifrato
     * @throws Exception
     */
    public static String decrypt(String mssCrittato) throws Exception {
    	return decrypt(KEY, IV,  mssCrittato);
    }
    
    /**
     * Metodo per cifrare una stringa in mainera simmetrica.
     * @param ChiavePub
     * Chiave
     * @param iv
     * vettore di inizializazione,
     * <a href="https://en.wikipedia.org/wiki/Initialization_vector">info</a>; 
     * deve essere di 16 bytes
     * @param mssInChiaro
     * Testo in chiaro
     * @return Testo Cifrato
     * @throws Exception
     */
    public static String encrypt(String ChiavePub, String iv, String mssInChiaro) throws Exception {
        byte[] bitesChiave = ChiavePub.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = md.digest(bitesChiave);
        byte[] ivBytes = iv.getBytes();
        SecretKeySpec chiaveSegreta = new SecretKeySpec(keyBytes, ALGO);
        Cipher cifrario = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrario.init(Cipher.ENCRYPT_MODE, chiaveSegreta, new IvParameterSpec(ivBytes));
        byte[] result = cifrario.doFinal(mssInChiaro.getBytes());
        return Base64.getMimeEncoder().encodeToString(result);
    }

    /**
     * Metodo per decifrare una stringa.
     * @param ChiavePub
     * Chiave
     * @param iv
     * vettore di inizializazione,
     * <a href="https://en.wikipedia.org/wiki/Initialization_vector">info</a>; 
     * deve essere di 16 bytes
     * @param mssCrittato
     * Testo in cifrato
     * @return Testo in chiaro
     * @throws Exception
     */
    public static String decrypt(String ChiavePub, String iv, String mssCrittato) throws Exception {
        byte[] bitesChiave = ChiavePub.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = md.digest(bitesChiave);
        byte[] ivBytes = iv.getBytes();
        byte[] biteCifrati = Base64.getMimeDecoder().decode(mssCrittato);
        SecretKeySpec chiaveSegreta = new SecretKeySpec(keyBytes, ALGO);
        Cipher cifrario = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cifrario.init(Cipher.DECRYPT_MODE, chiaveSegreta, new IvParameterSpec(ivBytes));
        byte[] result = cifrario.doFinal(biteCifrati);
        return new String(result);
    }
/* PROVA
    public static void main(String[] args) {
        try {
            String s = FileToString.transformFileToString("in.txt");
            String res = encrypt(KEY, IV, s);
            PrintWriter writer = new PrintWriter("out.txt");
            writer.print(res);
            writer.close();
            s = FileToString.transformFileToString("out.txt");
            res = decrypt(KEY, IV, s);
            writer = new PrintWriter("out2.txt");
            writer.print(res);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
 //*/
}