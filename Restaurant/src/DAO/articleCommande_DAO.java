package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.articleCommande;

public class articleCommande_DAO {
	public  articleCommande_DAO() {
		
	}
	private static Connection conn = null;
	
	/*
	 * Récupérer la liste des détail sur la commande pour afficher dans la table de détail de commande
	 * les infos besoin sont ID produit, libellé, quantité commandée et prix .
	 */
	public ArrayList<articleCommande> getListeArticleParTable(int id){
		ArrayList<articleCommande> liste = new ArrayList<articleCommande>();
		   try {
	        	conn = ConnexionBDD.getConnect() ;	
	            String sql = "SELECT produit.ID_Produit, produit.Libelle,produit_commande.Quantite, produit_commande.prix_unitaire "
	            		+ "FROM produit_commande INNER JOIN produit ON produit_commande.ID_Produit = produit.ID_Produit "
	            		+ "INNER JOIN commande ON produit_commande.ID_Commande = commande.ID_Commande "
	            		+ "WHERE commande.statut = 0 AND commande.id_table="+id;
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            while(rs.next()) {
	            	articleCommande article = new articleCommande();
	            	article.setIdArticle(rs.getInt(1));
	            	article.setLibelle(rs.getString(2));
	            	article.setQuantite(rs.getInt(3));
	            	article.setPrixUnit(rs.getFloat(4));
	                liste.add(article);
	            }
	        } catch(SQLException ex) {
	        	ex.printStackTrace();
	        	System.out.println("getListeArticleParTable-SQLException: " + ex.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("getListeArticleParTable-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return liste;
	}
	
}
