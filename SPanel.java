import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SPanel extends JPanel implements ActionListener{
	
	// Properties
	Timer thetimer = new Timer(1000/60, this);
	JLabel thelabel = new JLabel();
	// Player 1
	int intP1Y = 220;
	int intP1DefY = 0;
	int intP1 = 0;
	String strP1Username = "";
	
	// Player 2
	int intP2Y = 220;
	int intP2DefY = 0;
	int intP2 = 0;
	String strP2Username = "";
	
	// Ball
	int intBallX = 380;
	int intBallY = 280;
	int intBallDefX = 0;
	int intBallDefY = 0;
	
	BufferedImage theEE;
	BufferedImage thebrick;
	BufferedImage theball;
	BufferedImage thewin;
	BufferedImage thelose;
	
	String strWinnerName;

	// Methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			this.repaint();
		}
	}
	public static void pause(int ms) {
		try{
			Thread.sleep(ms);
		}catch(InterruptedException e) {
			System.out.println("IOException");
		}
	}
	// Override paintComponent
	public void paintComponent(Graphics g){
		// Background Changer
		if (intP1 >= 5 || intP2 >= 5){
			g.setColor(new Color(210, 210, 210));
			g.fillRect(0, 0, 800, 600);
		}else if (intP1 == 4 || intP2 == 4){
			g.setColor(new Color(160, 160, 160));
			g.fillRect(0, 0, 800, 600);
		}else if (intP1 == 3 || intP2 == 3){
			g.setColor(new Color(140, 140, 140));
			g.fillRect(0, 0, 800, 600);
		}else if (intP1 == 2 || intP2 == 2){
			g.setColor(new Color(80, 80, 80));
			g.fillRect(0, 0, 800, 600);
		}else if (intP1 == 1 || intP2 == 1){
			g.setColor(new Color(40, 40, 40));
			g.fillRect(0, 0, 800, 600);
		}else{
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, 800, 600);
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(393, 0, 5, 600);
		
		// Player 1
		g.setColor(Color.WHITE);
		g.drawImage(thebrick, 0, intP1Y, null);
		intP1Y = intP1Y + intP1DefY;
		
		// Player 2
		g.setColor(Color.WHITE);
		g.drawImage(thebrick, 780, intP2Y, null);
		intP2Y = intP2Y + intP2DefY;
		
		// ScoreBoard
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
		g.drawString(""+intP1, 280, 100);
		g.drawString(""+intP2, 430, 100);
		
		g.drawImage(theball, intBallX, intBallY, null);
		intBallX = intBallX + intBallDefX;
		intBallY = intBallY + intBallDefY;
		
		// Drawing Usernames
		g.setColor(Color.WHITE);
		g.drawString(strP1Username, 80, 100);
		g.drawString(strP2Username, 500, 100);
		
		// Ball Controls
		if(intP2Y < 0){
            intP2Y = 0;
		}else if(intP2Y >= 780){
            intP2Y = 780;
        }else if(intP1Y < 0){
            intP1Y = 0;
		}else if(intP1Y >= 780){
            intP1Y = 780;
        }
        if (intBallX <= 0){
			intP2++;
			intBallX = 380;
			intBallY = 280;
			intBallDefX = 0;
			intBallDefY = 0;
		}else if (intBallX >= 785){
			intP1++;
			intBallX = 380;
			intBallY = 280;
			intBallDefX = 0;
			intBallDefY = 0;
		}else if (intBallY <= 0){
			intBallDefY = intBallDefY * -1;
		}else if (intBallY >= 580){
			intBallDefY = intBallDefY * -1;
		}else if (intBallX <= 10 && intBallY <= intP1Y + 100 && intBallY >= intP1Y){
			intBallDefX = intBallDefX * -1;
		}else if (intBallX >= 760 && intBallY <= intP2Y + 100 && intBallY >= intP2Y){
			intBallDefX = intBallDefX * -1;
		}

		if(intP1 == 5 && intP2 == 0){
			g.drawImage(theEE, 0, 0, null);
			try{			
				PrintWriter winners = new PrintWriter(new FileWriter("winners.txt", true));
				strWinnerName = strP1Username;
				winners.println(strWinnerName);
				winners.close();
			}catch(IOException e){
				System.out.println("Error reading from keyboard or writing to file");
			}
		}else if(intP1 == 0 && intP2 == 5){
			g.drawImage(theEE, 0, 0, null);
			try{			
				PrintWriter winners = new PrintWriter(new FileWriter("winners.txt", true));
				strWinnerName = strP2Username;
				winners.println(strWinnerName);
				winners.close();
			}catch(IOException e){
				System.out.println("Error reading from keyboard or writing to file");
			}
		}else if(intP1 == 5){
			g.drawImage(thewin, 100, 0, null);
			g.drawImage(thelose, 450, 0, null);
			try{			
				PrintWriter winners = new PrintWriter(new FileWriter("winners.txt", true));
				strWinnerName = strP1Username;
				winners.println(strWinnerName);
				winners.close();
			}catch(IOException e){
				System.out.println("Error reading from keyboard or writing to file");
			}
				
		}else if(intP2 == 5){
			g.drawImage(thelose, 100, 0, null);
			g.drawImage(thewin, 450, 0, null);	
			try{			
				PrintWriter winners = new PrintWriter(new FileWriter("winners.txt", true));
				strWinnerName = strP2Username;
				winners.println(strWinnerName);
				winners.close();
000	.
		}catch(IOException e){
				System.out.println("Error reading from keyboard or writing to file");
			}
		}
		
	}
	
	
	// Constructor
	public SPanel(){
		super();
		this.setPreferredSize(new Dimension(800, 600));
		this.setLayout(null);
		thetimer.start();
		try{
			thewin = ImageIO.read(new File("win.png"));
		}catch(IOException e){
			System.out.println("ERROR");
		}
		try{
			thelose = ImageIO.read(new File("lose.png"));
		}catch(IOException e){
			System.out.println("ERROR");
		}
		try{
			thebrick = ImageIO.read(new File("brick.png"));
		}catch(IOException e){
			System.out.println("ERROR");
		}
		try{
			theball = ImageIO.read(new File("ball.png"));
		}catch(IOException e){
			System.out.println("ERROR");
		}
		try{
			theEE = ImageIO.read(new File("easteregg.png"));
		}catch(IOException e){
			System.out.println("ERROR");
		}
	
	}
}
