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
    
    public boolean siCommandeContientProduit(int idCmd,int idProduit) {
    	return detailCmdDAO.siCommandeContientProduit(idCmd,idProduit);
    }

    /*public ArrayList<detailCommande> getListCTHoaDonTheoMaHD(String maHD) {
        int ma = Integer.parseInt(maHD);
        ArrayList<CTHoaDon> dsct = new ArrayList<>();

        for (CTHoaDon cthd : listeDetailCommande) {
            if (cthd.getMaHD() == ma)
                dsct.add(cthd);
        }

        return dsct;
    }*/

    public void addDetailCommande(int idCommande,int idProduit, float prix) {
        detailCommande produit = new detailCommande(idCommande, idProduit, prix);
        detailCmdDAO.addDetailCommande(produit);
    }
    
    public void plusUnAProduitExistantACommande(int idCommande,int idProduit) {
        detailCommande produit = new detailCommande(idCommande, idProduit);
        detailCmdDAO.plusUnAProduitExistantACommande(produit);
    }
}
