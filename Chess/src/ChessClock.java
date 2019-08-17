import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ChessClock extends JPanel implements Runnable, ActionListener {
	
	private static JLabel whitetime, blacktime, winner;			//JLabels
	private static int p1time, p2time;								//Variables for time
	public static Thread counter;							//What allows the clock to run
	public static boolean started, isWhite = true;			//To switch the time
	
	//Chess clock object
	public ChessClock() {
		setLayout(new GridLayout(6, 1));					//This JPanel uses a Grid Layout
		initializeClock();
		started = false;
	}
	
	//This initializes the clock and all of it's elements
	private void initializeClock() {
		//Set up the variables
		int timeLimit = 15;									//The number of minutes
		timeLimit*=600;
		counter = new Thread(this);
		p1time = timeLimit; p2time = timeLimit;				//Each players time
		//Sets up the draw button
		JButton draw = new JButton("<html><p style='color: black; font-size:15px'>Draw</p></html>");
	    draw.setPreferredSize(new Dimension(5, 5));
		draw.addActionListener(this);
		//Create all the JLabel content that the user sees.
		JLabel white = new JLabel("<html><p style='font-size:40px'>White</p></html>");
		JLabel black = new JLabel("<html><p style='font-size:40px'>Black</p></html>");
		whitetime = new JLabel("<html><p style='font-size:35px'>"+p1time/600+":"+p1time%60+"0</p></html>");
		blacktime = new JLabel("<html><p style='font-size:35px'>"+p2time/600+":"+p2time%60+"0</p></html>");
		winner = new JLabel("<html><p style='color: black; font-size:20px'>----------</p></html>");
		//Adds all the JLabels to the JFrame
		add(black);
		add(blacktime);
		add(winner);
		add(whitetime);
		add(white);
		add(draw);
		//Centers all the JLabels
		white.setHorizontalAlignment(JLabel.CENTER);
		whitetime.setHorizontalAlignment(JLabel.CENTER);
		black.setHorizontalAlignment(JLabel.CENTER);
		blacktime.setHorizontalAlignment(JLabel.CENTER);
		winner.setHorizontalAlignment(JLabel.CENTER);
	}
	
	//This method allows for the clock to continuously run using the runnable interface
	@Override
	public void run() {	
		while(started) {
			try  {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {}
			//If it is white move
			if (isWhite) {
				p1time--;
				whitetime.setText("<html><p style='color:red; font-size:35px'>"+p1time/600+":"+(p1time%600)/10+"</p></html>");
				blacktime.setText("<html><p style='color:black; font-size:35px'>"+p2time/600+":"+(p2time%600)/10+"</p></html>");
				//If white time is done
				if(p1time == 0) {
					winner.setText("<html><p style='color: red; font-size:20px'>---Black won!---"+"</p></html>");
					whitetime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p1time/600+":"+(p1time%600)/10+"</p></html>");
					blacktime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p2time/600+":"+(p2time%600)/10+"</p></html>");
					break;
				}
			}
			//If it is black move
			else {
				p2time--;
				whitetime.setText("<html><p style='color:black; font-size:35px; text-align: center'>"+p1time/600+":"+(p1time%600)/10+"</p></html>");
				blacktime.setText("<html><p style='color:red; font-size:35px; text-align: center'>"+p2time/600+":"+(p2time%600)/10+"</p></html>");
				//If black time is done
				if(p2time == 0) {
					winner.setText("<html><p style='color: red; font-size:20px'>---White won!---"+"</p></html>");
					whitetime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p1time/600+":"+(p1time%600)/10+"</p></html>");
					blacktime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p2time/600+":"+(p2time%600)/10+"</p></html>");
					break;
				}
			}
		}
		//If the draw button is clicked, it changes the font style
		if (!started) {
			whitetime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p1time/600+":"+(p1time%600)/10+"</p></html>");
			blacktime.setText("<html><p style='color:orange; font-size:35px; text-align: center'>"+p2time/600+":"+(p2time%600)/10+"</p></html>");
		}
	}

	//When the draw button is pressed it stops the timer and shows draw
	@Override
	public void actionPerformed(ActionEvent e) {
		started = false;
		winner.setText("<html><p style='color: red; font-size:20px'>---Draw!---"+"</p></html>");
	}
}