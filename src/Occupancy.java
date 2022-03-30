import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Occupancy extends Thread{
	WebDriver driver;
	Statement st;
	JTextArea area;
	DateTimeFormatter dtf;
    LocalTime localTime;
    
	
	String hotel[]= {"Aqua Hotel","LaCasa","Royal Palms Resort & Spa","Tranquilo","Victoria Park Hotel","Beach Gardens","North Beach Hotel","Tara Hotel","Tropirock","Winterset"};
	String links[]= {"https://sky-us2.clock-software.com/77801/12036/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12510/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12034/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12511/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12037/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12508/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/11718/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12512/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12509/occupancy_incomes_report",
					 "https://sky-us2.clock-software.com/77801/12513/occupancy_incomes_report"};
	                
	public void textAppend(String stringIn) {
   	 area.append(stringIn);
    }	
	public Occupancy(JTextArea area) {
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
	public void Login(String serial,String user,String pass) {

		textAppend("Clock Occupancy report starting - "+dtf.format(localTime)+"\n");
		setBrowser();
		driver.get("https://sky-us2.clock-software.com/");
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(2000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div[1]/label/div/div[1]/div/input")).sendKeys(serial);
		                             
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button")).click();	                       
		                               
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div/label/div/div[1]/div/input")).sendKeys(user);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button[2]")).click();
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div[2]/label/div/div[1]/div[1]/input")).sendKeys(pass);
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[2]/button[2]")).click();
		textAppend("Clock Occupancy done login\n");
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}		
		driver.get("https://sky-us2.clock-software.com/77801/12036/reports");
	}	
	
	public void run() {
		
		Login("7780186186722297726985559","reports","NBVreports2020!");
		textAppend("Clock Occupancy getting data\n");
		for(int i=0;i<hotel.length;i++) {
			
			driver.get(links[i]);
			driver.findElement(By.xpath("/html/body/span[2]/div/form/div/div[2]/div/div/input")).click();
			while(true) {
				
				try {
					Thread.sleep(1000);
				}catch(Exception ee) {ee.printStackTrace();}
											
				System.out.println("waiting!");		
				if(driver.findElements(By.xpath("/html/body/span[2]/div/table")).size() != 0) {
					
					WebElement table = driver.findElement(By.xpath("/html/body/span[2]/div/table"));
				    List<WebElement> date = table.findElements(By.xpath(".//tr/td[1]"));
				    List<WebElement> capacity = table.findElements(By.xpath(".//tr/td[2]"));
				    List<WebElement> oos = table.findElements(By.xpath(".//tr/td[3]"));
				    List<WebElement> booked_rooms = table.findElements(By.xpath(".//tr/td[4]"));
				    List<WebElement> booked_percent = table.findElements(By.xpath(".//tr/td[5]"));
				    List<WebElement> occupancy = table.findElements(By.xpath(".//tr/td[6]"));
				    List<WebElement> occupancy_percent = table.findElements(By.xpath(".//tr/td[7]"));
				    List<WebElement> charges = table.findElements(By.xpath(".//tr/td[8]"));
				    List<WebElement> adr = table.findElements(By.xpath(".//tr/td[9]"));
				    List<WebElement> revpar = table.findElements(By.xpath(".//tr/td[10]"));
				    List<WebElement> bednight = table.findElements(By.xpath(".//tr/td[11]"));
					
					 try {	
					    	st.execute("UPDATE occupancy SET Date = '"+date.get(0).getText()+"', Capacity = '"+capacity.get(0).getText()+"', OOS = '"+oos.get(0).getText()+"', Booked_rooms = '"+booked_rooms.get(0).getText()+"', Booked_percent = '"+booked_percent.get(0).getText()+"', Occupancy = '"+occupancy.get(0).getText()+"',Occupancy_percent = '"+occupancy_percent.get(0).getText()+"', Charges = '"+charges.get(0).getText()+"', ADR = '"+adr.get(0).getText()+"', RevPAR = '"+revpar.get(0).getText()+"', Bednights = '"+bednight.get(0).getText()+"' where Hotel='"+hotel[i]+"'");				    			
					    }catch(Exception ee) {ee.printStackTrace();}										
					break;
				}else {driver.navigate().refresh();}
			}
		}
		textAppend("Clock Occupancy done getting data\n");
		driver.get("http://localhost/googleapi/clock-occupancy.php");
		try {
			Thread.sleep(4000);
		}catch(Exception ee) {ee.printStackTrace();}
		textAppend("Clock Occupancy done updating google sheet - "+dtf.format(localTime)+"\n\n");
		driver.quit();
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
	public static void main(String args[]) {}
}
