package GUI;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import BUS.table_BUS;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import Custom.transparentPanel;
import DTO.table;

@SuppressWarnings("serial")
public class table_GUI extends JPanel{
	public table_GUI() {
		changeLNF("FlatLaf");
		addControls();
        addEvents();
	}
	
	private table_BUS tableBUS = new table_BUS();

    final Color colorPanel = new Color(250, 240, 230);
    JTextField  txtId,txtNom, txtStatut;
    JButton btnAjoute, btnModif, btnRecherche, btnReset,btnSupp;
    monTableau tabTable;
    DefaultTableModel modelTabTable;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 18);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);
        /*
        =========================================================================
                                    PANEL TABLE
        =========================================================================
         */
        JPanel panelTable = new transparentPanel();
        panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.Y_AXIS));
        
        JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

        JPanel pnTitle = new transparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Gestion de tables</h1></html>");
        pnTitle.add(lblTitle);
        panelTop.add(pnTitle);

        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblNom, lblStatut, lblId;
        lblId = new JLabel("Id table");
        lblNom = new JLabel("Nom *");
        lblStatut = new JLabel("Statut ");
        lblId.setFont(font);
        lblNom.setFont(font);
        lblStatut.setFont(font);
        
        txtId = new JTextField(20);
        txtId.setEditable(false);
        txtNom = new JTextField(20);
        txtStatut = new JTextField(20);
        txtStatut.setEditable(false);

        txtId.setFont(font);
        txtNom.setFont(font);
        txtStatut.setFont(font);

        JPanel panelId = new transparentPanel();
        panelId.add(lblId);
        panelId.add(txtId);
        pnTextField.add(panelId);
        
        JPanel panelNom = new transparentPanel();
        panelNom.add(lblNom);
        panelNom.add(txtNom);
        pnTextField.add(panelNom);

        JPanel panelStatut = new transparentPanel();
        panelStatut.add(lblStatut);
        panelStatut.add(txtStatut);
        pnTextField.add(panelStatut);
        

        Dimension lblSize = new Dimension(80,30);
        lblId.setPreferredSize(lblSize);
        lblNom.setPreferredSize(lblSize);
        lblStatut.setPreferredSize(lblSize);

        panelTop.add(pnTextField);
        panelTable.add(panelTop);

        //===============PANEL BUTTON=============
        JPanel pnButton = new transparentPanel();
        ImageIcon iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnModif = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnReset = new monButton("Réinitialiser", iconReset);
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        btnAjoute.setFont(fontButton);
        btnModif.setFont(fontButton);
        btnSupp.setFont(fontButton);
        btnReset.setFont(fontButton);
        panelTable.add(pnButton);

        pnButton.add(btnAjoute);
        pnButton.add(btnModif);
        pnButton.add(btnSupp);
        pnButton.add(btnReset);

        
        btnAjoute.setPreferredSize(new Dimension(160,40));
        btnModif.setPreferredSize(new Dimension(160,40));
        btnSupp.setPreferredSize(new Dimension(160,40));
        btnReset.setPreferredSize(new Dimension(160,40));
        
        //=========================TABLEAU=====================
        modelTabTable = new DefaultTableModel();
        modelTabTable.addColumn("ID table");
        modelTabTable.addColumn("Nom");
        modelTabTable.addColumn("Statut");

        tabTable = new monTableau();
        tabTable.setModel(modelTabTable);
        
        JScrollPane scrtabTable = new JScrollPane(tabTable);
        scrtabTable.setBounds(10,10,10,10);
        scrtabTable.getViewport().setBackground(new Color(250, 240, 230));
        this.add(panelTable);
        this.add(scrtabTable);

        loadTabTable();
    }

   

	@SuppressWarnings("unused")
	private void setLayout(BorderLayout borderLayout) {
		
	}

	private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage();
                mainGUI.loadPage() ;
            }
        });

        tabTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CliqueTabTable();
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

        btnAjoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteAjouteTable();
                mainGUI.loadPage() ;
            }
        });

        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteModifTable();
                mainGUI.loadPage() ;
            }
        });
        
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteSuppTable();
                mainGUI.loadPage() ;
            }
        });

    }

    private void loadTabTable() {
        ArrayList<table> liste = tableBUS.getlisteTable();
        loadTabTable(liste);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadTabTable(ArrayList<table> liste) {
        modelTabTable.setRowCount(0);
        for (table c : liste) {
            Vector vec = new Vector();
            vec.add(c.getId());
            vec.add(c.getLibelle());
            vec.add(c.getStatut());
            modelTabTable.addRow(vec);
        }
    }

    private void CliqueTabTable() {
        int row = tabTable.getSelectedRow();
        if (row > -1) {
        	txtId.setText(tabTable.getValueAt(row,0)+ "");
            txtNom.setText(tabTable.getValueAt(row, 1) + "");
            txtStatut.setText(tabTable.getValueAt(row, 2) + "");
        }
    }


    private void traiteAjouteTable() {
        if (tableBUS.ajouteTable(txtNom.getText()))
            btnReset.doClick();
    }

    private void traiteModifTable() {
        if (tableBUS.majTable(txtId.getText(),txtNom.getText()))
            btnReset.doClick();
    }
    
    private void  traiteSuppTable() {
    	monDialogue dlg = new monDialogue("La table sera �tre supprim�e ?", monDialogue.WARNING_DIALOG);
        if (monDialogue.OK_OPTION == dlg.getAction()) {
            boolean flag = tableBUS.deleteTable(txtId.getText());
            if (flag) {
            	loadPage();
            }
        }
    }

    private void loadPage() {
    	loadTabTable();
    	txtId.setText("");
        txtNom.setText("");
        txtStatut.setText("");
    }
}

