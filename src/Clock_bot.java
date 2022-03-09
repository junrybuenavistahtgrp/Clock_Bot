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

import org.openqa.selenium.JavascriptExecutor;

public class Clock_bot extends Thread{
	WebDriver driver;
	GUI gui;
	
	public Clock_bot() {
		
	}
	
	public void setBrowser() {		
		System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "C:"+File.separator+"Square_download");
		chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}
	public void Login(String serial,String user,String pass) {

		//gui.textAppend("Starting Login\n");
		setBrowser();
		driver.get("https://sky-us2.clock-software.com/");
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(2000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div[1]/label/div/div[1]/div/input")).sendKeys(serial);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button/span[2]/span")).click();
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div/label/div/div[1]/div/input")).sendKeys(user);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button[2]/span[2]/span")).click();
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div[2]/label/div/div[1]/div[1]/input")).sendKeys(pass);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button[2]/span[2]/span")).click();
		
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}		
		driver.get("https://sky-us2.clock-software.com/77801/12036/reports");
	}	
	public void run() {
		
		Login("7780186186722297726985559","reports","NBVreports2020!");
	}
	class GUI  
	{   JTextArea area;
	
	     GUI(){  
		    	 area=new JTextArea();
		    	 area.setEditable(false);
		    	
		    	 
		    	 JScrollPane scrollableTextArea = new JScrollPane(area);
				   scrollableTextArea.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
				        public void adjustmentValueChanged(AdjustmentEvent e) {  
				            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
				        }
				    });
		    	 JFrame frame=new JFrame("Clock Bot is Running");
		    	 frame.add(scrollableTextArea);
		    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	 frame.setSize(400,750);
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
