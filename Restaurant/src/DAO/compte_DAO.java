package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Custom.ConvertTime;
import DTO.compteModele;
import DTO.employeModele;

public class compte_DAO {
	private static Connection conn = null;
	PreparedStatement insert, delete, select, update, recherche;
	private String sql;

	//GENERER LA LISTE DES COMPTES
    public ArrayList<compteModele> initierListeCompte() {
    	
    	ArrayList<compteModele> listeCompte = new ArrayList<compteModele>();
    	
        try {
            String sql = "SELECT * FROM compte_user INNER JOIN employe ON compte_user.IDRH = employe.IDRH";
            conn = ConnexionBDD.getConnect() ;	
            select = conn.prepareStatement(sql);
            ResultSet rs = select.executeQuery();
                
            while (rs.next()) {   	
            	compteModele compte = new compteModele();
            	compte.setIdrh(rs.getInt("IDRH"));
            	compte.setIdentifiant(rs.getString("Identifiant"));
            	compte.setMdp(rs.getString("Mot_de_passe"));
            	compte.setHabilitation(rs.getInt("Habilitation"));
            	compte.setNom(rs.getString("Nom"));
            	compte.setPrenom(rs.getString("Prenom"));
            	compte.setEmploi(rs.getString("Emploi"));
            	listeCompte.add(compte);
            }
            return listeCompte;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("InitierListeCompte-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("InitierListeCompte-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }   	
    	return null;
	}
    
    //INITIER LA LISTE IDRH
    
    public ArrayList<Integer> initierListeIDRH() {
    	
    	ArrayList<Integer> listeIDRH = new ArrayList<Integer>();
    	
        try {
            String sql = "SELECT * FROM employe WHERE employe.statut = 1";
            conn = ConnexionBDD.getConnect() ;	
            select = conn.prepareStatement(sql);
            ResultSet rs = select.executeQuery();
                
            while (rs.next()) {
            	int idrh = rs.getInt("IDRH");
            
            	listeIDRH.add(idrh);
            }
            return listeIDRH;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("initierListeIDRH-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("initierListeIDRH-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }
     
    //INITIER LA LISTE HABILITATION
    
    public ArrayList<Integer> initierListeHabilitation() {
    	
    	ArrayList<Integer> listeHabilitation = new ArrayList<Integer>();
    	
        try {
            String sql = "SELECT * FROM habilitation";
            conn = ConnexionBDD.getConnect() ;	
            select = conn.prepareStatement(sql);
            ResultSet rs = select.executeQuery();
                
            while (rs.next()) {
            	int codeRole = rs.getInt("Habilitation");
            
            	listeHabilitation.add(codeRole);
            }
            return listeHabilitation;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("initierListeHabilitation-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("initierListeHabilitation-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    } 
    
    
    //AJOUTE D'UN NOUVEAU COMPTE
    
    public boolean ajouteCompte(compteModele compte) { 	  	
    	 try {  	
    		
    		conn = ConnexionBDD.getConnect() ;	
 			sql = "INSERT into compte_user(IDRH, Identifiant,Mot_de_passe,Habilitation) values (?,?,?,?)";
 			insert = conn.prepareStatement(sql);
 			insert.setInt(1, compte.getIdrh());
 			insert.setString(2, compte.getIdentifiant());
 			insert.setString(3, compte.getMdp()); 			
 			insert.setInt(4, compte.getHabilitation());
 			
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
    
    //SUPPRIMER UN COMPTE
    
    public boolean supprimerCompte(int idrh) {	
        try {
        	conn = ConnexionBDD.getConnect() ;
        	
        	sql = "DELETE FROM compte_user WHERE IDRH="+idrh;
			delete = conn.prepareStatement(sql);
			delete.execute();	          
            return true;
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("supprimerCompte-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("supprimerCompte-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        	return false;
    	}
    
    //MODIFIER COMPTE
    
    public boolean modifierCompte(int idrh, compteModele compte) {	
       
    	try {
        	conn = ConnexionBDD.getConnect() ;
        	
        	sql = "UPDATE compte_user set Habilitation = ? WHERE IDRH ="+idrh;						
			update = conn.prepareStatement(sql);
			update.setInt(1, compte.getHabilitation());		
			update.execute(); 
            return true;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("modifierCompte-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("modifierCompte-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        	return false;
    	}
    
    //RECHERCHE COMPTE PAR IDENTIFIANT

    public ArrayList<compteModele> rechercherCompte(String identifiantRecherche) {
    	
    	ArrayList<compteModele> listeCompteTrouve = new ArrayList<compteModele>();
    	
    	try {
    		Connection cnx = ConnexionBDD.getConnect() ;	
			/*
			 * Requete select where et exécution
			 */	
			sql = "SELECT * FROM compte_user INNER JOIN employe ON compte_user.IDRH = employe.IDRH WHERE Identifiant like '%"+identifiantRecherche+"%'";
			recherche = cnx.prepareStatement(sql);
			
			ResultSet rs = recherche.executeQuery();
			
			while (rs.next()) {
								
				int idrh = rs.getInt("IDRH");
				String identifiant = rs.getString("Identifiant");
				String mdp = rs.getString("Mot_de_passe");
				int habilitation = rs.getInt("Habilitation");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				String emploi = rs.getString("Emploi");
						
				compteModele compte = new compteModele(idrh, identifiant, mdp, habilitation, nom, prenom, emploi);				
				listeCompteTrouve.add(compte);			
			}			
			return listeCompteTrouve;
	    	
	    } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("rechercherCompte-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("rechercherCompte-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        	return null;
    }
     
    public boolean compteAjoute(int idrhRecherche) {
    	
    	boolean trouve = false;
    	try {
    		Connection cnx = ConnexionBDD.getConnect() ;	
			/*
			 * Requete select where et exécution
			 */	
			sql = "SELECT * FROM compte_user";
			recherche = cnx.prepareStatement(sql);
			
			ResultSet rs = recherche.executeQuery();
			
			while (rs.next() && !trouve) {									
				int idrh = rs.getInt("IDRH");					
				if (idrh == idrhRecherche) {
					trouve = true;						
					return trouve;					
				}	
			}	    	
	    } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("compteAjoute-SQLException: " + e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("compteAjoute-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        	return false;    	
    }
      
}
