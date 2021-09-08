package BUS;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import Custom.monDialogue;
import DAO.commande_DAO;
import DTO.commande;

public class facture_BUS {
	private ArrayList<commande> listeFacture = null;
    private commande_DAO commandeDAO = new commande_DAO();

    /*
     * obtenir la liste de facture
     * les factures sont des commandes payants
     */
    public ArrayList<commande> getListeFacture() {
        listeFacture = commandeDAO.getListeFacture();
        return listeFacture;
    }

    
    /*
     * recherche des factures par id, idClient, IDRH, date 
     */
    public ArrayList<commande> rechercheFacture(String idCommande,String IDRH, String date) {
    	
        ArrayList<commande> listeTrouve  = new ArrayList<>();
        for (commande c : listeFacture) {
            String idCmd = Integer.toString(c.getIdCommande());
            String idRh = Integer.toString(c.getIDRH());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(c.getDate());
            if (idCmd.contains(idCommande) && idRh.contains(IDRH) && strDate.contains(date)) {
                listeTrouve.add(c);
            }
        }
        return listeTrouve;
    }
}
