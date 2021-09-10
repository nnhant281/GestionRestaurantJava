/*
 * classe de connexion � la base de donn�es 
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	/*
	 * les variables pour la connexion 
	 * classname
	 * url de la base de données
	 * compte utilisateur
	 * mot de passe
	 */
	private final static String classname = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/gestion_restaurant";
    private final static String user = "root";
    private final static String mdp = "";
    
    private static Connection connexion;
    
    /*
     * connexion à la base de données
     */
    public static Connection getConnect() {
        try {
            Class.forName(classname);
            connexion = (Connection) DriverManager.getConnection(url, user, mdp);
        } catch (SQLException ex) {
        	ex.printStackTrace();
			System.out.println("connexion �chue !");
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
			System.out.println("connexion �chue !");
        }
        return connexion;
    }
    
    /*
     * fermer la connexion
     */
    public static void getClose(){
        try {
            connexion.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }
    

}
