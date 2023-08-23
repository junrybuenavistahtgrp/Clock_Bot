import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.ResultSet;

import java.io.*;

public class test2 {
	Statement st;
	Statement st2;
	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	public test2() {
		System.out.println(dformat.format(new Date()));
		setNgrok();
		//setDataBaseConnection("0.tcp.ap.ngrok.io:11905");
		updatePing();
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
	public void setNgrok() {
		while(true) {
			System.out.println("Ngrok connecting");
			
			try{  
				Thread.sleep(1500);
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con2=DriverManager.getConnection(
				"jdbc:mysql://mindanaotravelguide.info/u798452166_ngrok","u798452166_link","Mangoi123");  	
				 st2=con2.createStatement();
				 while(true) {
					 try {
						 ResultSet rs = st2.executeQuery("Select * from ngrok where name = 'mylink'");
						 String ngrok="";
						 rs.next();
						 ngrok = rs.getString("Link");	
						 System.out.println(ngrok);
						 setDataBaseConnection(ngrok);
						 break;
					 }catch(Exception ee) {System.out.println("Retrying");}
				 }
				 break;
				//ff
			   }catch(Exception e){e.printStackTrace();}
		}
	}	
	public void updatePing() {
		
	        try {
	          
	            while(true){
	            	Thread.sleep(1000);
	            	 ResultSet rs = st.executeQuery("SELECT * FROM `ping` where name = 'Junry'");
					 rs.next();	
					 System.out.println(rs.getString("myping"));           
	            }    

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	}
	public String getMac() {
		InetAddress ip;
		String ret= "";
		try {
				
			ip = InetAddress.getLocalHost();
			//System.out.println("Current IP address : " + ip.getHostAddress());
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
				
			//System.out.print("Current MAC address : ");
				
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			ret = sb.toString();
				
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			
		} catch (SocketException e){
				
			e.printStackTrace();
				
		}
		return ret;
	}
    public static void main(String[] args) {
    	new test2();
    }
}