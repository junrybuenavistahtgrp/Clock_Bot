import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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


public class Arrival extends Thread{
	WebDriver driver;
	Statement st;
	JTextArea area;
	DateTimeFormatter dtf;
    LocalTime localTime;
    String hotel[]= {"Aqua Hotel","LaCasa","Royal Palms Resort & Spa","Tranquilo","Victoria Park Hotel","Beach Gardens","North Beach Hotel","Tara Hotel","Tropirock","Winterset"};
    String links[]= {"https://sky-us2.clock-software.com/77801/12036/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12510/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12034/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12511/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12037/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12508/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/11718/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12512/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12509/bookings/arrivals_search",
					 "https://sky-us2.clock-software.com/77801/12513/bookings/arrivals_search"};
    public Arrival(JTextArea area) {
		setDataBaseConnection();
		this.area=area;
		dtf = DateTimeFormatter.ofPattern("HH:mm");
        localTime = LocalTime.now();
        System.out.println(dtf.format(localTime));
       
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
    public void textAppend(String stringIn) {
      	 area.append(stringIn);
       }	
    public void Login(String serial,String user,String pass) {

		textAppend("Clock Occupancy report starting - "+dtf.format(localTime)+"\n");
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
		textAppend("Clock Occupancy done login\n");
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}		
		driver.get("https://sky-us2.clock-software.com/77801/12036/reports");
	}
    public void run() {
    	Login("7780186186722297726985559","reports","NBVreports2020!");
    }
    
    public void setDataBaseConnection() {
		while(true) {
			System.out.println("Database connecting");
			
			try{  
				Thread.sleep(1500);
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/clock_report","root","");  	
				 st=con.createStatement();
				 break;			
			   }catch(Exception e){}
		}
	}

}