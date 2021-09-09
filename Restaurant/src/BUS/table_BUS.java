package BUS;

import java.util.ArrayList;

import Custom.monDialogue;
import DAO.table_DAO;
import DTO.client;
import DTO.table;

public class table_BUS {

	private ArrayList<table> listeTable = null;
    private table_DAO tableDAO = new table_DAO();
    
    /*
     * recupérer la liste de tables
     */
    public void lireListeTable() {
        this.listeTable = tableDAO.getListeTable();
    }
    
    /*
     * mise à jour la liste de Tables 
     */
    public ArrayList<table> getlisteTable() {
        listeTable = null;
        lireListeTable();
        return listeTable;
    }
    
    /*
     * recherche des tables par libellé 
     */
    public ArrayList<table> rechercheTable(String cle) {
        cle = cle.toLowerCase();
        ArrayList<table> listeTrouve  = new ArrayList<>();
        for (table c : listeTable) {
            String libelle = c.getLibelle().toLowerCase();
            if (libelle.contains(cle)) {
                listeTrouve.add(c);
            }
        }
        return listeTrouve;
    }
    
    /*
     * ajouter une table 
     */
    public boolean ajouteTable(String libelle) {
        if (libelle.trim().equals("")) {
            new monDialogue("Veuillez saisir le nom de la table !", monDialogue.ERROR_DIALOG);
            return false;
        }
        table c = new table(libelle);
        boolean flag = tableDAO.ajouteTable(c);
        if (flag) {
            new monDialogue("La table est ajoutée !", monDialogue.SUCCESS_DIALOG);
        } else {
            new monDialogue("Il y a un erreur. La table n'est pas ajoutée !", monDialogue.ERROR_DIALOG);
        }
        return flag;
    }

    
    /*
     * librer une table 
     */
    public boolean tableDispo(String id) {
    	int idTable = Integer.parseInt(id);
    	return tableDAO.tableDispo(idTable);
    }
    
    /*
    * librer une table 
    */
   public boolean tableDispo(int idTable) {
   	return tableDAO.tableDispo(idTable);
   }
    
    /*
     * changer le statut de la table de disponible à occupée
     */
    public boolean tableOccupee(int id) {
    	return tableDAO.tableOccupee(id);
    }
    
    
    /*
     * déactiver une table de l'application
     */
    public boolean deleteTable(String id) {
    	int idTable = Integer.parseInt(id);
    	monDialogue dlg = new monDialogue("La table sera être supprimée ?", monDialogue.WARNING_DIALOG);
        if(dlg.getAction() == monDialogue.CANCEL_OPTION)
            return false;
        boolean flag = tableDAO.deleteTable(idTable);
        if (flag) {
            new monDialogue("La suppression est effectuée !", monDialogue.SUCCESS_DIALOG);
        } else {
            new monDialogue("La suppression est échue !", monDialogue.ERROR_DIALOG);
        }
        return flag;
    }

    /*
     * modifier le nom de la table
     */
    public boolean majTable(String id, String nom) {
    	if (nom.trim().equals("")) {
            new monDialogue("Veuillez saisir le nom de la table !", monDialogue.ERROR_DIALOG);
            return false;
        } 
    	table c = new table();
    	int idTable = Integer.parseInt(id);
    	c.setId(idTable);
        c.setLibelle(nom);
        boolean flag = tableDAO.majTable(c);
        if (flag) {
            new monDialogue("La modification est effectuée !", monDialogue.SUCCESS_DIALOG);
        } else {
            new monDialogue("La modification est échue !", monDialogue.ERROR_DIALOG);
        }
        return flag;
    }
    
}
