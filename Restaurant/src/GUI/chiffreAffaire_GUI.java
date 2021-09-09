package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JOptionPane;
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
import DTO.chiffreAffaireMoisAnModele;
import DTO.classementModele;
import DTO.compteModele;

@SuppressWarnings("serial")
public class chiffreAffaire_GUI extends JPanel{
	
	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	
	JPanel panelTitre, panelTable, panelGraphe, panelCategorie, panelBottom, panelAnnee, panelChart;
	JTable table;
	DefaultTableModel model;
	JScrollPane pane;
	JComboBox<String> choixCategorie;
	JComboBox<Integer> choixAnnee;
	JLabel categorie, titre, annee;
	chiffreAffaire_BUS chiffreAffaireBUS = new chiffreAffaire_BUS();
	static categorie_BUS categorieBUS = new categorie_BUS();
	
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
		
		panelBottom = new JPanel();
		panelBottom.setLayout(new GridLayout(1,0));
		
		
		panelTable = new JPanel();
		panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));
		
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
           					CREATION DE TABLE
		============================================================
		 */
		
		
		panelTable.add(panelCategorie);
		panelTable.add(pane);
		panelGraphe.add(panelAnnee);
		dessinerChart();
		
		
		panelBottom.add(panelTable);
		panelBottom.add(panelGraphe);
	

		this.setLayout(new BorderLayout());
		this.add(panelTitre, BorderLayout.NORTH);
		this.add(panelBottom, BorderLayout.SOUTH);
		
		Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
		panelTitre.setBorder(blackline);
		panelCategorie.setBorder(blackline);
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadTabClassement(String categorie) {
		model.setRowCount(0);
		
		ArrayList<classementModele> listeClassementParCategorie = chiffreAffaireBUS.getNouveauListeClassement(categorie);
		
		if (choixCategorie.getSelectedItem() != null) {
			if (listeClassementParCategorie.size() == 0) {
				new monDialogue("Il y aucun produit de ce catégorie a été vendu", monDialogue.INFO_DIALOG);
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
		 panelChart.setPreferredSize(new Dimension(800, 441));
		 panelGraphe.add(panelChart);
	       	             
	 }

	    private JFreeChart createChart() {
	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Chiffre d'affaire "+ConvertStringToInt.convert(choixAnnee.getSelectedItem().toString()),
	                "Mois", "Chiffre d'affaire",
	                createDataset(), PlotOrientation.VERTICAL, false, false, false);
	        return barChart;
	    }

	    private CategoryDataset createDataset() {
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        
	        
	        for (int i = 1; i <= 12; i++) {
	        	listeChiffreAffaireMoisAn = chiffreAffaireMoisAnModeleDAO.getListeChiffreAffaireMoisAn(i, ConvertStringToInt.convert(choixAnnee.getSelectedItem().toString()));
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
	        for (int i = year; i >= year - 10; i--) {
	        	choixAnnee.addItem(i);
	        }	            
	 }
	    
	
	
		

}
