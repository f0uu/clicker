import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;

public class GameClicker implements ActionListener,  KeyListener {

	public JFrame frame;
	File file = new File("D:\\Users\\Abc\\eclipse-workspace\\Clicker\\src\\save.txt");
	
	///////////money
    int money = 1;
	int off = 1;
	int secOff = 0; 
	int counter = 0;
	int spentMoney = 0;
	JLabel perClick;
	JPanel panelMoney;
	JLabel labelMoney;
	JLabel amountMoney;
	JButton buttonMoney;
	JLabel perSec;
	
	////////tabs
	JPanel upg = new JPanel();
	JPanel perks = new JPanel();
	JPanel settings = new JPanel();
	JTabbedPane menu;
	JPanel upgMenu = new JPanel();
	
	//////////upgrades
	private JScrollPane scroll;
	int total = 8;
	String[] namesUpg = {"Slaves", "CPU", "Notebooks", "High-tech", "Ads", "Monitor", "President help", "Nukes", "GPU morele.pl"};
	ArrayList<Building> buildings = new ArrayList<Building>();
	
	
	/////////perks	
	private JScrollPane scroll2;
	String[] namesPerks = {"Fideltronik", "Google", "Apple", "Shaggy"};
	ArrayList<Timer> timerArray = new ArrayList<Timer>();
	ArrayList<Building> perksArray = new ArrayList<Building>();
	int frequency;
	
	
	////////stats
	private JScrollPane scroll3;
	String[] stats = {"Clicks count", "Money spent"};
	ArrayList<Building> statsArray = new ArrayList<Building>();
	JButton save = new JButton("Save");
	JButton reset = new JButton("Reset game");
	ArrayList<String[]> in = new ArrayList<String[]>();
	
	////////drawing	
//	Drawing d = new Drawing();
	
	
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


	public GameClicker() throws IOException {
		tab();
		data();
		initialize();
		adding();

	}
	
	private void adding() {
		
		buttonMoney.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				money += off;
				amountMoney.setText(money + " $");
				counter++;
				statsArray.get(0).amountLabel.setText(counter + " clicks");
			}
		});		

	}
	
	private void tab() {
	
		/////upgrades
		for(int i = 0; i <= total; i++) {
			buildings.add(new Building(namesUpg[i], i+1, 1));	
			upg.add(buildings.get(i));
			buildings.get(i).buy.addActionListener(new ButtonListener(i));
		}	
		
		
		///////perks
		for(int i = 0; i <= 3; i++) {
			timerArray.add(new Timer());
			perksArray.add(new Building(namesPerks[i], i+1, (i+1)*4));	
			perks.add(perksArray.get(i));
			perksArray.get(i).buy.addActionListener(new ButtonListenerPerks(i));
		}
		
		
		//////stats
		for(int k = 0; k < 2; k++) {
			statsArray.add(new Building(stats[k], k+1, (k+1)*4));
			statsArray.get(k).nameLabel.setText(stats[k]);
			statsArray.get(k).buy.setVisible(false);
			settings.add(statsArray.get(k));	
		}	
		statsArray.get(0).amountLabel.setText(counter+ " clicks");
		statsArray.get(1).amountLabel.setText(spentMoney+" $");
		settings.add(save);
		settings.add(reset);
	}


	private void initialize() {
		frame = new JFrame();		
		frame.setBackground(Color.WHITE);
		frame.setSize(300,480);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Bitcoin Clicker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
//		JPanel draw = new JPanel();
//		draw.setPreferredSize(new Dimension(260,98));
//		draw.setBounds(10, 77, 275, 175);
//		draw.add(new Drawing());
//		frame.getContentPane().add(draw);
		
		panelMoney = new JPanel();
		panelMoney.setBackground(Color.BLACK);
		panelMoney.setBounds(10, 11, 275, 61);
		frame.getContentPane().add(panelMoney);
		panelMoney.setLayout(new GridLayout(2, 2, 3, 0));
		
		labelMoney = new JLabel("Bitcoins:");
		labelMoney.setHorizontalAlignment(SwingConstants.CENTER);
		labelMoney.setBackground(Color.WHITE);
		labelMoney.setForeground(Color.WHITE);  
		panelMoney.add(labelMoney);
		labelMoney.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		amountMoney = new JLabel(money + " $");
		amountMoney.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelMoney.add(amountMoney);
		amountMoney.setForeground(Color.WHITE);
		amountMoney.setBackground(Color.PINK);
		
		perClick = new JLabel("per click: " + off);
		perClick.setHorizontalAlignment(SwingConstants.CENTER);
		perClick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		perClick.setForeground(Color.PINK);
		panelMoney.add(perClick);
		
		perSec = new JLabel("per sec: " + secOff);
		perSec.setForeground(Color.PINK);
		perSec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelMoney.add(perSec);
		
		buttonMoney = new JButton("Make bitcoin");
		buttonMoney.setForeground(new Color(0, 0, 0));
		buttonMoney.setFont(new Font("Monospaced", Font.BOLD, 30));
		buttonMoney.setBackground(Color.PINK);
		buttonMoney.setBounds(10, 110, 275, 140);
		frame.getContentPane().add(buttonMoney);
	
		
		///////tabs
		menu = new JTabbedPane(JTabbedPane.TOP);
		menu.setBounds(10, 268, 275, 172);
		frame.getContentPane().add(menu);
		
		upg.setLayout(new GridLayout(buildings.size(), 1, 0, 10));
		scroll = new JScrollPane(upg, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		perks.setLayout(new GridLayout(perksArray.size(), 1, 0, 10));
		scroll2 = new JScrollPane(perks, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		settings.setLayout(new GridLayout(statsArray.size()+2, 1, 0, 10));
		scroll3 = new JScrollPane(settings, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		menu.add("upgrades", scroll);
		menu.add("perks", scroll2);
		menu.add("settings", scroll3);	
	}

	private class ButtonListener implements ActionListener{
		int i;	
		public ButtonListener(int index) {
			this.i = index;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Building b = buildings.get(i);
			
			if (money >= b.price) {	
				money -= b.price;
				spentMoney += b.price;
				off += b.lvl;
				amountMoney.setText(money + " $");
				perClick.setText("per click: " + off);
				
				b.amount++;
				b.calculatePrice();
				b.nameLabel.setText(b.name +": "+ b.amount);
				b.amountLabel.setText(b.price + "$ ");
				
				statsArray.get(1).amountLabel.setText(spentMoney+" $");
			}	
		}		
	}
	
	private class ButtonListenerPerks implements ActionListener{
		int i;	
		public ButtonListenerPerks(int index) {
			this.i = index;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Building p = perksArray.get(i);
			Timer t = timerArray.get(i);
			if (money >= p.price) {	
				money -= p.price;	
				spentMoney += p.price;
				amountMoney.setText(money + " $");
				secOff += (p.lvl*2)-1;
				perSec.setText("per sec: " + (secOff));
				
				p.amount++;
				p.calculatePrice();
				p.nameLabel.setText(p.name +": "+ p.amount);
				p.amountLabel.setText(p.price + "$ ");
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						money += secOff;
						amountMoney.setText(money + " $");
						
					}
				}, p.power * 1000, p.power * 1000);
				statsArray.get(1).amountLabel.setText(spentMoney+" $");
			}	
		}		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void data() throws IOException {
		/////loading
		try {	
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;	
	
			while((line = br.readLine())!= null) {
				String[] lines = line.split(";");
				in.add(lines);

			}	
			br.close();
			
			 String[] basicReader = in.get(0);
			  money = Integer.valueOf(basicReader[0]);
			  off = Integer.valueOf(basicReader[1]);
			  secOff = Integer.valueOf(basicReader[2]); 
			  counter = Integer.valueOf(basicReader[3]);
		      spentMoney = Integer.valueOf(basicReader[4]);
		      
		      String[] secondLine = in.get(1);
		      int g = 0;
		     for(int i = 0; i < secondLine.length; i++) {
		    	 if(i < 9) {
			    	 Building b = buildings.get(i);
			    	 b.amount = Integer.valueOf(secondLine[i]);
			    	 b.calculatePrice();
			    	 b.nameLabel.setText(b.name+": "+b.amount);
			    	 b.amountLabel.setText(b.price +" $");
			    	 if(b.amount > 0) off += (b.amount * b.lvl);
			    	 
		    	 } else if(i >= 9) { 
		    		 Building b = perksArray.get(g);
			    	 b.amount = Integer.valueOf(secondLine[i]);
			    	 b.calculatePrice();
			    	 b.nameLabel.setText(b.name+": "+b.amount);
			    	 b.amountLabel.setText(b.price +" $");
			    	 if(b.amount > 0) off += (b.amount * (b.lvl*2)-1);

			    	 g++;
		    	 }
		    	 
		     }		      
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		/////saving
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrintWriter saver;
				try {
					saver = new PrintWriter(file);
					String basicSaver = money+";"+off+";"+secOff+";"+counter+";"+spentMoney+";";
					saver.println(basicSaver);
					
					for(int i=0; i<buildings.size();i++) {
						saver.print(buildings.get(i).amount+";");
					}	
					for(int i=0; i<perksArray.size();i++) {
						saver.print(perksArray.get(i).amount+";");
					}
					saver.close();
				} catch (FileNotFoundException e1) {	
					e1.printStackTrace();
				}
				
				
			}
		});
	
		
		
		
		
		
		
		////reseting
		reset.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e2) {
			money = 0;
			off = 1;
			secOff = 0;
			spentMoney = 0;
			counter = 0;
			amountMoney.setText(money + " $");
			perClick.setText("per click: " + off);
			perSec.setText("per sec: " + (secOff));
			
			
			for(Building b : buildings) {
				b.reset();
			}
			for(Building b : perksArray) {
				b.reset();
			}
			
			statsArray.get(0).amountLabel.setText(counter + " clicks");
			statsArray.get(1).amountLabel.setText(spentMoney+" $");
			
			}
		});
	}
}
