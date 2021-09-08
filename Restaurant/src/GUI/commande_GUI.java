package GUI;

import static Main.main.changeLNF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BUS.table_BUS;
import BUS.articleCommande_BUS;
import BUS.categorie_BUS;
import BUS.commande_BUS;
import BUS.detailCommande_BUS;
import BUS.produit_BUS;
import Custom.monButton;
import Custom.monTableau;
import Custom.transparentPanel;
import DAO.commande_DAO;
import DTO.articleCommande;
import DTO.client;
import DTO.detailCommande;
import DTO.produitModele;
import DTO.table;


public class commande_GUI extends JPanel{
	
	public commande_GUI() {
		changeLNF("FlatLaf");
		addControls();
        addEvents();
	}
	
	private produit_BUS produitBUS = new produit_BUS();
    private categorie_BUS categorieBUS = new categorie_BUS();
	private table_BUS tableBUS = new table_BUS();
	private commande_BUS commandeBUS = new commande_BUS();
	private detailCommande_BUS detailCommandeBUS = new detailCommande_BUS();
	final Color colorPanel = new Color(247, 247, 247);
	JLabel titre,label;
	JButton btnAjoute, btnSupp, btnPaie;
    monTableau tabDetail;
    DefaultTableModel modelTabDetail;
    JComboBox<String> choixCategorie = new JComboBox<String>();	
    JComboBox<produitModele> choixArticle = new JComboBox<produitModele>();	
	
	private void addControls() {
		
		modelTabDetail = new DefaultTableModel();
	    modelTabDetail.addColumn("ID ");
	    modelTabDetail.addColumn("Libell�");
	    modelTabDetail.addColumn("Quantit�");
	    modelTabDetail.addColumn("Prix");
	    tabDetail = new monTableau();
	    tabDetail.setModel(modelTabDetail);
        
		Font font = new Font("Tahoma", Font.PLAIN, 20);

        this.setLayout(new BorderLayout());
        this.setBackground(colorPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelTop = new transparentPanel();
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
        
		JPanel pnTitle = new transparentPanel();
        titre = new JLabel("<html><h1>ORDER</h1></html>");
        pnTitle.add(titre);
        panelTop.add(pnTitle);
        
        this.add(panelTop);
        
        JPanel panelRemplir= new transparentPanel();
        panelRemplir.setLayout(new BoxLayout(panelRemplir, BoxLayout.X_AXIS));
        
        //================PANEL TABLE=========
        ArrayList<table> tableList = tableBUS.getlisteTable();
        JPanel panelTable = new transparentPanel();
        panelTable.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        loadTable(panelTable);
     
        JScrollPane scrtabTable = new JScrollPane(panelTable);
        panelRemplir.add(scrtabTable);
        
        /*
		============================================================
           					PANEL INFO 
		============================================================
		 */
        
        JPanel panelDetail= new transparentPanel();
        panelDetail.setLayout(new BoxLayout(panelDetail, BoxLayout.Y_AXIS));
        
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
        
        Dimension lblSize = lblCategorie.getPreferredSize();
        lblCategorie.setPreferredSize(lblSize);
        lblArticle.setPreferredSize(lblSize);
        
        choixCategorie.setPreferredSize( new Dimension( 400, 30 ) );
        choixArticle.setPreferredSize( new Dimension( 400, 30 ) );
        
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
		
		panelDetail.add(pnAjoute);
        
        
      //================PANEL TABLE=========
        JPanel pnAffiche = new transparentPanel();
        pnAffiche.setLayout(new BoxLayout(pnAffiche, BoxLayout.Y_AXIS));

        
        JScrollPane scrtabDetail = new JScrollPane(tabDetail);
        panelDetail.add(scrtabDetail);

        
        //========= TEXTE MONTANT ==========//
        JPanel pnText = new transparentPanel();
        pnText.setLayout(new BoxLayout(pnText, BoxLayout.Y_AXIS));
        
        JPanel pnLabel = new transparentPanel();
        label = new JLabel("<html><h3>Montant : </h3></html>");
        pnLabel.add(label);
        pnText.add(pnLabel);
        
        panelDetail.add(pnText);
        
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
        
        
        panelDetail.add(pnBtn);
        panelRemplir.add(panelDetail);
        this.add(panelRemplir);
        
        loadCategorie();
	}
	
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
            	traiteAjouteArticle();
            }
        });
	}
	private articleCommande_BUS articleCommandeBUS = new articleCommande_BUS();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showCommande(int idTable) {
		modelTabDetail.setRowCount(0);
        ArrayList<articleCommande> liste = new ArrayList<articleCommande> ();
        liste = articleCommandeBUS.getListeArticleParTable(idTable);
        for (articleCommande c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdArticle());
            vec.add(c.getLibelle());
            vec.add(c.getQuantite());
            vec.add(c.getPrixUnit());
            modelTabDetail.addRow(vec);
        } 
	}
	
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
                	status = "occup�e";
                    break;
            }
            button[i].setText("<html>"+(String)tableList.get(i).getLibelle()+"<br>"+status+ "</html>");
            button[i].setBackground(c);
            // Handler des actions avec les boutons Table
            Ecouteur e = new Ecouteur(tableList.get(i).getId(),button[i],modelTabDetail);
    		button[i].addActionListener(e);
    		
            panelTable.add(button[i]);
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
	 
	 private void traiteAjouteArticle() {
		  if (choixArticle.getSelectedIndex()>0) {
				 int IDRH = 1;
				 int idTable = Ecouteur.getIdTableClique();
				 float prix = ((produitModele)choixArticle.getSelectedItem()).getPrixUnitaire();
				 int idProduit = ((produitModele)choixArticle.getSelectedItem()).getIdProduit();
				 int idCommande = commandeBUS.getUncheckBillIDByTableID(idTable);
				 if(idCommande == -1) {
					 commandeBUS.creationCommande(IDRH, idTable, prix );
					 detailCommandeBUS.addDetailCommande(commandeBUS.getIdDerniereCommande(),idProduit,prix);
					 tableBUS.tableOccupee(idTable);
				 }
				 else {
					 if(detailCommandeBUS.siCommandeContientProduit(idCommande,idProduit)) {
						 detailCommandeBUS.plusUnAProduitExistantACommande(idCommande,idProduit);
					 }else {
						 detailCommandeBUS.addDetailCommande(idCommande,idProduit,prix);
					 }
					 commandeBUS.majMontantCommande(idCommande, prix);
				 }
				 showCommande(idTable);
		  }
		  
	 } 
	 
	 public void resetPage() {
		 
	 }
}

class Ecouteur implements ActionListener{
	private int idTable;
	private JButton btn;
	private DefaultTableModel model;
	private static JButton btnAncien = new JButton();
	private static int idTableClique = -1;
	
	public Ecouteur(int idTable,JButton btn, DefaultTableModel model) {
		this.idTable = idTable;
		this.btn = btn;
		this.model = model;
		this.idTableClique = idTable;
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
		btnAncien.setBorder(btn.getBorder());
		btn.setBorder(BorderFactory.createLoweredBevelBorder());
		btnAncien = btn;
        model.setRowCount(0);
        ArrayList<articleCommande> liste = new ArrayList<articleCommande> ();
        liste = articleCommandeBUS.getListeArticleParTable(idTable);
        for (articleCommande c : liste) {
            Vector vec = new Vector();
            vec.add(c.getIdArticle());
            vec.add(c.getLibelle());
            vec.add(c.getQuantite());
            vec.add(c.getPrixUnit());
            model.addRow(vec);
        } 
    }     
}

