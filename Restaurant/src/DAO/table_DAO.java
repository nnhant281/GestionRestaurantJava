package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.table;

public class table_DAO {
	private static Connection conn = null;
	
	/*
	 * recupérér la liste de tab 
	 */
	public ArrayList<table> getListeTable() {
        try {
            conn = ConnexionBDD.getConnect() ;	
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tab WHERE statut != 0");
            ArrayList<table> listeTable = new ArrayList<>();
            while (rs.next()) {
                table tab= new table();
                tab.setId(rs.getInt(1));
                tab.setLibelle(rs.getString(2));
                tab.setStatut(rs.getInt(3));
                listeTable.add(tab);
            }
            return listeTable;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("GetListeTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetListeTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }
	
	/*
     * ajouter une nouvelle tab  
     */
    public boolean ajouteTable(table t) {
        boolean result = false;
        try {
		 conn = ConnexionBDD.getConnect() ;	
		 String sql = "INSERT INTO tab(libelle, statut) VALUES(?,?)";
		 PreparedStatement prep = conn.prepareStatement(sql);
	     prep.setString(1, t.getLibelle());
	     prep.setInt(2, t.getStatut());
	     result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("ajouteTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("ajouteTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * Modifier le nom de la table
     */
    public boolean majTable(table t) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE tab SET libelle=? WHERE id="+ t.getId();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, t.getLibelle());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("majTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("majTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * supprimer un table ,
     * l'id d'une tab est un clé étrangé, donc il n'est pas supprimé mais caché de l'application 
     */
    public boolean deleteTable(int id) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE tab SET statut=0 WHERE id=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("deleteTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * changer le statut de la tab lorsque des clients de la table passent la cammande 
     */
    public boolean tableOccupee(int id) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE tab SET statut=2 WHERE id=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("tabOccupee-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("tabOccupee-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * libérer la tab lorsque la commande est payée
     */
    public boolean tableDispo(int id) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE tab SET statut=1 WHERE id=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("tabDispo-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("tabDispo-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
}
