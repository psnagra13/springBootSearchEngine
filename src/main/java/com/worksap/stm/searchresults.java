package com.worksap.stm;

import java.util.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

@RestController
//@EnableAutoConfiguration
public class searchresults {

    
    @RequestMapping("/result")
    String home(@RequestParam String query) {
    	
    	
        
    	  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
    	  Session session = cluster.connect();           
    	  
    	  
    	   	  
    	  session.execute("use searchEngine;");
   	       	  
    	  long millis = System.currentTimeMillis() ;
		  int t= (int) millis;
	      String query2 = "INSERT INTO searchHistory (id, word, time)"
				
		 				+ " VALUES(" + "1"  +",'" +   query+ "',"+ Integer.toString(t) +  ");" ;
    	  
    	 
	      session.execute(query2); 
    	  
    	 
//		    String query = "CREATE TABLE if not exists indexTable("
//		    
//			  	+ "word text, "
//		         + "title text,   sno text, score int , date text, snippet text , "
//		       
//		         + " primary key(word , score))  WITH CLUSTERING ORDER BY (score DESC);";
//
	    session.execute("use  mysearchengine;"); 
	    
	    
	    String cassquery = "Select * from indexTable where word = '" + query+ "'  Limit 20;";//Getting the ResultSet
	    
	    ResultSet result = session.execute(cassquery);
	      
//	    System.out.println(result.all());
   
	    List<Row> al= result.all();

	    List <List <String >> results = new ArrayList();
	    
	    
	    for(int i=0;i<al.size();i++)
	    {
	    	Row r= al.get(i);
	    	
	    	List<String> temp = new ArrayList();
	    	
	    	String title = r.getString("title");
	    	String snippet = r.getString("snippet");
	    	String date = r.getString("date");
	    	String sno = r.getString("sno");
	    	int  score= r.getInt("score")   ;
	    	
	    	temp.add(title);
	    	temp.add(snippet);
	    	temp.add(date);
	    	temp.add(sno);
	    	temp.add( Integer.toString(score) );
	    	
	    	
	    	results.add(temp);
//	    	System.out.println(r);
//	    	
//	    	System.out.println(r.getInt("id"));
	    	
	    	
	    }
	    
	    
	    session.close();
	    
	   cluster.close();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
   	String htmlresult ="<!DOCTYPE html> <html> <head> <title>My Search Engine</title> </head>  <style>   h1 { color: #111; font-family: 'Helvetica Neue', sans-serif; font-size: 30px; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: left; ;margin-top:30px; margin-left:2%;  }   input[type=text], select {     width: 60%;     padding: 12px 20px;     margin: 0px 0; margin-left:2%;      border: 1px solid #ccc;     border-radius: 4px;     box-sizing: border-box; }  input[type=submit] {     width: 10%;     background-color: #4CAF50;     color: white;     padding: 14px 20px;     margin: 8px 0; margin-left:2%;     border: none;     border-radius: 4px;     cursor: pointer; }  input[type=submit]:hover {     background-color: #45a049; }   </style>        <body bgcolor =\"#FFC300\">  <h1> My Search Engine</h1>  <div  style=\"margin-top: 10px;margin-left:0%\"> ";

   	htmlresult+= "<form action=result method=GET>    <input type=\"text\" name=\"query\" placeholder=\"Type Your Query Here\">     <input type=\"submit\" value=\"Submit\"> </form>    </div> ";
   	
    			
    			
    			
   	htmlresult = htmlresult+ 			"    <div  style=\" margin-top: 40px;margin-left:5%;text-align: center;width:80% ; font-size:20px;\" >        Search Results For : " +
    			"" +
    			"" +
    		query +	"       </div>                             " +
    				"   <div  style=\" margin-top: 40px;margin-left:5%;text-align: left;width:80% ; font-size:18px;\" >       " +
    				"" +
    				"" ;
    				
    				
//    				
//    			for(int i=0;i<15;i++)
//    			{
//    				
//    				htmlresult = htmlresult+  "         <a href=\" \" >Title for webpage 1</a><br>   ";
//    				
//    				htmlresult = htmlresult+  " <span style=\" font-size:15px\" >some text from page</span>        <br><br>   ";
//    				                                              
//    				
//    				
//    				
//    			}
    			
    			
    			
    			for(int i=0;i<results.size();i++)
    			{
    				List <String> temp= results.get(i);
    				
    				htmlresult = htmlresult+  "         <a href=\" " +  "pageContents/" +temp.get(3) + "/"+  query + "/"+  temp.get(4) +"\" >   " +temp.get(0)  + "</a><br>   ";
    				
    				htmlresult = htmlresult+  " <span style=\" font-size:15px\" > " + temp.get(1)+ "</span>        <br><br>   ";
    				                                              
    				
    				
    				
    			}
    			
    			
    			
    			
    			
    			
    			
    			
    				
    			htmlresult = htmlresult+  "  </div>    </body>        </html>";
   		    	
   			    	
    	
        return htmlresult;
        
        }
   
    
    
    
    

}