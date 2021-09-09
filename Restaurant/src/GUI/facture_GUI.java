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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import BUS.facture_BUS;
import Custom.monButton;
import Custom.monTableau;
import Custom.transparentPanel;
import DTO.commande;

@SuppressWarnings("serial")
public class facture_GUI extends JPanel{
	
	public facture_GUI() {
		changeLNF("FlatLaf");
		addControls();
        addEvents();
	}
	
	
	private facture_BUS factureBUS = new facture_BUS();
	
	final Color colorPanel = new Color(250, 240, 230);
    JTextField txtIdCmd, txtIDRH, txtDate;
    JButton  btnReset;
    monTableau tabFacture;
    DefaultTableModel modelTabFacture;

    private void addControls() {
        Font font = new Font("Tahoma", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        
        /*
        =========================================================================
                                    PANEL FACTURE
        =========================================================================
         */
        JPanel panelFacture = new transparentPanel();
        panelFacture.setLayout(new BoxLayout(panelFacture, BoxLayout.Y_AXIS));

        JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

        JPanel pnTitle = new transparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Gestion de factures</h1></html>");
        pnTitle.add(lblTitle);
        panelTop.add(pnTitle);

        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        JLabel lblIdCmd, lblIDRH, lblDate;
        lblIdCmd = new JLabel("ID Commande");
        lblIDRH = new JLabel("IDRH caissier");
        lblDate = new JLabel("Date ");

        lblIdCmd.setFont(font);
        lblIDRH.setFont(font);
        lblDate.setFont(font);

        txtIdCmd = new JTextField(20);
        txtIDRH = new JTextField(20);
        txtDate = new JTextField("01/01/2020",20);
        
        txtIdCmd.setFont(font);
        txtIDRH.setFont(font);
        txtDate.setFont(font);

        JPanel panelCmd = new transparentPanel();
        panelCmd.add(lblIdCmd);
        panelCmd.add(txtIdCmd);
        pnTextField.add(panelCmd);

        JPanel panelIDRH = new transparentPanel();
        panelIDRH.add(lblIDRH);
        panelIDRH.add(txtIDRH);
        pnTextField.add(panelIDRH);

        JPanel panelDate = new transparentPanel();
        panelDate.add(lblDate);
        panelDate.add(txtDate);
        pnTextField.add(panelDate);

        Dimension lblSize = new Dimension(120,30);
        lblIdCmd.setPreferredSize(lblSize);
        lblIDRH.setPreferredSize(lblSize);
        lblDate.setPreferredSize(lblSize);


        panelTop.add(pnTextField);
        panelFacture.add(panelTop);
        
        //===============PANEL BUTTON=============
        JPanel pnButton = new transparentPanel();
        ImageIcon iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		btnReset = new monButton("RÃ©initialiser", iconReset);
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);

        btnReset.setFont(fontButton);
        panelFacture.add(pnButton);

        pnButton.add(btnReset);

        Dimension btnSize = new Dimension(160,35);
        btnReset.setPreferredSize(btnSize);

        
        //=========================TABLEAU=====================
        modelTabFacture = new DefaultTableModel();
        modelTabFacture.addColumn("ID commande");
        modelTabFacture.addColumn("ID employée");
        modelTabFacture.addColumn("ID client");
        modelTabFacture.addColumn("ID table");
        modelTabFacture.addColumn("Date");
        modelTabFacture.addColumn("Type commande");
        modelTabFacture.addColumn("Montant");

        tabFacture = new monTableau();
        tabFacture.setModel(modelTabFacture);
        
        JScrollPane scrtabFacture = new JScrollPane(tabFacture);
        scrtabFacture.setBounds(10,10,10,10);
        scrtabFacture.getViewport().setBackground(new Color(250, 240, 230));
        this.add(panelFacture, BorderLayout.NORTH);
        this.add(scrtabFacture, BorderLayout.CENTER);
        loadTabFacture();
 }
    
    private void addEvents() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage();
            }
        });

        
        txtIdCmd.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }
        });
        
        txtIDRH.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }
        });
        
        
        txtDate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheFacture();
            }
        });
        

    }

    private void loadTabFacture() {
        ArrayList<commande> liste = factureBUS.getListeFacture();
        System.out.print(liste);
        addTabFacture(liste);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void addTabFacture(ArrayList<commande> list) {
    	
        modelTabFacture.setRowCount(0);
        if (list != null) {
        	for (commande c : list) {
	            Vector vec = new Vector();
	            vec.add(c.getIdCommande());
	            vec.add(c.getIDRH());
	            vec.add(c.getIdClient());
	            vec.add(c.getIdTable());
	            vec.add(c.getDate());
	            vec.add(c.getTypeCommande());
	            vec.add(c.getTotal());
	            modelTabFacture.addRow(vec);
	        }
        }
        
    }

   
    private void traiteRechercheFacture() {
        ArrayList<commande> liste = factureBUS.rechercheFacture(txtIdCmd.getText(),txtIDRH.getText(),txtDate.getText());
        addTabFacture(liste);
    }


    private void loadPage() {
    	loadTabFacture();
        txtIdCmd.setText("");
        txtIDRH.setText("");
        txtDate.setText("");
    }
}
