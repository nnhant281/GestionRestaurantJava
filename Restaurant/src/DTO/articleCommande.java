package DTO;

public class articleCommande {
	private int idArticle;
	private String libelle;
	private int quantite;
	private float prixUnit;
	
	public articleCommande() {
		
	}
	
	public articleCommande(int idArticle, String libelle, int quantite, float prixUnit) {
		this.idArticle = idArticle;
		this.libelle = libelle;
		this.quantite = quantite;
		this.prixUnit = prixUnit;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getPrixUnit() {
		return prixUnit;
	}

	public void setPrixUnit(float prixUnit) {
		this.prixUnit = prixUnit;
	}
	
	
}
