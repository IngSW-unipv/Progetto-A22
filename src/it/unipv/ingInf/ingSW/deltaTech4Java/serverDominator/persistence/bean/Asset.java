package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean;


import java.util.ArrayList;

/**
 * Qualunque asset aquistabile nel gioco 
 * @author TawaHabib
 * @version 1.0
 * @see AssetOwn
 */
public class Asset{

	/**
	 * identificativo dell'asset
	 */
	private Integer idAsset;
	
	/**
	 * costo dell'asset
	 */
	private Integer costo;
	
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
	private Integer livello;
	
	/**
	 * Associazione uno a molti con AssetOun
	 */
	private ArrayList<AssetOwn> asetOwns = new ArrayList<AssetOwn>(0);

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
	public Asset(Integer idAsset, Integer livello, Integer costo) {
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
	public Asset(Integer idAsset, Integer costo, String nome, String descrizione, Integer livello) {
		this.idAsset = idAsset;
		this.costo = costo;
		this.nome = nome;
		this.descrizione = descrizione;
		this.livello = livello;
	}
	
	/**
	 * @return id dell'asset
	 */

	public Integer getIdAsset() {
		return this.idAsset;
	}

	/**
	 * settare l'id dell'asset
	 * @param idAsset
	 */
	public void setIdAsset(Integer idAsset) {
		this.idAsset = idAsset;
	}

	/**
	 * @return Costo dell'asset
	 */
	public Integer getCosto() {
		return this.costo;
	}

	/**
	 * settare costo dell'asset
	 * @param costo
	 */
	public void setCosto(Integer costo) {
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
	public Integer getLivello() {
		return this.livello;
	}

	/**
	 * settare livello asset
	 * @param livello
	 */
	public void setLivello(Integer livello) {
		this.livello = livello;
	}
    /**
     * mappature dell'associazione con assetOwn
     * @return
     */

	public ArrayList<AssetOwn> getAsetOwns() {
		return this.asetOwns;
	}

	/**
	 * settare gli assetOwm (relationship)
	 * @param asetOwns
	 */
	public void setAsetOwns(ArrayList<AssetOwn> asetOwns) {
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
	public void addAssetOwn(AssetOwn asetOwns) {
		this.asetOwns.add(asetOwns);
	}
	/**
	 * Rimozione asset Psseduto dal giocatore
	 * @param asetOwns
	 */
	public void remuveAssetOwn(AssetOwn asetOwns) {
		this.asetOwns.remove(asetOwns);
	}

	@Override
	public String toString() {
		return "Asset [idAsset=" + idAsset + ", costo=" + costo + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", livello=" + livello + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asset other = (Asset) obj;
		return (costo == other.costo && descrizione.equalsIgnoreCase(other.descrizione)  && idAsset == other.idAsset
				&& livello == other.livello && nome.equalsIgnoreCase(other.nome));
	}

}
