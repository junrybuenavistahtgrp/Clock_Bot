import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class SendHttp {
	Statement st;
	Statement st2;
	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
	public SendHttp() {
		System.out.println(dformat.format(new Date()));
		
		//replaceSt("Reply from 180.150.230.195: bytes=32 time=231ms TTL=109");
		updatePing();
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
				                	inputLine="timedout";
				                }else {
				                	inputLine = replaceSt(inputLine);
				                }
				                System.out.println(inputLine);
				                pingResult += inputLine+"nn";
			        	   	}
			             }
			            
			            System.out.println("-------------"+timeout);
			            try {
			            System.out.println(getMac());
			           
			            	if(timeout>2)
			            		 sendPost("http://localhost/Webtest/latency/server.php?ping="+pingResult+"&status=2mac="+getMac());
			            	else sendPost("http://localhost/Webtest/latency/server.php?ping="+pingResult+"&status=1&mac="+getMac());
			            }catch(Exception ee) {ee.printStackTrace();updatePing();}
		            
	            }    
	            //in.close();

	        } catch (IOException e) {
	            System.out.println(e);
	        }
	}
	public String replaceSt(String st) {
		String ret="";
		StringTokenizer tok = new StringTokenizer(st);
		int i=0;
		while(tok.hasMoreTokens()) {
			
			if(i==4) {
				ret+=tok.nextToken()+"RR";
			}else if(i==5) {
				ret+=tok.nextToken();
			}
			else tok.nextToken();
			i++;
		}
		return ret;
	}
	public void sendPost(String ss) {
		try {
			 	URL yahoo = new URL(ss);
		        URLConnection yc = yahoo.openConnection();
		        BufferedReader in = new BufferedReader(
		                                new InputStreamReader(
		                                yc.getInputStream()));
		        String inputLine;
	
		        while ((inputLine = in.readLine()) != null) 
		            System.out.println(inputLine);
		        in.close();
		}catch(Exception ee) {ee.printStackTrace();}
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
    	new SendHttp();
    }
}