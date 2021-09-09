package BUS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Custom.monDialogue;
import DAO.client_DAO;
import DTO.client;
import DTO.commande;

public class client_BUS {
	
	private ArrayList<client> listeClient = null;
    private client_DAO clientDAO = new client_DAO();
    
    /*
     * recupérer la liste de clients 
     */
    public void lireListeClient() {
        this.listeClient = clientDAO.getListeClient();
    }
    
    /*
     * mise à jour la liste de clients 
     */
    public ArrayList<client> getlisteClient() {
        listeClient = null;
        lireListeClient();
        return listeClient;
    }
    
    /*
     * recherche des clients par nom, prénom ou email 
     */
    public ArrayList<client> rechercheClient(String cle) {
        cle = cle.toLowerCase();
        ArrayList<client> listeTrouve  = new ArrayList<>();
        for (client c : listeClient) {
            String nom = c.getNom().toLowerCase();
            String prenom = c.getPrenom().toLowerCase();
            String numTel = c.getNumTel();
            String email = c.getEmail().toLowerCase();
            if (nom.contains(cle) || prenom.contains(cle) || numTel.contains(cle) || email.contains(cle)) {
                listeTrouve.add(c);
            }
        }
        return listeTrouve;
    }
    
    /*
     * recherche des clients par nom ,prenom et numéro de téléphone
     */
    public ArrayList<client> rechercheClientParMultiInfo(String nomR,String prenomR, String numTelR) {	
        ArrayList<client> listeTrouve  = new ArrayList<>();
        for (client c : listeClient) {
            String nom= c.getNom().toLowerCase();
            String prenom = c.getPrenom().toLowerCase();
            String num = c.getPrenom();
            if (nom.contains(nomR.toLowerCase()) && prenom.contains(prenomR.toLowerCase()) && num.contains(numTelR)) {
                listeTrouve.add(c);
            }
        }
        return listeTrouve;
    }
    
    /*
     * ajouter un client 
     */
    public boolean ajouteClient(String nom, String prenom, String adresse, String numTel, String email) {
        if (nom.trim().equals("")) {
            new monDialogue("Veuillez saisir le nom !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (prenom.trim().equals("")) {
            new monDialogue("Veuillez saisir le prénom !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (adresse.trim().equals("")) {
            new monDialogue("Veuillez saisir l'adresse !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (numTel.trim().equals("")) {
            new monDialogue("Veuillez saisir le numéro de téléphone !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (numTel.trim().length()!= 10) {
            new monDialogue("Le numéro de téléphone contient 10 chiffres. Veuillez ressaisir !", monDialogue.ERROR_DIALOG);
            return false;
        }else if (!numTel.trim().matches("[0-9]+")) {
            new monDialogue("Le numéro de téléphone contient que les chiffres . Veuillez ressaisir !", monDialogue.ERROR_DIALOG);
            return false;
        } else if ((!email.trim().equals("")) && !email.contains("@")) {
            new monDialogue("Veuillez resssaisir l'adresse email !", monDialogue.ERROR_DIALOG);
            return false;
        }
        client c = new client();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setAdresse(adresse);
        c.setNumTel(numTel);
        c.setEmail(email);
        c.setPoint(0);
        boolean flag = clientDAO.ajouteClient(c);
        if (flag) {
            new monDialogue("Le client est ajouté !", monDialogue.SUCCESS_DIALOG);
        } else {
            new monDialogue("Il y a un erreur. Le client n'est pas ajouté !", monDialogue.ERROR_DIALOG);
        }
        return flag;
    }

    /*
     * modifier un client 
     */
    public boolean majClient(String id, String nom, String prenom, String adresse, String numTel, String email) {
    	if (nom.trim().equals("")) {
            new monDialogue("Veuillez saisir le nom !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (prenom.trim().equals("")) {
            new monDialogue("Veuillez saisir le prénom !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (adresse.trim().equals("")) {
            new monDialogue("Veuillez saisir l'adresse !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (numTel.trim().equals("")) {
            new monDialogue("Veuillez saisir le numéro de téléphone !", monDialogue.ERROR_DIALOG);
            return false;
        } else if (numTel.trim().length()!= 10) {
            new monDialogue("Le numéro de téléphone contient 10 chiffres. Veuillez ressaisir !", monDialogue.ERROR_DIALOG);
            return false;
        }else if (!numTel.trim().matches("[0-9]+")) {
            new monDialogue("Le numéro de téléphone contient que les chiffres . Veuillez ressaisir !", monDialogue.ERROR_DIALOG);
            return false;
        } else if ((!email.trim().equals("")) && !email.contains("@")) {
            new monDialogue("Veuillez resssaisir l'adresse email !", monDialogue.ERROR_DIALOG);
            return false;
        }
    	int idClient = Integer.parseInt(id);
        client c = new client();
        c.setIdClient(idClient);
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setAdresse(adresse);
        c.setNumTel(numTel);
        c.setEmail(email);
        boolean flag = clientDAO.majClient(c);
        if (flag) {
            new monDialogue("La modification est effectuée !", monDialogue.SUCCESS_DIALOG);
        } else {
            new monDialogue("La modification est échue !", monDialogue.ERROR_DIALOG);
        }
        return flag;
    }

   
    public boolean majPoint(int idClient, String point ) {
    	float p = Float.parseFloat(point);
    	return clientDAO.majPoint(idClient, p) ;
    }
}
