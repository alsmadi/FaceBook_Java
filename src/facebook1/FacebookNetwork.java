/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook1;

//package FacebookGraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import static facebook1.Facebook1.facebookClient;
import java.util.Collections;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookNetwork {

	public Properties properties = new Properties();
	public FacebookClient fbClient = null;
	public String accessToken = "CAACEdEose0cBABU0B5KhMixP7t2tzaIil0nYy38UnzRItjws1geOjZBfNzIqniOwx1UIlK91hhEZABqzbak7AAf1lGx2ZCfKhPFZApOTX6JHU6WQ9MwyYxGop2QdboL19OWWYd3ySzY8Ma6aXqZB1bMkJ43s7NpnX801ZBqoh5AZAx656pFKq23PbugFiXUcb8FVZCcFYAUuwrVb67QtM57F";
	
	public FacebookNetwork()
	{
		//try {
			//properties.load(new FileInputStream("fb.properties"));
			//this.accessToken = properties.getProperty("access_token");
			this.fbClient = new DefaultFacebookClient(this.accessToken);			
		/*} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}
	
	/*
	 * Get a list of your facebook friends and related details
	 */
	public void GetFriends()
	{
             facebookClient = new DefaultFacebookClient(accessToken);
		//this is a bad hack to get the https to work. not recommended
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
			    new X509TrustManager() {
			        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			            return null;
			        }
			        public void checkClientTrusted(
			            java.security.cert.X509Certificate[] certs, String authType) {
			        }
			        public void checkServerTrusted(
			            java.security.cert.X509Certificate[] certs, String authType) {
			        }
			    }
			};
		
		// Install the all-trusting trust manager
		try {
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Connection<User> myFriends = this.fbClient.fetchConnection("me/friends", User.class);
		
	//	for (User friend: myFriends.getData())
                int i;
               
                 for(i=7000; i<7100;i++ ){
		{
		try{	//String id = friend.getId();
                     String id= Integer.toString(i);
                     

			String nodeUrl = "https://graph.facebook.com/" + id + "?access_token=" + this.accessToken;
			
				URL graphNode = new URL(nodeUrl);
				URLConnection conn = graphNode.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				BufferedWriter writer = new BufferedWriter(new FileWriter("facebook-friends/" + id + ".txt"));
                               
//User user = Facebook1.facebookClient.fetchObject(id, User.class);
                              
				//String query = "SELECT uid, name FROM user WHERE uid=id";
                                
                                //List<User> users;
                  /*  try{
                        users = Facebook1.facebookClient.executeFqlQuery(query, User.class);
                     

                    out.println("Users: " + users);
                    }
                    catch(Exception ex){
                        
                    } */
                     //   }     
                               String name=null;
                                while ((line = in.readLine()) != null)
				{
					writer.write(line);
                                        int k= line.indexOf("\"name\"")+8;
                                        int k1= line.indexOf("updated_time")-3;
                                        name= line.substring(k,k1 );
                                        String result= facebookClient.fetchObject(name, String.class);
                    System.out.println(result);
                    writer.write(result);
				}
                                 
				in.close();
				writer.flush();
				writer.close();
                                
                                
                                 try{
                                   
                                 List<User> myFriends2 =    Facebook1.getFriends1(name, this.accessToken);
                                  for (User friend: myFriends2){
                                   writer.write(name+","+ friend.getName());
                                  }
                                Connection<User> myFriends1 = this.fbClient.fetchConnection(name, User.class);
                                for (User friend: myFriends.getData()){
                                  String id1 = friend.getId();  
                                  String nodeUrl1 = "https://graph.facebook.com/" + id1 + "?access_token=" + this.accessToken;
			
				URL graphNode1 = new URL(nodeUrl1);
				URLConnection conn1 = graphNode1.openConnection();
				BufferedReader in1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
				String line1;
				BufferedWriter writer1 = new BufferedWriter(new FileWriter("facebook-friends/" + id1 + ".txt"));
                                
                                 while ((line1 = in1.readLine()) != null)
				{
					writer1.write(line1);
				}
                                 
				in1.close();
				writer1.flush();
				writer1.close();
                                }
                               }
                               catch(Exception ex){
                                   
                               }
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                }
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FacebookNetwork fn = new FacebookNetwork();
		fn.GetFriends();		
	}

}

