package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import BUS.categorie_BUS;
import BUS.chiffreAffaire_BUS;
import Custom.ConvertStringToInt;
import Custom.monDialogue;
import Custom.monTableau;
import DAO.chiffreAffaireMoisAnModele_DAO;
import DAO.classement_DAO;
import DAO.employe_DAO;
import DTO.chiffreAffaireMoisAnModele;
import DTO.classementModele;

@SuppressWarnings("serial")
public class chiffreAffaire_GUI extends JPanel{
	
	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	Font vueGeneral = new Font("Tahoma", Font.PLAIN, 18);
	
	JPanel panelTitre, panelTable, panelGraphe, panelCategorie, panelBottom, panelAnnee, panelChart, panelTitreTable, panelCenter;
	JPanel panelEmploye, panelClient, panelMenu, panelVueGeneral;
	JLabel labelEmploye, labelClient, labelMenu;
	JTable table;
	DefaultTableModel model;
	JScrollPane pane;
	JComboBox<String> choixCategorie;
	JComboBox<Integer> choixAnnee;
	JLabel categorie, titre, annee, titreTable;
	chiffreAffaire_BUS chiffreAffaireBUS = new chiffreAffaire_BUS();
	static categorie_BUS categorieBUS = new categorie_BUS();
	classement_DAO classementDAO = new classement_DAO();
	
	ArrayList<classementModele> listeClassementParCategorie = new ArrayList<classementModele>();
	ArrayList<chiffreAffaireMoisAnModele> listeChiffreAffaireMoisAn = new ArrayList<chiffreAffaireMoisAnModele> ();
	chiffreAffaireMoisAnModele_DAO chiffreAffaireMoisAnModeleDAO = new chiffreAffaireMoisAnModele_DAO();
	ChartPanel chart;
	
	public chiffreAffaire_GUI() {		
		addComponents();
		addEvents();		
	}
	
	public void addComponents() {
		/*
		============================================================
           				CREATION DES COMPOSANTS
		============================================================
		 */
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout());
		panelVueGeneral = new JPanel();
		panelCenter.add(panelVueGeneral);
		panelVueGeneral.setLayout(new GridLayout(1,3));
		panelEmploye = new JPanel();
		panelClient = new JPanel();
		panelMenu = new JPanel();
		
		
		
		panelVueGeneral.add(panelEmploye);
		panelVueGeneral.add(panelClient);
		panelVueGeneral.add(panelMenu);
		panelEmploye.setLayout(new GridBagLayout());
		panelClient.setLayout(new GridBagLayout());
		panelMenu.setLayout(new GridBagLayout());
			
		getNbEmployes();
		getNbPlats();
		getNbClients();
		panelEmploye.add(labelEmploye);
		panelClient.add(labelClient);
		panelMenu.add(labelMenu);
	
		
		panelBottom = new JPanel();
		panelBottom.setLayout(new GridLayout(1,0));
			
		panelTable = new JPanel();
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));
		panelTitreTable = new JPanel();
		titreTable = new JLabel("Top 5 des ventes");
		titreTable.setFont(font);
		panelTitreTable.add(titreTable);
		
		panelGraphe = new JPanel();
		panelGraphe.setLayout(new BoxLayout(panelGraphe, BoxLayout.Y_AXIS));
		
		panelChart = new JPanel();
		panelChart.setLayout(new BorderLayout());
				
		panelTitre = new JPanel();
		titre = new JLabel("<HTML><H1>Dashboard<H1><HTML>");
		panelTitre.add(titre);
		titre.setFont(font);
			
		panelCategorie = new JPanel();
		panelCategorie.setLayout(new FlowLayout());
		categorie = new JLabel("Categorie");		
		categorie.setFont(font);
		choixCategorie = new JComboBox<String>();
		choixCategorie.setFont(font);
		loadCategorie();
				
		panelCategorie.add(categorie);	
		panelCategorie.add(choixCategorie);
				
		panelAnnee = new JPanel();
		panelAnnee.setLayout(new FlowLayout());
		
		choixAnnee= new JComboBox<Integer>();
		choixAnnee.setFont(font);
		genererCalendar();
		
		annee = new JLabel("Annee");
		annee.setFont(font);
		
		panelAnnee.add(annee);
		panelAnnee.add(choixAnnee);			
		/*
		============================================================
           					CREATION DE TABLE
		============================================================
		 */		
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"Top", "Libelle produit", "Quantite"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
	
		pane = new JScrollPane(table);		
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		/*
		============================================================
           				ARRANGEMENT DES COMPOSANTS
		============================================================
		 */				
		panelTable.add(panelCategorie);
		panelTable.add(panelTitreTable);
		panelTable.add(pane);
		panelGraphe.add(panelAnnee);
		dessinerChart();
				
		panelBottom.add(panelTable);
		panelBottom.add(panelGraphe);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		panelTitre.setOpaque(true);
		panelTitre.setBackground(new Color(250, 240, 230));
		panelEmploye.setOpaque(true);
		panelEmploye.setBackground(new Color(178, 211, 225));
		panelClient.setOpaque(true);
		panelClient.setBackground(new Color(231, 206, 191));
		panelMenu.setOpaque(true);
		panelMenu.setBackground(new Color(178, 211, 225));
	
		this.add(panelTitre);
		this.add(panelCenter);
		this.add(panelBottom);
		
		
		
		/*
		============================================================
           						DIVERS
		============================================================
		 */			
		Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
		
		panelTable.setBorder(blackline);
		panelGraphe.setBorder(blackline);
		
	}
	
	public void addEvents() {
		choixCategorie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTabClassement(choixCategorie.getSelectedItem().toString());							
			}	
		});	
		choixAnnee.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dessinerChart();				
			}	
		});		
	}
	/*
	============================================================
       			CHARGER LA TABLE DE CLASSEMENT
	============================================================
	 */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadTabClassement(String categorie) {
		
		model.setRowCount(0);		
		ArrayList<classementModele> listeClassementParCategorie = chiffreAffaireBUS.getNouveauListeClassement(categorie);
		
		if (choixCategorie.getSelectedItem() != null) {
			if (listeClassementParCategorie.size() == 0) {
				new monDialogue("Il y aucun produit de cette catégorie a été vendu", monDialogue.INFO_DIALOG);
				choixCategorie.setSelectedIndex(0);				
			}
			else {		
				if (listeClassementParCategorie.size() >=5) {
					for (int i = 0; i <=4; i++) {
			            Vector vec = new Vector();
			            vec.add(i+1);	            
			            vec.add(listeClassementParCategorie.get(i).getProduit());
			            vec.add(listeClassementParCategorie.get(i).getQuantite());                    
			           	model.addRow(vec); 	  		     
					}						
				}
				else {
					for (int i = 0; i <= listeClassementParCategorie.size() -1 ; i++) {
			            Vector vec = new Vector();
			            vec.add(i+1);	            
			            vec.add(listeClassementParCategorie.get(i).getProduit());
			            vec.add(listeClassementParCategorie.get(i).getQuantite());                    
			           	model.addRow(vec); 	  				
					}
				}		
			}		
		}	
	}
	
	private void loadCategorie() {
		 choixCategorie.removeAllItems();
	     ArrayList<String> listeCategorie = categorieBUS.getListeCategorie();
	     choixCategorie.addItem("");
	     for (String cate : listeCategorie) {
	    	 choixCategorie.addItem(cate);
	     }	       
	 }
	 	 
	 private void dessinerChart() {	 
		 panelChart.removeAll();
		 chart = new ChartPanel(createChart());
		 panelChart.add(chart);
		 panelGraphe.add(panelChart);	       	             
	 }

	 private JFreeChart createChart() {
		 		JFreeChart barChart = ChartFactory.createBarChart("Chiffre d'affaire "
				 +ConvertStringToInt.convert(choixAnnee.getSelectedItem().toString()),
	                "Mois", "Chiffre d'affaire",
	                createDataset(), PlotOrientation.VERTICAL, false, false, false);
		 return barChart;	       
	 }
	 
	 
	 /*
	  * GENERER UN DATA SET POUR ALIMENTER LE GRAPHE
	  * DANS LE CAS OU LE MOIS CHOISI N'A AUCUNE VENTE, CHIFFRE AFFAIRE = 0 POUR CE MOIS
	  */
	 private CategoryDataset createDataset() {
		 
		 final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 
	     	for (int i = 1; i <= 12; i++) {
	        	listeChiffreAffaireMoisAn = chiffreAffaireMoisAnModeleDAO.getListeChiffreAffaireMoisAn(i, ConvertStringToInt.convert(choixAnnee.getSelectedItem().toString()));
	        	/*
	        	 * SI AUCUN RESULTAT (LISTE CHIFFRE AFFAIRE MOIS AN == NULL), AJOUTER DANS CETTE LISTE UN OBJET QUI CONTIENT (MOI, ANNEE, CHIFFRE AFFAIRE =0)
	        	 */	        	
	        	if (listeChiffreAffaireMoisAn.size()== 0) {
	        		chiffreAffaireMoisAnModele chiffreAffaireMoisAn = new chiffreAffaireMoisAnModele(i, ConvertStringToInt.convert(choixAnnee.getSelectedItem().toString()), 0);
	        		listeChiffreAffaireMoisAn.add(chiffreAffaireMoisAn);
	        	}
	            dataset.addValue(listeChiffreAffaireMoisAn.get(0).getCa(), "Chiffre d'affaire", i + "");
	        }
	        return dataset;	       
	 }

	 public void genererCalendar() {
		  int year = Calendar.getInstance().get(Calendar.YEAR);		  
		  // JCOMBOBOX DE 10 ANS
		  for (int i = year; i >= year - 10; i--) {
	        	choixAnnee.addItem(i);
	      }	         	                
	 }
	 
	 public void getNbEmployes() {
		 int nbEmploye = classementDAO.getNombreEmploye();
		 labelEmploye = new JLabel(nbEmploye+" Employé(s)");
		 labelEmploye.setFont(font);
	 }
	 
	 public void getNbClients() {
		 int nbClient = classementDAO.getNombreClient();
		 labelClient = new JLabel(nbClient+" Client(s)");
		 labelClient.setFont(font);
	 }
	 
	 public void getNbPlats() {
		 int nbPlats = classementDAO.getNombreArticles();
		 labelMenu = new JLabel(nbPlats+" articles dans le Menu");
		 labelMenu.setFont(font);
	 }	 
}
