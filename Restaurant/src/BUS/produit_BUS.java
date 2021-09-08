package BUS;

import java.util.ArrayList;

import Custom.monDialogue;
import DAO.produit_DAO;
import DTO.produitModele;

public class produit_BUS {
	private ArrayList<produitModele> listeProduit = null;
    private produit_DAO produitDAO = new produit_DAO();

    public produit_BUS() {
        lireListeProduit();
    }

    public void lireListeProduit() {
    	listeProduit = produitDAO.GetListeProduit();
    }
    
    public ArrayList<produitModele> getListeProduit() {
        if (listeProduit == null) {
        	lireListeProduit();
        }
        return listeProduit;
    }

    public produitModele getProduit(String id) {
        if (!id.trim().equals("")) {
            try {
                int idProduit = Integer.parseInt(id);
                for (produitModele produit : listeProduit) {
                    if (produit.getIdProduit() == idProduit) {
                        return produit;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
    
    /*
     * Recherche des produits selon la libellé
     */
    public ArrayList<produitModele> getProduitSelonLibelle(String libelle) {
        ArrayList<produitModele> listeProduitTrouve = new ArrayList<>();
        for (produitModele produit : listeProduit) {
            String libelleProduit = produit.getLibelleProduit().toLowerCase();
            if (libelleProduit.contains(libelle.toLowerCase())) {
            	listeProduitTrouve.add(produit);
            }
        }
        return listeProduitTrouve;
    }
    
    /*
     * Recherche des produits d'une catégorie
     */
    public ArrayList<produitModele> getProduitSelonCategorie(String categorie) {
        if (!categorie.trim().equals("")) {
            ArrayList<produitModele> listeProduitTrouve = new ArrayList<>(0);
            try {
                for (produitModele produit : listeProduit) {
                    if (produit.getCategorie().toLowerCase().equals(categorie.toLowerCase())) {
                    	listeProduitTrouve.add(produit);
                    }
                }
                return listeProduitTrouve;
            } catch (Exception e) {
            	e.printStackTrace();
				System.out.println("getProduitSelonCategorie-Exception: " + e.getMessage());
            }
        }
        return null;
    }
    
    /*
     * obtenir le lien d'image de produit 
     */
    public String getImage(String id) {
        int idProduit = Integer.parseInt(id);
        return produitDAO.getImage(idProduit);
    }

    /*
     * ajoute d'un produit
     */
    public boolean ajouteProduit(String libelle,
            String categorie,
            String prix,
            String photo) {

    	if (libelle.trim().equals("")) {
            new monDialogue("Veuillez saisir la libelle de produit !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (categorie.trim().equals("")) {
            new monDialogue("Veuillez choisir la catégorie !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (prix.trim().equals("")) {
            new monDialogue("Veuillez saisir le prix de produit !", monDialogue.ERROR_DIALOG);
            return false;
        } else {	
	        try {
	            float prixUnitaire = Float.parseFloat(prix);
	            produitModele produit = new produitModele(libelle,categorie,prixUnitaire,photo);

	            if (produitDAO.ajouteProduit(produit)) {
	                new monDialogue("Le produit est ajouté !", monDialogue.SUCCESS_DIALOG);
	                return true;
	            } else {
	                new monDialogue("Il y a un erreur. Le produit n'est pas ajouté !", monDialogue.ERROR_DIALOG);
	                return false;
	            }
	        } catch (Exception e) {
	            new monDialogue("Le prix est erroné !", monDialogue.ERROR_DIALOG);
	        }
	        return false;
        }
    }
    
    /*
     * Suppression d'un produit 
     */
    public boolean suppressionProduit(String id) {
        if (id.trim().equals("")) {
            new monDialogue("Veuillez choisir le produit à supprimer !", monDialogue.ERROR_DIALOG);
            return false;
        }

        int idProduit = Integer.parseInt(id);
        if (produitDAO.suppressionProduit(idProduit)) {
        	 new monDialogue("Le produit est supprimé !", monDialogue.SUCCESS_DIALOG);
            return true;
        }

        new monDialogue("Il y a un erreur. La suppression échue !", monDialogue.ERROR_DIALOG);
        return false;
    }

    /*
     * modification d'un produit
     */
    public boolean majProduit(String id,
            String libelle,
            String categorie,
            String prix,
            String photo) {

        try {
            if (id.trim().equals("")) {
            	 new monDialogue("Veuillez choisir un produit !", monDialogue.ERROR_DIALOG);
                return false;
            }
            int idProduit = Integer.parseInt(id);
            

            if (libelle.trim().equals("")) {
           	 new monDialogue("Veuillez saisir la libellé de produit !", monDialogue.ERROR_DIALOG);
               return false;
            }

            if (categorie.trim().equals("")) {
              	 new monDialogue("Veuillez saisir la catégorie de produit !", monDialogue.ERROR_DIALOG);
                  return false;
            }
            
            if (prix.trim().equals("")) {
             	 new monDialogue("Veuillez saisir le prix de produit !", monDialogue.ERROR_DIALOG);
                 return false;
           }
            float prixUnitaire = Float.parseFloat(prix);

            produitModele produit = new produitModele(idProduit, libelle, categorie, prixUnitaire, photo);

            if (produitDAO.majProduit(produit)) {
            	new monDialogue("La modification est effectuée !", monDialogue.SUCCESS_DIALOG);
                return true;
            } else {
            	new monDialogue("Il y a un erreur. La modification échue !", monDialogue.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
        	new monDialogue("Veuillez resaisir le prix de produit !", monDialogue.ERROR_DIALOG);
        }
        return false;
    }

    /*
     * rechercher la libelle d'un produit
     */
    public String getLibelleProduit(int id) {
        for (produitModele produit : listeProduit) {
            if (produit.getIdProduit() == id) {
                return produit.getLibelleProduit();
            }
        }
        return "";
    }
}
