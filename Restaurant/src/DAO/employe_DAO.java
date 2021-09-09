package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Custom.ConvertTime;
import DTO.employeModele;



public class employe_DAO {
	
	 private static Connection conn = null;
	 PreparedStatement insert, delete, select, update, recherche;
	 private String sql;
	  
	   //INITIER LA LISTE DES EMPLOYES
	    
	    public ArrayList<employeModele> InitierListeEmploye() {
	    	
	    	ArrayList<employeModele> listeEmploye = new ArrayList<employeModele>();
	    	
	        try {
	            String sql = "SELECT * FROM employe WHERE statut = 1 ";
	            conn = ConnexionBDD.getConnect() ;	
	            select = conn.prepareStatement(sql);
	            ResultSet rs = select.executeQuery();
	                
	            while (rs.next()) {   	
	            	employeModele employe = new employeModele();
	            	employe.setIDRH(rs.getInt(1));	
	            	employe.setNom(rs.getString(2));    	
	            	employe.setPrenom(rs.getString(3));	            	
	            	employe.setDateNaissance(rs.getDate(4));	            	
	            	employe.setAdresse(rs.getString(5));	            	
	            	employe.setTel(rs.getString(6));	            	
	            	employe.setTypeContrat(rs.getString(7));	            	
	            	employe.setDateDebut(rs.getDate(8));	            	
	            	employe.setDateFin(rs.getDate(9));	            	
	            	employe.setDureeHebdo(rs.getString(10));	            	
	            	employe.setEmploi(rs.getString(11));
	            	employe.setStatut(rs.getInt(12));
	            	listeEmploye.add(employe);
	            }
	            return listeEmploye;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("InitierListeEmploye-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("InitierListeEmploye-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        return null;
	    }
	    
	    	
	    //AJOUTE D'UN NOUVEAU EMPLOYE
	    
	    public boolean ajouteEmploye(employeModele employe) { 	  	
	    	 try {  	
	    		java.sql.Date dateNaissance = ConvertTime.convert(employe.getDateNaissance());
	    		java.sql.Date dateDebut = ConvertTime.convert(employe.getDateDebut());
	    		java.sql.Date dateFin = ConvertTime.convert(employe.getDateFin());
	    		
	    		conn = ConnexionBDD.getConnect() ;	
	 			sql = "INSERT into employe(Nom,Prenom,Date_naissance,Adresse,Num_tel,Type_contrat,Date_debut_contrat,Date_fin_contrat,Duree_hebdomadaire,Emploi) values (?,?,?,?,?,?,?,?,?,?)";
	 			insert = conn.prepareStatement(sql);
	 				
	 			insert.setString(1, employe.getNom().toUpperCase());
	 			insert.setString(2, employe.getPrenom().toUpperCase()); 			
	 			insert.setDate(3, dateNaissance);
	 			insert.setString(4, employe.getAdresse().toUpperCase());
	 			insert.setString(5, employe.getTel());
	 			insert.setString(6, employe.getTypeContrat());
	 			insert.setDate(7, dateDebut);
	 			insert.setDate(8, dateFin);		
	 			insert.setString(9, employe.getDureeHebdo());
	 			insert.setString(10, employe.getEmploi());		
	 			insert.setInt(11,employe.getStatut());
	 			
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
	    
	    public boolean supprimerEmploye(int idrh) {	
	        try {
	        	conn = ConnexionBDD.getConnect() ;
	        	
	        	sql = "DELETE FROM employe WHERE IDRH="+idrh;
				delete = conn.prepareStatement(sql);
				delete.execute();	          
	            return true;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("supprimerEmploye-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("supprimerEmploye-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        	return false;
	    	}
	    
	    //MODIFIER EMPLOYE
	    
	    public boolean modifierEmploye(int idrh, employeModele employe) {	
	        
	    	java.sql.Date dateNaissance = ConvertTime.convert(employe.getDateNaissance());
    		java.sql.Date dateDebut = ConvertTime.convert(employe.getDateDebut());
    		java.sql.Date dateFin = ConvertTime.convert(employe.getDateFin());
	    	try {
	        	conn = ConnexionBDD.getConnect() ;
	        	
	        	sql = "UPDATE employe set Nom = ? , Prenom = ? , Date_naissance = ? , Adresse = ? , Num_tel = ? , Type_contrat = ? , Date_debut_contrat  = ? , Date_fin_contrat = ?, Duree_hebdomadaire = ?, Emploi  = ? WHERE IDRH ="+idrh;						
				update = conn.prepareStatement(sql);

				update.setString(1, employe.getNom());
				update.setString(2, employe.getPrenom());
				update.setDate(3, dateNaissance);
				update.setString(4, employe.getAdresse());
				update.setString(5, employe.getTel());
				update.setString(6, employe.getTypeContrat());
				update.setDate(7, dateDebut);
				update.setDate(8, dateFin);
				update.setString(9, employe.getDureeHebdo());
				update.setString(10, employe.getEmploi());
				
				update.execute(); 
	            return true;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("modifierEmploye-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("modifierEmploye-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        	return false;
	    	}
	    
	    //RECHERCHE EMPLOYE PAR SON NOM
	    
	    public ArrayList<employeModele> rechercherEmploye(String nomRecherche) {
	    	
	    	ArrayList<employeModele> listeEmployeTrouve = new ArrayList<employeModele>();
	    	
	    	try {
	    		Connection cnx = ConnexionBDD.getConnect() ;	
				/*
				 * Requete select where et exécution
				 */	
				sql = "SELECT * FROM employe WHERE Nom like '%"+nomRecherche+"%'";
				recherche = cnx.prepareStatement(sql);
				
				ResultSet rs = recherche.executeQuery();
				
				while (rs.next()) {		
					int idrh = rs.getInt("IDRH");		
					String nom = rs.getString("Nom");
					String prenom = rs.getString("Prenom");
					Date dateNaissance = rs.getDate("Date_naissance");
					String adresse = rs.getString("Adresse");				
					String typeContrat = rs.getString("Type_contrat");
					Date dateDebut = rs.getDate("Date_debut_contrat");
					Date dateFin = rs.getDate("Date_fin_contrat");
					String emploi = rs.getString("Emploi");
					String tel = rs.getString("Num_tel");
					String dureeHebdo = rs.getString("Duree_hebdomadaire");
					
					employeModele employe = new employeModele(idrh, nom, prenom, dateNaissance, adresse, tel, 
							typeContrat, dateDebut, dateFin, dureeHebdo, emploi);
					
					listeEmployeTrouve.add(employe);			
				}
				
				return listeEmployeTrouve;
		    	
		    } catch (SQLException e) {
	        	e.printStackTrace();
	        	System.out.println("rechercherEmploye-SQLException: " + e.getMessage());
	        } catch (Exception e) {
				e.printStackTrace();
				System.out.println("rechercherEmploye-Exception: " + e.getMessage());
			}finally {
	        	ConnexionBDD.getClose();
	        }
	        	return null;
	    	}	        		    
}
