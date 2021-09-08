package Custom;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class monTableau extends JTable {
	
	public monTableau() {
		
		JTableHeader TblHeader = this.getTableHeader();
		TblHeader.setBackground(Color.ORANGE);
		TblHeader.setFont(new Font("Tahoma", Font.PLAIN,14));
	
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
		this.setSelectionBackground(Color.ORANGE);
		this.setGridColor(Color.GRAY);
		this.setSelectionForeground(Color.WHITE);
		this.setFont(new Font("Tahoma", Font.PLAIN,12));
		this.setRowHeight(30);
		this.setAutoCreateRowSorter(true);		
	}
}
