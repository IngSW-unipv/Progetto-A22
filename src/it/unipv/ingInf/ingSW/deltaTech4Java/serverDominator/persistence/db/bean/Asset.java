package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean;


import java.util.ArrayList;

/**
 * Qualunque asset aquistabile nel gioco 
 * @author ME
 * @version 1.0
 * @see AsetOwn
 */
public class Asset{

	/**
	 * identificativo dell'asset
	 */
	private int idAsset;
	
	/**
	 * costo dell'asset
	 */
	private int costo;
	
	/**
	 * nome dell'asset
	 */
	private String nome;
	
	/**
	 * descrizione dell'asset
	 */
	private String descrizione;
	
	/**
	 * livello dell'asset
	 */
	private int livello;
	
	/**
	 * Associazione uno a molti con AssetOun
	 */
	private ArrayList<AsetOwn> asetOwns = new ArrayList<AsetOwn>(0);

	/**
	 * Crea asset vuoto
	 */
	public Asset() {
	}

	/**
	 * Crea asset
	 * @param idAsset
	 * @param livello
	 * @param costo
	 */
	public Asset(int idAsset, int livello, int costo) {
		this.idAsset = idAsset;
		this.livello = livello;
		this.costo=costo;
	}
	
	/**
	 * Crea asset 
	 * @param idAsset
	 * @param costo
	 * @param nome
	 * @param descrizione
	 * @param livello
	 */
	public Asset(int idAsset, int costo, String nome, String descrizione, int livello) {
		this.idAsset = idAsset;
		this.costo = costo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.livello = livello;
	}
	
	/**
	 * @return id dell'asset
	 */

	public int getIdAsset() {
		return this.idAsset;
	}

	/**
	 * settare l'id dell'asset
	 * @param idAsset
	 */
	public void setIdAsset(int idAsset) {
		this.idAsset = idAsset;
	}

	/**
	 * @return Costo dell'asset
	 */
	public int getCosto() {
		return this.costo;
	}

	/**
	 * settare costo dell'asset
	 * @param costo
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}

	/**
	 * @return nome dell'asset
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Settare nome asset
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Descrizione asset
	 */
	public String getDescrizione() {
		return this.descrizione;
	}

	/**
	 * settare descrizione asset
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return livello dell'asset
	 */
	public int getLivello() {
		return this.livello;
	}

	/**
	 * settare livello asset
	 * @param livello
	 */
	public void setLivello(int livello) {
		this.livello = livello;
	}
    /**
     * mappature dell'associazione con assetOwn
     * @return
     */

	public ArrayList<AsetOwn> getAsetOwns() {
		return this.asetOwns;
	}

	/**
	 * settare gli assetOwm (relationship)
	 * @param asetOwns
	 */
	public void setAsetOwns(ArrayList<AsetOwn> asetOwns) {
		this.asetOwns = asetOwns;
	}
	/**
	 * @return this asset
	 */
	public Asset getAsset () {
		return this;
	}

	/**
	 * Asset viene posseduto dal giocatore
	 * @param asetOwns
	 */
	public void addAssetOwn(AsetOwn asetOwns) {
		this.asetOwns.add(asetOwns);
	}
	/**
	 * Rimozione asset Psseduto dal giocatore
	 * @param asetOwns
	 */
	public void remuveAssetOwn(AsetOwn asetOwns) {
		this.asetOwns.remove(asetOwns);
	}

	@Override
	public String toString() {
		return "Asset [idAsset=" + idAsset + ", costo=" + costo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", livello=" + livello + "]";
	}

}
