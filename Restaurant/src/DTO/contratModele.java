package DTO;

public class contratModele {
	
	private String typeContrat;
	
	public contratModele() {
		
	}
	
	public contratModele(String typeContrat) {		
		this.typeContrat = typeContrat;		
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
}
