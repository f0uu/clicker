import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Drawing extends JComponent {
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Shape drawLine = new Line2D.Float(40, 60, 25, 150);
		g2.setPaint(Color.black);
		g2.draw(drawLine);
		g2.drawString("skksks", 20, 100);
	}

}
