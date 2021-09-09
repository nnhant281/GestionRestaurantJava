package DTO;

public class produitModele {
	private int idProduit;
	private String libelleProduit;
	private String categorie;
	private float prixUnitaire;
	private String photo;
	private int valable = 1;
	
	public produitModele() {
		
	}
	
	// création nouveau produit
	public produitModele( String libelleProduit,
					String categorie,
					float prixUnitaire,
					String photo) {
		this.libelleProduit = libelleProduit;
		this.categorie = categorie;
		this.prixUnitaire = prixUnitaire;
		this.photo = photo;
	}
	
	// modification ou supprimer du produit
	public produitModele( int idProduit,
					String libelleProduit,
					String categorie,
					float prixUnitaire,
					String photo) {
		this.idProduit = idProduit;
		this.libelleProduit = libelleProduit;
		this.categorie = categorie;
		this.prixUnitaire = prixUnitaire;
		this.photo = photo;
	}

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public String getLibelleProduit() {
		return libelleProduit;
	}

	public void setLibelleProduit(String libelleProduit) {
		this.libelleProduit = libelleProduit;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) throws Exception{
		if (prixUnitaire <0)
			throw new Exception("prix erroné");
		this.prixUnitaire = prixUnitaire;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

	public int getValable() {
		return valable;
	}

	public void setValable(int valable) {
		this.valable = valable;
	}

	@Override
	public String toString() {
		return libelleProduit;
	}
	
}
