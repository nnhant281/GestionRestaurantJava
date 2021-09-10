package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.detailCommande;

public class detailCommande_DAO {
	private static Connection conn = null;
	
	/*
	 * récupérer la liste de détails des commandes 
	 */
	public ArrayList<detailCommande> getListeDetailCommande() {
        ArrayList<detailCommande> listeDetail = new ArrayList<>();
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM produit_commande";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                detailCommande detailCmd = new detailCommande();
                detailCmd.setIdCommande(rs.getInt(1));
                detailCmd.setIdProduit(rs.getInt(2));
                detailCmd.setQuantite(rs.getInt(3));
                detailCmd.setPrixUnitaire(rs.getFloat(4));
                listeDetail.add(detailCmd);
            }
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("getLisDdetailCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("getLisDdetailCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return listeDetail;
    }

	/*
	 * récupérer la liste de produits d'une commande 
	 */
    public ArrayList<detailCommande> getListdetailCommandeSelonIdCmd(int cmd) {
        ArrayList<detailCommande> listeDetail = new ArrayList<>();
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM produit_commande WHERE ID_Commande="+cmd;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                detailCommande detailCmd = new detailCommande();
                detailCmd.setIdCommande(rs.getInt(1));
                detailCmd.setIdProduit(rs.getInt(2));
                detailCmd.setQuantite(rs.getInt(3));
                detailCmd.setPrixUnitaire(rs.getFloat(4));
                listeDetail.add(detailCmd);
            }
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("getLisDdetailCommandeSelonIdCmd-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("getLisDdetailCommandeSelonIdCmd-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return listeDetail;
    }

    
    /*
     * chercher un produit dans une commande 
     */
    public int siCommandeContientProduit(int idCmd,int idProduit) {
        int quantite = -1;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM produit_commande WHERE ID_Commande = "+idCmd+" AND ID_Produit="+idProduit;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
            	quantite = rs.getInt(3);
            }
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("getLisDdetailCommandeContientProduit-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("getLisDdetailCommandeContientProduit-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return quantite;
    }

    /*
     * ajouter un nouveau produit dans la commande 
     */
    public boolean addDetailCommande(detailCommande detailCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "INSERT INTO produit_commande VALUES(?,?,?,?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, detailCmd.getIdCommande());
            prep.setInt(2, detailCmd.getIdProduit());
            prep.setInt(3, detailCmd.getQuantite());
            prep.setFloat(4, detailCmd.getPrixUnitaire());
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("addDetailCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("addDetailCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }

    /*
     * augmenter la quantité de produit existant dans la commande 
     * augementer 1 unit�  
     */
    public boolean plusUnAProduitExistantACommande(detailCommande detailCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE produit_commande SET quantite=? WHERE ID_Commande=? AND ID_Produit=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, detailCmd.getQuantite()+1);
            prep.setInt(2, detailCmd.getIdCommande());
            prep.setInt(3, detailCmd.getIdProduit());
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("plusUnAProduitExistantCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("plusUnAProduitExistantCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    /*
     * annuler , retirer des produits dans la commande 
     */
    public boolean enleverProduitDeCommande(int idCmd, int idProduit) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "DELETE FROM produit_commande WHERE ID_Commande="+idCmd+" AND ID_Produit="+idProduit;
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("enleverProduitDeCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("enleverProduitDeCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }

}
