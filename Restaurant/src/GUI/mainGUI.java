package GUI;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import DTO.compteModele;


@SuppressWarnings("serial")
public class mainGUI extends javax.swing.JFrame{
	static JTabbedPane tabs;
	JPanel tabEmploye, tabMenu, tabCommande, tabClient, tabCompte, tabCA;
	static compteModele user = new compteModele();
	static employe_GUI employe = new employe_GUI();
	static compte_GUI compte = new compte_GUI();
	static client_GUI client = new client_GUI();
	static chiffreAffaire_GUI ca = new chiffreAffaire_GUI();
	static facture_GUI facture = new facture_GUI();
	static table_GUI table = new table_GUI();
	static produit_GUI produit = new produit_GUI();
	static commande_GUI commande = new commande_GUI(user);

	
	public mainGUI(compteModele user) {
		this.user = user;	
		addComponents();
		afficheModuleParHabilitation();	
	}
	
	public void addComponents() {
		tabs = new JTabbedPane(JTabbedPane.LEFT);
		this.setTitle("APPLICATION GESTION DE RESTAURANT");
		Container cprincipal = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabs.setBackground(Color.DARK_GRAY);
		Font font = new Font("Tahoma", Font.PLAIN, 18);
		tabs.setFont(font);
		
		tabs.addTab("Commande",commande);
		tabs.addTab("Table", table);
		tabs.addTab("Facture", facture);
		tabs.addTab("Client", client);
		tabs.addTab("Employ�",employe);
		tabs.addTab("Produit",produit);
		tabs.addTab("Chiffre d'affaire",ca);
		tabs.addTab("Compte",compte);
		loadPage();
		tabs.setSelectedIndex(0);
			
		cprincipal.add(tabs);
		
		

		this.pack(); // calcul de la taille adéquate		
		this.setVisible(true); // permet de faire apparaître l'interface graphique à l'écran	
		
	}
	
	public void afficheModuleParHabilitation() {
				
		if (user.getHabilitation() == 2) {			
			tabs.setEnabledAt(4, false);
			tabs.setEnabledAt(5, false);
			tabs.setEnabledAt(6, false);
			tabs.setEnabledAt(7, false);		
		}
	}
	
	/*
	 * recharger la frame qui contient tous les paneaux 
	 */
	public static void loadPage() {
		employe = new employe_GUI();
		compte = new compte_GUI();
		client = new client_GUI();
		ca = new chiffreAffaire_GUI();
		facture = new facture_GUI();
		table = new table_GUI();
		produit = new produit_GUI();
		commande = new commande_GUI(user);	
		
		ArrayList<String> title = new ArrayList<String>();
		title.add("Commande");
		title.add("Table");
		title.add("Facture");
		title.add("Client");
		title.add("Employ�");
		title.add("Produit");
		title.add("Chiffre d'affaire");
		title.add("Compte");
		
		ArrayList<Component> pn = new ArrayList<Component>();
		pn.add(commande);
		pn.add(table);
		pn.add(facture);
		pn.add(client);
		pn.add(employe);
		pn.add(produit);
		pn.add(ca);
		pn.add(compte);
		int indx = tabs.getSelectedIndex();
		int size = tabs.getTabCount();
		tabs.removeAll();
		for (int i = 0 ; i < size ;i++) {
			/*if(i != indx ) {*/
				tabs.addTab(title.get(i),pn.get(i));
			/*}else {
				
				tabs.addTab(title.get(i),tabs.getSelectedComponent());
			}*/
		}

		tabs.setSelectedIndex(indx);
		
	}
}
