package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BUS.changerMDP_BUS;
import Custom.monDialogue;


@SuppressWarnings("serial")
public class changerMDP_GUI{
	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	
	JFrame frame;
	JLabel identifiant, ancienMDP, nouveauMDP, confirmeMDP;
	JTextField txtIdentifiant;
	JPasswordField txtAncienMDP, txtNouveauMDP, txtConfirmeMDP;
	JButton btnChangerMDP, btnQuitter;
	Container cprincipal;
	JPanel panel;

	changerMDP_BUS changerMDPBUS = new changerMDP_BUS();
	login_GUI login_GUI = new login_GUI();
	
	public changerMDP_GUI () {
		addComponents();
		addEvents();
		login_GUI.frame.setEnabled(false);
	}
	
	public void addComponents() {
		/*
		============================================================
	           			CREATION DES COMPOSANTS
		============================================================
		*/	
		frame = new JFrame("<HTML><H2>CHANGER MOT DE PASSE<H2><HTML>");		
		cprincipal = frame.getContentPane();	
		panel = new JPanel();
		cprincipal.add(panel);
		frame.setBackground(Color.ORANGE);
		
		identifiant = new JLabel("Identifiant");
		ancienMDP = new JLabel("Ancien MDP");
		nouveauMDP = new JLabel("Nouveau MDP");
		confirmeMDP = new JLabel("Confirme MDP");
		
		identifiant.setFont(font);
		ancienMDP.setFont(font);
		nouveauMDP.setFont(font);
		confirmeMDP.setFont(font);
		
		txtIdentifiant = new JTextField("", 13);
		txtAncienMDP = new JPasswordField("", 13);
		txtNouveauMDP = new JPasswordField("", 13);
		txtConfirmeMDP = new JPasswordField("", 13);
		
		txtIdentifiant.setFont(font);
		txtAncienMDP.setFont(font);
		txtNouveauMDP.setFont(font);
		txtConfirmeMDP.setFont(font);
		
		/*
		============================================================
	           			CREATION DES BUTTONS
		============================================================
		*/	
		btnChangerMDP = new JButton("S'actualiser");
		btnQuitter = new JButton("Quitter");
		
		btnChangerMDP.setFont(fontButton);
		btnQuitter.setFont(fontButton);
		
		/*
		============================================================
	           			ARRANGEMENTS DES COMPOSANTS
		============================================================
		*/	
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 1;
		gc.gridy = 0;
		panel.add(identifiant, gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		panel.add(txtIdentifiant, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		panel.add(ancienMDP, gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		panel.add(txtAncienMDP, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		panel.add(nouveauMDP, gc);
		
		gc.gridx = 2;
		gc.gridy = 2;
		panel.add(txtNouveauMDP, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		panel.add(confirmeMDP, gc);
		
		gc.gridx = 2;
		gc.gridy = 3;
		panel.add(txtConfirmeMDP, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 4;
		panel.add(btnChangerMDP, gc);
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 2;
		gc.gridy = 4;
		panel.add(btnQuitter, gc);
		
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addEvents() {
		
		btnChangerMDP.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteChangerMDP();				
	        }
		});
		
		btnQuitter.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				frame.dispose();
				login_GUI.frame.setEnabled(true);
							
	        }
		});	
	}
	
	private void traiteChangerMDP(){
	
		if (changerMDPBUS.changerMDP(txtIdentifiant, txtAncienMDP, txtNouveauMDP, txtConfirmeMDP)) {
			new monDialogue("Votre mot de passe a été modifié !!!", monDialogue.INFO_DIALOG);
			frame.dispose();		
		}
	}
}
