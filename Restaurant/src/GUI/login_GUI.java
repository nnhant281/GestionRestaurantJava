package GUI;

import static Main.main.changeLNF;

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
import BUS.login_BUS;
import Custom.ConvertCharToString;
import DTO.compteModele;

public class login_GUI  extends javax.swing.JFrame {
	
	
	JFrame frame;	
	Container cprincipal;
	JPanel panelCenter;
	
	JLabel id, mdp;	
	Font font = new Font("Tahoma", Font.PLAIN, 18);
	Font fontButton = new Font("Tahoma", Font.PLAIN, 16);
	
	JTextField txtID = new JTextField("");	
	JPasswordField txtMDP = new JPasswordField("");	
	JButton btnConnect, btnChangerMDP;

	login_BUS loginBUS = new login_BUS();
	changerMDP_GUI changerMDP;
	mainGUI mainGUI;
	compteModele compte = new compteModele();
	
	public login_GUI() {
		changeLNF("Nimbus");
		addComponents();
		addEvents();
	}
	
	public void addComponents() {
		
		frame = new JFrame("LOGIN FORM");		
		cprincipal = frame.getContentPane();	
		panelCenter = new JPanel();
		cprincipal.add(panelCenter);
		frame.setBackground(Color.ORANGE);
		
		/*
		============================================================
							COMPOSANTS
		============================================================
		 */
		
		id = new JLabel("Identifiant");		
		mdp = new JLabel("Mot de passe");		
		txtID = new JTextField(20);	
		txtMDP = new JPasswordField(20);	
		
		id.setFont(font);
		mdp.setFont(font);
		txtID.setFont(font);
		txtMDP.setFont(font);
		
		
		btnConnect = new JButton("Se connecter");
		btnChangerMDP = new JButton("Changer MDP");
		
		btnConnect.setFont(fontButton);
		btnChangerMDP.setFont(fontButton);
		/*
		============================================================
						ARRANGEMENT DES COMPOSANTS
		============================================================
		 */
			
		panelCenter.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 2;		
		gc.gridy = 0;		
		panelCenter.add(id, gc);
		
		gc.gridx = 2;	
		gc.gridy = 1;	
		panelCenter.add(mdp, gc);
		
		gc.gridx = 3;	
		gc.gridy = 0;	
		panelCenter.add(txtID, gc);
		
		gc.gridx = 3;	
		gc.gridy = 1;
		panelCenter.add(txtMDP, gc);
		
		gc.anchor = GridBagConstraints.LINE_START;	
		gc.gridx = 3;	
		gc.gridy = 2;
		panelCenter.add(btnConnect, gc);
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 3;
		gc.gridy = 2;
		
		panelCenter.add(btnChangerMDP, gc);
		
		/*
		============================================================
						DIVERS
		============================================================
		 */

		frame.setVisible(true);	
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	public void addEvents() {
		
		btnConnect.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				traiteAuthentifier();
	        }
		});
		
		btnChangerMDP.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				changerMDP = new changerMDP_GUI();
				frame.setEnabled(false);
	        }
		});		
	}
	
	public void traiteAuthentifier() {	
		String mdp = ConvertCharToString.convert(txtMDP);

		compte= loginBUS.authentifier(txtID.getText(), mdp);
		
		if (compte.getHabilitation() != 0) {
			mainGUI = new mainGUI(compte);	
			mainGUI.setVisible(true);
            this.setVisible(false);
		}		
	}
}
