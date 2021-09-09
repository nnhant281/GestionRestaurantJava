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
import java.text.SimpleDateFormat;
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

import BUS.client_BUS;
import BUS.commande_BUS;
import BUS.facture_BUS;
import BUS.table_BUS;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import Custom.transparentPanel;
import DTO.client;
import DTO.commande;
import DTO.compteModele;

public class DlgSaisiePaiement_GUI extends javax.swing.JDialog {
	
	private compteModele user;
	private int idCommande;
	private commande_GUI cmdGUI;
	
	public DlgSaisiePaiement_GUI (int idCommande, compteModele user, commande_GUI cmdGUI){
		this.cmdGUI = cmdGUI;
		this.user = user;
		this.idCommande = idCommande;
		changeLNF("Nimbus");
		addControls();
        addEvents();
        this.pack();
	}
	
	private commande_BUS commandeBUS = new commande_BUS();
	private client_BUS clientBUS = new client_BUS();
	private table_BUS tableBUS = new table_BUS();
	private facture_GUI factureGUI = new facture_GUI();
	
	final Color colorPanel = new Color(250, 240, 230);
    JTextField txtIdCmd, txtIdTable, txtDate, txtMontant, txtIDRH,txtIdClient, txtChercheNom, txtCherchePrenom, txtChercheNumTel;
    JButton  btnPaiement, btnReset;
    monTableau tabClient;
    DefaultTableModel modelTabClient;
	
	public void addControls() {
		Font font = new Font("Tahoma", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
        
        /*
        =========================================================================
                                    PANEL TITRE
        =========================================================================
         */
      

        JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));

        JPanel pnTitle = new transparentPanel();
        JLabel lblTitle = new JLabel("<html><h1>Processus de paiement</h1></html>");
        pnTitle.add(lblTitle);
        panelTop.add(pnTitle);

        this.add(panelTop,BorderLayout.NORTH);
        
        /*
        =========================================================================
                                    PANEL CENTRE
        =========================================================================
         */
        JPanel panelCentre = new transparentPanel();
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.X_AXIS));
        /*
        =========================================================================
                                    PANEL GAUCHE
        =========================================================================
         */
        JPanel panelGauche = new transparentPanel();
        panelGauche.setLayout(new BorderLayout());
        
       
        
        //======PANEL DETAIL COMMANDE=======
        JPanel panelFacture = new transparentPanel();
        panelFacture.setLayout(new BoxLayout(panelFacture, BoxLayout.Y_AXIS));
        
        //======PANEL TITRE FACTURE=======
        JPanel pnTitreGauche = new transparentPanel();
        JLabel lblTitleGauche = new JLabel("<html><h1>Facture</h1></html>");
        pnTitreGauche.add(lblTitleGauche);
        panelFacture.add(pnTitreGauche);
        
        JLabel lblIdCmd,lblIdTable, lblIDRH,lblDate,lblMontant, lblIdClient;
        
        lblIdCmd = new JLabel("ID Commande");
        lblIDRH = new JLabel("ID caissier");
        lblIdTable= new JLabel("ID table");
        lblDate = new JLabel("Date ");
        lblMontant = new JLabel("Montant");
        lblIdClient = new JLabel("Client");

        lblIdCmd.setFont(font);
        lblIDRH.setFont(font);
        lblIdTable.setFont(font);
        lblDate.setFont(font);
        lblMontant.setFont(font);
        lblIdClient.setFont(font);

        txtIdCmd = new JTextField(20);
        txtIDRH = new JTextField(20);
        txtIdTable = new JTextField(20);
        txtDate = new JTextField(20);
        txtMontant = new JTextField(20);
        txtIdClient = new JTextField(20);
        
        txtIdCmd.setFont(font);
        txtIDRH.setFont(font);
        txtIdTable.setFont(font);
        txtDate.setFont(font);
        txtMontant.setFont(font);
        txtIdClient.setFont(font);

        JPanel panelCmd = new transparentPanel();
        panelCmd.add(lblIdCmd);
        panelCmd.add(txtIdCmd);
        panelFacture.add(panelCmd);

        JPanel panelIDRH = new transparentPanel();
        panelIDRH.add(lblIDRH);
        panelIDRH.add(txtIDRH);
        panelFacture.add(panelIDRH);

        JPanel panelTable = new transparentPanel();
        panelTable.add(lblIdTable);
        panelTable.add(txtIdTable);
        panelFacture.add(panelTable);
        
        JPanel panelDate = new transparentPanel();
        panelDate.add(lblDate);
        panelDate.add(txtDate);
        panelFacture.add(panelDate);
        
        JPanel panelMontant = new transparentPanel();
        panelMontant.add(lblMontant);
        panelMontant.add(txtMontant);
        panelFacture.add(panelMontant);
        
        JPanel panelIdClient = new transparentPanel();
        panelIdClient.add(lblIdClient);
        panelIdClient.add(txtIdClient);
        panelFacture.add(panelIdClient);

        Dimension lblSize = lblIdCmd.getPreferredSize();
        lblIdCmd.setPreferredSize(lblSize);
        lblIDRH.setPreferredSize(lblSize);
        lblIdTable.setPreferredSize(lblSize);
        lblDate.setPreferredSize(lblSize);
        lblMontant.setPreferredSize(lblSize);
        lblIdClient.setPreferredSize(lblSize);

        //===============PANEL BUTTON=============
        JPanel pnBtnConfirme = new transparentPanel();
        ImageIcon iconPaiement = new ImageIcon(new ImageIcon("images/Buttons/commande.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnPaiement = new monButton("Payer", iconPaiement);
        Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
        btnPaiement.setFont(fontButton);
        btnPaiement.setPreferredSize(new Dimension(160,40));
        pnBtnConfirme.add(btnPaiement);
        panelGauche.add(pnBtnConfirme);
        
        
        panelGauche.add(panelFacture,BorderLayout.NORTH);
        panelCentre.add(panelGauche);
        
        /*
        =========================================================================
                                    PANEL DROIT
        =========================================================================
         */
        JPanel panelDroit = new transparentPanel();
        panelDroit.setLayout(new BorderLayout());
        
        
        //======PANEL TEXT FIELD=======
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));
        
        JPanel pnTitreDroit = new transparentPanel();
        JLabel lblTitleDroit = new JLabel("<html><h1>Recherche le compte de client</h1></html>");
        pnTitreDroit.add(lblTitleDroit);
        pnTextField.add(pnTitreDroit);
        
        JLabel lblChercheNom,lblCherchePrenom, lblChercheNumTel;
        
        lblChercheNom = new JLabel("Nom");
        lblCherchePrenom = new JLabel("Prénom");
        lblChercheNumTel= new JLabel("Tél");

        lblChercheNom.setFont(font);
        lblCherchePrenom.setFont(font);
        lblChercheNumTel.setFont(font);

        txtChercheNom = new JTextField(20);
        txtCherchePrenom = new JTextField(20);
        txtChercheNumTel = new JTextField(20);
        
        txtChercheNom.setFont(font);
        txtCherchePrenom.setFont(font);
        txtChercheNumTel.setFont(font);

        JPanel panelChercheNom = new transparentPanel();
        panelChercheNom.add(lblChercheNom);
        panelChercheNom.add(txtChercheNom);
        pnTextField.add(panelChercheNom);

        JPanel panelCherchePrenom = new transparentPanel();
        panelCherchePrenom.add(lblCherchePrenom);
        panelCherchePrenom.add(txtCherchePrenom);
        pnTextField.add(panelCherchePrenom);

        JPanel panelChercheNumTel = new transparentPanel();
        panelChercheNumTel.add(lblChercheNumTel);
        panelChercheNumTel.add(txtChercheNumTel);
        pnTextField.add(panelChercheNumTel);
        
        lblChercheNom.setPreferredSize(lblSize);
        lblCherchePrenom.setPreferredSize(lblSize);
        lblChercheNumTel.setPreferredSize(lblSize);

      //===============PANEL BUTTON=============
        JPanel pnBtnReset = new transparentPanel();
        ImageIcon iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnReset = new monButton("RÃ©initialiser", iconReset);
        btnReset.setFont(fontButton);
        btnReset.setPreferredSize(new Dimension(160,40));
        pnBtnReset.add(btnReset);
        pnTextField.add(pnBtnReset);
        
        panelDroit.add(pnTextField,BorderLayout.NORTH);
     
        //=========================TABLEAU=====================
        modelTabClient = new DefaultTableModel();
        modelTabClient.addColumn("ID client");
        modelTabClient.addColumn("Nom");
        modelTabClient.addColumn("PrÃ©nom");
        modelTabClient.addColumn("Adresse");
        modelTabClient.addColumn("Tel");
        modelTabClient.addColumn("Email");
        modelTabClient.addColumn("Point");;

        tabClient = new monTableau();
        tabClient.setModel(modelTabClient);
        
        JScrollPane scrtabClient = new JScrollPane(tabClient);

        panelDroit.add(scrtabClient, BorderLayout.CENTER);
        
        panelCentre.add(panelDroit);
        this.add(panelCentre,BorderLayout.CENTER);
        

        loadCommande();
        loadTabClient();
	}
	
	public void addEvents() {
		 btnReset.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                loadPageRecherche();
	            }
	        });

		 btnPaiement.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	traitePaiement();
	            }
	        });
		 
		txtChercheNom.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }
        });
		
		txtCherchePrenom.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }
        });
		
		txtChercheNumTel.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	traiteRechercheClient();
            }
        });
		
		tabClient.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CliqueTabClient();
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
	
	public void loadCommande() {
		commande c = commandeBUS.getCommandeParIDCommande(idCommande);
		txtIdCmd.setText(Integer.toString(c.getIdCommande()));
        txtIDRH.setText(Integer.toString(c.getIDRH()));
        txtIdTable.setText(Integer.toString(c.getIdTable())); 
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        String strDate = formatter.format(c.getDate());  
        txtDate.setText(strDate);
        txtMontant.setText(Float.toString(c.getTotal()));
	}
	
	 private void loadTabClient() {
        ArrayList<client> liste = clientBUS.getlisteClient();
        loadTabClient(liste);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadTabClient(ArrayList<client> liste) {
        modelTabClient.setRowCount(0);
        for (client c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdClient());
            vec.add(c.getNom());
            vec.add(c.getPrenom());
            vec.add(c.getAdresse());
            vec.add(c.getNumTel());
            vec.add(c.getEmail());
            vec.add(c.getPoint());
            modelTabClient.addRow(vec);
        }
    }

    private void CliqueTabClient() {
        int row = tabClient.getSelectedRow();
        if (row > -1) {
            txtIdClient.setText(Integer.toString((int)tabClient.getValueAt(row, 0)));
            txtChercheNom.setText((String)tabClient.getValueAt(row, 1));
            
        }
    }
    
    private void traiteRechercheClient() {
        ArrayList<client> liste = clientBUS.rechercheClientParMultiInfo(txtChercheNom.getText(),txtCherchePrenom.getText(),txtChercheNumTel.getText());
        loadTabClient(liste);
    }
    
    private void traitePaiement() {
    	boolean flag = false ; 
        if(!txtIdClient.getText().trim().equals("")) {
        	int idClient = Integer.parseInt(txtIdClient.getText());
        	flag = commandeBUS.saisieIdClientALaCommande(idCommande, idClient);
        	flag = clientBUS.majPoint(idClient, txtMontant.getText());
        }
        flag = commandeBUS.commandePayee(idCommande);
        flag = tableBUS.tableDispo(txtIdTable.getText());
        if (flag) {
        	monDialogue dlg = new monDialogue("La commande est factuée !", monDialogue.SUCCESS_DIALOG);
        	 if(dlg.getAction() == monDialogue.OK_OPTION) {
        		 this. setVisible(false);
        		 mainGUI.loadPage() ;
        	 }
        } else {
        	monDialogue dlg = new monDialogue("Il y a un erreur. La facturation est échue !", monDialogue.ERROR_DIALOG);
        }
    }
    
    private void loadPageRecherche() {
    	loadTabClient();
        txtIdClient.setText("");
        txtChercheNom.setText("");
        txtCherchePrenom.setText("");
        txtChercheNumTel.setText("");
    }
}
