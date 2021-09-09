package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import DTO.client;

public class client_DAO {
	private static Connection conn = null;
	
	/*
	 * récupérer la liste de clients
	 */
	public ArrayList<client> getListeClient() {
        try {
        	
            String sql = "SELECT * FROM client";
            conn = ConnexionBDD.getConnect() ;	
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<client> listeClient = new ArrayList<>();
            while (rs.next()) {
                client c = new client();
                c.setIdClient(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setAdresse(rs.getString(4));
                c.setNumTel(rs.getString(5));
                c.setEmail(rs.getString(6));
                c.setPoint(rs.getInt(7));
                listeClient.add(c);
            }
            return listeClient;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("GetListeClient-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetListeClient-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }

	/*
	 * chercher un client selon son ID 
	 */
    public client getClient(int id) {
        client c = null;
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM client WHERE ID_client= '"+id+"'");
            while (rs.next()) {
            	c = new client();
                c.setIdClient(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setPrenom(rs.getString(3));
                c.setAdresse(rs.getString(4));
                c.setNumTel(rs.getString(5));
                c.setEmail(rs.getString(6));
                c.setPoint(rs.getInt(7)); 
                return c;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        	System.out.println("GetClient-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetClient-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
    	return null;
    }

    /*
     * ajouter un nouveau client 
     */
    public boolean ajouteClient(client c) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	 String sql = "INSERT INTO client VALUES(?,?,?,?,?,?,?)";
        	 PreparedStatement prep = conn.prepareStatement(sql);
             prep.setInt(1, c.getIdClient());
             prep.setString(2, c.getNom());
             prep.setString(3, c.getPrenom());
             prep.setString(4, c.getAdresse());
             prep.setString(5, c.getNumTel());
             prep.setString(6, c.getEmail());
             prep.setInt(7, c.getPoint());
            
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("ajouteClient-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("ajouteClient-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }

    
    /*
     * modifier une ligne de la table client dans la base de donnée
     */
    public boolean majClient(client c) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;
            String sql = "UPDATE client SET nom=?, prenom=?, adresse=?, num_tel=?,email=? WHERE ID_client="+c.getIdClient();
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, c.getNom());
            prep.setString(2, c.getPrenom());
            prep.setString(3, c.getAdresse());
            prep.setString(4, c.getNumTel());
            prep.setString(5, c.getEmail());
            prep.execute();
            return true;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("majClient-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("majClient-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }

    /*
     * misa à jour le point de client 
     */
    public boolean majPoint(int id, float point ) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;
            String sql = "UPDATE client SET point=" + point + " WHERE ID_client =" + id;
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("majPoint-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("majPoint-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
}
