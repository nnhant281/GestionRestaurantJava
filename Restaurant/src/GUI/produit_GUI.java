package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DTO.produitModele;
import BUS.categorie_BUS;
import BUS.produit_BUS;
import Custom.ChoisirFichier;
import Custom.monButton;
import Custom.monDialogue;
import Custom.monTableau;
import Custom.transparentPanel;

import static Main.main.changeLNF;


@SuppressWarnings("serial")
public class produit_GUI extends JPanel{
	
	public produit_GUI() {
		changeLNF("Nimbus");
		addControlsProduit();
		evenementProduit();
	}
	
	produit_BUS produitBUS = new produit_BUS();
    static categorie_BUS categorieBUS = new categorie_BUS();
    final Color colorPanel = new Color(247, 247, 247);
    monTableau tabProduit;
    DefaultTableModel model;
    JTextField txtIdProduit, txtLibelleProduit, txtPrixUnitaire,txtPhoto;
	static JComboBox<String> choixCategorie = new JComboBox<String>();	
	monButton btnAjoute, btnMod, btnSupp, btnReset;	
	JButton btnRecherche, btnImg ;
	JTextField txtRecherche;
	
	Font f = new Font("TimesRoman", Font.BOLD, 16);
	
	ArrayList<JButton> listButtons = new ArrayList<JButton>();
	
	JPanel panelTop, panelCenter;
	
	JPanel panelRemplir, panelButtons, panelRecherche, panelTab, panelImage;
	
	JLabel idProduit,libelleProduit,categorie, prixUnitaire, titre, photo, recherche;
	
	ImageIcon iconAjoute, iconModifier, iconSupprimer, iconRechercher, iconReset;
	JScrollPane pane;


	private void addControlsProduit() {
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(colorPanel);

        JPanel pnTitle = new transparentPanel();
        titre = new JLabel("<html><h1>Gestion de produits</h1></html>");
        pnTitle.add(titre);
        this.add(pnTitle);

        panelRemplir= new transparentPanel();
        panelRemplir.setLayout(new BoxLayout(panelRemplir, BoxLayout.X_AXIS));

        //================PANEL INPUT=========
        JPanel pnTextField = new transparentPanel();
        pnTextField.setLayout(new BoxLayout(pnTextField, BoxLayout.Y_AXIS));

        idProduit = new JLabel("ID produit");
        libelleProduit = new JLabel("Libellé");
        categorie = new JLabel("Catégorie");
        prixUnitaire = new JLabel("Prix unitaire");
        
        txtIdProduit = new JTextField(15);
        txtIdProduit.setEditable(false);
        txtLibelleProduit = new JTextField(15);
        choixCategorie = new JComboBox<String>();
        txtPrixUnitaire = new JTextField(15);


        JPanel pnMa = new transparentPanel();
        idProduit.setFont(font);
        txtIdProduit.setFont(font);
        pnMa.add(idProduit);
        pnMa.add(txtIdProduit);
        pnTextField.add(pnMa);

        JPanel pnTen = new transparentPanel();
        libelleProduit.setFont(font);
        txtLibelleProduit.setFont(font);
        pnTen.add(libelleProduit);
        pnTen.add(txtLibelleProduit);
        pnTextField.add(pnTen);

        JPanel pnLoai = new transparentPanel();
        categorie.setFont(font);
        choixCategorie.setFont(font);
        choixCategorie.setPreferredSize(txtIdProduit.getPreferredSize());
        pnLoai.add(categorie);
        pnLoai.add(choixCategorie);
        pnTextField.add(pnLoai);

        JPanel pnSoLuong = new transparentPanel();
        prixUnitaire.setFont(font);
        txtPrixUnitaire.setFont(font);
        pnSoLuong.add(prixUnitaire);
        pnSoLuong.add(txtPrixUnitaire);
        pnTextField.add(pnSoLuong);

        Dimension lblSize = prixUnitaire.getPreferredSize();
        idProduit.setPreferredSize(lblSize);
        libelleProduit.setPreferredSize(lblSize);
        categorie.setPreferredSize(lblSize);
        prixUnitaire.setPreferredSize(lblSize);

        panelRemplir.add(pnTextField);

        /*
		============================================================
           					PANEL IMAGE
		============================================================
		 */
        panelImage = new transparentPanel();
        panelImage.setLayout(new BoxLayout(panelImage, BoxLayout.Y_AXIS));

        JPanel pnChuaAnh = new transparentPanel();
        pnChuaAnh.setPreferredSize(new Dimension((int) panelImage.getPreferredSize().getWidth(), 250));
        photo = new JLabel();
        photo.setPreferredSize(new Dimension(200, 200));
        photo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        photo.setIcon(getImage(""));
        pnChuaAnh.add(photo);
        panelImage.add(pnChuaAnh);

        JPanel pnButtonAnh = new transparentPanel();
        pnButtonAnh.setPreferredSize(new Dimension(
                (int) pnChuaAnh.getPreferredSize().getHeight(), 40));
        btnImg = new JButton("Choissisez l'image");
        btnImg.setFont(font);
        pnButtonAnh.add(btnImg);
        pnChuaAnh.add(pnButtonAnh);

        panelRemplir.add(panelImage);
        this.add(panelRemplir);

    	/*
		============================================================
            				CREATION DES BUTTONS
		============================================================
		 */
        panelButtons = new transparentPanel();
		iconAjoute = new ImageIcon(new ImageIcon("images/Buttons/ajoute.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconModifier = new ImageIcon(new ImageIcon("images/Buttons/maj.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconSupprimer = new ImageIcon(new ImageIcon("images/Buttons/supprime.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconRechercher = new ImageIcon(new ImageIcon("images/Buttons/recherche.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		iconReset = new ImageIcon(new ImageIcon("images/Buttons/reset.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		btnAjoute = new monButton("Ajouter", iconAjoute);		
		btnMod = new monButton("Modifier", iconModifier);		
		btnSupp = new monButton("Supprimer", iconSupprimer);
		btnRecherche = new monButton("Rechercher", iconRechercher);
		btnReset = new monButton("RÃ©initialiser", iconReset);
		
		Font fontButton = new Font("Tahoma", Font.PLAIN, 14);
		btnAjoute.setFont(fontButton);
		btnMod.setFont(fontButton);
		btnSupp.setFont(fontButton);
		btnRecherche.setFont(fontButton);
		btnReset.setFont(fontButton);
		
		panelButtons.setLayout(new FlowLayout());
		panelButtons.add(btnAjoute);
		panelButtons.add(btnMod);
		panelButtons.add(btnSupp);
		panelButtons.add(btnRecherche);
		panelButtons.add(btnReset);
		
		listButtons.add(btnAjoute);
		listButtons.add(btnMod);
		listButtons.add(btnSupp);
		listButtons.add(btnRecherche);
		listButtons.add(btnReset);
		
		panelRecherche = new transparentPanel();
        recherche = new JLabel("Recherche");
        recherche.setFont(font);
        txtRecherche = new JTextField("Recherche par libellé...",20);
        txtRecherche.setFont(font);
        txtRecherche.setForeground(Color.GRAY);
        panelRecherche.add(recherche);
        panelRecherche.add(txtRecherche);
        this.add(panelRecherche);
        
        Dimension btnSize = btnReset.getPreferredSize();
        btnAjoute.setPreferredSize(btnSize);
        btnMod.setPreferredSize(btnSize);
        btnSupp.setPreferredSize(btnSize);
        btnRecherche.setPreferredSize(btnSize);
        btnReset.setPreferredSize(btnSize);
        
        this.add(panelButtons);

        /*
		============================================================
           					CREATION DE TABLE
		============================================================
		 */
        panelTab = new transparentPanel(new BorderLayout());

        tabProduit = new monTableau();
		model = new DefaultTableModel();
		Object[] columns = {"ID Produit","Libellé","Catégorie","Prix Unitaire","Image"};
		model.setColumnIdentifiers(columns);
		tabProduit.setModel(model);

        tabProduit.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tabProduit.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        TableColumnModel columnModel = tabProduit.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(77);
        columnModel.getColumn(1).setPreferredWidth(282);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(85);
        columnModel.getColumn(4).setPreferredWidth(0);

        pane  = new JScrollPane(tabProduit);
        pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(10,10,10,10);
        panelTab.add(pane, BorderLayout.CENTER);
        this.add(pane);

        loadCategorie();
        loadTabProduit();
    }
	
	private void evenementProduit() {
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	resetPage();
            }
        });

        tabProduit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CliqueTabProduit();
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

        choixCategorie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteAjouteCategorie();
            }
        });

        btnAjoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteAjouteProduit();
                resetPage();
            }
        });

        btnMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteModifProduit();
                resetPage();
            }
        });

        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteSuppressionProduit();
            }
        });

        btnImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteChoisirImage();
            }
        });

        btnRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteRecherche();
            }
        });

        txtRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traiteRecherche();
            }
        });
        
    }

	 private void loadImage(String img) {
	        photo.setIcon(getImage(img));
	    }

	    private void CliqueTabProduit() {
	        int row = tabProduit.getSelectedRow();
	        if (row > -1) {
	            String id = tabProduit.getValueAt(row, 0) + "";
	            String libelle = tabProduit.getValueAt(row, 1) + "";
	            String cate = tabProduit.getValueAt(row, 2) + "";
	            String prix = tabProduit.getValueAt(row, 3) + "";
	            String img = tabProduit.getValueAt(row, 4) + "";

	            txtIdProduit.setText(id);
	            txtLibelleProduit.setText(libelle);
	            txtPrixUnitaire.setText(prix);

	            int flag = 0;
	            for (int i = 0; i < choixCategorie.getItemCount(); i++) {
	                if (choixCategorie.getItemAt(i).contains(cate)) {
	                    flag = i;
	                    break;
	                }
	            }
	            choixCategorie.setSelectedIndex(flag);
	            loadImage("images/Produit/" + img);
	        }
	    }

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		private void loadTabProduit() {
	        produitBUS.lireListeProduit();
	        model.setRowCount(0);

	        ArrayList<produitModele> listeProduit = produitBUS.getListeProduit();

	        for (produitModele produit : listeProduit) {
	            Vector vec = new Vector();
	            vec.add(produit.getIdProduit());
	            vec.add(produit.getLibelleProduit());
	            vec.add(produit.getCategorie());
	            vec.add(produit.getPrixUnitaire());
	            vec.add(produit.getPhoto());
	           	model.addRow(vec);
	        }
	    }

	    private void loadCategorie() {
	        choixCategorie.removeAllItems();

	        ArrayList<String> listeCategorie = categorieBUS.getListeCategorie();
	        choixCategorie.addItem("");
	        for (String cate : listeCategorie) {
	            choixCategorie.addItem(cate);
	        }
	        choixCategorie.addItem("Autres...");
	    }

	    /*
	     * cliquer sur la ligne "Autres.." pour ajouter des catégories 
	     */
	    private void traiteAjouteCategorie() {
	        int x = choixCategorie.getSelectedIndex();
	        if (x == choixCategorie.getItemCount() - 1) {
	        	DlgCategorie_GUI categorieGUI = new DlgCategorie_GUI();
	        	categorieGUI.setVisible(true);
	            loadCategorie();
	        }
	    }

	    private void traiteAjouteProduit() {
	    	String img = fichierImg.getName();
	        System.out.println(fichierImg.getName());
	        boolean flag = produitBUS.ajouteProduit(txtLibelleProduit.getText(),
	        		(String)choixCategorie.getSelectedItem(),
	                txtPrixUnitaire.getText(),
	        		img);
	        produitBUS.lireListeProduit();
	        loadTabProduit();
	        enregistreImage();
	    }


	File fichierImg;
	
	private void traiteModifProduit() {
        String img = fichierImg.getName();
        boolean flag = produitBUS.majProduit(txtIdProduit.getText(),
                txtLibelleProduit.getText(),
                (String)choixCategorie.getSelectedItem(),
                txtPrixUnitaire.getText(),
        		img);
        produitBUS.lireListeProduit();
        loadTabProduit();
        enregistreImage();
    }

    private void traiteSuppressionProduit() {
        
    }
	/*
	 * enregister le fichier d'image 
	 */
	 private void enregistreImage() {
	        BufferedImage bImage = null;
	        try {
	            File initialImage = new File(fichierImg.getPath());
	            bImage = ImageIO.read(initialImage);

	            ImageIO.write(bImage, "png", new File("images/Produit/" + fichierImg.getName()));

	        } catch (IOException e) {
	            System.out.println("Exception occured :" + e.getMessage());
	        }
	    }

	    private void traiteChoisirImage() {
	        JFileChooser fileChooser = new ChoisirFichier("images/Produit/");
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg");
	        fileChooser.setFileFilter(filter);
	        int returnVal = fileChooser.showOpenDialog(null);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	fichierImg = fileChooser.getSelectedFile();
	            photo.setIcon(getImage(fichierImg.getPath()));
	        }
	    }
	/*
	 * afficher l'image de produit 
	 */
	private ImageIcon getImage(String src) {
        src = src.trim().equals("") ? "default.png" : src;
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("images/Produit/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fichierImg = new File(src);
        } catch (IOException e) {
        	fichierImg = new File("imgs/anhthe/avatar.jpg");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void traiteRecherche() {
        String libelle = txtRecherche.getText().toLowerCase();
        model.setRowCount(0);
        ArrayList<produitModele> listeProduit = null;
        listeProduit = produitBUS.getProduitSelonLibelle(libelle);
        for (produitModele produit : listeProduit) {
            Vector vec = new Vector();
            vec.add(produit.getIdProduit());
            vec.add(produit.getLibelleProduit());
            vec.add(produit.getCategorie());
            vec.add(produit.getPrixUnitaire());
            vec.add(produit.getPhoto());
           	model.addRow(vec);
        }
        if (listeProduit.size() == 0) {
        	monDialogue dlg = new monDialogue("Aucun produit trouvé ", monDialogue.INFO_DIALOG);
        }
        
    }
	
	private void resetPage() {
		loadImage("");
        loadTabProduit();
        txtIdProduit.setText("");
        txtLibelleProduit.setText("");
        txtPrixUnitaire.setText("");
        choixCategorie.setSelectedIndex(0);
	}
}

