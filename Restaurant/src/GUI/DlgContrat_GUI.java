package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.categorie_BUS;
import BUS.typeContrat_BUS;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import DTO.contratModele;

public class DlgContrat_GUI {
	
	private DefaultTableModel model;
	private JTable table;
	private JLabel titre;
	private JFrame frame;
	private Container cprincipal;
	private JPanel panelCenter, panelButtons, panelRemplir, panelTitre;
	private JLabel typeContrat;
	private JTextField txtTypeContrat;
	private ImageIcon iconAjoute, iconSupprimer;
	
	private monButton btnAjouter, btnSupprimer;
	private JScrollPane pane;
	
	
	typeContrat_BUS typeContratBUS = new typeContrat_BUS();
	Font f = new Font("TimesRoman", Font.BOLD, 18);
	
	
	public DlgContrat_GUI() {
		addComponents();
		addEvents();
	}
	
	private void addComponents() {
		
		frame = new JFrame("Mon application");		
		cprincipal = frame.getContentPane();	
		panelCenter = new JPanel();
		cprincipal.add(panelCenter);
		frame.setBackground(Color.ORANGE);
		
		
		panelTitre = new JPanel();
		titre  = new JLabel("Gestion des types de contrat");
		titre.setFont(f);
		panelTitre.add(titre);
		
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
		
		
		
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(panelTitre);
		panelCenter.add(pane);
		frame.setLocationRelativeTo(null);
		frame.setLocation(new Point(0, 0));
		

		frame.setVisible(true);	
		frame.pack();
		
		panelButtons = new JPanel();
	
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnAjouter = new monButton("Ajouter", iconAjoute);
		btnSupprimer = new monButton("Supprimer", iconSupprimer);
		
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnAjouter);
		panelButtons.add(btnSupprimer);
		
		
		typeContrat = new JLabel("Libellé du type contrat");
		txtTypeContrat = new JTextField("", 15);
		
		panelRemplir = new JPanel();
		panelRemplir.setLayout(new FlowLayout());
		panelRemplir.add(typeContrat);
		panelRemplir.add(txtTypeContrat);
		
		
		panelCenter.add(panelRemplir);
		panelCenter.add(panelButtons);
		
	}
	
	public void addEvents() {
		
		btnAjouter.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteAjouteTypeContrat();
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
				resetPage();
				new monDialogue(typeContrat+" a été ajouté!!!", monDialogue.INFO_DIALOG);
			}				    	
	    }			   	
    }
    
    public void resetPage() {
    	txtTypeContrat.setText("");  	
    }
}
