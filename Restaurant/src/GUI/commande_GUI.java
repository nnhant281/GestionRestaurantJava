/*
 * Interface pour gérer la prise de commande et les commandes en cours 
 * afficher les tables avec leur statut "disponible" ou "occupée" 
 * choisir la table disponible pour ajouter une commande 
 * ou consulter un table occupée pour ajouter, supprimer les articles 
 * passer la paiement 
 */
package GUI;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BUS.table_BUS;
import BUS.articleCommande_BUS;
import BUS.categorie_BUS;
import BUS.commande_BUS;
import BUS.detailCommande_BUS;
import BUS.produit_BUS;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import Custom.wrapLayout;
import Custom.transparentPanel;
import DAO.commande_DAO;
import DTO.articleCommande;
import DTO.client;
import DTO.compteModele;
import DTO.detailCommande;
import DTO.produitModele;
import DTO.table;


@SuppressWarnings("serial")
public class commande_GUI extends JPanel{
	
	public commande_GUI(compteModele user) {
		this.user = user;
		changeLNF("FlatLaf");
		addControls();
        addEvents();
        resetPage();
	}
	
	private compteModele user = new compteModele();
	private produit_BUS produitBUS = new produit_BUS();
    private categorie_BUS categorieBUS = new categorie_BUS();
	private table_BUS tableBUS = new table_BUS();
	private commande_BUS commandeBUS = new commande_BUS();
	private detailCommande_BUS detailCommandeBUS = new detailCommande_BUS();
	final Color colorPanel = new Color(247, 247, 247);
	JLabel titre,label;
	JTextField txtMontant = new JTextField(10);;
	JButton btnAjoute, btnSupp, btnPaie;
    monTableau tabDetail;
    DefaultTableModel modelTabDetail;
    JComboBox<String> choixCategorie = new JComboBox<String>();	
    JComboBox<produitModele> choixArticle = new JComboBox<produitModele>();	
    JPanel panelTable ;
	
    /*
     * créer l'interface
     */
	private void addControls() {
		
		// ============ MODEL DE TABLE =========//
		modelTabDetail = new DefaultTableModel();
	    modelTabDetail.addColumn("ID ");
	    modelTabDetail.addColumn("Libellï¿½");
	    modelTabDetail.addColumn("Quantitï¿½");
	    modelTabDetail.addColumn("Prix");
	    tabDetail = new monTableau();
	    tabDetail.setModel(modelTabDetail);

        TableColumnModel columnModel = tabDetail.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        
		Font font = new Font("Tahoma", Font.PLAIN, 18);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
		
		JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        
		JPanel pnTitle = new transparentPanel();
        titre = new JLabel("<html><h1>ORDER</h1></html>");
        pnTitle.add(titre);
        panelTop.add(pnTitle);
        
        this.add(panelTop,BorderLayout.NORTH);
        
        JPanel panelRemplir= new transparentPanel();
        panelRemplir.setLayout(new GridLayout(1,2, 5, 5));
        
        //================PANEL TABLE=========
        JPanel panelConteneur =  new transparentPanel();
        panelTable = new transparentPanel();
        loadTable(panelTable);
        panelTable.setLayout(new wrapLayout());
        panelTable.setSize(new Dimension(300,300));
        
        
        JScrollPane scrtabTable = new JScrollPane(panelTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelRemplir.add(scrtabTable);
        
        /*
		============================================================
           					PANEL INFO 
		============================================================
		 */
        
        JPanel panelDetail= new transparentPanel();
        panelDetail.setLayout(new BorderLayout());
        
      //================PANEL AJOUTER =========
        JPanel pnAjoute = new transparentPanel();
        pnAjoute.setLayout(new BoxLayout(pnAjoute, BoxLayout.X_AXIS));
        
        //============== Champs de choisir============= //
        JPanel pnBox = new transparentPanel();
        pnBox.setLayout(new BoxLayout(pnBox, BoxLayout.Y_AXIS));
        
        JLabel lblCategorie, lblArticle;
        lblCategorie = new JLabel("Categorie");
        lblArticle = new JLabel("Article");
        
        lblCategorie.setFont(font);
        lblArticle.setFont(font);

        
        choixCategorie.setFont(font);
        choixArticle.setFont(font);

        lblCategorie.setPreferredSize(new Dimension (80,30));
        lblArticle.setPreferredSize(new Dimension (80,30));
        
        choixCategorie.setPreferredSize( new Dimension( 250, 30 ) );
        choixArticle.setPreferredSize( new Dimension( 250, 30 ) );
        
        JPanel pnCategorie = new transparentPanel();
        pnCategorie.add(lblCategorie);
        pnCategorie.add(choixCategorie);
        pnBox.add(pnCategorie);
        
        JPanel pnArticle = new transparentPanel();
        pnArticle.add(lblArticle);
        pnArticle.add(choixArticle);
        pnBox.add(pnArticle);
        pnAjoute.add(pnBox);
      
        
        //========= BOUTON AJOUTER ========//
        JPanel pnBtnAjoute = new transparentPanel();
        pnBtnAjoute.setLayout(new FlowLayout());
        
        ImageIcon iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
        btnAjoute = new monButton("Ajouter", iconAjoute);
        
		Font fontButton = new Font("Tahoma", Font.PLAIN, 14);
		btnAjoute.setFont(fontButton);
		btnAjoute.setPreferredSize( new Dimension( 100,80 ) );
		
		pnBtnAjoute.add(btnAjoute);
		pnAjoute.add(pnBtnAjoute);
		
		panelDetail.add(pnAjoute,BorderLayout.NORTH);
        
        
      //================PANEL TABLE=========
        JPanel pnAffiche = new transparentPanel();
        pnAffiche.setLayout(new BoxLayout(pnAffiche, BoxLayout.Y_AXIS));

        
        JScrollPane scrtabDetail = new JScrollPane(tabDetail);
        scrtabDetail.setBounds(10,10,10,10);
        scrtabDetail.getViewport().setBackground(new Color(250, 240, 230));
        panelDetail.add(scrtabDetail,BorderLayout.CENTER);

      //========= PANEL BAS  ==========//
        JPanel pnBas = new transparentPanel();
        pnBas.setLayout(new BoxLayout(pnBas, BoxLayout.Y_AXIS));
        
        //========= TEXTE MONTANT ==========//
        JPanel pnText = new transparentPanel();
        
        label = new JLabel("<html><h3>Montant :</h3></html>");
        
        
        txtMontant.setFont(font);
        txtMontant.setHorizontalAlignment(SwingConstants.RIGHT);
        pnText.add(label);
        pnText.add(txtMontant);

        
        pnBas.add(pnText);
        
      //================PANEL BUTTON=========
        JPanel pnBtn = new transparentPanel();
        pnBtn.setLayout(new BoxLayout(pnBtn,BoxLayout.X_AXIS));
        
		ImageIcon iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon iconPayer = new ImageIcon(new ImageIcon("images/Buttons/commande.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnSupp = new monButton("Supprimer un article", iconSupprimer);
		btnPaie = new monButton("Payer", iconPayer);
		
		btnSupp.setFont(fontButton);
		btnPaie.setFont(fontButton);
		
		pnBtn.add(btnSupp);
		pnBtn.add(btnPaie);

        btnSupp.setSize(200,30);
        btnPaie.setSize(100,30);
        
        pnBas.add(pnBtn);
        panelDetail.add(pnBas,BorderLayout.SOUTH);
        panelRemplir.add(panelDetail);
        this.add(panelRemplir,BorderLayout.CENTER);
        
        loadCategorie();
	}
	
	/*
	 * les événements 
	 */
	private void addEvents() {
		choixCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	loadProduitParCategorie((String)choixCategorie.getSelectedItem());
            }
        });
		
		btnAjoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	traiteAjouteArticle(panelTable);
            }
        });
		
		btnPaie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cliqueBtnPaie(Ecouteur.getIdTableClique());
            	showCommande(Ecouteur.getIdTableClique());
            }
        });
	
		EcouteurSupprimer es = new EcouteurSupprimer(tabDetail,txtMontant);
		btnSupp.addActionListener(es);
		
		
	}
	
	private articleCommande_BUS articleCommandeBUS = new articleCommande_BUS();
	/*
	 * afficher la tableau détailée de la commande 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showCommande(int idTable) {
		modelTabDetail.setRowCount(0);
        ArrayList<articleCommande> liste = new ArrayList<articleCommande> ();
        liste = articleCommandeBUS.getListeArticleParTable(idTable);
        float montant = 0.f;
        for (articleCommande c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdArticle());
            vec.add(c.getLibelle());
            vec.add(c.getQuantite());
            vec.add(c.getPrixUnit());
            modelTabDetail.addRow(vec);
            montant +=  c.getQuantite() * c.getPrixUnit();
        } 
        String m = Float.toString(montant);
        txtMontant.setText(m);
	}
	
	/*
	 * afficher les buttons signifiés les tables du restaurant
	 */
	private void loadTable(JPanel panelTable) {
		panelTable.removeAll();
		ArrayList<table> tableList = tableBUS.getlisteTable();
		JButton[] button = new JButton[tableList.size()];
        for(int i=0;i<tableList.size();i++)
        {
        	
        	String status = null ;
            Color c;
            button[i] = new JButton() ;
            button[i].setPreferredSize(new Dimension(120,80));
            switch (tableList.get(i).getStatut())
            {
                case 1:
                	c =  new Color(51,204,255); //VERY_LIGHT_BLUE
                	status = " disponible";
                    break;
                default:
                	c = new Color(255,102,102); // LIGHT_PINK
                	status = "occupï¿½e";
                	
                    break;
            }
            button[i].setText("<html>"+(String)tableList.get(i).getLibelle()+"<br>"+status+ "</html>");
            button[i].setBackground(c);
            // Handler des actions avec les boutons Table
            Ecouteur e = new Ecouteur(tableList.get(i).getId(),button[i],modelTabDetail,txtMontant);
    		button[i].addActionListener(e);
    		
            panelTable.add(button[i]);
        }
	}
	
	/*
	 * charger la liste de catégorie
	 */
	 private void loadCategorie() {
        choixCategorie.removeAllItems();
        ArrayList<String> listeCategorie = categorieBUS.getListeCategorie();
        choixCategorie.addItem("");
        for (String cate : listeCategorie) {
            choixCategorie.addItem(cate);
        }
	 }

	 /*
	  * Charger la liste d'article selon le catégorie
	  */
	 private void loadProduitParCategorie(String cate) {
        choixArticle.removeAllItems();
        ArrayList<produitModele> listeProduit = produitBUS.getProduitSelonCategorie(cate);
        choixArticle.addItem(new produitModele());
        if(listeProduit != null) {
        	for (produitModele article : listeProduit) {
                choixArticle.addItem(article);
            }
        }
        
	 }
	 
	 /*
	  * Ajouter un article dans une commande 
	  * il y a trois cas 
	  * 1. la commande n'est pas encore existant , il faut créer une nouvelle commande 
	  * 2. La commande existe déjà, mais l'article est commandée pour la première fois => ajouter une nouvelle ligne à la commande 
	  * 3. La commande existe déjà et l'article est déjà eu sur la commande => augmenter la quantité de l'article 
	  */
	 private void traiteAjouteArticle(JPanel panelTable) {
		  if (choixArticle.getSelectedIndex()>0) {
				 int IDRH = user.getIdrh();
				 int idTable = Ecouteur.getIdTableClique();
				 float prix = ((produitModele)choixArticle.getSelectedItem()).getPrixUnitaire();
				 int idProduit = ((produitModele)choixArticle.getSelectedItem()).getIdProduit();
				 if(idTable <0) {
					 monDialogue dlg = new monDialogue("Veuillez choisir la table !", monDialogue.ERROR_DIALOG);
				 }else {
					 int idCommande = commandeBUS.getUncheckBillIDByTableID(idTable);
					 if(idCommande == -1) {
						 commandeBUS.creationCommande(IDRH, idTable, prix );
						 detailCommandeBUS.addDetailCommande(commandeBUS.getIdDerniereCommande(),idProduit,prix);
						 tableBUS.tableOccupee(idTable);
						 loadTable(panelTable);
					 }
					 else {
						 if(detailCommandeBUS.siCommandeContientProduit(idCommande,idProduit)>0) {
							 detailCommandeBUS.plusUnAProduitExistantACommande(idCommande,idProduit);
						 }else {
							 detailCommandeBUS.addDetailCommande(idCommande,idProduit,prix);
						 }
						 commandeBUS.majMontantCommande(idCommande, prix);
						 loadTable(panelTable);
					 }
					 showCommande(idTable);
					 
				 }
				 
		  }
		  
	 } 
	 
	 /*
	  * déclencher la procédure de paiement 
	  */
	 private void cliqueBtnPaie(int idTable)
     {
		 int idCommande = commandeBUS.getUncheckBillIDByTableID(idTable);

         if (idCommande != -1)
         {
        	 DlgSaisiePaiement_GUI categorieGUI = new DlgSaisiePaiement_GUI(idCommande,user,this);
        	 categorieGUI.setVisible(true);
             
         }
     }
	 
	 /*
	  * réinitialiser la page 
	  */
	 public void resetPage() {
		 Ecouteur.setIdTableClique(-1);
		 loadTable(panelTable);
		 choixCategorie.setSelectedIndex(0);
		 showCommande(-1);
	 }
}

/*
 * événement quand on clique sur un bouton d'une table 
 */
class Ecouteur implements ActionListener{
	private int idTable;
	private JButton btn;
	private DefaultTableModel model;
	private static JButton btnAncien = new JButton();
	private static int idTableClique = -1;
	private JTextField txtMontant;
	
	public Ecouteur(int idTable,JButton btn, DefaultTableModel model, JTextField txtMontant) {
		this.idTable = idTable;
		this.btn = btn;
		this.model = model;
		this.txtMontant = txtMontant;
	}
	
	public Ecouteur() {
		
	}
	
	public static int getIdTableClique() {
		return idTableClique;
	}

	public static void setIdTableClique(int idTableClique) {
		Ecouteur.idTableClique = idTableClique;
	}

	private articleCommande_BUS articleCommandeBUS = new articleCommande_BUS();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		setIdTableClique(idTable) ;
		btnAncien.setBorder(btn.getBorder());
		btn.setBorder(BorderFactory.createLoweredBevelBorder());
		btnAncien = btn;
        model.setRowCount(0);
        ArrayList<articleCommande> liste = new ArrayList<articleCommande> ();
        liste = articleCommandeBUS.getListeArticleParTable(idTable);
        float somme = 0.f;
        for (articleCommande c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdArticle());
            vec.add(c.getLibelle());
            vec.add(c.getQuantite());
            vec.add(c.getPrixUnit());
            model.addRow(vec);
            somme +=  c.getQuantite() * c.getPrixUnit();
        } 
        String m = Float.toString(somme);
        txtMontant.setText(m);
    }     
}

/*
 * événement quand on clique sur button "supprimer"
 */
class EcouteurSupprimer implements ActionListener{

	private monTableau tabDetail;
	private JTextField txtMontant;
	
	public EcouteurSupprimer(monTableau tabDetail, JTextField txtMontant) {
		this.tabDetail = tabDetail;
		this.txtMontant = txtMontant;
	}

	private detailCommande_BUS detailCommandeBUS = new detailCommande_BUS();
	private commande_BUS cmdBUS = new commande_BUS();
	private articleCommande_BUS articleCommandeBUS = new articleCommande_BUS();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = tabDetail.getSelectedRow();
        if (row > -1) {
        	int idProduit = Integer.parseInt(tabDetail.getValueAt(row, 0) + "");
        	int quantite = Integer.parseInt(tabDetail.getValueAt(row, 2) + "");
        	float prixUnit = Float.parseFloat(tabDetail.getValueAt(row, 3) + "");

            int idTable = Ecouteur.getIdTableClique();
            int idCommande = cmdBUS.getUncheckBillIDByTableID(idTable);
            detailCommandeBUS.enleverProduitDeCommande(idCommande,idProduit);
            float montant = (prixUnit * quantite)* -1 ;
            cmdBUS.majMontantCommande(idCommande, montant);
            DefaultTableModel tableModel = (DefaultTableModel) tabDetail.getModel();
            tableModel.setRowCount(0);
            ArrayList<articleCommande> liste = new ArrayList<articleCommande> ();
            liste = articleCommandeBUS.getListeArticleParTable(idTable);
            float somme = 0.f;
            for (articleCommande c : liste) {
                Vector vec = new Vector();
                vec.add(c.getIdArticle());
                vec.add(c.getLibelle());
                vec.add(c.getQuantite());
                vec.add(c.getPrixUnit());
                tableModel.addRow(vec);
                somme +=  c.getQuantite() * c.getPrixUnit();
            } 
            String m = Float.toString(somme);
            txtMontant.setText(m);
        }
    }     
}
