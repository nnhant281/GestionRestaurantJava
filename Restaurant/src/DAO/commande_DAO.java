package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.commande;
import DTO.detailCommande;

public class commande_DAO {
	private static Connection conn = null;
	
    /*
     * récupérer la liste de commande de la base de données
     */
	public ArrayList<commande> getListeCommandeEnCour() {
		
        ArrayList<commande> listeCommande = new ArrayList<>(0);
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM commande WHERE statut=0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                commande cmd = new commande();
                cmd.setIdCommande(rs.getInt(1));
                cmd.setIDRH(rs.getInt(2));
                cmd.setIdClient(rs.getInt(3));
                cmd.setIdTable(rs.getInt(4));
                cmd.setDate(rs.getDate(5));
                cmd.setStatut(rs.getInt(6));
                cmd.setTypeCommande(rs.getInt(7));
                cmd.setTotal(rs.getFloat(8));
                listeCommande.add(cmd);
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("GetListeCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetListeCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return listeCommande;
    }
	
	/*
     * récupérer la liste de commande de la base de données
     */
	public int getCommandeEnCourParTable(int idTable) {
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM commande WHERE statut=0 AND id_table = " + idTable;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("getCommandeEnCourParTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("getCommandeEnCourParTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return -1;
    }
	
	/*
     * récupérer la commande 
     */
	public commande getCommandeParIDCommande(int idCmd) {
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM commande WHERE ID_Commande = " + idCmd;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	 commande cmd = new commande();
                 cmd.setIdCommande(rs.getInt(1));
                 cmd.setIDRH(rs.getInt(2));
                 cmd.setIdClient(rs.getInt(3));
                 cmd.setIdTable(rs.getInt(4));
                 cmd.setDate(rs.getDate(5));
                 cmd.setStatut(rs.getInt(6));
                 cmd.setTypeCommande(rs.getInt(7));
                 cmd.setTotal(rs.getFloat(8));
                 return cmd;
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("getCommandeEnCourParTable-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("getCommandeEnCourParTable-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }
	
	/*
     * récupérer la liste de commande de la base de données
     */
	public ArrayList<commande> getListeFacture() {
		
        ArrayList<commande> listeFacture = new ArrayList<>();
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM commande WHERE statut=1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                commande cmd = new commande();
                cmd.setIdCommande(rs.getInt(1));
                cmd.setIDRH(rs.getInt(2));
                cmd.setIdClient(rs.getInt(3));
                cmd.setIdTable(rs.getInt(4));
                cmd.setDate(rs.getDate(5));
                cmd.setStatut(rs.getInt(6));
                cmd.setTypeCommande(rs.getInt(7));
                cmd.setTotal(rs.getFloat(8));
                listeFacture.add(cmd);
            }
            return listeFacture;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("GetListeCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("GetListeCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
        
    }
	
	
	/*
	 * ajouter une commande dans la base de données
	 */
    public boolean addCommande(commande cmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	String sql = "INSERT INTO commande(IDRH,id_client, id_table, Date, Statut,Type_Commande, Total_TTC) VALUES(?, ?, ?, ?, ?, ?,?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, cmd.getIDRH());
            prep.setInt(2, cmd.getIdClient());
            prep.setInt(3, cmd.getIdTable());
            prep.setDate(4, (Date) cmd.getDate());
            prep.setInt(5, cmd.getStatut());
            prep.setInt(6, cmd.getTypeCommande());
            prep.setFloat(7, cmd.getTotal());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("addCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("addCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }

    /*
     * recherche le ID de la dernière commande 
     * on en a besoins dans le cas une nouvelle commande est créée et le ID est inconnu
     */
    public int getIdDerniereCommande() {
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT MAX(ID_Commande) FROM commande";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    /*
     * chercher le ID de la commande en cours qui est attachée d'une table 
     * une table est liée à plusieurs commandes mais il y a une maximum une seule commande en cours
     */
    public int getUncheckBillIDByTableID(int id){
    	conn = ConnexionBDD.getConnect() ;	
        String sql = "SELECT * FROM commande WHERE statut = 0 AND id_table = "+id;
        Statement st;
		try {
			st = conn.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next())
	        return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return -1;
    }

    /*
     * mise à jour le montant de la commande
     * soit ajouter quand les clients commandent les articles 
     * soit diminuer lorsque les articles sont annulés
     */
    public boolean majMontantCommande(int idCmd, float prix) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE commande SET Total_TTC = Total_TTC + ? WHERE ID_Commande=? ";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setFloat(1, prix);
            prep.setInt(2, idCmd);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("majMontantCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("majMontantCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * les commandes payées sont aussi les factures 
     */
    public boolean commandePayee(int idCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE commande SET statut = 1 WHERE ID_Commande=? ";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, idCmd);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("commandePayee-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("commandePayee-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * supprimer la commande 
     */
    public boolean supprimerCommande(int idCmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "DELETE commande WHERE Total_TTC = 0.0 AND ID_Commande=? ";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, idCmd);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("commandePayee-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("commandePayee-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
    
    /*
     * attacher un client à la commande 
     * ça aide à ajouter les points au client
     */
    public boolean saisieIdClientALaCommande(int idCmd, int idClient) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "UPDATE commande SET id_client = ? WHERE ID_Commande=? ";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, idClient);
            prep.setInt(2, idCmd);
            result = prep.executeUpdate() > 0;
        } catch(SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("saisieIDClientALaCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("saisieIDClientALaCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return result;
    }
}
