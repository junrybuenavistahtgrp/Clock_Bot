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
	SimpleDateFormat dateformat;
	public test() {
		setBrowser();
		setDataBaseConnection();
		dateformat = new SimpleDateFormat("MM/1/yyyy");
		
	
		driver.get("file:///C:/xampp/htdocs/testweb/Aqua%20Hotel%20_%20Booking%20searches%20_%20Arrivals.html");
		
		if(driver.findElements(By.xpath("/html/body/span[2]/div/div/div/div[2]/div/div[2]/div/table")).size() == 0) {		                           
			System.out.println("Not Found");			
		}else {
			System.out.println("Found");
			WebElement table = driver.findElement(By.xpath("/html/body/span[2]/div/div/div/div[2]/div/div[2]/div/table"));
		    List<WebElement> folio_no = table.findElements(By.xpath(".//tr/td[2]"));
		    List<WebElement> stay_date = table.findElements(By.xpath(".//tr/td[10]"));
		    List<WebElement> balance = table.findElements(By.xpath(".//tr/td[12]"));
		
		    try {
		    	int index=0;
			    for(int i=0;i<folio_no.size();i++) {
			    	
			    	if(folio_no.get(i).getText().contains("#")) {
			    	System.out.println(folio_no.get(i).getText()+"--"+stay_date.get(index).getText()+""+balance.get(index).getText());	    	
			    	st.execute("INSERT INTO arrival (folio_number,stay_date,balance, Hotel)\r\n"
			    			+ "VALUES ('"+folio_no.get(i).getText()+"','"+stay_date.get(index).getText()+"','"+balance.get(index).getText()+"','Tropirock');");
			    	index++;
			    	}
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
