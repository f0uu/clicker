import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Building {
	
	String name;
	int x;
	int y;
	int w,h;
	public int amount = 0;
	public int price;
	public int lvl;
	JPanel panel;
	JLabel nameLabel;
	JLabel amountLabel;
	JButton buy;
	
	Building(String name_, int x_, int y_, int w_, int h_, int price_, int lvl_) {
		name = name_;
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		lvl = lvl_;
		if(amount == 0) price = (price_+10) * lvl * 4;
		else price = (price_+10) * lvl * amount * 10;
	}

	
	JPanel show() {
		
		JPanel panel = new JPanel();
		panel.setBounds(x, y, w, h);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{115, 80, 60, 0};
		gbl_panel.rowHeights = new int[]{h, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		nameLabel = new JLabel(name + ": " + amount);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.BOTH;
		gbc_nameLabel.insets = new Insets(0, 0, 0, 0);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		panel.add(nameLabel, gbc_nameLabel);
		
		amountLabel = new JLabel(price + "$");
		GridBagConstraints gbc_amountLabel = new GridBagConstraints();
		gbc_amountLabel.fill = GridBagConstraints.BOTH;
		gbc_amountLabel.insets = new Insets(0, 0, 0, 50);
		gbc_amountLabel.gridx = 1;
		gbc_amountLabel.gridy = 0;
		panel.add(amountLabel, gbc_amountLabel);
		
		buy = new JButton("BUY");
		GridBagConstraints gbc_buy = new GridBagConstraints();
		gbc_buy.fill = GridBagConstraints.BOTH;
		gbc_buy.gridx = 2;
		gbc_buy.gridy = 0;
		panel.add(buy, gbc_buy);
		return panel;
	}
	

}
