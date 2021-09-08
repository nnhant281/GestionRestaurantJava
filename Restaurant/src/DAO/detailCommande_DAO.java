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
    public boolean siCommandeContientProduit(int idCmd,int idProduit) {
        boolean existe = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM produit_commande WHERE ID_Commande = "+idCmd+" AND ID_Produit="+idProduit;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
            	existe = true;
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
        return existe;
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
     * augementer 1 unité  
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

    /*
     * supprimer des détails d'une commande 
     */
    /*public boolean deleteDetailCommande(int idCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "DELETE FROM produit_commande WHERE ID_Commande="+idCmd;
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql) > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("deleteDetailCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteDetailCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }*/

    /*
     * modifier les détails de la commande 
     */
    /*public boolean updatedetailCommande(int idCmd, int idProduit, detailCommande detailCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE cthoadon SET MaHD=?, MaSP=?, SoLuong=?, DonGia=? ThanhTien=? WHERE MaHD=? AND MaSP=?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, detailCmd.getIdCommande());
            prep.setInt(2, detailCmd.getIdProduit());
            prep.setInt(3, detailCmd.getQuantite());
            prep.setFloat(4, detailCmd.getPrixUnitaire());

            prep.setInt(6, maHD);
            prep.setInt(7, maSP);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
            return false;
        }
        return result;
    }*/
}
