package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.chiffreAffaireMoisAnModele;

public class chiffreAffaireMoisAnModele_DAO {
		
	/*
	============================================================
           			CHIFFRE AFFAIRE PAR MOIS PAR ANNEE
	============================================================
	*/	
	
	 private static Connection conn = null;
	 PreparedStatement select, update;
	 private String sql;
	  
	 //RETOURNER UNE LISTE QUI CONTIENT UN OBJET SOUS FORME (CHIFFRE AFFAIRE, MOIS, ANNEE)
	 public ArrayList<chiffreAffaireMoisAnModele> getListeChiffreAffaireMoisAn(int mois, int annee) {
		 
		 ArrayList<chiffreAffaireMoisAnModele> chiffreAffaireMoisAn = new ArrayList<chiffreAffaireMoisAnModele>();
		 
		 try {
			 conn = ConnexionBDD.getConnect();
			 
			//REQUETE PERMETTANT DE CALCULER LE CHIFFRE D'AFFAIRE DE CHAQUE MOIS, FILTRE PAR ANNEE 	
	        sql = "SELECT * FROM ( SELECT YEAR(c.Date) as 'Annee', MONTH(c.Date) as 'Mois', SUM(pc.Quantite*pc.Prix_unitaire) as 'CA' FROM commande c INNER JOIN produit_commande pc ON c.ID_Commande = pc.ID_Commande WHERE c.Statut = 1 GROUP BY Annee,Mois) as Total WHERE Annee="+annee+" AND Mois ="+ mois;
	        select = conn.prepareStatement(sql);
		    ResultSet rs = select.executeQuery();
		    
		    while (rs.next()) {
        		int mois1 = rs.getInt("Mois");
        		int annee1 = rs.getInt("Annee");
        		float ca = rs.getFloat("CA");
        		chiffreAffaireMoisAnModele chiffre = new chiffreAffaireMoisAnModele(mois1,annee1, ca);
        		chiffreAffaireMoisAn.add(chiffre);    		
		    }
        	return chiffreAffaireMoisAn;
    	        
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("getListeChiffreAffaireMoisAn-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("getListeChiffreAffaireMoisAn-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return chiffreAffaireMoisAn;
		 }
	 
        	      	    		
}
