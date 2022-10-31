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
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class test {
	Statement st;
	ResultSet rs;
	WebDriver driver;
	JavascriptExecutor Jscript;
	SimpleDateFormat dateformat;
	public test() {
		setBrowser();
		setDataBaseConnection();
		dateformat = new SimpleDateFormat("MM/1/yyyy");
		
	
		driver.get("file:///C:/xampp/htdocs/Test/test2/Aqua%20Hotel%20_%20Booking%20searches%20_%20Advanced.html");
		
		try {
			Thread.sleep(5000);			
		}catch(Exception ee) {ee.printStackTrace();}
		
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[2]/input")).click();	
	
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[4]/div/label/input")).click();	
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[5]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[6]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[7]/div/label/input")).click();		
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[22]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[25]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[26]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[27]/div/label/input")).click();
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[29]/div/label/input")).click();
		
		Jscript = (JavascriptExecutor) driver;
	
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
		    	
		    	st.execute("DELETE FROM `advance_search`");
			    for(int i=0;i<num.size();i++) {
			    	
			    	System.out.println(num.get(i).getText());
			    	if(i<(num.size()-1)) {
			    	st.execute("INSERT INTO advance_search (num,ref_num,arr,def,stay,guest,room_char,other_char,total_char,balance,marketing,hotel_name) VALUES ("
			    			+ "'"+num.get(i).getText()+"',"
			    			+ "'"+ref_num.get(i).getText()+"',"
			    			+ "'"+arr.get(i).getText()+"',"
			    			+ "'"+def.get(i).getText()+"',"
			    			+ "'"+stay.get(i).getText()+"',"
			    			+ "'"+guest.get(i).getText()+"',"
			    			+ "'"+room_char.get(i).getText()+"',"
			    			+ "'"+other_char.get(i).getText()+"',"
			    			+ "'"+total_char.get(i).getText()+"',"
			    			+ "'"+balance.get(i).getText()+"',"
			    			+ "'"+marketing.get(i).getText()+"',"
			    			+ "'Aqua Hotel')");}
			    	else {
			    		st.execute("INSERT INTO advance_search (num,hotel_name) VALUES ("
			    				+ "'"+num.get(i).getText()+"',"
			    				+ "'Aqua Hotel')");}
			    	}
			    		
					    	
			    }catch(Exception ee) {ee.printStackTrace();}
		}
	    
	   
		
	 
	
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
	
	
	public void setBrowser() {		
		System.setProperty("webdriver.chrome.driver", "C:\\Jars\\chromedriver.exe");		
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "C:"+File.separator+"Square_download");
		chromePrefs.put("excludeSwitches", "enable-popup-blocking");	
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	}
	public static void main(String args[]) {
		new test();
	}

}
