package Custom;

import java.awt.*;

import javax.swing.*;

public class imagePanel extends JPanel {
	private Image img;

    public imagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public imagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        this.setSize(size);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
