package DTO;

public class categorie {
	private String libelleCategorie;
	private int statut =1;
	

	public categorie() {
		
	}
	
	public categorie(String libelle) {
		this.libelleCategorie = libelle;
	}

	public String getLibelleCategorie() {
		return libelleCategorie;
	}
	
	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}


	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}
	
}
