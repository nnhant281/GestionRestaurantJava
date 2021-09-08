package DTO;

public class compteModele {
	
	private String mdp;
	private String identifiant;
	private int habilitation;
	private int  idrh;
	private String nom;
	private String prenom;
	private String emploi;
	
	public compteModele() {
		
	}
	
	public compteModele(String identifiant, String mdp, int habilitation) {
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.habilitation = habilitation;
		
	}
	
	public compteModele(int idrh, int habilitation) {
		this.idrh = idrh;
		this.habilitation = habilitation;
		
	}
	
	public compteModele(int idrh, String identifiant, String mdp, int habilitation) {
		this.idrh = idrh;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.habilitation = habilitation;	
	}
	
	public compteModele(int idrh, String identifiant, String mdp, int habilitation, String nom, String prenom, String emploi) {
		this.idrh = idrh;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.habilitation = habilitation;	
		this.nom = nom;
		this.prenom = prenom;
		this.emploi = emploi;
	}


	public String getEmploi() {
		return emploi;
	}

	public void setEmploi(String emploi) {
		this.emploi = emploi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public int getHabilitation() {
		return habilitation;
	}

	public void setHabilitation(int habilitation) {
		this.habilitation = habilitation;
	}

	public int getIdrh() {
		return idrh;
	}

	public void setIdrh(int idrh) {
		this.idrh = idrh;
	}
}
