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
public class pageCOntents {

    
	
	@RequestMapping(value="/pageContents/{itemid}/{query}/{score}", method = RequestMethod.GET)
	public @ResponseBody
	String getitem(@PathVariable("itemid") String itemid,@PathVariable("query") String query,@PathVariable("score") String score) {   
	    
    	  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
    	  Session session = cluster.connect();           
    
//--------------------------delete from table old value-------------------------------     
    	  session.execute("use  mysearchengine;"); 
    	  String getquery = "Select * from indexTable where word = '" + query+"'  and score = "+ score+ " and sno= '" + itemid+ "';";//Getting the ResultSet
      	  ResultSet result2 = session.execute(getquery);    	  
    	  List<Row> al2= result2.all();   
    	  List<String> temp = new ArrayList();  
    	   
    		temp.add(query);
    		temp.add( Integer.toString(Integer.parseInt(score) + 1));
    		temp.add(itemid);   		
      
  	    for(int i=0;i<al2.size();i++)
  	    {
  	    	Row r= al2.get(i); 
  	    	String title = r.getString("title");
  	    	String snippet = r.getString("snippet");
  	    	String date = r.getString("date");
  	    	temp.add(title);
  	    	temp.add(snippet);
  	    	temp.add(date);
  	    }
      
  	    
  	    String delquery = "Delete  from indexTable where word = '" + query+"'  and score = "+ score+ " and sno= '" + itemid+ "';";//Getting the ResultSet	    
  	  	session.execute(delquery);
  	  	
  	    
  	    String insertquery = "INSERT INTO indexTable (word,title,sno,score,date,snippet)"
  				
  		 				+ " VALUES(" + "'" +   temp.get(0).replace("'", "")+ "','"+ temp.get(3).replace("'", "") +   "','" + temp.get(2)+   "'," + temp.get(1)  +   ",'" + temp.get(5) +     "','" + temp.get(4).replace("'", "")      + "');" ;
  	    session.execute(insertquery);
  	    
 	    
  	      
	    
	    String cassquery = "Select * from indexContents where sno = '" + itemid+ "'  ;";//Getting the ResultSet
	    
	    ResultSet result = session.execute(cassquery);
	      
//	    System.out.println(result.all());
   
	    List<Row> al= result.all();


	    
	    List<String> temp2 = new ArrayList();
	    for(int i=0;i<al.size();i++)
	    {
	    	Row r= al.get(i);
	    	
	    	
	    	
	    	String title = r.getString("title");
	    	String data = r.getString("contents");
	    	String date = r.getString("date");
	    	
	    	temp2.add(title);
	    	temp2.add(data);
	    	temp2.add(date);
	    	
	    	
	    }
	    
	    
	    session.close();
	    
	   cluster.close();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
   	String htmlresult ="<!DOCTYPE html> <html> <head> <title>My Search Engine</title> </head>  <style>   h1 { color: #111; font-family: 'Helvetica Neue', sans-serif; font-size: 30px; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: left; ;margin-top:30px; margin-left:2%;  }   input[type=text], select {     width: 60%;     padding: 12px 20px;     margin: 0px 0; margin-left:2%;      border: 1px solid #ccc;     border-radius: 4px;     box-sizing: border-box; }  input[type=submit] {     width: 10%;     background-color: #4CAF50;     color: white;     padding: 14px 20px;     margin: 8px 0; margin-left:2%;     border: none;     border-radius: 4px;     cursor: pointer; }  input[type=submit]:hover {     background-color: #45a049; }   </style>        <body bgcolor =\"#FFC300\">  <h1> My Search Engine</h1>  <div  style=\"margin-top: 10px;margin-left:0%\"> ";

   	htmlresult+= "<form action=result method=GET>    <input type=\"text\" name=\"query\" placeholder=\"Type Your Query Here\">     <input type=\"submit\" value=\"Submit\"> </form>    </div> ";
   	
    			
    			
    			
   	htmlresult = htmlresult+ 			"    <div  style=\" margin-top: 40px;margin-left:5%;text-align: center;width:80% ; font-size:20px;\" >         " +
    			"" +
    			"" +
    		temp2.get(0) +	"       </div>                             " +
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
    			
    			

    				
    				htmlresult = htmlresult+  temp2.get(1)  ;
    				

    			
    			
    			
    			
    			
    			
    			
    			
    				
    			htmlresult = htmlresult+  "  </div>    </body>        </html>";
   		    	
   			    	
    	
        return htmlresult;
        
        }
   
    
    
    
    

}