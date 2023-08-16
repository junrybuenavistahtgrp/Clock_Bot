import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//test
public class test2 {
	WebDriver driver;
	SimpleDateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
	
	public test2() {
		setBrowser();
		driver.get("file:///C:/xampp/htdocs/Test/test1/Aqua%20Hotel%20_%20Booking%20searches%20_%20Advanced.html");
		
		
		
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
				Thread.sleep(500000);			
			}catch(Exception ee) {ee.printStackTrace();}
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/form/div[1]/div[2]/div/label/input")).click();
		
	}
	public static void main(String args[]) {
		new test2();
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
}
