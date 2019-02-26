import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Building extends JPanel {
	
	String name;
	int x;
	int y;
	int w,h;
	public int amount = 0;
	public int price;
	public int lvl;
	public int power;
	JLabel nameLabel;
	JLabel amountLabel;
	JButton buy;
	GridBagConstraints frameConstraints = new GridBagConstraints();
	
	Building(String name_, int lvl_, int power_) {
		name = name_;
//		x = x_;
//		y = y_;
//		w = w_;
//		h = h_;
		lvl = lvl_+1;
		power = power_;
		calculatePrice();
			
		setBounds(x, y, w, h);
		setLayout(new GridLayout(1,3,0,0));
		nameLabel = new JLabel(name + ": " + amount);
		nameLabel.setPreferredSize(new Dimension(100,25));
		nameLabel.setHorizontalAlignment(SwingConstants.LEADING);
		amountLabel = new JLabel(price + "$ ");
		amountLabel.setPreferredSize(new Dimension(0,0));
		amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buy = new JButton("BUY");
		buy.setHorizontalAlignment(SwingConstants.LEFT);
		
		add(nameLabel);
		add(amountLabel);
		add(buy);

	}
	
	int calculatePrice() {
		price = (4 * (amount * (amount + 10))) + (int)(Math.pow(lvl, 5));
		price *= power;
		return price;
	}
	
	void reset() {
		amount = 0;
		price = (4 * (amount * (amount + 10))) + (int)(Math.pow(lvl, 5));
		price *= power;
		nameLabel.setText(name + ": " + amount);
		amountLabel.setText(price + " $");
	}
	

}
