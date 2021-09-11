package DTO;

import java.util.Date;

public class employeModele {
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresse;
	private String tel;
	private String typeContrat;
	private Date dateDebut;
	private Date dateFin;
	private String dureeHebdo;
	private String emploi;
	private int iDRH;
	private int statut = 1;

	
	public employeModele() {
		
	}
	
	public employeModele(String nom, String prenom, Date dateNaissance, String adresse, String tel, 
			String typeContrat, Date dateDebut, Date dateFin, String dureeHebdo, String emploi, int statut) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.tel = tel;
		this.typeContrat = typeContrat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dureeHebdo = dureeHebdo;
		this.emploi = emploi;
		this.statut = statut;
	}
	
	public employeModele(int IDRH, String nom, String prenom, Date dateNaissance, String adresse, String tel, 
			String typeContrat, Date dateDebut, Date dateFin, String dureeHebdo, String emploi) {
		this.iDRH = IDRH;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.tel = tel;
		this.typeContrat = typeContrat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dureeHebdo = dureeHebdo;
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDureeHebdo() {
		return dureeHebdo;
	}

	public void setDureeHebdo(String dureeHebdo) {
		this.dureeHebdo = dureeHebdo;
	}

	public String getEmploi() {
		return emploi;
	}

	public void setEmploi(String emploi) {
		this.emploi = emploi;
	}

	public int getIDRH() {
		return iDRH;
	}

	public void setIDRH(int iDRH) {
		this.iDRH = iDRH;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}
	
	
}
