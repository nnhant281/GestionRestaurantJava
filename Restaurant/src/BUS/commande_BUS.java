package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ConnexionBDD;
import DAO.commande_DAO;
import DAO.detailCommande_DAO;
import DTO.commande;
import DTO.detailCommande;

public class commande_BUS {
	
	private ArrayList<commande> listeCommande;
    private commande_DAO cmdDAO = new commande_DAO();
    private detailCommande_DAO detailDAO = new detailCommande_DAO();

    public ArrayList<commande> getListeCommandeEnCour() {
        listeCommande = cmdDAO.getListeCommandeEnCour();
        return listeCommande;
    }

    public void creationCommande(int idRH, int idTable, float montant ) {
        commande cmd = new commande(idRH,idTable,montant);
        cmdDAO.addCommande(cmd);
    }

    public int getIdDerniereCommande() {
    	return cmdDAO.getIdDerniereCommande();
    }
    public int getUncheckBillIDByTableID(int id){
    	return cmdDAO.getUncheckBillIDByTableID(id);
    }

    public boolean majMontantCommande(int idCmd, float prix) {
    	return cmdDAO.majMontantCommande(idCmd, prix) ;
    }
    
    public commande getCommandeParIDCommande(int idCmd) {
    	return cmdDAO.getCommandeParIDCommande(idCmd);
    }
    
    public boolean saisieIdClientALaCommande(int idCmd, int idClient) {
    	return cmdDAO.saisieIdClientALaCommande(idCmd,idClient);
    }
    
    public boolean commandePayee(int idCmd) {
    	return cmdDAO.commandePayee(idCmd) ;
    }
    
    public ArrayList<detailCommande> showCommandeDeTable(String idTable) {
        int id = Integer.parseInt(idTable);
        ArrayList<detailCommande> listeDetail = new ArrayList<detailCommande>();
        listeDetail = detailDAO.getListdetailCommandeSelonIdCmd(cmdDAO.getCommandeEnCourParTable(id));
        return listeDetail;
    }

}
