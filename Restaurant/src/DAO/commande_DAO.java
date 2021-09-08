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
        System.out.print(listeCommande);
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
                return listeFacture;
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
        return null;
        
    }
	
	
	/*
	 * ajouter une commande dans la base de données
	 */
    public boolean addCommande(commande cmd) {
        boolean result = false;
        try {
        	conn = ConnexionBDD.getConnect() ;	
        	Statement st = conn.createStatement();
        	String sql = "INSERT INTO commande(IDRH,id_client, id_table, Date, Statut,Type_Commande, Total_TTC) VALUES(?, ?, ?, ?, ?, ?,?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, cmd.getIDRH());
            prep.setInt(2, cmd.getIdClient());
            prep.setInt(3, cmd.getIdTable());
            prep.setDate(4, (Date) cmd.getDate());
            prep.setInt(5, cmd.getStatut());
            prep.setInt(6, cmd.getTypeCommande());
            prep.setFloat(7, cmd.getTotal());
            /*String sql1 = "UPDATE client SET point+=" + (int)cmd.getTotal() + " WHERE ID_client=" + cmd.getIdClient();
            Statement st = conn.createStatement();
            st.executeUpdate(sql1);
            String sql = "INSERT INTO commande(IDRH, id_client,id_table, Date, Statut,Type_Commande, Total_TTC) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, cmd.getIDRH());
            prep.setInt(2, cmd.getIdClient());
            prep.setInt(3, cmd.getIdTable());
            prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(5, cmd.getStatut());
            prep.setInt(6, cmd.getTypeCommande());
            prep.setFloat(6, cmd.getTotal());*/
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
    
    public ArrayList<commande> getListFacture(Date dateMin, Date dateMax) {
        try {
        	conn = ConnexionBDD.getConnect() ;	
            String sql = "SELECT * FROM commande WHERE Date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();

            ArrayList<commande> listeCommande = new ArrayList<>();

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
            return listeCommande;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.out.println("addCommande-SQLException: " + ex.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println("addCommande-Exception: " + e.getMessage());
		}finally {
        	ConnexionBDD.getClose();
        }
        return null;
    }
    
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
