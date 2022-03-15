import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



public class Clock_bot extends Thread{
	WebDriver driver;
	JTextArea area;
	JTextArea area2;
	public Clock_bot() {
		GUI gui = new GUI();
	}
	
	public void run() {
		
		new Occupancy(area).start();
		new Arrival(area2).start();
		while(true) {
			
			try {
				Thread.sleep(1800000);
			}catch(Exception ee) {ee.printStackTrace();}
			
			new Occupancy(area).start();
			new Arrival(area2).start();
		}
		
	}
	class GUI  
	{  
	
	     GUI(){  
		    	 area=new JTextArea();
		    	 area.setEditable(false);
		    	 area2=new JTextArea();
		    	 area2.setEditable(false);
		    	
		    	 
		    	 JScrollPane scrollableTextArea = new JScrollPane(area);
				   scrollableTextArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
				        public void adjustmentValueChanged(AdjustmentEvent e) {  
				            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
				        }
				    });
				   JScrollPane scrollableTextArea2 = new JScrollPane(area2);
				   scrollableTextArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
				        public void adjustmentValueChanged(AdjustmentEvent e) {  
				            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
				        }
				    }); 
				   
		    	 JFrame frame=new JFrame("Clock Bot is Running");
		    	 frame.add(scrollableTextArea);
		    	 frame.add(scrollableTextArea2);
		    	 frame.setLayout(new GridLayout()); 
		    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	 frame.setSize(700,750);
		    	 frame.setLocationRelativeTo(null);
		    	 frame.setVisible(true);
	     }
	     public void textAppend(String stringIn) {
	    	 area.append(stringIn);
	     }
	     public void textClear() {
	    	 area.setText("");
	     }
	}
	
	public static void main(String args[]) {
		new Clock_bot().start();
	}
}
