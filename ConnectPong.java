// Program: Network Pong Client
// Version: 1.0
// Created By: Lecia Cheng
// Date Completed: Friday, December 3, 2021

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class ConnectPong extends JFrame implements ActionListener, KeyListener{
	JFrame theframe = new JFrame("Client");;
	JPanel thepanel = new JPanel();
	JLabel thelabeltitle = new JLabel("Network Pong");
	JLabel thelabelip = new JLabel("IP");
	JLabel theusernamelabel = new JLabel("Username");
	JLabel thelocalhostlabel = new JLabel("Port");
	JTextArea thereceiving = new JTextArea("Welcome! Please enter \nyour username.\n");
	JTextField theport = new JTextField("6112");
	JTextField theserverip = new JTextField("127.0.0.1");
	JTextField theUsername = new JTextField("");
	JScrollPane theScroll;
	// Super Socket Master
	SuperSocketMaster ssm;
	
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		// Getting Server IP
		if(evt.getSource() == theserverip){
			String strIP = theserverip.getText();
			ssm = new SuperSocketMaster(strIP, 6112, this);
			ssm.connect();
			theserverip.setEnabled(false);
			theport.setEnabled(false);
		// Getting Username
		}else if(evt.getSource() == theUsername){
			ssm.sendText(theUsername.getText());
			theUsername.setEnabled(false);
			thereceiving.setEnabled(false);
		}
	}
	public void keyReleased(KeyEvent evt){
		ssm.sendText("Stop");
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyCode() == KeyEvent.VK_UP){
			ssm.sendText("Up");
		}else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
			ssm.sendText("Down");
		}
	}
	public void keyTyped(KeyEvent evt){
	}
	
	public ConnectPong(){
		// Frame, Panel
		thepanel.setLayout(null);		
		theframe.setPreferredSize(new Dimension(320, 500));
		theframe.addKeyListener(this);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.pack();
		theframe.setVisible(true);
		// Labels, TextAreas
		thelabeltitle.setSize(200, 100);
		thelabeltitle.setLocation(115, -20);
		thepanel.add(thelabeltitle);
		thereceiving.setSize(190, 70);
		thereceiving.setLocation(60, 50);
		thepanel.add(thereceiving);
		theport.setSize(110, 35);
		theport.setLocation(105, 310);
		thepanel.add(theport);
		theport.addActionListener(this);
		thelocalhostlabel.setSize(200, 100);
		thelocalhostlabel.setLocation(145, 240);
		thepanel.add(thelocalhostlabel);
		theserverip.setSize(100, 50);
		theserverip.setLocation(110, 220);
		theserverip.addActionListener(this);
		thepanel.add(theserverip);
		thelabelip.setSize(100, 50);
		thelabelip.setLocation(155, 180);
		thepanel.add(thelabelip);
		// Username
		theUsername.setSize(100, 25);
		theUsername.setLocation(110, 150);
		theUsername.addActionListener(this);
		thepanel.add(theUsername);
		theusernamelabel.setSize(200, 130);
		theusernamelabel.setLocation(130, 70);
		thepanel.add(theusernamelabel);
		theScroll = new JScrollPane(thereceiving);
		theScroll.setSize(190,70);
		theScroll.setLocation(60, 50);
		thepanel.add(theScroll);
		
    
	} 
	
	public static void main(String[] args){
		new ConnectPong();
	}
}
