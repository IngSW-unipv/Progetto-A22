package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;

/**
 * Una tipologia di asset utilizzata per la difesa 
 * @author ME
 * @version 1.0
 * @see Asset
 *
 */
public class Antivirus extends Asset{


	/**
	 * Capacità di difesa dell'antivirus
	 */
	private int capacitaDifensiva;
	/**
	 * Categoria di antivirus a cui appartine l'antivirus
	 */
	private String tipo;

	/**
	 * Crea antivirus vuoto
	 */
	public Antivirus() {
		super();
	}

	/**
	 * Crea antivirus
	 * @param asset
	 * @param capacitaDifensiva
	 */
	public Antivirus(Asset asset, int capacitaDifensiva) {
		super(	asset.getIdAsset(), asset.getCosto(), 
				asset.getNome(), asset.getDescrizione(), 
				asset.getLivello());
		this.capacitaDifensiva = capacitaDifensiva;
	}

	/**
	 * Crea antivirus
	 * @param asset
	 * @param capacitaDifensiva
	 * @param tipo
	 */
	public Antivirus(Asset asset, int capacitaDifensiva, String tipo) {
		super(	asset.getIdAsset(), asset.getCosto(), 
				asset.getNome(), asset.getDescrizione(), 
				asset.getLivello());
		this.capacitaDifensiva = capacitaDifensiva;
		this.tipo = tipo;
	}
	
	/**
	 * @return identificativo Dell'antivirus (stesso dell'asset genitore)
	 */
	public int getAssetIdAsset() {
		return super.getIdAsset();
	}

	/**
	 * setta id dell' antivirus (asset padre)
	 * @param assetIdAsset
	 */
	public void setAssetIdAsset(int assetIdAsset) {
		super.setIdAsset(assetIdAsset);;
	}

	/**
	 * @return Asset padre 
	 */
	public Asset getAsset() {
		return super.getAsset();
	}
	
	/**
	 * Cambia i l'istanza dell'asset padre e
	 * @param asset
	 */
	public void setAsset(Asset asset) {
		super.setIdAsset(asset.getIdAsset());
		super.setCosto(asset.getCosto());
		super.setNome(asset.getNome());
		super.setDescrizione(asset.getDescrizione());
		super.setLivello(asset.getLivello());
		super.setAsetOwns(asset.getAsetOwns());
	}

	/**
	 * @return capacità di difesa dell'antivirus
	 */
	public int getCapacitaDifensiva() {
		return this.capacitaDifensiva;
	}

	/**
	 * Settare capacità di difesa dell'antivirus
	 * @param capacitaDifensiva
	 */
	public void setCapacitaDifensiva(int capacitaDifensiva) {
		this.capacitaDifensiva = capacitaDifensiva;
	}

	/**
	 * @return tipologia dell'antivirus
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Settare tipologia dell'antivirus
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
