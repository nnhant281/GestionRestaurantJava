package GUI;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import BUS.employe_BUS;
import BUS.typeContrat_BUS;
import Custom.ConvertStringToInt;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import DAO.compte_DAO;
import DAO.typeContrat_DAO;
import DTO.contratModele;
import DTO.employeModele;

		/*
		============================================================
            INITIATION L'INTERFACE GRAPHIQUE DU MODULE EMPLOYE
		============================================================
		 */
		
@SuppressWarnings("serial")
public class employe_GUI extends JPanel {
	
	
	public employe_GUI () {	
		changeLNF("Nimbus");
		addComponents();
		addEvents();	
	}
	
	employe_BUS employeBUS = new employe_BUS();
	compte_DAO compteDAO = new compte_DAO();
	typeContrat_DAO typeContratDAO = new typeContrat_DAO();
	typeContrat_BUS typeContratBUS = new typeContrat_BUS();
	DlgContrat_GUI dlgContrat_GUI;

	JPanel panelLeft, panelTop, panelCenter, panelRemplirE;
	
	JLabel nomE, preNomE, dateNaissanceE, dateDebutE, dateFinE, typeContratE, dureeHebdoE, emploiE, adresseE, telE,titre;
	JTextField txtNomE, txtPreNomE, txtDureeHebdoE, txtAdresseE, txtTelE;
	JDateChooser txtDateNaissanceE, txtDateDebutE, txtDateFinE;
	JComboBox<String> choixTypeContrat , choixEmploi;
	
	monTableau table;
	DefaultTableModel model;
	JScrollPane pane;
	
	JTextField recherche;
	JButton btnRecherche;

	JPanel panelButtons;	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset; 	
	monButton btnAjoute, btnMod, btnSupp, btnReset;
	ArrayList<JButton> listButtons = new ArrayList<JButton>();

	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	

	public void addComponents() {
			
		/*
        ============================================================
                         CREATION 3 ZONES PRINCIPALES
        ============================================================
         */
		
		panelLeft = new JPanel();
		panelTop = new JPanel();
		panelCenter = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelLeft, BorderLayout.WEST);	
		
		
		/*
        ============================================================
                         COMPOSANTS DE PANEL CENTER
        ============================================================
         */
		
		panelRemplirE = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelRemplirE);
		panelRemplirE.setOpaque(true);
		panelRemplirE.setBackground(new Color(250, 240, 230));
		
		nomE = new JLabel("Nom");
		preNomE = new JLabel("Prenom");
		dateNaissanceE = new JLabel("Date de naissance");
		dateDebutE = new JLabel("Date début");
		dateFinE = new JLabel("Date fin");
		typeContratE = new JLabel("Type contrat");
		dureeHebdoE = new JLabel("Durée hebdomadaire");
		emploiE = new JLabel("Emploi");
		adresseE = new JLabel("Adresse");
		telE = new JLabel("Telephone");
		titre = new JLabel("<HTML><H1>Gestion de la liste des employés<h1><HTML>");
		
		nomE.setFont(font);
		preNomE.setFont(font);
		dateNaissanceE.setFont(font);
		dateDebutE.setFont(font);
		dateFinE.setFont(font);
		typeContratE.setFont(font);
		dureeHebdoE.setFont(font);
		emploiE.setFont(font);
		adresseE.setFont(font);
		telE.setFont(font);
		titre.setFont(font);
		
		txtNomE = new JTextField(15);
		txtPreNomE = new JTextField(15);
		txtDateNaissanceE = new JDateChooser();
		txtDateNaissanceE.setDateFormatString("dd-MM-yyyy");
		
		txtDateDebutE = new JDateChooser();
		txtDateDebutE.setDateFormatString("dd-MM-yyyy");
		
		txtDateFinE = new JDateChooser();
		txtDateFinE.setDateFormatString("dd-MM-yyyy");
		txtDureeHebdoE = new JTextField(15);
	
		txtAdresseE = new JTextField(15);
		txtTelE = new JTextField(15);
		
		txtNomE = new JTextField(15);
		txtPreNomE = new JTextField(15);
		
		txtDateNaissanceE.setFont(font);
		txtDateDebutE.setFont(font);
		txtDateFinE.setFont(font);
		txtDureeHebdoE.setFont(font);
		txtAdresseE.setFont(font);
		txtTelE.setFont(font);
		txtNomE.setFont(font);
		txtPreNomE.setFont(font);
		
		choixTypeContrat = new JComboBox<String>();
		loadListeTypeContrat();
		
		choixEmploi = new JComboBox<String>();
		choixEmploi.addItem("");
		choixEmploi.addItem("Serveur(se)");
		choixEmploi.addItem("Caissier(e)");
		choixEmploi.addItem("Plongeur(se)");
		choixEmploi.addItem("Cuisinier(e)");
		
		choixTypeContrat.setFont(font);
		choixEmploi.setFont(font);
		
		txtTelE.setPreferredSize(txtNomE.getPreferredSize());
		txtDateNaissanceE.setPreferredSize(txtNomE.getPreferredSize());
		txtDateDebutE.setPreferredSize(txtNomE.getPreferredSize());
		txtDateFinE.setPreferredSize(txtNomE.getPreferredSize());
		choixTypeContrat.setPreferredSize(txtNomE.getPreferredSize());
		choixEmploi.setPreferredSize(txtNomE.getPreferredSize());
		
		/*
        ============================================================
         ARRANGEMENT DES COMPONENTS DANS LE PANEL CHAMPS A REMPLIR
        ============================================================
         */
	
		panelRemplirE.setLayout(new GridBagLayout());
		
		GridBagConstraints gc1 = new GridBagConstraints();
		gc1.insets = new Insets(10,0,0,0);
		gc1.anchor = GridBagConstraints.LINE_START;
		
		gc1.gridx = 1;
		gc1.gridy = 0;
		panelRemplirE.add(titre, gc1);
		
		gc1.gridx = 0;
		gc1.gridy = 1;
		panelRemplirE.add(nomE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 1;
		panelRemplirE.add(txtNomE, gc1);
	
		gc1.gridx = 2;
		gc1.gridy = 1;
		panelRemplirE.add(preNomE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 1;
		panelRemplirE.add(txtPreNomE,gc1);
				
		gc1.gridx = 0;
		gc1.gridy = 2;
		panelRemplirE.add(adresseE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 2;
		panelRemplirE.add(txtAdresseE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 2;
		panelRemplirE.add(telE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 2;
		panelRemplirE.add(txtTelE,gc1);
			
		gc1.gridx = 0;
		gc1.gridy = 3;
		panelRemplirE.add(dateNaissanceE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 3;
		panelRemplirE.add(txtDateNaissanceE,gc1);	
		
		gc1.gridx = 2;
		gc1.gridy = 3;
		panelRemplirE.add(typeContratE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 3;
		panelRemplirE.add(choixTypeContrat, gc1);
		
		gc1.gridx = 0;
		gc1.gridy = 4;
		panelRemplirE.add(dateDebutE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 4;
		panelRemplirE.add(txtDateDebutE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 4;
		panelRemplirE.add(dateFinE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 4;
		panelRemplirE.add(txtDateFinE,gc1);
		
		gc1.gridx = 0;
		gc1.gridy = 5;
		panelRemplirE.add(dureeHebdoE, gc1);
		
		gc1.gridx = 1;
		gc1.gridy = 5;
		panelRemplirE.add(txtDureeHebdoE,gc1);
		
		gc1.gridx = 2;
		gc1.gridy = 5;
		panelRemplirE.add(emploiE, gc1);
		
		gc1.gridx = 3;
		gc1.gridy = 5;
		panelRemplirE.add(choixEmploi,gc1);		

		/*
        ============================================================
         					CREATION DES BUTTONS
        ============================================================
         */
	
		panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout());
		panelCenter.add(panelButtons);
		panelButtons.setOpaque(true);
		panelButtons.setBackground(new Color(250, 240, 230));
				
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconRechercher = new ImageIcon(new ImageIcon("images/Buttons/recherche.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnMod = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnReset = new monButton("Réinitialiser", iconReset);
			
		panelButtons.add(btnAjoute);
		panelButtons.add(btnMod);
		panelButtons.add(btnSupp);
		panelButtons.add(btnReset);
		
		listButtons.add(btnAjoute);
		listButtons.add(btnMod);
		listButtons.add(btnSupp);
		listButtons.add(btnReset);		
		
		btnAjoute.setFont(fontButton);
		btnMod.setFont(fontButton);	
		btnSupp.setFont(fontButton);
		btnReset.setFont(fontButton);
		
		Dimension btnSize = new Dimension(160,35);
        btnAjoute.setPreferredSize(btnSize);
        btnMod.setPreferredSize(btnSize);
        btnSupp.setPreferredSize(btnSize);
        btnReset.setPreferredSize(btnSize);

    	/*
        ============================================================
         				CREATION BARRE DE RECHERCHE
        ============================================================
         */
        
		JPanel panelRecherche = new JPanel();
		panelRecherche.setOpaque(true);
		panelRecherche.setBackground(new Color(250, 240, 230));
		
		btnRecherche = new JButton(iconRechercher);
		//btnRecherche.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
		recherche = new JTextField("Recherche par nom...", 20);
		recherche.setForeground(Color.GRAY);
		panelRecherche.add(recherche);
		panelRecherche.add(btnRecherche);
		panelCenter.add(panelRecherche);

		/*
        ============================================================
         				CREATION TABLEAU EMPLOYE
        ============================================================
         */
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"ID","Nom", "Prénom","Date Naissance","Adresse","Type Contrat", "Date début"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
			
		pane = new JScrollPane(table);
		panelCenter.add(pane);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		pane.getViewport().setBackground(new Color(250, 240, 230));
		
		loadTabEmploye();
		/*
		============================================================
          						DIVERS
		============================================================
		 */
					
		Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
		panelCenter.setBorder(blackline);
	}
	
	public void addEvents() {
		
		btnAjoute.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteAjouterEmploye();
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
				traiteRechercherEmploye();
	        }	
		});
		
		btnSupp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteSupprimerEmploye();
	        }	
		});
		
		btnMod.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteModifierEmploye();
	        }	
		});
		
		table.addMouseListener(new MouseListener() {
			
			 @Override
	            public void mouseClicked(MouseEvent e) {
	                tabEmployeClique();
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
		
		choixTypeContrat.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				traiteTypeContrat();
							
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
						recherche.setText("Recherche par nom...");
						recherche.setForeground(Color.GRAY);		
					}	
				}
		});
	}
	
	public void traiteAjouterEmploye() {
		
		String dureeHebdo = txtDureeHebdoE.getText();
		String nom = txtNomE.getText();
		String prenom = txtPreNomE.getText();
		Date dateNaissance = txtDateNaissanceE.getDate();
		String adresse = txtAdresseE.getText();
		String tel = txtTelE.getText();
		Date dateDebut = txtDateDebutE.getDate();
		String contrat = choixTypeContrat.getSelectedItem().toString();
		String emploie = choixEmploi.getSelectedItem().toString();
			
		Date dateFin;
		if (contrat.equals("CDI")) {
			dateFin = null;
		}
		else {
			dateFin = txtDateFinE.getDate();
		}
			
		monDialogue dlg = new monDialogue("Voulez-vous ajouter "+nom+" ?", monDialogue.WARNING_DIALOG);
	    if (monDialogue.OK_OPTION == dlg.getAction()) {
			if(employeBUS.ajouterEmploye(nom, prenom, dateNaissance, contrat, dateDebut, dateFin, dureeHebdo, emploie, adresse, tel)) {
				loadTabEmploye();
				resetPage();		
			};			    	
	    }			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadTabEmploye() {
        
        model.setRowCount(0);

        ArrayList<employeModele> listeEmploye = employeBUS.getNouveauListeEmploye();

        for (employeModele employe : listeEmploye) {
            Vector vec = new Vector();
            vec.add(employe.getIDRH());
            vec.add(employe.getNom());
            vec.add(employe.getPrenom());
            vec.add(employe.getDateNaissance());
            vec.add(employe.getAdresse());
            vec.add(employe.getTypeContrat());
            vec.add(employe.getDateDebut());
           	model.addRow(vec);
        }
    }
	
	private void resetPage() {
		
		txtNomE.setText("");
		txtPreNomE.setText("");
		txtDateNaissanceE.setCalendar(null);
		txtAdresseE.setText("");
		txtTelE.setText("");
		choixTypeContrat.setSelectedIndex(0);
		txtDureeHebdoE.setText("");
		choixEmploi.setSelectedIndex(0);
		txtDateDebutE.setCalendar(null);
		txtDateFinE.setCalendar(null);
		recherche.setText("Recherche par nom...");
		recherche.setForeground(Color.GRAY);
		
		loadTabEmploye();
		
		for (JButton btn : listButtons) {
			btn.setEnabled(true);
		}
	}
	
	private void tabEmployeClique() {
	
		ArrayList<employeModele> listeEmploye = employeBUS.getNouveauListeEmploye();
		int indice = table.getSelectedRow();	
		int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());
		
		if (idrh >=0) {
			for (employeModele employe : listeEmploye) {
				if (employe.getIDRH() == idrh) {
					
					txtNomE.setText(employe.getNom());
					txtPreNomE.setText(employe.getPrenom());
					txtDateNaissanceE.setDate(employe.getDateNaissance());
					txtAdresseE.setText(employe.getAdresse());
					txtTelE.setText(employe.getTel());
					choixTypeContrat.setSelectedItem(employe.getTypeContrat());
					txtDureeHebdoE.setText(employe.getDureeHebdo());
					choixEmploi.setSelectedItem(employe.getEmploi());
					txtDateDebutE.setDate(employe.getDateDebut());
					txtDateFinE.setDate(employe.getDateFin());
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
	
	private void traiteSupprimerEmploye() {
		
		int indice = table.getSelectedRow();	
		int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());	
		boolean compteExiste = compteDAO.compteAjoute(idrh);
		
		if (compteExiste) {
			new monDialogue("Merci de supprimer le compte associé à cette personne avant!!!", monDialogue.WARNING_DIALOG);
		}
		else {
			monDialogue dlg1 = new monDialogue("Voulez-vous supprimer cet employé ?", monDialogue.WARNING_DIALOG);
		    if (monDialogue.OK_OPTION == dlg1.getAction()) {		
				if (employeBUS.supprimerEmploye(idrh)) {
					loadTabEmploye();
					resetPage();		
				}	    	
		    }		
		}	
	}
	
	private void traiteModifierEmploye() {
		monDialogue dlg = new monDialogue("Voulez-vous modifier cet employé ?", monDialogue.WARNING_DIALOG);
	    if (monDialogue.OK_OPTION == dlg.getAction()) {
	    	int indice = table.getSelectedRow();	
			int idrh = ConvertStringToInt.convert(model.getValueAt(indice, 0).toString());
			
			String dureeHebdo = txtDureeHebdoE.getText();
			String nom = txtNomE.getText();
			String prenom = txtPreNomE.getText();
			Date dateNaissance = txtDateNaissanceE.getDate();
			String adresse = txtAdresseE.getText();
			String tel = txtTelE.getText();
			Date dateDebut = txtDateDebutE.getDate();
			Date dateFin = txtDateFinE.getDate();
			String contrat = choixTypeContrat.getSelectedItem().toString();
			String emploie = choixEmploi.getSelectedItem().toString();
						
			if (employeBUS.modifierEmploye(idrh, nom, prenom, dateNaissance, contrat,
					dateDebut, dateFin, dureeHebdo, emploie, adresse, tel )) {
				loadTabEmploye();
				resetPage();		
			}	    	
	    }		
	}
	
	private void traiteDureeCDI () {
		if (choixTypeContrat.getSelectedItem() !=null) {
			if (choixTypeContrat.getSelectedItem().toString().equals("CDI") && txtDateFinE.getDate() != null) {							
				txtDateFinE.setCalendar(null);
				txtDateFinE.setEnabled(false);	
			}
			else {
				txtDateFinE.setEnabled(true);
			}			
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void traiteRechercherEmploye() {
		
		ArrayList<employeModele> listeEmploye = employeBUS.rechercherEmploye(recherche.getText());
		
		if (listeEmploye == null) {
			new monDialogue("Aucun employé n'a été trouvé!!!", monDialogue.ERROR_DIALOG);
		}
		else {
			model.setRowCount(0);
			for (employeModele employe : listeEmploye) {
				Vector vec = new Vector();
		        vec.add(employe.getIDRH());
		        vec.add(employe.getNom());
		        vec.add(employe.getPrenom());
		        vec.add(employe.getDateNaissance());
		        vec.add(employe.getAdresse());
		        vec.add(employe.getTypeContrat());
		        vec.add(employe.getDateDebut());
		        model.addRow(vec);	
			}
		}
			
	}
	
	public void loadListeTypeContrat() {
		
		ArrayList<contratModele> listeTypeContrat = typeContratBUS.getNouveauListeTypeContrat();
		choixTypeContrat.addItem(null);
		
		for (contratModele typeContrat : listeTypeContrat) {			
			choixTypeContrat.addItem(typeContrat.getTypeContrat());			
		}		
		choixTypeContrat.addItem("Autres...");		
	}
	
	public void traiteTypeContrat() {
		if (choixTypeContrat.getSelectedIndex() == choixTypeContrat.getItemCount() - 1) {
			dlgContrat_GUI = new DlgContrat_GUI();
		}
		else {
			traiteDureeCDI();	
		}					
	}
	
}
