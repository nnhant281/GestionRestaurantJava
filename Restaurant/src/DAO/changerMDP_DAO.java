package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class changerMDP_DAO {
	
	 private static Connection conn = null;
	 
	 PreparedStatement update;
	 private String sql;
	 
	 public boolean changerMDP(String identifiant, String mdp) {
		
		try {  		
    		conn = ConnexionBDD.getConnect() ;
        	
        	sql = "UPDATE compte_user set Mot_de_passe = ? WHERE Identifiant ='"+identifiant+"'";						
			update = conn.prepareStatement(sql);
			update.setString(1, mdp);		
			update.execute(); 
            return true;
				    	
	    } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("changerMDP-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("changerMDP-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
    	return false;    
	 }
}
