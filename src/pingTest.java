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
import java.io.*;

public class pingTest {
	Statement st;
	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	public pingTest() {
		System.out.println(dformat.format(new Date()));
		setDataBaseConnection();
		
		updatePing();
	}
	public void setDataBaseConnection() {
		while(true) {
			System.out.println("Database connecting");
			
			try{  
				Thread.sleep(1500);
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://bukidnon-resorts.info/u798452166_mangoiboda","u798452166_mangoiboda","Mangoi123");  	
				 st=con.createStatement();
				 break;
				
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
			           
			            	if(timeout>3)
			            	     st.execute("UPDATE `ping` SET myping = '"+pingResult+"', status = '2', updated = '"+dformat.format(new Date())+"' WHERE mac = '"+getMac()+"'");
			            	else st.execute("UPDATE `ping` SET myping = '"+pingResult+"', status = '1', updated = '"+dformat.format(new Date())+"' WHERE mac = '"+getMac()+"'");
			            }catch(Exception ee) {ee.printStackTrace();}
		            
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