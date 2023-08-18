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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//test
public class test2 {

	Statement st;
	
	public test2() {
		try {
			setDataBaseConnection("0.tcp.ap.ngrok.io:17912");
			while(true) {
				Thread.sleep(1000);
				 ResultSet rs = st.executeQuery("SELECT * FROM `ping` where name='Junry'");				 
				 rs.next();
				 System.out.println(rs.getString("myping"));
			}
		}catch(Exception ee) {ee.printStackTrace();}
	}
	public void setDataBaseConnection(String link) throws Exception {
		System.out.println("Database connecting");	
	 
			Thread.sleep(1500);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("jdbc:mysql://"+link+"/bodaping");
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://"+link+"/bodaping","root","");  	
			 st=con.createStatement();			
	}
	public static void main(String args[]) {
		new test2();
	}
}
