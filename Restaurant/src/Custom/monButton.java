package Custom;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

public class monButton extends JButton {
		
	private static final long serialVersionUID = -2920847727576536736L;

	public monButton(String btnName, Icon btnImage) {		
		this.setText(btnName);
		this.setIcon(btnImage);
		this.setPreferredSize(new Dimension(120,30));
		this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));	
	}
}
