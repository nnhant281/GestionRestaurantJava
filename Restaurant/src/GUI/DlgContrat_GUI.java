package GUI;

import static Main.main.changeLNF;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.typeContrat_BUS;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import DAO.typeContrat_DAO;
import DTO.contratModele;

@SuppressWarnings("serial")
public class DlgContrat_GUI extends JDialog {
	
	private DefaultTableModel model;
	private JTable table;
	private JLabel titre;
	private JPanel panelCenter, panelButtons, panelRemplir, panelTitre;
	private JLabel typeContrat;
	private JTextField txtTypeContrat;
	private ImageIcon iconAjoute, iconSupprimer;
	
	private monButton btnAjouter, btnSupprimer;
	private JScrollPane pane;
	
	
	typeContrat_BUS typeContratBUS = new typeContrat_BUS();
	typeContrat_DAO typeContratDAO = new typeContrat_DAO();
	Font f = new Font("TimesRoman", Font.BOLD, 18);
	
	
	public DlgContrat_GUI() {
		changeLNF("Nimbus");
		addComponents();
		addEvents();
	}
	
	private void addComponents() {
		
		
		/*
        ============================================================
                         		TITRE
        ============================================================
         */
		panelCenter = new JPanel();
		this.add(panelCenter);
		panelTitre = new JPanel();
		titre  = new JLabel("Gestion des types de contrat");
		titre.setFont(f);
		panelTitre.add(titre);
			
		/*
        ============================================================
                         CREATION DE LA TABLE
        ============================================================
         */
		
		table = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"Type Contrat"};
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
		pane.getViewport().setBackground(new Color(250, 240, 230));
		loadTabTypeContrat();
		
		/*
        ============================================================
                        CREATION DES BUTTONS
        ============================================================
         */
		panelButtons = new JPanel();

		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnAjouter = new monButton("Ajouter", iconAjoute);
		btnSupprimer = new monButton("Supprimer", iconSupprimer);
		
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnAjouter);
		panelButtons.add(btnSupprimer);
				
		/*
        ============================================================
                       CREATION PANEL 0 REMPLIR
        ============================================================
         */
		
		panelRemplir = new JPanel();		
		panelRemplir.setLayout(new FlowLayout());
		
		typeContrat = new JLabel("Libellé du type contrat");
		txtTypeContrat = new JTextField("", 15);
		
	
		panelRemplir.add(typeContrat);
		panelRemplir.add(txtTypeContrat);
		
		/*
        ============================================================
                     COMPOSANTS DU PANEL CENTER
        ============================================================
         */
		
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelTitre);
		panelCenter.add(table);
		panelCenter.add(panelRemplir);
		panelCenter.add(panelButtons);	
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.pack();
	}
	
	public void addEvents() {
		
		btnAjouter.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteAjouteTypeContrat();
	        }
		});
		
		btnSupprimer.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteSupprimerTypeContrat();
	        }
		});
		
		table.addMouseListener(new MouseListener() {
			
			 @Override
	            public void mouseClicked(MouseEvent e) {
				 	tabTypeContratClique();
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
		
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadTabTypeContrat() {
    	 	
        model.setRowCount(0);
        ArrayList<contratModele> listeTypeContrat = typeContratBUS.getNouveauListeTypeContrat();
        if (listeTypeContrat != null) {
            for (contratModele typeContrat : listeTypeContrat) {
            	          	
                Vector vec = new Vector();
                vec.add(typeContrat.getTypeContrat().toUpperCase());
                model.addRow(vec);
                
            }
        }      
    }
    
    public void traiteAjouteTypeContrat() {
    	
    	
    	String typeContrat = txtTypeContrat.getText();
    	
    	monDialogue dlg = new monDialogue("Voulez-vous ajouter "+typeContrat+" ?", monDialogue.WARNING_DIALOG);
	    if (monDialogue.OK_OPTION == dlg.getAction()) {
			if(typeContratBUS.ajouterTypeContrat(typeContrat)){
				loadTabTypeContrat();
				new monDialogue(typeContrat+" a été ajouté!!!", monDialogue.INFO_DIALOG);
			}
			resetPage();
	    }			   	
    }
    
    private void traiteSupprimerTypeContrat() {
		
		int indice = table.getSelectedRow();	
		
		String typeContratASupprimer=model.getValueAt(indice, 0).toString();
	
		monDialogue dlg1 = new monDialogue("Voulez-vous supprimer cet employé ?", monDialogue.WARNING_DIALOG);
		    if (monDialogue.OK_OPTION == dlg1.getAction()) {		
				if (typeContratBUS.supprimerTypeContrat(typeContratASupprimer)) {
					loadTabTypeContrat();
					resetPage();		
				}	    	
		    }		
		}	
    
    public void resetPage() {
    	txtTypeContrat.setText("");  	
    }
    
    private void tabTypeContratClique() {
    	
		ArrayList<contratModele> listeTypeContrat = typeContratBUS.getNouveauListeTypeContrat();
		int indice = table.getSelectedRow();	
		String typeContrat = model.getValueAt(indice, 0).toString().toUpperCase();
		
		if (typeContrat.length() >0) {
			for (contratModele contrat : listeTypeContrat) {
				if (contrat.getTypeContrat().equals(typeContrat)) {
					txtTypeContrat.setText(typeContrat);	
					break;		
				}
			}				
		}	
	}
}
