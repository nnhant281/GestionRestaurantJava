package Custom;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class transparentPanel extends JPanel{
	 public transparentPanel() {
	        this.setOpaque(false);
	    }
	    
	    public transparentPanel(LayoutManager layout) {
	        this.setLayout(layout);
	    }

}
