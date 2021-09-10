package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.categorie;

public class categorie_DAO {
	
	private static Connection conn = null;
	
	/*
	 * obtenir la liste de catégories
	 */
	public ArrayList<String> getListeCategorie() {
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Categorie WHERE statut = 1");
            ArrayList<String> listeCategogie = new ArrayList<>();
            while (rs.next()) {
                String cate = rs.getString(1);
                listeCategogie.add(cate);
            }
            return listeCategogie;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("GetListeCategorie-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetListeCategorie-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }
	
	/*
	 * Ajouter une catégorie
	 */
	public boolean ajouteCategorie(categorie cate) {
        try {
        	conn = ConnexionBDD.getConnect() ;

	        String sql = "INSERT INTO categorie(Libelle_Categorie,statut) VALUES (?,?) ";
	 		PreparedStatement prep = conn.prepareStatement(sql);
	    	prep.setString(1, cate.getLibelleCategorie());
	    	prep.setInt(2, cate.getStatut());
	    	return prep.executeUpdate() > 0;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("ajouteCategorie-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("ajouteCategorie-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return false;
    }
	/*
	 * Supprimer une catégorie 
	 */
	public boolean suppressionCategorie(String libelleCategorie) {
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	Statement s = conn.createStatement();
            s.execute("UPDATE categorie SET statut = 0 WHERE Libelle_Categorie= '"+libelleCategorie+"'");
            return true;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("suppressionCategorie-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("suppressionCategorie-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return false;
    }

}
