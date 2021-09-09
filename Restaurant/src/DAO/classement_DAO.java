package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.classementModele;

public class classement_DAO {
	
	 private static Connection conn = null;
	 PreparedStatement select;
	 private String sql;
	 
	 //INITIER LA LISTE DE CLASSEMENT PAR CATEGORIE
	 public ArrayList<classementModele> InitierListeClassement(String categorie) {
	    	
	    	ArrayList<classementModele> listeClassementParCategorie = new ArrayList<classementModele>();
	        try {
	            sql ="SELECT p.Libelle, SUM(Quantite) FROM produit_commande pc INNER JOIN commande c ON pc.ID_Commande = c.ID_Commande INNER JOIN produit p ON pc.ID_Produit = p.ID_Produit INNER JOIN categorie cat ON p.Libelle_categorie = cat.Libelle_categorie  WHERE c.Statut  = 1 AND cat.Libelle_categorie ='"+categorie+"'"+"GROUP BY pc.ID_Produit ORDER BY SUM(Quantite) DESC";

	            conn = ConnexionBDD.getConnect() ;	
	            select = conn.prepareStatement(sql);
	            ResultSet rs = select.executeQuery();
	                
	            while (rs.next()) {
	            	classementModele classement = new classementModele();
	            	
	            	classement.setProduit(rs.getString("Libelle"));
	            	classement.setQuantite(rs.getInt("SUM(Quantite)"));
	        	
	            	listeClassementParCategorie.add(classement);
	            }
	            return listeClassementParCategorie;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("InitierListeClassement-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("InitierListeClassement-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return null;
	   }	
}
