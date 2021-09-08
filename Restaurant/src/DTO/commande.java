package DTO;


import java.sql.Date;
import java.util.Calendar;

public class commande {
	private int idCommande;
    private int IDRH;
    private int idClient;
    private int idTable;
    private Date date = new Date(Calendar.getInstance().getTime().getTime());
    private int statut = 0 ;
    private int typeCommande = 0;
    private float total = 0.F;
    
    public commande() {
    	
    }
    
	public commande(int idCommande, int iDRH, int idClient, int idTable, Date date, int statut, int typeCommande,
			float total) {
		this.idCommande = idCommande;
		this.IDRH = iDRH;
		this.idClient = idClient;
		this.idTable = idTable;
		this.date = date;
		this.statut = statut;
		this.typeCommande = typeCommande;
		this.total = total;
	}
	
	public commande(int iDRH, int idTable, float total) {
		this.IDRH = iDRH;
		this.idTable = idTable;
		this.total = total;
	}

	public int getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public int getIDRH() {
		return IDRH;
	}

	public void setIDRH(int iDRH) {
		IDRH = iDRH;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public int getTypeCommande() {
		return typeCommande;
	}

	public void setTypeCommande(int typeCommande) {
		this.typeCommande = typeCommande;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
    
    
}
