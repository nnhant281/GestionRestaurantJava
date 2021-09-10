package GUI;

import static Main.main.changeLNF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.compte_BUS;
import BUS.employe_BUS;
import Custom.ConvertStringToInt;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import DTO.compteModele;
import DTO.employeModele;


@SuppressWarnings("serial")
public class compte_GUI extends JPanel {
	
	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	final Color colorPanel = new Color(250, 240, 230);
	
	ArrayList<JButton> listButtons = new ArrayList<JButton>();
	
	JPanel panelTop, panelCenter;
	
	JPanel panelRemplir, panelButtons, panelRecherche;
	
	JLabel idrh, identifiant, habilitation, titre, nom, prenom, emploi;
	JTextField txtIdentifiant, txtNom, txtPrenom, txtEmploi;
	JComboBox<Integer> choixIDRH = new JComboBox<Integer>();	
	JComboBox<Integer> choixHabilitation = new JComboBox<Integer>();
	
	monButton btnAjoute, btnMod, btnSupp, btnReset;	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset;
	
	JButton btnRecherche;
	JTextField recherche;
	
	monTableau table;
	DefaultTableModel model;
	JScrollPane pane;
	
	compte_BUS compteBUS = new compte_BUS();
	employe_BUS employeBUS = new employe_BUS();
	
	
	public compte_GUI() {
		changeLNF("Nimbus");
		addComponents();
		addEvents();

	}
	
	public void addComponents() {
	
		panelTop = new JPanel();
		panelCenter = new JPanel();
		this.add(panelTop);
		this.add(panelCenter);
		this.setOpaque(true);
		this.setBackground(new Color(250, 240, 230));
		
		panelRemplir = new JPanel();
		panelButtons = new JPanel();
		panelRecherche = new JPanel();
		panelRemplir.setOpaque(true);
		panelRemplir.setBackground(new Color(250, 240, 230));
		panelButtons.setOpaque(true);
		panelButtons.setBackground(new Color(250, 240, 230));
		panelRecherche.setOpaque(true);
		panelRecherche.setBackground(new Color(250, 240, 230));
		panelCenter.setOpaque(true);
		panelCenter.setBackground(new Color(250, 240, 230));
	
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelRemplir);
		panelCenter.add(panelButtons);
		panelCenter.add(panelRecherche);
		
		panelRemplir.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		
		/*
		============================================================
           		ARRANGEMENT DES COMPOSANTS DANS LE PANEL REMPLIR
		============================================================
		 */
		
		titre = new JLabel("<HTML><H1>Gestion de compte<H1><HTML>");
		idrh = new JLabel("IDRH");
		identifiant = new JLabel("Identifiant");
		habilitation = new JLabel("Habilitation");
		nom = new JLabel("Nom");
		prenom = new JLabel("Prenom");
		emploi = new JLabel("Emploi");
		
		titre.setFont(font);
		idrh.setFont(font);
		identifiant.setFont(font);
		habilitation.setFont(font);
		nom.setFont(font);
		prenom.setFont(font);
		emploi.setFont(font);
		
		txtIdentifiant = new JTextField("", 15);
		txtIdentifiant.setEditable(false);
		txtIdentifiant.setBackground(Color.gray);

		txtNom = new JTextField("", 15);
		txtNom.setEditable(false);
		txtNom.setBackground(Color.gray);
		
		txtPrenom = new JTextField("", 15);
		txtPrenom.setEditable(false);
		txtPrenom.setBackground(Color.gray);
		
		txtEmploi = new JTextField("", 15);
		txtEmploi.setEditable(false);
		txtEmploi.setBackground(Color.gray);
		
		txtIdentifiant.setFont(font);
		txtNom.setFont(font);
		txtPrenom.setFont(font);
		txtEmploi.setFont(font);
		choixHabilitation.setFont(font);
		choixIDRH.setFont(font);
		
		choixIDRH.setPreferredSize(txtNom.getPreferredSize());
		txtEmploi.setPreferredSize(txtNom.getPreferredSize());
		choixHabilitation.setPreferredSize(txtNom.getPreferredSize());

		
		gc.gridx = 2;
		gc.gridy = 0;
		panelRemplir.add(titre, gc);
	
		gc.gridx = 1;
		gc.gridy = 1;
		panelRemplir.add(idrh, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		panelRemplir.add(choixIDRH, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		panelRemplir.add(habilitation, gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		panelRemplir.add(choixHabilitation, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		panelRemplir.add(nom, gc);
		
		gc.gridx = 2;
		gc.gridy = 3;
		panelRemplir.add(txtNom, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		panelRemplir.add(prenom, gc);
		
		gc.gridx = 2;
		gc.gridy = 4;
		panelRemplir.add(txtPrenom, gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		panelRemplir.add(identifiant, gc);
		
		gc.gridx = 2;
		gc.gridy = 5;
		panelRemplir.add(txtIdentifiant, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		panelRemplir.add(emploi, gc);
		
		gc.gridx = 2;
		gc.gridy = 6;
		panelRemplir.add(txtEmploi, gc);	
		/*
		============================================================
            				CREATION DES BUTTONS
		============================================================
		 */
		
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconRechercher = new ImageIcon(new ImageIcon("images/Buttons/recherche.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnMod = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnReset = new monButton("Réinitialiser", iconReset);
		
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnAjoute);
		panelButtons.add(btnMod);
		panelButtons.add(btnSupp);
		panelButtons.add(btnReset);
		
		listButtons.add(btnAjoute);
		listButtons.add(btnMod);
		listButtons.add(btnSupp);
		listButtons.add(btnReset);
			
		btnRecherche = new JButton(iconRechercher);
		recherche = new JTextField("Recherche par identifiant...", 20);
		recherche.setForeground(Color.GRAY);
		panelRecherche.add(recherche);
		panelRecherche.add(btnRecherche);
		
		btnAjoute.setFont(fontButton);	
		btnMod.setFont(fontButton);
		btnSupp.setFont(fontButton);
		btnReset.setFont(fontButton);
		btnRecherche.setFont(fontButton);
		
		Dimension btnSize = new Dimension(140,35);
        btnAjoute.setPreferredSize(btnSize);
        btnMod.setPreferredSize(btnSize);
        btnSupp.setPreferredSize(btnSize);
        btnRecherche.setPreferredSize(btnSize);
        btnReset.setPreferredSize(btnSize);
	        
		/*
		============================================================
           					CREATION DE TABLE
		============================================================
		 */
		
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"IDRH","Identifiant","Nom","Prenom", "Habilitation"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
	
		pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		loadTabCompte();
		
		/*
		============================================================
            		INITIATION DES COMBOBOX
		============================================================
		 */
	
		choixIDRH.setEditable(false);
		afficherListeIDRH();
		
		/*
		============================================================
        			INITIATION LES CHOIX DE IDRH
		============================================================
		 */
		choixHabilitation.setEditable(false);
		afficherListeHabilitation();
	}	
		

	public void addEvents() {
		
		btnAjoute.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteAjouterCompte();
	        }
		});
		
		btnReset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				resetPage();
	        }	
		});
		
		btnRecherche.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteRechercherCompte();
	        }	
		});
		
		btnSupp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteSupprimerCompte();
	        }	
		});
		
		btnMod.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteModifierCompte();
	        }	
		});
		
		table.addMouseListener(new MouseListener() {
			
			 @Override
	            public void mouseClicked(MouseEvent e) {
	                tabCompteClique();
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {	            
	        }
		});
		
		
		recherche.addFocusListener((FocusListener) new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent e) {		
					recherche.setText("");
					recherche.setForeground(Color.BLACK);		
				}
				
				/*
				 * En sortant la zone de recherche :
				 * Remettre le placeholder si l'utilisateur ne remplit pas
				 */
				
			public void focusLost(FocusEvent e) {	
					if (recherche.getText().toString().length() == 0) {			
						recherche.setText("Recherche par identifiant...");
						recherche.setForeground(Color.GRAY);		
					}	
				}
		});
		
		choixIDRH.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {				
				autoRemplirParIdrh();			
	        }	
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadTabCompte() {
		
		model.setRowCount(0);

        ArrayList<compteModele> listeCompte = compteBUS.getNouveauListeCompte();

        for (compteModele compte : listeCompte) {
            Vector vec = new Vector();
            vec.add(compte.getIdrh());
            vec.add(compte.getIdentifiant());
            vec.add(compte.getNom());
            vec.add(compte.getPrenom());
            vec.add(compte.getHabilitation());
             
           	model.addRow(vec); 	  		     
        }		
		recherche.setText("Recherche par identifiant...");
		recherche.setForeground(Color.GRAY);
	}
	
	private void resetPage() {
		
		choixIDRH.setSelectedIndex(0);
		txtIdentifiant.setText("");
		choixHabilitation.setSelectedIndex(0);
		txtNom.setText("");
		txtPrenom.setText("");
		txtEmploi.setText("");
			
		loadTabCompte();
		
		for (JButton btn : listButtons) {
			btn.setEnabled(true);
		}
	}
	
	private void tabCompteClique() {
		
		ArrayList<compteModele> listeCompte = compteBUS.getNouveauListeCompte();
		int indice = table.getSelectedRow();	
		int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());
		
		if (idrh >0) {
			for (compteModele compte : listeCompte) {
				if (compte.getIdrh() == idrh) {
									
					choixIDRH.setSelectedIndex(compte.getIdrh());
					txtIdentifiant.setText(compte.getIdentifiant());
					choixHabilitation.setSelectedIndex(compte.getHabilitation());
					txtNom.setText(compte.getNom());
					txtPrenom.setText(compte.getPrenom());
					txtEmploi.setText(compte.getEmploi());
					break;		
				}
			}
			
			for (JButton btn : listButtons) {
				if (btn.getText().matches("Ajouter|Rechercher")){
					btn.setEnabled(false);		
				}
			}
			
		}	
	}
	
	private void traiteSupprimerCompte() {
		monDialogue dlg = new monDialogue("Voulez-vous supprimer ce compte ?", monDialogue.WARNING_DIALOG);
	    if (monDialogue.OK_OPTION == dlg.getAction()) {
	    	int indice = table.getSelectedRow();	
			int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());
			
			if (compteBUS.supprimerCompte(idrh)) {
				monDialogue dlg1 = new monDialogue("Compte supprimé!!!", monDialogue.INFO_DIALOG);
				loadTabCompte();
				resetPage();		
			}	    	
	    }		
	}
	
	private void traiteModifierCompte() {
		monDialogue dlg = new monDialogue("Voulez-vous modifier ce compte ?", monDialogue.WARNING_DIALOG);
	    if (monDialogue.OK_OPTION == dlg.getAction()) {
	    	
	    	int indice = table.getSelectedRow();	
			int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());					
			int habilitation = ConvertStringToInt.convert(choixHabilitation.getSelectedItem().toString());
						
			if (compteBUS.modifierCompte(idrh, habilitation)) {
				loadTabCompte();
				resetPage();		
			}	    	
	    }		
	}
	
	public void traiteAjouterCompte() {
			
		if (choixHabilitation.getSelectedItem() == null) {
			new monDialogue("Merci de renseigner tous les champs nécessaires !!!", monDialogue.ERROR_DIALOG);			
		}
		else {
			
			int idrh = ConvertStringToInt.convert(choixIDRH.getSelectedItem().toString());					
			int habilitation = ConvertStringToInt.convert(choixHabilitation.getSelectedItem().toString());		
			String identifiant = "NB"+idrh;
			String mdp = "123456";
			monDialogue dlg1 = new monDialogue("Voulez-vous créer un nouveau compte ?", monDialogue.INFO_DIALOG);
		    if (monDialogue.OK_OPTION == dlg1.getAction()) {
		    	
		    	if (compteBUS.compteAjoute(idrh)) {
		    		monDialogue dlg2 = new monDialogue("Ce compte existe déjà !!!", monDialogue.ERROR_DIALOG);
		    	}
		    	
		    	else{
		    		if (compteBUS.ajouterCompte(idrh, identifiant, mdp, habilitation)) {
						loadTabCompte();
						resetPage();		
					};		
		    	}	    	
		    }			
			
		}		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void traiteRechercherCompte() {
		
		ArrayList<compteModele> listeCompte = compteBUS.rechercherCompte(recherche.getText());
		
		if (listeCompte == null) {
			new monDialogue("Aucun compte n'a été trouvé!!!", monDialogue.ERROR_DIALOG);
		}
		else {
			model.setRowCount(0);
			for (compteModele compte : listeCompte) {
				Vector vec = new Vector();
		        vec.add(compte.getIdrh());
		        vec.add(compte.getIdentifiant());
		        vec.add(compte.getPrenom());
		        vec.add(compte.getNom());
		        vec.add(compte.getHabilitation());
		        model.addRow(vec);	
			}
		}
			
	}
	
	public void afficherListeIDRH() {
		
		ArrayList<Integer> listeIDRH = compteBUS.getNouveauListeIDRH();
		choixIDRH.addItem(null);
		
		for (Integer i : listeIDRH) {			
			choixIDRH.addItem(i);			
		}
	}
	
	public void afficherListeHabilitation() {
		
		ArrayList<Integer> listeHabilitation = compteBUS.getNouveauListeHabilitation();
		choixHabilitation.addItem(null);
		
		for (Integer i : listeHabilitation) {			
			choixHabilitation.addItem(i);			
		}
	}
	
	public void autoRemplirParIdrh() {
		
		if (choixIDRH.getSelectedItem() != null) {
			int idrh = ConvertStringToInt.convert(choixIDRH.getSelectedItem().toString());
			ArrayList<compteModele> listeCompte = compteBUS.getNouveauListeCompte();
			ArrayList<employeModele> listeEmploye = employeBUS.getNouveauListeEmploye();
			choixHabilitation.setSelectedItem(null);
			
			for (compteModele compte : listeCompte) {
				if (compte.getIdrh() == idrh) {			
					choixHabilitation.setSelectedIndex(compte.getHabilitation());
					
				}
			}		
			for (employeModele employe : listeEmploye) {
				
				if (employe.getIDRH() == idrh) {			
					choixIDRH.setSelectedIndex(employe.getIDRH());
					txtIdentifiant.setText("NB"+employe.getIDRH());
					txtNom.setText(employe.getNom());
					txtPrenom.setText(employe.getPrenom());
					txtEmploi.setText(employe.getEmploi());					
				}				
			}
		}
		else {
			choixIDRH.setSelectedItem("");
			choixHabilitation.setSelectedItem(null);
			txtIdentifiant.setText("");
			txtNom.setText("");
			txtPrenom.setText("");
			txtEmploi.setText("");				
		}
	}	
}
