package DTO;

public class chiffreAffaireMoisAnModele {

	private int mois;
	private float ca;
	private int annee;

	public chiffreAffaireMoisAnModele(int mois, int annee, float ca) {
		this.mois = mois;
		this.annee = annee;
		this.ca = ca;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public float getCa() {
		return ca;
	}

	public void setCa(float ca) {
		this.ca = ca;
	}
}
