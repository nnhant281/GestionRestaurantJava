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

    
    public void enregistrerFacture(int idCommande, int IDRH, int idClient, int idTable, Date date, int statut, int typeCommande, float total) {
        commande cmd = new commande();
        cmd.setIdCommande(idCommande);
        cmd.setIDRH(IDRH);
        cmd.setIdClient(idClient);
        cmd.setIdTable(idTable);
        cmd.setDate(date);
        cmd.setStatut(statut);
        cmd.setTypeCommande(typeCommande);
        cmd.setTotal(total);

        commandeDAO.addCommande(cmd);
    }


    /*public ArrayList<commande> getListeFactureSelonDate(String min, String max) {
        try {
        	if (!min.trim().matches("[0-9]/")&& !max.trim().matches("[0-9]/")) {
        		new monDialogue("La date est en format DD/MM/YYYY . Veuillez ressaisir !", monDialogue.ERROR_DIALOG);
        	}
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date minDate = sdf.parse(min);
            Date maxDate = sdf.parse(max);

            java.sql.Date dateMin = new java.sql.Date(minDate.getTime());
            java.sql.Date dateMax = new java.sql.Date(maxDate.getTime());

            ArrayList<commande> listeFacture = commandeDAO.getListFacture(dateMin, dateMax);
            return listeFacture;
        } catch (Exception e) {
            new monDialogue("Veuillez ressaisir la date!", monDialogue.ERROR_DIALOG);
        }
        return null;
    }*/
    
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
