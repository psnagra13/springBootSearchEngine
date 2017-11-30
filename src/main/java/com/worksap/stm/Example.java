package com.worksap.stm;

import java.util.*;
import java.util.List;

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
public class Example {

    
    @RequestMapping("/")
    String home() {

    	
    	
    	 Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
 	    //Creating Session object
 	    Session session = cluster.connect(); 
 	    session.execute("use searchEngine"); 
 	    
 	    
 	    String query = "Select * from searchHistory LIMIT 10 ;";//Getting the ResultSet
 	    
 	    ResultSet result = session.execute(query);
 	      
// 	    System.out.println(result.all());
 	    
 	    
 	    List<Row> al= result.all();
 	    List <String > history= new ArrayList();
 	    
 	    
 	    for(int i=0;i<al.size();i++)
 	    {
 	    	Row r= al.get(i);
 	    	history.add(r.getString("word"));
// 	    	System.out.println(r);
// 	    	
// 	    	System.out.println(r.getInt("id"));
 	    	
 	    	
 	    }
 	    
 	    
 	    session.close();
 	    
 	   cluster.close();
    	
    	
    	
    	
    	
    	String htmlresult ="<!DOCTYPE html> <html> <head> <title>My Search Engine</title> </head>  <style>   h1 { color: #111; font-family: 'Helvetica Neue', sans-serif; font-size: 100px; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: center; }   input[type=text], select {     width: 70%;     padding: 12px 20px;     margin: 8px 0; margin-left:14%;     display: inline-block;     border: 1px solid #ccc;     border-radius: 4px;     box-sizing: border-box; }  input[type=submit] {     width: 20%;     background-color: #4CAF50;     color: white;     padding: 14px 20px;     margin: 8px 0; margin-left:38%;     border: none;     border-radius: 4px;     cursor: pointer; }  input[type=submit]:hover {     background-color: #45a049; }   </style>        <body bgcolor =\"#FFC300\">  <h1> My Search Engine</h1>  <div  style=\"margin-top: 10px;margin-left:0%\">  <form action=\"/action_page.php\">   <br> <input type=\"text\" name=\"query\" placeholder=\"Type Your Query Here\"><br>     <input type=\"submit\" value=\"Submit\"> </form>    </div>     <div  style=\"margin-top: 10px;margin-left:20%\"> <h4>Recent Searches:</h4> <ul> ";
    	
    	for (int i=0;i<history.size();i++)
    	{
    		htmlresult+="<li>";

    		htmlresult+=(String)history.get(i);
    		

    		
    		
    		
    	}
    	
    	
    	htmlresult+=" </ul>   </div>                </body> </html>";
    	
        return htmlresult;
        
        }
   
    
    
    
    
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }

}