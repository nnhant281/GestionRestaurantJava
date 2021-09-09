package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.contratModele;

public class typeContrat_DAO {
	
	 private static Connection conn = null;
	 PreparedStatement insert, delete, select, recherche;
	 private String sql;
	   
	   //INITIER LA LISTE DES CONTRATS
	    
	    public ArrayList<contratModele> InitierListeTypeContrat() {
	    	
	    	ArrayList<contratModele> listeContrat = new ArrayList<contratModele>();
	    	
	        try {
	            String sql = "SELECT * FROM type_contrat";
	            conn = ConnexionBDD.getConnect() ;	
	            select = conn.prepareStatement(sql);
	            ResultSet rs = select.executeQuery();
	                
	            while (rs.next()) {   	
	            	contratModele contrat = new contratModele();
	            	contrat.setTypeContrat(rs.getString("Type_contrat"));		            	
	            	listeContrat.add(contrat);
	            }
	            return listeContrat;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("InitierListeTypeContrat-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("InitierListeTypeContrat-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return null;
	    }
	    
	    //AJOUTE UN NOUVEAU TYPE DE CONTRAT
	    
	    public boolean ajouteTypeContrat(contratModele contrat) { 	  	
	    	 try {  	
	    		
	    		conn = ConnexionBDD.getConnect() ;	
	 			sql = "INSERT into type_contrat(Type_contrat) values (?)";
	 			insert = conn.prepareStatement(sql);
	 				
	 			insert.setString(1, contrat.getTypeContrat().toUpperCase()); 			
	 			insert.executeUpdate();			
	 			return true;
	 		} catch (SQLException e1) {
	 			e1.printStackTrace();
	 		} catch (NumberFormatException e1) {
	 			e1.printStackTrace();
	 		} finally {
	 			ConnexionBDD.getClose();
	        }
	    	return false;    	
	    }
	    
	    //SUPPRIMER D'UN EMPLOYE
	    
	    public boolean supprimerTypeContrat(String typeContrat) {	
	        try {
	        	conn = ConnexionBDD.getConnect() ;
	        	
	        	sql = "DELETE FROM type_contrat WHERE Type_contrat='"+typeContrat.toUpperCase()+"'";
				delete = conn.prepareStatement(sql);
				delete.execute();	          
	            return true;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("supprimerTypeContrat-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("supprimerTypeContrat-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        	return false;
	    }
	    
	    public boolean typeContratExiste(String typeContratRecherche) {
	    	
	    	boolean trouve = false;
	    	try {
	    		Connection cnx = ConnexionBDD.getConnect() ;	
				/*
				 * Requete select where et exécution
				 */	
				sql = "SELECT * FROM employe";
				recherche = cnx.prepareStatement(sql);
				
				ResultSet rs = recherche.executeQuery();
				
				while (rs.next() && !trouve) {									
					String typeContrat = rs.getString("Type_contrat");					
					if (typeContrat.equals(typeContratRecherche)) {
						trouve = true;										
					}	
				}
				return trouve;	
		    } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("typeContratExiste-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("typeContratExiste-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        	return false;    	
	    }
	    
	    
}
