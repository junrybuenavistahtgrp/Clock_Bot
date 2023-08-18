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

public class pingTest {
	Statement st;
	Statement st2;
	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	public pingTest() {
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
		 String ip = "180.150.230.195 -t";
	        

	        String pingCmd = "ping " + ip;
	        try {
	            Runtime r = Runtime.getRuntime(); 
	            Process p = r.exec(pingCmd);

	            BufferedReader in = new BufferedReader(new
	            InputStreamReader(p.getInputStream()));
	            String inputLine;
	            while(true){
	            		String pingResult = "";
	            		int timeout = 0; 
			            for(int i = 0;i<17;++i) {
			        	   inputLine = in.readLine();
			        	   	if(i>1) {   
				                System.out.println(inputLine);
				                if(inputLine.equalsIgnoreCase("Request timed out.")) {
				                	timeout++;
				                }
				                pingResult += inputLine+"\n";
			        	   	}
			             }
			            System.out.println("-------------"+timeout);
			            try {
			            System.out.println(getMac());
			           
			            	if(timeout>2)
			            	     st.execute("UPDATE `ping` SET myping = '"+pingResult+"', status = '2', updated = '"+dformat.format(new Date())+"' WHERE mac = '"+getMac()+"'");
			            	else st.execute("UPDATE `ping` SET myping = '"+pingResult+"', status = '1', updated = '"+dformat.format(new Date())+"' WHERE mac = '"+getMac()+"'");
			            }catch(Exception ee) {ee.printStackTrace();setNgrok();updatePing();}
		            
	            }    
	            //in.close();

	        } catch (IOException e) {
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
    	new pingTest();
    }
}