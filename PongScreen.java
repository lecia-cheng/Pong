// Program: Network Pong
// Version: 1.0
// Created By: Lecia Cheng
// Date Completed: Friday, December 3, 2021

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PongScreen implements ActionListener, KeyListener{
	// Properties
	JFrame theframe = new JFrame("Pong Game");
	SPanel thepanel = new SPanel();
	Timer thetimer = new Timer(1000/60, this);
	int intNextP1 = 0;
	int intNextP2 = 0;
	JFrame theframe1;
	JPanel thepanel1;
	JLabel thelabeltitle = new JLabel("Network Pong");
	JLabel thelabelip = new JLabel("IP");
	JLabel theusernamelabel = new JLabel("Username");
	JTextArea thereceiving = new JTextArea("Welcome! Please enter \nyour username.\n");
	JTextField theserverip = new JTextField("127.0.0.1");
	JTextField theusername = new JTextField("");
	String strusername;
	JScrollPane theScroll;
	// Super Socket Master
	SuperSocketMaster ssm = new SuperSocketMaster(6112, this);
	
	// Methods
	public void actionPerformed(ActionEvent evt){
		// Getting and sending username
		if(evt.getSource() == theusername){
			if(ssm != null){
				thepanel.strP1Username = theusername.getText();
				theusername.setEnabled(false);
				ssm.sendText(theusername.getText());
				thereceiving.setEnabled(false);
				theserverip.setEnabled(false);
			}
		// Sending Up, Down, Stop messages
		}else if(evt.getSource() == ssm){
			thereceiving.append(ssm.readText()+"\n");
			String strMsgs = ssm.readText();
			if(strMsgs.equals("Up")){
				intNextP2 = thepanel.intP2Y - 5;
				if(intNextP2 > -5){
					thepanel.intP2DefY = -5;
				}else if(intNextP2 <= -5){
					thepanel.intP2DefY = 0;
				}
			}else if(strMsgs.equals("Down")){
				intNextP2 = thepanel.intP2Y + 5;
				if(intNextP2 >= 505){
					thepanel.intP2DefY = 0;
				}else if(intNextP2 < 505){
					thepanel.intP2DefY = 5;
				}
			}else if(strMsgs.equals("Stop")){
				thepanel.intP2DefY = 0;
			}else{
				thepanel.strP2Username = strMsgs;
			}
		}
	}
	// Key Presses
	public void keyReleased(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			thepanel.intP1DefY = 0;
		}else if(evt.getKeyChar() == 's'){
			thepanel.intP1DefY = 0;
		}
	}
	public void keyPressed(KeyEvent evt){
		if(evt.getKeyChar() == 'w'){
			intNextP1 = thepanel.intP1Y - 5;
			if(intNextP1 > -5){
				thepanel.intP1DefY = -5;
			}else if(intNextP1 <= -5){
				thepanel.intP1DefY = 0;
			}
		}else if(evt.getKeyChar() == 's'){
			intNextP1 = thepanel.intP1Y + 5;
			if(intNextP1 >= 505){
				thepanel.intP1DefY = 0;
			}else if(intNextP1 < 505){
				thepanel.intP1DefY = 5;
			}
		}
		if(evt.getKeyCode() == 32){
			int intRandNum = (int)(Math.random()*2+1);
			if(intRandNum == 0){
				thepanel.intBallDefX = 5;
			}else if(intRandNum == 1){
				thepanel.intBallDefX = -5;
			}
			thepanel.intBallDefY = (int)(Math.random()*11)-5;
			thepanel.intBallX = 380;
			thepanel.intBallY = 280;
			
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}

	// Constructor
	public PongScreen(){
		// Timer
		thetimer = new Timer(1000/60, this);
		thetimer.start();
		// SuperSocketMaster server mode
		ssm = new SuperSocketMaster(6112, this);
		ssm.connect();
		// Frame
		theframe.addKeyListener(this);
		theframe.requestFocus();
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.setContentPane(thepanel);
		theframe.setResizable(false);
		theframe.pack();
		theframe.setVisible(true);	
		// Frame 1 & Panel 1
		theframe1 = new JFrame("Server Client");
		theframe1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thepanel1 = new JPanel();
		thepanel1.setLayout(null);	
		thepanel1.setPreferredSize(new Dimension(320, 500));
		theframe1.addKeyListener(this);
		theframe1.setContentPane(thepanel1);
		theframe1.setResizable(false);
		theframe1.pack();
		// Addings Labels, Text Boxes
		thelabeltitle.setSize(200, 100);
		thelabeltitle.setLocation(115, -20);
		thepanel1.add(thelabeltitle);
		thereceiving.setSize(190, 70);
		thereceiving.setLocation(60, 50);
		thepanel1.add(thereceiving);
		theserverip.setSize(100, 50);
		theserverip.setLocation(110, 220);
		theserverip.addActionListener(this);
		thepanel1.add(theserverip);
		thelabelip.setSize(100, 50);
		thelabelip.setLocation(155, 180);
		thepanel1.add(thelabelip);
		// Username
		theusername.setSize(100, 25);
		theusername.setLocation(110, 150);
		theusername.addActionListener(this);
		thepanel1.add(theusername);
		theusernamelabel.setSize(200, 130);
		theusernamelabel.setLocation(130, 70);
		thepanel1.add(theusernamelabel);
		// Scroll
		theScroll = new JScrollPane(thereceiving);
		theScroll.setSize(190,70);
		theScroll.setLocation(60, 50);
		thepanel1.add(theScroll);
		theframe1.setVisible(true);
		
	}
	
	// Main Method
	public static void main(String[] args){
		PongScreen ps = new PongScreen();
	}

}
