package DTO;

public class classementModele {
	
	
	private String categorie;
	private String produit;
	private int quantite;
	private float chiffreAffaire;	
	private int ID_commande;
	private int ID_Produit;

	

	public int getID_Produit() {
		return ID_Produit;
	}

	public void setID_Produit(int iD_Produit) {
		ID_Produit = iD_Produit;
	}

	public float getChiffreAffaire() {
		return chiffreAffaire;
	}

	public void setChiffreAffaire(float chiffreAffaire) {
		this.chiffreAffaire = chiffreAffaire;
	}

	public classementModele() {
		
	}
	
	public classementModele(String produit, int quantite) {
		this.produit = produit;
		this.quantite = quantite;	
	}
	
	public classementModele(int ID_commande) {
		this.ID_commande = ID_commande;
		
	}
	
	
	public int getID_commande() {
		return ID_commande;
	}

	public void setID_commande(int iD_commande) {
		ID_commande = iD_commande;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
