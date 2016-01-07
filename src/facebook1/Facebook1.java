/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook1;

/**
 * Copyright (c) 2010-2016 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import java.util.List;
import java.util.NoSuchElementException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

/**
 * Examples of RestFB's Graph API functionality.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
@SuppressWarnings("deprecation")
public class Facebook1 extends Example {
  /**
   * RestFB Graph API client.
   */
  private static FacebookClient facebookClient23;
  private static FacebookClient facebookClient20;
  public static FacebookClient facebookClient;
  private static final String FRIEND_FQL = "SELECT uid,name FROM user WHERE uid in (SELECT uid1 FROM friend WHERE uid2 = me() and uid1 = #friendId#)";
  private static final String FRIEND_FQL_REPLACEMENT = "#friendId#";
  public Properties properties = new Properties();
  static FileOutputStream fop = null;
  static File file;
  static OutputStream os;// = new FileOutputStream("turnip");
  static Writer writer;// = new OutputStreamWriter(os,"UTF-8");
  /**
   * Entry point. You must provide a single argument on the command line: a valid Graph API access token.
   * 
   * @param args
   *          Command-line arguments.
   * @throws IllegalArgumentException
   *           If no command-line arguments are provided.
   */
  static User getUser(String userName){
      String MY_ACCESS_TOKEN="";
      String MY_APP_SECRET="";
      facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);
      User user = facebookClient.fetchObject(userName, User.class);
      return user;
  }
  
  static void getfeed(){
      Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

        //System.out.println("Count of my friends: " + myFriends.getData().size());
        System.out.println("First item in my feed: " + myFeed.getData().get(0));

        // Connections support paging and are iterable

        for (List<Post> myFeedConnectionPage : myFeed)
            for (Post post : myFeedConnectionPage)
                System.out.println("Post: " + post);

  }
  public static void main(String[] args) {
      
      
 /*   if (args.length == 0)
      throw new IllegalArgumentException("You must provide an OAuth access token parameter. "
          + "See README for more information."); */
    String MY_ACCESS_TOKEN="";
      String MY_APP_SECRET="";
      //facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET);
      //System.out.println("Get an access token in the following address: https://developers.facebook.com/tools/explorer");
      Facebook1 fb1 = new Facebook1(MY_ACCESS_TOKEN);
      file = new File("facebook.csv");
      List<User> myFacebookFriendList;
      List<User> myFacebookFriendList1;
       List<User> myFacebookFriendList2;
       List<User> myFacebookFriendList3;
       List<User> myFacebookFriendList4;
      User user= getUser("me");
     // me.setName();
      try{
          os = new FileOutputStream("facebook_data.csv");
          writer= new OutputStreamWriter(os,"UTF-8");
          int i;
          for(i=4; i<50;i++ ){
              String abc= Integer.toString(i);
           myFacebookFriendList4=getFriend(MY_ACCESS_TOKEN,abc);
           if(myFacebookFriendList4 !=null){
           for (User c : myFacebookFriendList4) {
             writer.write(user.getName()+ ","+user.getId()+","+c.getName()+","+c.getId()+"\n");
           }
            }}
         // getfeed();
          myFacebookFriendList=FaceBookClientWrapper.getFriends(MY_ACCESS_TOKEN);
          //myFacebookFriendList=getFriends(user.getName(), MY_ACCESS_TOKEN);
         // myFacebookFriendList = findFacebookFriendsUsingRest(MY_ACCESS_TOKEN);
          
          
          
           for (User c : myFacebookFriendList) {
            
            
             myFacebookFriendList1=getFriends(c.getName(),MY_ACCESS_TOKEN);
             writer.write(user.getName()+ ","+user.getId()+","+c.getName()+","+c.getId()+"\n");
             User user1= getUser(c.getUsername());
             for (User c1 : myFacebookFriendList1) {
                  writer.write(c.getName()+","+ c.getId()+","+c1.getName()+","+c1.getId()+"\n");
                  
                   myFacebookFriendList2=getFriends(c1.getUsername(), MY_ACCESS_TOKEN);
             
             for (User c2 : myFacebookFriendList2) {
                  writer.write(c1.getName()+","+ c1.getId()+","+c2.getName()+","+c2.getId()+"\n");
             
             
             myFacebookFriendList3=getFriends(c2.getUsername(), MY_ACCESS_TOKEN);
             
             for (User c3 : myFacebookFriendList3) {
                  writer.write(c2.getName()+","+ c2.getId()+","+c3.getName()+","+c3.getId()+"\n");
             }
             }
             }
           // System.out.println(c.getAbout()+","+c.getBio()+","+c.getFirstName());
          // byte[] contentInBytes = c.getBio();
           //  fop.write(contentInBytes);
        }
			//fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			//if (!file.exists()) {
			//	file.createNewFile();
			//}
      }
      catch(Exception ex){
          
      }
    /*  finally {
			try {
				if (fop != null) {
					fop.close();
                                        writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
      } */
 
//runEverything();
 //   new Facebook1(MY_ACCESS_TOKEN).runEverything();
  } 
      
  Facebook1(String accessToken) {
    facebookClient23 = new DefaultFacebookClient(accessToken, Version.VERSION_2_3);
    facebookClient20 = new DefaultFacebookClient(accessToken, Version.VERSION_2_0);
  }
public static List<User> getFriend(String accessToken, String facebookId)  { 
 // FacebookClient fb = createFacebookClient(accessToken); 
  List<User> users;
  try{
 users = facebookClient.executeFqlQuery(FRIEND_FQL.replaceAll(FRIEND_FQL_REPLACEMENT, facebookId), User.class); 
  //try { 
   return users; 
  } catch (Exception e) { 
   return null; 
  } 
 } 
  void runEverything() {
   // fetchObject();
   // fetchObjects();
   // fetchObjectsAsJsonObject();
    fetchConnections();
    //fetchDifferentDataTypesAsJsonObject();
    //query();
    //multiquery();
    //search();
    //metadata();
    //paging();
    //selection();
    //parameters();
    rawJsonResponse();
    try{
    fop.close();
    }
      catch(Exception ex){
          
      }
  }

  public static List<User> getFriends1(String user, String access_token) {
        FacebookClient facebookClient = new DefaultFacebookClient(access_token);
      //  user="me/friends/";
         List<User> totalFrnds;
        try{
        Connection<User> myFriends = facebookClient.fetchConnection(user,
                User.class);
        totalFrnds = myFriends.getData();
        }
        catch(Exception ex){
            return null;
        }
        
        return totalFrnds;
  }
  public static List<User> getFriends(String user, String access_token) {
        FacebookClient facebookClient = new DefaultFacebookClient(access_token);
      //  user="me/friends/";
        Connection<User> myFriends = facebookClient.fetchConnection(user,
                User.class, Parameter.with("locale", "en_US"),
                Parameter.with("fields", "birthday,id,first_name, last_name, "
                + "name, location,gender,bio,about,email"));
        List<User> totalFrnds = myFriends.getData();
        
        return totalFrnds;
  }
  void fetchObject() {
    out.println("* Fetching single objects *");

    User user = facebookClient23.fetchObject("me", User.class);
    Page page = facebookClient23.fetchObject("cocacola", Page.class);

    out.println("User name: " + user.getName());
    out.println("Page likes: " + page.getLikes());
  }

  void fetchObjectsAsJsonObject() {
    out.println("* Fetching multiple objects at once as a JsonObject *");

    List<String> ids = new ArrayList<String>();
    ids.add("4");
    ids.add("http://www.imdb.com/title/tt0117500/");

    // Make the API call
    JsonObject results = facebookClient23.fetchObjects(ids, JsonObject.class);

      System.out.println(results.toString(3));
    
    // Pull out JSON data by key and map each type by hand.
    JsonMapper jsonMapper = new DefaultJsonMapper();
    User user = jsonMapper.toJavaObject(results.getString("4"), User.class);
    Url url = jsonMapper.toJavaObject(results.getString("http://www.imdb.com/title/tt0117500/"), Url.class);

    out.println("User is " + user);
    out.println("URL is " + url);
  }

  void fetchObjects() {
    out.println("* Fetching multiple objects at once *");

    FetchObjectsResults fetchObjectsResults =
        facebookClient23.fetchObjects(Arrays.asList("me", "cocacola"), FetchObjectsResults.class);

    out.println("User name: " + fetchObjectsResults.me.getName());
    out.println("Page likes: " + fetchObjectsResults.page.getLikes());
  }

  void fetchDifferentDataTypesAsJsonObject() {
    out.println("* Fetching different types of data as JsonObject *");

    JsonObject zuck = facebookClient23.fetchObject("4", JsonObject.class);
    out.println(zuck.getString("name"));

    JsonObject photosConnection = facebookClient23.fetchObject("me/photos", JsonObject.class);
    JsonArray photosConnectionData = photosConnection.getJsonArray("data");

    if (photosConnectionData.length() > 0) {
      String firstPhotoUrl = photosConnectionData.getJsonObject(0).getString("source");
      out.println(firstPhotoUrl);
    }

    String query = "SELECT uid, name FROM user WHERE uid=4 or uid=11";
    List<JsonObject> queryResults = facebookClient20.executeFqlQuery(query, JsonObject.class);

    if (queryResults.size() > 0)
      out.println(queryResults.get(0).getString("name"));
  }

  /**
   * Holds results from a "fetchObjects" call.
   */
  public static class FetchObjectsResults {
    @Facebook
    User me;

    @Facebook(value = "cocacola")
    Page page;
  }
  static public List<User> findFacebookFriendsUsingRest(String facebookAccessToken){

    List<User> myFacebookFriendList= new ArrayList();
    final FacebookClient facebookClient;
    facebookClient = new DefaultFacebookClient(facebookAccessToken);
    User user = facebookClient.fetchObject("me", User.class);
    String userName = user.getFirstName();

    if (userName == null){
        userName = user.getLastName();
    }

    String userEmail = user.getEmail();
    com.restfb.Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);

    System.out.println("Count of my friends: " + myFriends.getData().size());

    for(User friend: myFriends.getData()){
        System.out.println("Friends id and name: "+friend.getId()+" , "+friend.getName());      
        myFacebookFriendList.add(friend);
    }

    System.out.println("All Friends : "+myFacebookFriendList);
    
    return myFacebookFriendList;
}
  
  static public List<User> findFacebookFriendsUsingRest(User userIn, String facebookAccessToken){

    List<User> myFacebookFriendList= new ArrayList();
    final FacebookClient facebookClient;
    facebookClient = new DefaultFacebookClient(facebookAccessToken);
    String inputUser;
    if(userIn.getName().contains("Izzat")){
        inputUser="me";
    }
    else{
        inputUser=userIn.getName();
    }
    User user = facebookClient.fetchObject(inputUser, User.class);
    String userName = user.getFirstName();

    if (userName == null){
        userName = user.getLastName();
    }

    String userEmail = user.getEmail();
    com.restfb.Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);

    System.out.println("Count of my friends: " + myFriends.getData().size());

    for(User friend: myFriends.getData()){
        System.out.println("Friends id and name: "+friend.getId()+" , "+friend.getName());      
        myFacebookFriendList.add(friend);
    }

    System.out.println("All Friends : "+myFacebookFriendList);
    
    return myFacebookFriendList;
}
  void fetchConnections() {
    out.println("* Fetching connections *");
     //fop.write("test");
    Connection<User> myFriends = facebookClient23.fetchConnection("me/friends", User.class);
   // Connection<Post> myFeed = facebookClient23.fetchConnection("me/feed", Post.class);

    out.println("Count of my friends: " + myFriends.getData().size());
    try{
        for (User c : myFriends.getData()) {
            
            writer.write(c.getAbout()+","+c.getBio()+","+c.getFirstName());
            System.out.println(c.getAbout()+","+c.getBio()+","+c.getFirstName());
          // byte[] contentInBytes = c.getBio();
           //  fop.write(contentInBytes);
        }
    writer.write(myFriends.getData().size());
    writer.flush();
    fop.write(myFriends.getData().size());
    fop.flush();
    }
    catch(Exception ex){
        
    }

    /*if (myFeed.getData().size() > 0)
      out.println("First item in my feed: " + myFeed.getData().get(0).getMessage()); */
  }

  void query() {
    out.println("* FQL Query *");

    List<FqlUser> users =
        facebookClient20.executeFqlQuery("SELECT uid, name FROM user WHERE uid=4 or uid=11", FqlUser.class);

    out.println("User: " + users);
  }

  void multiquery() {
    out.println("* FQL Multiquery *");

    Map<String, String> queries = new HashMap<String, String>();
    queries.put("users", "SELECT uid, name FROM user WHERE uid=4 OR uid=11");
    queries.put("likers", "SELECT user_id FROM like WHERE object_id=122788341354");

    MultiqueryResults multiqueryResults = facebookClient20.executeFqlMultiquery(queries, MultiqueryResults.class);

    out.println("Users: " + multiqueryResults.users);
    out.println("People who liked: " + multiqueryResults.likers);
  }

  /**
   * Holds results from an "executeQuery" call.
   * <p>
   * Be aware that FQL fields don't always map to Graph API Object fields.
   */
  public static class FqlUser {
    @Facebook
    String uid;

    @Facebook
    String name;

    @Override
    public String toString() {
      return format("%s (%s)", name, uid);
    }
  }

  /**
   * Holds results from an "executeQuery" call.
   * <p>
   * Be aware that FQL fields don't always map to Graph API Object fields.
   */
  public static class FqlLiker {
    @Facebook("user_id")
    String userId;

    @Override
    public String toString() {
      return userId;
    }
  }

  /**
   * Holds results from a "multiquery" call.
   */
  public static class MultiqueryResults {
    @Facebook
    List<FqlUser> users;

    @Facebook
    List<FqlLiker> likers;
  }

  void search() {
    out.println("* Searching connections *");

//    Connection<Post> publicSearch =
//        facebookClient23.fetchConnection("search", Post.class, Parameter.with("q", "watermelon"),
//          Parameter.with("type", "post"));

    Connection<User> targetedSearch =
        facebookClient23.fetchConnection("search", User.class, Parameter.with("q", "Mark"),
          Parameter.with("type", "user"));

//    if (publicSearch.getData().size() > 0)
//      out.println("Public search: " + publicSearch.getData().get(0).getMessage());

    out.println("Posts on my wall by friends named Mark: " + targetedSearch.getData().size());
  }

  void metadata() {
    out.println("* Metadata *");

    User userWithMetadata = facebookClient23.fetchObject("me", User.class, Parameter.with("metadata", 1));

    out.println("User metadata: has albums? " + userWithMetadata.getMetadata().getConnections().hasAlbums());
  }

  void paging() {
    out.println("* Paging support *");

    Connection<User> myFriends = facebookClient23.fetchConnection("me/friends", User.class);
    Connection<Post> myFeed = facebookClient23.fetchConnection("me/feed", Post.class, Parameter.with("limit", 100));

    out.println("Count of my friends: " + myFriends.getData().size());

    if (myFeed.getData().size() > 0)
      out.println("First item in my feed: " + myFeed.getData().get(0));

    for (List<Post> myFeedConnectionPage : myFeed)
      for (Post post : myFeedConnectionPage)
        out.println("Post from my feed: " + post);
  }

  void selection() {
    out.println("* Selecting specific fields *");

    User user = facebookClient23.fetchObject("me", User.class, Parameter.with("fields", "id,name"));

    out.println("User name: " + user.getName());
  }

  void parameters() {
    out.println("* Parameter support *");

    Date oneWeekAgo = new Date(currentTimeMillis() - 1000L * 60L * 60L * 24L * 7L);

    Connection<Post> filteredFeed =
        facebookClient23.fetchConnection("me/feed", Post.class, Parameter.with("limit", 3),
          Parameter.with("until", "yesterday"), Parameter.with("since", oneWeekAgo));

    out.println("Filtered feed count: " + filteredFeed.getData().size());
  }
public void GetFriends()
	{
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
		
		Connection<User> myFriends = this.facebookClient.fetchConnection("me/friends", User.class);
		
		for (User friend: myFriends.getData())
		{
			String id = friend.getId();
			String nodeUrl = "https://graph.facebook.com/" + id + "?access_token=" + properties.getProperty("access_token");
			try {
				URL graphNode = new URL(nodeUrl);
				URLConnection conn = graphNode.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				BufferedWriter writer = new BufferedWriter(new FileWriter("facebook-friends/" + id + ".txt"));
				while ((line = in.readLine()) != null)
				{
					writer.write(line);
				}
				in.close();
				writer.flush();
				writer.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
  void rawJsonResponse() {
    out.println("* Raw JSON *");
    out.println("User object JSON: " + facebookClient23.fetchObject("me", String.class));
  }
}