import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class AdvanceSearch extends Thread{
	WebDriver driver;
	Statement st;
	JTextArea area;
	DateTimeFormatter dtf;
    LocalTime localTime;
    SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
    
	
	String hotel[]= {"Aqua Hotel","LaCasa","Royal Palms Resort & Spa","Tranquilo","Victoria Park Hotel","Beach Gardens","North Beach Hotel","Tara Hotel","Tropirock","Winterset"};
	String links[]= {"https://sky-us2.clock-software.com/77801/12036/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12510/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12034/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12511/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12037/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12508/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/11718/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12512/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12509/bookings/advanced_search",
					 "https://sky-us2.clock-software.com/77801/12513/bookings/advanced_search"};
	
	
	
	public void textAppend(String stringIn) {
   	 area.append(stringIn);
    }	
	public AdvanceSearch(JTextArea area) {
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
		
		textAppend("Clock Advance search starting - "+dtf.format(localTime)+"\n");
		setBrowser();
		driver.manage().window().maximize();
		driver.get("https://sky-us2.clock-software.com/");
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(2000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/main/div/div/div/div/div/main/div/div[2]/div/div/div/div/div[1]/div[1]/label/div/div[1]/div/input")).sendKeys(serial);
		try {
			Thread.sleep(4000);			
		}catch(Exception ee) {ee.printStackTrace();}                             
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
	}	
	
	public void run() {
		
		Login("7780186186722297726985559","reports","NBVreports2020!");
		textAppend("Clock Advance search getting data\n");
		
		 
		for(int i=0;i<hotel.length;i++) {
			driver.get(links[i]);		
			
			queryMonth();
			
					if(i==0) {
						
						driver.findElement(By.xpath("/html/body/span[1]/nav/div/div/div[2]/div/a[1]")).click();
						try {
							Thread.sleep(2000);			
						}catch(Exception ee) {ee.printStackTrace();}
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[3]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[4]/div/label/input")).click();	
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[5]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[6]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[7]/div/label/input")).click();		
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[22]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[25]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[26]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[27]/div/label/input")).click();
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[29]/div/label/input")).click();
						
						driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[2]/input")).click();
						                       
						try {
							Thread.sleep(4000);			
						}catch(Exception ee) {ee.printStackTrace();}
						
					}
			
			try {
				Thread.sleep(2000);			
			}catch(Exception ee) {ee.printStackTrace();}
			
			if(driver.findElements(By.xpath("/html/body/span[2]/div/div/div[4]/table")).size() == 0) {		                           
				System.out.println("Not Found");			
			}else {
				System.out.println("Found");
				
				WebElement table = driver.findElement(By.xpath("/html/body/span[2]/div/div/div[4]/table"));
			    List<WebElement> num = table.findElements(By.xpath(".//tr/td[1]"));
			    List<WebElement> ref_num = table.findElements(By.xpath(".//tr/td[3]"));
			    List<WebElement> arr = table.findElements(By.xpath(".//tr/td[4]"));
			    List<WebElement> def = table.findElements(By.xpath(".//tr/td[5]"));
			    List<WebElement> stay = table.findElements(By.xpath(".//tr/td[6]"));
			    List<WebElement> guest = table.findElements(By.xpath(".//tr/td[7]"));
			    List<WebElement> room_char = table.findElements(By.xpath(".//tr/td[22]"));
			    List<WebElement> other_char = table.findElements(By.xpath(".//tr/td[25]"));
			    List<WebElement> total_char = table.findElements(By.xpath(".//tr/td[26]"));
			    List<WebElement> balance = table.findElements(By.xpath(".//tr/td[27]"));
			    List<WebElement> marketing = table.findElements(By.xpath(".//tr/td[29]"));
			    
			
			    System.out.println(num.get(0).getText());
			    System.out.println("ffffff");
			    try {
					Thread.sleep(2000);			
				}catch(Exception ee) {ee.printStackTrace();}
			    try {
			    	
			    	st.execute("DELETE FROM `advance_search` where hotel_name='"+hotel[i]+"'");
				    for(int i1=0;i1<num.size();i1++) {
				    	
				    	System.out.println(num.get(i1).getText());
				    	if(i1<(num.size()-1)) {
				    	st.execute("INSERT INTO advance_search (num,ref_num,arr,def,stay,guest,room_char,other_char,total_char,balance,marketing,hotel_name) VALUES ("
				    			+ "'"+num.get(i1).getText()+"',"
				    			+ "'"+ref_num.get(i1).getText()+"',"
				    			+ "'"+arr.get(i1).getText()+"',"
				    			+ "'"+def.get(i1).getText()+"',"
				    			+ "'"+stay.get(i1).getText()+"',"
				    			+ "'"+checkAppos(guest.get(i1).getText())+"',"
				    			+ "'"+room_char.get(i1).getText()+"',"
				    			+ "'"+other_char.get(i1).getText()+"',"
				    			+ "'"+total_char.get(i1).getText()+"',"
				    			+ "'"+balance.get(i1).getText()+"',"
				    			+ "'"+marketing.get(i1).getText()+"',"
				    			+ "'"+hotel[i]+"')");}
				    	else {
				    		st.execute("INSERT INTO advance_search (num,hotel_name) VALUES ("
				    				+ "'"+num.get(i1).getText()+"',"
				    				+ "'"+hotel[i]+"')");}
				    	}
				    		
						    	
				    }catch(Exception ee) {ee.printStackTrace();}
			}
			
		}
		
		textAppend("Clock advance search done getting data\n");
		driver.get("http://localhost/googleapi/clock-advance_search.php");
		
		textAppend("Clock advance search done updating google sheet - "+dtf.format(localTime)+"\n\n");
		
		
		
		
		
		driver.quit();
	}
	public String checkAppos(String in) {
		String ret="";
		StringTokenizer tok= new StringTokenizer(in,"'");
		int index= tok.countTokens();
		int pos= 1;
		while(tok.hasMoreTokens()) {
		    if(pos==index)
		    	ret+=tok.nextToken();
		    else
		    	ret+=tok.nextToken()+"''";
		    pos++;
		}
		return ret;
	}
	public void queryMonth() {
		
		try {
			Thread.sleep(4000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
        Date fromD = cal.getTime();    
        String fromdate = dateformat.format(fromD);
        
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date toD = cal.getTime();    
        String todate = dateformat.format(toD);
        
        driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[2]/div/div[1]/input")).clear();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[2]/div/div[1]/input")).sendKeys(fromdate);
		                         
		                             
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[2]/div/div[2]/input")).sendKeys(todate);
		                     
		try {
			Thread.sleep(2000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[8]/div/div/label[1]")).click();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[8]/div/div/label[2]")).click();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[8]/div/div/label[3]")).click();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[8]/div/div/label[4]")).click();
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[2]/div[1]/div[8]/div/div/label[5]")).click();
		
		try {
			Thread.sleep(2000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/span[2]/div/div/div[1]/div[3]/div[1]/form/div[3]/div/button")).click();
		
		try {
			Thread.sleep(6000);			
		}catch(Exception ee) {ee.printStackTrace();}
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
