package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import BUS.categorie_BUS;
import BUS.chiffreAffaire_BUS;
import Custom.monDialogue;
import Custom.monTableau;
import DTO.classementModele;
import DTO.compteModele;

@SuppressWarnings("serial")
public class chiffreAffaire_GUI extends JPanel{
	
	
	JPanel panelTitre, panelTable, panelGraphe, panelCategorie;
	JTable table;
	DefaultTableModel model;
	JScrollPane pane;
	JComboBox<String> choixCategorie;
	JLabel categorie, titre;
	chiffreAffaire_BUS chiffreAffaireBUS;
	static categorie_BUS categorieBUS = new categorie_BUS();
	
	
	
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
		panelTable = new JPanel();
		panelGraphe = new JPanel();
		
		panelTitre = new JPanel();
		titre = new JLabel("Dashboard");
		
		panelCategorie = new JPanel();
		panelCategorie.setLayout(new FlowLayout());
		categorie = new JLabel("Categorie");		
		choixCategorie = new JComboBox<String>();
		loadCategorie();
		
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
		panelTable.setPreferredSize(new Dimension(300, 300));
		panelTable.add(pane);
		
		
		
	

		/*
		============================================================
           					CREATION DE TABLE
		============================================================
		 */
		
		panelTitre.add(titre);
		
		panelCategorie.add(categorie);	
		panelCategorie.add(choixCategorie);
		
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(panelTitre);
		this.add(panelCategorie);
		this.add(panelTable);
		
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
				if (choixCategorie.getSelectedItem() !=null) {
					chiffreAffaireBUS = new chiffreAffaire_BUS(choixCategorie.getSelectedItem().toString());
					loadTabClassement(choixCategorie.getSelectedItem().toString());
				}
			
	        }	
		});
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadTabClassement(String categorie) {
		model.setRowCount(0);
		
		ArrayList<classementModele> listeClassementParCategorie = chiffreAffaireBUS.getNouveauListeClassement();
		
		if (listeClassementParCategorie.size() == 0) {
			new monDialogue("Il y aucun produit de ce catégorie a été vendu", monDialogue.INFO_DIALOG);
			
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
	
	 private void loadCategorie() {
	        choixCategorie.removeAllItems();

	        ArrayList<String> listeCategorie = categorieBUS.getListeCategorie();
	        choixCategorie.addItem("");
	        for (String cate : listeCategorie) {
	            choixCategorie.addItem(cate);
	        }
	    }

	
		

}
