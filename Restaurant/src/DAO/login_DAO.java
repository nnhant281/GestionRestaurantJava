package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTO.compteModele;
import DTO.employeModele;

public class login_DAO {
	
	private static Connection conn = null;
	PreparedStatement select;
	private String sql;

	
	  //RECHERCHE COMPTE PAR IDENTIFIANT
    
    public compteModele authentifier(String identifiantRecherche, String mdpRecherche) {
    	
    	boolean trouve = false;
    	compteModele compte = new compteModele();

    	try {
    		Connection conn = ConnexionBDD.getConnect() ;	
			/*
			 * Requete select where et exécution
			 */	
			sql = "SELECT * FROM compte_user";
			select = conn.prepareStatement(sql);			
			ResultSet rs = select.executeQuery();
			
			while (rs.next() && !trouve) {							
				String identifiant = rs.getString("Identifiant");
				String mdp = rs.getString("Mot_de_passe");
								
				if (identifiant.equals(identifiantRecherche) && mdp.equals(mdpRecherche)) {
					compte.setIdrh(rs.getInt("IDRH"));
					compte.setHabilitation(rs.getInt("Habilitation"));
					trouve = true;														
				}
			}
			return compte;
			
	    } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("authentifier-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("authentifier-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
    	return compte;
    }
}
