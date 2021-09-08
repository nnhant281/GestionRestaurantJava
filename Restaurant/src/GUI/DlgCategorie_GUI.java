package GUI;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import BUS.categorie_BUS;
import Custom.monDialogue;
import Custom.monTableau;
import DTO.categorie;

public class DlgCategorie_GUI extends javax.swing.JDialog {
	 	private DefaultTableModel tabModelCategorie;
	 	
	    private javax.swing.JButton btnAjouter;
	    private javax.swing.JButton btnSupprimer;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel3;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JLabel lblLibelle;
	    private javax.swing.JPanel pnButton;
	    private javax.swing.JPanel pnTable;
	    private javax.swing.JPanel pnLibelle;
	    private javax.swing.JTable tabCategorie;
	    private javax.swing.JTextField txtLibelle;
	    
	    
	    /*
	     * contructeur
	     */
	    public DlgCategorie_GUI() {
	        initComponents();
	    	tabModelCategorie = new DefaultTableModel();
	    	tabModelCategorie.addColumn("Libell� cat�gorie");
	        tabCategorie.setModel(tabModelCategorie);
	        loadTabCategorie();
	        this.setLocationRelativeTo(null);
	    }

	   	categorie_BUS categorieBUS = new categorie_BUS();

	    private void loadTabCategorie() {
	        tabModelCategorie.setRowCount(0);
	        ArrayList<String> listeCategorie = categorieBUS.getListeCategorie();
	        if (listeCategorie != null) {
	            for (String cat : listeCategorie) {
	                Vector vec = new Vector();
	                vec.add(cat);
	                tabModelCategorie.addRow(vec);
	            }
	        }
	    }
	    
	    /*
	     * cr�er l'interface de la gestion des cat�gorie
	     */
	    @SuppressWarnings("unchecked")
	    private void initComponents() {

	        jPanel1 = new javax.swing.JPanel();
	        jLabel1 = new javax.swing.JLabel();
	        pnTable = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        tabCategorie = new monTableau();
	        jPanel3 = new javax.swing.JPanel();
	        pnLibelle = new javax.swing.JPanel();
	        lblLibelle = new javax.swing.JLabel();
	        txtLibelle = new javax.swing.JTextField();
	        pnButton = new javax.swing.JPanel();
	        btnAjouter = new javax.swing.JButton();
	        btnSupprimer = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	        setLocation(new java.awt.Point(0, 0));
	        setModal(true);

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
	        jLabel1.setText("Gestion des cat�gories");
	        jPanel1.add(jLabel1);

	        tabCategorie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        tabCategorie.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Cat�gorie"
	            }
	        ));
	        tabCategorie.getTableHeader().setReorderingAllowed(false);
	        tabCategorie.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	            	tabCategorieMouseClicked(evt);
	            }
	        });
	        jScrollPane1.setViewportView(tabCategorie);

	        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
	        pnTable.setLayout(pnTableLayout);
	        pnTableLayout.setHorizontalGroup(
	            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
	        );
	        pnTableLayout.setVerticalGroup(
	            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(pnTableLayout.createSequentialGroup()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, Short.MAX_VALUE))
	        );

	        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

	   
	        lblLibelle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        lblLibelle.setText("Libelle cat�gorie");
	        pnLibelle.add(lblLibelle);

	        txtLibelle.setColumns(15);
	        txtLibelle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        pnLibelle.add(txtLibelle);

	        jPanel3.add(pnLibelle);

	        btnAjouter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        btnAjouter.setText("Ajouter");
	        btnAjouter.setPreferredSize(new java.awt.Dimension(80, 35));
	        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnAjouterActionPerformed(evt);
	            }
	        });
	        pnButton.add(btnAjouter);

	        btnSupprimer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
	        btnSupprimer.setText("Supprimer");
	        btnSupprimer.setPreferredSize(new java.awt.Dimension(80, 35));
	        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnSupprimerActionPerformed(evt);
	            }
	        });
	        pnButton.add(btnSupprimer);

	        jPanel3.add(pnButton);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
	            .addComponent(pnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	        );

	        pack();
	    }
	    
	    
	    /*
	     * �v�nement quand on clique sur une ligne de la table Cat�gorie, 
	     * des informations de la ligne va afficher sur les champs 
	     */
	    private void tabCategorieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiMouseClicked
	        int row = tabCategorie.getSelectedRow();
	        if (row > -1) {
	            String libelle = tabCategorie.getValueAt(row, 0) + "";
	            txtLibelle.setText(libelle);
	        }
	    }

	    /*
	     * �v�nement de bouton "Ajouter"
	     * cliquer sur la bouton "Ajouter" pour ajouter un nouvelle cat�gorie
	     */
	    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
	        // TODO add your handling code here:
	        if (categorieBUS.ajouteCategorie(txtLibelle.getText())) {
	        	loadTabCategorie();
	        }
	    }
	    
	    /*
	     * �v�nement de bouton "Supprimer"
	     * cliquer sur la bouton "Ajouter" pour ajouter un nouvelle cat�gorie
	     */
	    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
	        monDialogue dlg = new monDialogue("Etes-vous s�r de vouloir supprimer cette cat�gorie?", monDialogue.WARNING_DIALOG);
	        if (dlg.OK_OPTION == dlg.getAction()) {
	            String libelle = txtLibelle.getText();
	            if (categorieBUS.suppressionCategorie(libelle)) {
	            	loadTabCategorie();
	            }
	        }
	    }

	  
}
