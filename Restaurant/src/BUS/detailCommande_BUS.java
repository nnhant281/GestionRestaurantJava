package BUS;

import java.util.ArrayList;

import DAO.commande_DAO;
import DAO.detailCommande_DAO;
import DTO.detailCommande;

public class detailCommande_BUS {
	private ArrayList<detailCommande> listeDetailCommande = null;
    private detailCommande_DAO detailCmdDAO = new detailCommande_DAO();
    private facture_BUS factureBUS = new facture_BUS();
    private commande_DAO commandeDAO = new commande_DAO();
    
    public void lireListeDetail() {
        this.listeDetailCommande = detailCmdDAO.getListeDetailCommande();
    }

    public ArrayList<detailCommande> getListeDetailCommande() {
    	listeDetailCommande = null;
    	lireListeDetail();
        return listeDetailCommande;
    }
    
    public int siCommandeContientProduit(int idCmd,int idProduit) {
    	return detailCmdDAO.siCommandeContientProduit(idCmd,idProduit);
    }

    public void addDetailCommande(int idCommande,int idProduit, float prix) {
        detailCommande produit = new detailCommande(idCommande, idProduit, prix);
        detailCmdDAO.addDetailCommande(produit);
    }
    
    public void plusUnAProduitExistantACommande(int idCommande,int idProduit) {
    	int quantite = siCommandeContientProduit(idCommande,idProduit);
        detailCommande produit = new detailCommande(idCommande, idProduit, quantite);
        detailCmdDAO.plusUnAProduitExistantACommande(produit);
    }
    
    public int enleverProduitDeCommande(int idCmd, int idProduit) {
    	detailCmdDAO.enleverProduitDeCommande(idCmd,idProduit) ;
    	int reste = detailCmdDAO.siCommandeContientProduit(idCmd,idProduit);
    	if (reste <=0 ) {
    		commandeDAO.supprimerCommande(idCmd);
    	}
    	return reste;
    }
}
