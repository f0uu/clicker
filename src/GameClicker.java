import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GameClicker implements ActionListener,  KeyListener {

	public JFrame frame;
	
	///////////money
    float money = 1;
	float off = 1;
	JLabel perClick;
	JPanel panelMoney;
	JLabel labelMoney;
	JLabel amountMoney;
	JButton buttonMoney;
	
	//////////toggle  buttons
	ButtonGroup group = new ButtonGroup();
	JPanel panelMenu;
	JToggleButton upgrades;
	JToggleButton earnings;
	JToggleButton look;
	JToggleButton menu;
	
	//////////upgrades
	int w = 280;
	int h = 35;
	String[] names = {"Slaves", "CPU", "IT rooms", "Own buildings", "Ads", "buraki"};
	ArrayList<Building> buildings = new ArrayList<Building>();
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameClicker window = new GameClicker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GameClicker() {
		initialize();
		menu();
		adding();
	}
	
	private void adding() {
		
		buttonMoney.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				money += off;
				amountMoney.setText(money + " $");
			}
		});		
		
		buildings.get(0).buy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(money >= buildings.get(0).price) {
					buildings.get(0).amount++;
					money-= buildings.get(0).price;
					off+= buildings.get(0).lvl;
					amountMoney.setText(money + " $");
					perClick.setText("Per: " + off);
				}
				
			}
		});
		
	buildings.get(1).buy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(money >= buildings.get(1).price) {
					buildings.get(1).amount++;
					money -= buildings.get(1).price;
					off+= buildings.get(1).lvl;
					amountMoney.setText(money + " $");
					perClick.setText("Per: " + off);
				}
				
			}
		});
	}
	
	private void menu() {
		int spc = 40;
		int y = 140;
		for(int i = 0; i < 6; i++) {
			buildings.add(new Building(names[i], 5, (y+=spc), w, h, i+1, i+1));
			frame.getContentPane().add(buildings.get(i).show());
		}
		
		upgrades.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setSize(300,480);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("clicker wowo ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelMoney = new JPanel();
		panelMoney.setBackground(Color.BLACK);
		panelMoney.setBounds(10, 11, 275, 31);
		frame.getContentPane().add(panelMoney);
		panelMoney.setLayout(new GridLayout(0, 3, 0, 0));
		
		labelMoney = new JLabel("Bitcoins:");
		labelMoney.setForeground(Color.WHITE);  
		panelMoney.add(labelMoney);
		labelMoney.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		amountMoney = new JLabel(money + "$");
		amountMoney.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelMoney.add(amountMoney);
		amountMoney.setForeground(Color.WHITE);
		amountMoney.setBackground(Color.PINK);
		
		perClick = new JLabel("Per: " + off);
		perClick.setFont(new Font("Tahoma", Font.PLAIN, 13));
		perClick.setForeground(Color.PINK);
		panelMoney.add(perClick);
		
		buttonMoney = new JButton("Make bitcoin");
		buttonMoney.setForeground(new Color(0, 0, 0));
		buttonMoney.setFont(new Font("Tahoma", Font.ITALIC, 18));
		buttonMoney.setBackground(Color.PINK);
		buttonMoney.setBounds(57, 53, 177, 31);
		frame.getContentPane().add(buttonMoney);
		
		panelMenu = new JPanel();
		panelMenu.setBounds(5, 110, 280, 57);
		frame.getContentPane().add(panelMenu);
		panelMenu.setLayout(new GridLayout(0, 2, 5, 5));
		
		upgrades = new JToggleButton("Upgrades");
		panelMenu.add(upgrades);
		
		earnings = new JToggleButton("Earnings");
		panelMenu.add(earnings);
		
		look = new JToggleButton("Look");
		panelMenu.add(look);
		
		menu = new JToggleButton("Menu");
		panelMenu.add(menu);
		
		group.add(upgrades); 
		group.add(earnings); 
		group.add(look); 
		group.add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}


	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyChar() == ' ') {
			money += off;
			amountMoney.setText(money + " $");
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
